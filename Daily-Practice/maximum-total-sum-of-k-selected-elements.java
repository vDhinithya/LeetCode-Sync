class Solution {
    public long maxSum(int[] nums, int k, int mul) {
        Arrays.sort(nums);

        long maxSum=0;
        int n = nums.length;

        for(int i =0;i<k;i++){
            long curEle = nums[n-1-i];
            long curMul= Math.max(1,mul-i);

            maxSum+= curEle*curMul;
        }
        return maxSum;
    }
}