class Solution {
    public int searchInsert(int[] nums, int target) {
        int ans = BinarySearch(nums, target);
        return ans;
    }
    public int BinarySearch(int[] arr, int target)
    {
        int start = 0;
        int end = arr.length-1;
        while(start <= end){
            int mid = start + (end - start)/2;
            if (arr[mid]==target)
                return mid;
            else if (arr[mid] < target) {
                start = mid +1;
            }else {
                end = mid-1;
            }
        }
        return start;
    }
}