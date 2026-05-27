import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class LeetCodeSync {

    private static final String SESSION = System.getenv("LEETCODE_SESSION");
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) {
        if (SESSION == null || SESSION.trim().isEmpty()) {
            System.err.println("Error: LEETCODE_SESSION secret is missing.");
            System.exit(1);
        }

        try {
            String username = getUsername();
            System.out.println("Successfully authenticated as: " + username);
            JsonNode submissions = getRecentSubmissions(username, 10);
            System.out.println("Found " + submissions.size() + " recent submissions.");

            for (JsonNode sub : submissions) {
                String subId = sub.get("id").asText();
                String titleSlug = sub.get("titleSlug").asText();
                System.out.println("\nChecking: " + titleSlug);

                JsonNode details = getSubmissionDetails(subId);

                if (details == null || details.isMissingNode() || details.isNull()) {
                    System.out.println(" -> FAILED: Could not fetch details from GraphQL API.");
                    continue;
                }

                String status = details.path("statusDisplay").asText();
                System.out.println(" -> Status found: " + status);

                if ("Accepted".equals(status)) {
                    String code = details.path("code").asText();
                    String lang = details.path("lang").path("name").asText();

                    String ext = lang.toLowerCase().contains("java") ? "java" : "txt";
                    Path filePath = Paths.get("Daily-Practice", titleSlug + "." + ext);

                    if (!Files.exists(filePath)) {
                        Files.createDirectories(filePath.getParent());
                        Files.writeString(filePath, code);
                        System.out.println(" -> SUCCESS: Synced " + titleSlug);
                    } else {
                        System.out.println(" -> SKIPPED: File already exists.");
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred during sync: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static HttpRequest.Builder getBaseRequestBuilder(String url) {
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Cookie", "LEETCODE_SESSION=" + SESSION)
                .header("Content-Type", "application/json")
                .header("User-Agent", "Mozilla/5.0");
    }

    private static String getUsername() throws Exception {
        Map<String, String> payload = new HashMap<>();
        payload.put("query", "{ user { username } }");

        HttpRequest request = getBaseRequestBuilder("https://leetcode.com/graphql")
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(payload)))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode root = mapper.readTree(response.body());
        return root.path("data").path("user").path("username").asText();
    }

    private static JsonNode getRecentSubmissions(String username, int limit) throws Exception {
        String query = "query recentAcSubmissions($username: String!, $limit: Int!) { " +
                "recentAcSubmissionList(username: $username, limit: $limit) { " +
                "id title titleSlug timestamp } }";

        ObjectNode payload = mapper.createObjectNode();
        payload.put("query", query);

        ObjectNode variables = mapper.createObjectNode();
        variables.put("username", username);
        variables.put("limit", limit);
        payload.set("variables", variables);

        HttpRequest request = getBaseRequestBuilder("https://leetcode.com/graphql")
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(payload)))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode root = mapper.readTree(response.body());
        return root.path("data").path("recentAcSubmissionList");
    }

    private static JsonNode getSubmissionDetails(String submissionId) throws Exception {
        // Use LeetCode's official GraphQL query for submission details
        String query = "query submissionDetails($submissionId: Int!) { " +
                "submissionDetails(submissionId: $submissionId) { " +
                "code statusDisplay lang { name } } }";

        ObjectNode payload = mapper.createObjectNode();
        payload.put("query", query);

        ObjectNode variables = mapper.createObjectNode();
        variables.put("submissionId", Integer.parseInt(submissionId));
        payload.set("variables", variables);

        HttpRequest request = getBaseRequestBuilder("https://leetcode.com/graphql")
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(payload)))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode root = mapper.readTree(response.body());

        return root.path("data").path("submissionDetails");
    }
}