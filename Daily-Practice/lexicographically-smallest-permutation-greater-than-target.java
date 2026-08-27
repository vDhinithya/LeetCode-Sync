class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int [26];
        for(int k =0; k<n;k++){
            cnt[s.charAt(k)-'a']++;
        }
        String ans = null;
        for (int i = 0; i <= n; i++) {
            if (i > 0) {
                int needed = target.charAt(i - 1) - 'a';
                if (cnt[needed] == 0) {
                    break;
                }
                cnt[needed]--;
            }
            if (i < n) {
                int targetChar = target.charAt(i) - 'a';
                for (int c = targetChar + 1; c < 26; c++) {
                    if (cnt[c] > 0) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(target, 0, i);
                        sb.append((char) ('a' + c));
                        cnt[c]--;

                        for (int chIdx = 0; chIdx < 26; chIdx++) {
                            for (int k = 0; k < cnt[chIdx]; k++) {
                                sb.append((char) ('a' + chIdx));
                            }
                        }

                        String cand = sb.toString();
                        if (ans == null || cand.compareTo(ans) < 0) {
                            ans = cand;
                        }

                        cnt[c]++; 
                    }
                }
            }
        }

        return ans != null ? ans : "";

    }
}