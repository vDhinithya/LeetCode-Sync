class Solution {
    public String minWindow(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0) {
            return "";
        }

        // Frequency map for characters in t
        int[] freq = new int[128];
        for (char c : t.toCharArray()) {
            freq[c]++;
        }

        int left = 0, right = 0;
        int required = t.length();
        int minLen = Integer.MAX_VALUE;
        int start = 0;

        while (right < s.length()) {
            char r = s.charAt(right);
            // If character in s is part of t, decrease required
            if (freq[r] > 0) {
                required--;
            }
            freq[r]--;
            right++;

            // When we have all characters, try to shrink the window
            while (required == 0) {
                // Update the minimum window
                if (right - left < minLen) {
                    minLen = right - left;
                    start = left;
                }
                
                // The character at 'left' is being removed from the window
                char l = s.charAt(left);
                freq[l]++;
                
                // If it's a character we actually needed, increment 'required'
                if (freq[l] > 0) {
                    required++;
                }
                
                // Shrink the window
                left++;
            }        
        }
        
        // If minLen was never updated, no window was found
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}