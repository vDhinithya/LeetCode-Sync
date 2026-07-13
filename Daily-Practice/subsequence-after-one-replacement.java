import java.util.Arrays;

class Solution {
    public boolean canMakeSubsequence(String s, String t) {
        int n = s.length();
        int m = t.length();
        
        if (n > m) {
            return false;
        }

        int[] left = new int[n + 1];
        Arrays.fill(left, Integer.MAX_VALUE);
        left[0] = -1;
        
        int curr = 0;
        for (int i = 0; i < n; i++) {
            while (curr < m && t.charAt(curr) != s.charAt(i)) {
                curr++;
            }
            if (curr < m) {
                left[i + 1] = curr;
                curr++;
            } else {
                break; 
            }
        }
        
        int[] right = new int[n + 1];
        Arrays.fill(right, -2);
        right[n] = m;
        
        curr = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            while (curr >= 0 && t.charAt(curr) != s.charAt(i)) {
                curr--;
            }
            if (curr >= 0) {
                right[i] = curr;
                curr--;
            } else {
                break;
            }
        }
        
        String temp = s + "|" + t;

        if (left[n] != Integer.MAX_VALUE) {
            return true;
        }

        for (int i = 0; i < n; i++) {
            if (left[i] != Integer.MAX_VALUE && right[i + 1] != -2) {
                if (right[i + 1] - left[i] >= 2) {
                    return true;
                }
            }
        }

        return false;
    }
}