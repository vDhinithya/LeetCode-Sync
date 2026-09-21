class Solution {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int peak = peakIndex(mountainArr);

        int firstTry = orderAgnosticBS(mountainArr, target, 0, peak);
        if (firstTry != -1) return firstTry;

        return orderAgnosticBS(mountainArr, target, peak + 1, mountainArr.length() - 1);
    }

    private int peakIndex(MountainArray mountainArr) {
        int start = 0;
        int end = mountainArr.length() - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;
            if (mountainArr.get(mid) > mountainArr.get(mid + 1)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    private int orderAgnosticBS(MountainArray mountainArr, int target, int start, int end) {
        boolean isAsc = mountainArr.get(start) < mountainArr.get(end);

        while (start <= end) {
            int mid = start + (end - start) / 2;
            int val = mountainArr.get(mid);

            if (val == target) return mid;

            if (isAsc) {
                if (target < val) end = mid - 1;
                else start = mid + 1;
            } else {
                if (target > val) end = mid - 1;
                else start = mid + 1;
            }
        }
        return -1;
    }
}
