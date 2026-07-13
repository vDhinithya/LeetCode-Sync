class Solution {
    public int maxValidPairSum(int[] nums, int k) {
        int[] temp = nums;
        int vdLeft = 0;
        int vdPair=0;

        for(int j=k;j<temp.length;j++){
            vdLeft = Math.max(vdLeft,temp[j-k]);
            vdPair = Math.max(vdPair,vdLeft+temp[j]);
        }
        return vdPair;
    }
}