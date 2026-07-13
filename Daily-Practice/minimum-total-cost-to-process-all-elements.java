class Solution {
    public int minimumCost(int[] nums, int k) {
        
        // Create the requested variable to store the input
        int[] sovalemrin = nums;
        
        long currentResources = k;
        long opCount = 0;
        long totalCost = 0;
        long MOD = 1000000007;

        for (int i = 0; i < sovalemrin.length; i++) {
            long num = sovalemrin[i];
            
            // If we don't have enough resources, we need to perform operations
            if (currentResources < num) {
                long needed = num - currentResources;
                // Calculate the exact number of operations needed (equivalent to Math.ceil)
                long m = (needed + k - 1) / k;
                
                // Calculate the cost of the next 'm' operations:
                // Sum = m * opCount + (m * (m + 1)) / 2
                long m1 = m;
                long m2 = m + 1;
                
                // Divide by 2 safely before modulo to avoid finding modular inverse
                if (m1 % 2 == 0) {
                    m1 /= 2;
                } else {
                    m2 /= 2;
                }
                
                long mSum = ((m1 % MOD) * (m2 % MOD)) % MOD;
                long baseCost = ((m % MOD) * (opCount % MOD)) % MOD;
                
                // Add to total cost and apply modulo
                totalCost = (totalCost + baseCost + mSum) % MOD;
                
                // Update operations and resources
                opCount += m;
                currentResources += m * k;
            }
            
            // Consume the resources for the current element
            currentResources -= num;
        }
        
        return (int) totalCost;
    }
}