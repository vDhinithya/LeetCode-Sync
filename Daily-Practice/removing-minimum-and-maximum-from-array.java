class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        int minIdx = 0, maxIdx = 0;

        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIdx]) {
                minIdx = i;
            }
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }

        int i = Math.min(minIdx, maxIdx);
        int j = Math.max(minIdx, maxIdx);

        int removeFromFront = j + 1;
        int removeFromBack = n - i;
        int removeFromBothEnds = (i + 1) + (n - j);

        return Math.min(removeFromFront, Math.min(removeFromBack, removeFromBothEnds));
    }
}