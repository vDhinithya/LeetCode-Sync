class Solution {
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> count = new HashMap<>();
        int limit = nums.length/2;

        for(int num : nums){
            int curCount = count.getOrDefault(num,0)+1;
            if(curCount>limit){
                return num;
            }
            count.put(num,curCount);
        }

        return -1;
    }
}