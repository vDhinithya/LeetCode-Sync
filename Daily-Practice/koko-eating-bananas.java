class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int start = 1;
        int end = 0;
        
        for (int pile : piles) {
            end = Math.max(end, pile);
        }
        
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (canEat(piles, h, mid)) {
                end = mid; 
            } else {
                start = mid + 1; 
            }
        }
        
        return start;
    }

    private boolean canEat(int[] piles, int h, int k) {
        int hours = 0;
        for (int pile : piles) {
            hours += (pile + k - 1) / k; 
            if (hours > h) {
                return false; 
            }
        }
        return hours <= h;
    }
}