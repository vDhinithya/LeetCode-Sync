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
            JsonNode submissions = getRecentSubmissions(username, 10);

            for (JsonNode sub : submissions) {
                String subId = sub.get("id").asText();
                String titleSlug = sub.get("titleSlug").asText();

                JsonNode details = getSubmissionDetails(subId);
                if (details != null && "Accepted".equals(details.path("status_display").asText())) {
                    String code = details.get("code").asText();
                    String lang = details.get("runtime_lang").asText();

                    String ext = lang.contains("java") ? "java" : "txt";
                    Path filePath = Paths.get("Daily-Practice", titleSlug + "." + ext);

                    if (!Files.exists(filePath)) {
                        Files.createDirectories(filePath.getParent());
                        Files.writeString(filePath, code);
                        System.out.println("Synced: " + titleSlug);
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
        String url = "https://leetcode.com/submissions/detail/" + submissionId + "/";
        HttpRequest request = getBaseRequestBuilder(url).GET().build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        // LeetCode embeds submission data inside a window element in the page source
        for (String line : body.split("\n")) {
            if (line.contains("pageData = ")) {
                String jsonStr = line.split("pageData = ")[1].trim();
                if (jsonStr.endsWith(";")) {
                    jsonStr = jsonStr.substring(0, jsonStr.length() - 1);
                }
                return mapper.readTree(jsonStr);
            }
        }
        return null;
    }
}