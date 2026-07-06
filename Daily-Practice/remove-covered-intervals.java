class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        int n = intervals.length;
        int coveredCount = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    // Check if interval j completely covers interval i
                    if (intervals[j][0] <= intervals[i][0] && intervals[j][1] >= intervals[i][1]) {
                        coveredCount++;
                        break; // Interval i is covered, no need to check further
                    }
                }
            }
        }
        
        return n - coveredCount;
    }
}