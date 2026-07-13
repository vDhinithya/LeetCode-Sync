class Solution {
    public boolean isMiddleElementUnique(int[] nums) {
        int midIdx = nums.length/2;
        int midEle = nums[midIdx];


        for(int i =0;i<nums.length; i++){
            if(i!= midIdx && nums[i] == midEle){
                return false;
            }
        }
        return true;
    }
}