class Solution {
    public boolean isPerfectSquare(int num) {
        if (num < 2) return true; // 0 and 1 are perfect squares

        long start = 1, end = num;  // use long to avoid overflow
        while (start <= end) {
            long mid = start + (end - start) / 2;
            long square = mid * mid;

            if (square == num) {
                return true;
            } else if (square < num) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return false;
    }
}
