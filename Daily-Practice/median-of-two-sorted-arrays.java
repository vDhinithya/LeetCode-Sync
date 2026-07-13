class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int totalLength = m + n;
        
        int p1 = 0; // Pointer for nums1
        int p2 = 0; // Pointer for nums2
        
        // We only need to store the current element and the previous element
        // to handle both even and odd total lengths.
        int current = 0;
        int previous = 0;
        
        // We traverse exactly (totalLength / 2) + 1 times
        for (int i = 0; i <= totalLength / 2; i++) {
            previous = current;
            
            // If nums1 is exhausted, take from nums2
            if (p1 == m) {
                current = nums2[p2];
                p2++;
            } 
            // If nums2 is exhausted, take from nums1
            else if (p2 == n) {
                current = nums1[p1];
                p1++;
            } 
            // Otherwise, take the smaller of the two
            else if (nums1[p1] < nums2[p2]) {
                current = nums1[p1];
                p1++;
            } 
            else {
                current = nums2[p2];
                p2++;
            }
        }
        
        // If total length is even, average the last two elements seen
        if (totalLength % 2 == 0) {
            return (previous + current) / 2.0;
        } 
        // If odd, just return the last element seen
        else {
            return current;
        }
    }
}