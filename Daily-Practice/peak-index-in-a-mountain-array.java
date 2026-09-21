class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int start = 0;
        int end = arr.length-1;

        while(start < end){
            int mid = start +(end - start)/2;
            if(arr[mid]> arr[mid+1]){
                /* it means now the control is in desc part of array but there may be the answer so look for left
                */
                end = mid;
            }else{
                // int the ascd part of array
                start = mid + 1;
            }
        }
        // in the end start == end that will be pointing to the target element
        return end;
    }
}