class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int left = 0, count = 0;
        String ans = "";

        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') {
                count++;
            }

            while (count == k) {
                // Shrink window from the left to remove leading zeros
                while (s.charAt(left) == '0') {
                    left++;
                }

                String sub = s.substring(left, right + 1);
                if (ans.isEmpty() || sub.length() < ans.length() || 
                   (sub.length() == ans.length() && sub.compareTo(ans) < 0)) {
                    ans = sub;
                }

                // Move left forward to search for the next window
                left++;
                count--;
            }
        }

        return ans;
    }
}