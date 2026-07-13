import java.util.Set;
import java.util.TreeSet;

class Solution {
    public int divisibleGame(int[] nums) {
        Set<Integer> primeFactors = new TreeSet<>();
        
        int[] arr = nums;
        
        for (int num : arr) {
            int temp = num;
            for (int i = 2; i * i <= temp; i++) {
                if (temp % i == 0) {
                    primeFactors.add(i);
                    while (temp % i == 0) {
                        temp /= i;
                    }
                }
            }
            if (temp > 1) {
                primeFactors.add(temp);
            }
        }
        
        if (primeFactors.isEmpty()) {
            primeFactors.add(2);
        }
        
        long maxScoreDiff = Long.MIN_VALUE;
        int bestK = -1;
        
        for (int k : primeFactors) {
            long currentDiff = getKadaneMaxSum(arr, k);
            
            if (currentDiff > maxScoreDiff) {
                maxScoreDiff = currentDiff;
                bestK = k;
            } else if (currentDiff == maxScoreDiff) {
                if (bestK == -1 || k < bestK) {
                    bestK = k;
                }
            }
        }
        
        long mod = 1000000007;
        
        long result = (maxScoreDiff % mod) * (bestK % mod);
        result %= mod;
        
        if (result < 0) {
            result += mod;
        }
        
        return (int) result;
    }
    
    private long getKadaneMaxSum(int[] arr, int k) {
        long maxEndingHere = (arr[0] % k == 0) ? arr[0] : -arr[0];
        long maxSoFar = maxEndingHere;
        
        for (int i = 1; i < arr.length; i++) {
            long val = (arr[i] % k == 0) ? arr[i] : -arr[i];
            
            maxEndingHere = Math.max(val, maxEndingHere + val);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        
        return maxSoFar;
    }
}