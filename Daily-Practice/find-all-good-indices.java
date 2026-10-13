import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> goodIndices(int[] nums, int k) {
        int n = nums.length;
        List<Integer> result = new ArrayList<>();
        
        int[] decr = new int[n];
        int[] incr = new int[n];
        
        decr[0] = 1;
        incr[n - 1] = 1;
        
        for (int i = 1; i < n; i++) {
            if (nums[i] <= nums[i - 1]) {
                decr[i] = decr[i - 1] + 1;
            } else {
                decr[i] = 1;
            }
        }
        
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] <= nums[i + 1]) {
                incr[i] = incr[i + 1] + 1;
            } else {
                incr[i] = 1;
            }
        }
        
        for (int i = k; i < n - k; i++) {
            if (decr[i - 1] >= k && incr[i + 1] >= k) {
                result.add(i);
            }
        }
        
        return result;
    }
}