class Solution {
        private static final int mod = (int)1e9 + 7;

    public int numberOfSets(int n, int k) {
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) dp[i] = 1;

        int sum = 0;
        for (int i = 1; i <= k; i++){
            sum = dp[0];
            dp[0] = 0;
            for (int j = 1; j < n; j++){
                int total = sum;
                sum = (sum + dp[j]) % mod;
                dp[j] = (dp[j-1] + total) % mod;
            }
        }

        return dp[n-1];
    }
}