class Solution {
    public int maxConsistentColumns(int[][] grid, int limit) {
        int[][] temp = grid;
        int m = temp.length;
        int n = temp[0].length;
        
        int[] dp = new int[n];
        int maxCols = 0;
        
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                boolean isValid = true;
                
                for (int row = 0; row < m; row++) {
                    if (Math.abs(temp[row][i] - temp[row][j]) > limit) {
                        isValid = false;
                        break; 
                    }
                }
                
                if (isValid) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            
            maxCols = Math.max(maxCols, dp[i]);
        }
        
        return maxCols;
    }
}