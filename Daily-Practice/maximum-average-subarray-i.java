class Solution {
    public double findMaxAverage(int[] nums, int k) {
        // Step 1: Calculate the sum of the first window of size k
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }

        int maxSum = windowSum;

        // Step 2: Slide the window across the array
        for (int i = k; i < nums.length; i++) {
            windowSum += nums[i] - nums[i - k]; // Add new element, remove old element
            maxSum = Math.max(maxSum, windowSum);
        }

        // Step 3: Return max sum divided by k as a double
        return (double) maxSum / k;
    }
}