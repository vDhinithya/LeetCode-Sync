class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        List<Deque<Integer>> grps = new ArrayList<>();
        Map<Integer, Integer> numToGrp = new HashMap<>();

        for(int i =0; i<n;i++){
            if(i==0 || sorted[i]-sorted[i-1]>limit){
                grps.add(new ArrayDeque<>());
            }
            int curIndx = grps.size()-1;
            grps.get(curIndx).addLast(sorted[i]);
            numToGrp.put(sorted[i], curIndx);
        }

        int[] res = new int[n];
        for(int i =0;i<n;i++){
            int grpIdx = numToGrp.get(nums[i]);
            res[i]= grps.get(grpIdx).pollFirst();
        }
        return res;
    }
}