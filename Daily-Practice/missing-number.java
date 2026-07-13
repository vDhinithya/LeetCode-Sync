class Solution {
    public int missingNumber(int[] nums) {
        int sum = 0;
        // xor with the range
        for(int n: nums){
            sum = sum^n;
        }
        // xor with the nums
        for(int i = 0; i<=nums.length;i++){
            sum = sum^i;
        } 
        // return the reminder
        return sum;
    }
}