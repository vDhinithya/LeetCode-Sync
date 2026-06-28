import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> filterOccupiedIntervals(int[][] occupiedIntervals, int freeStart, int freeEnd) {
        List<List<Integer>> result = new ArrayList<>();
        
        if (occupiedIntervals == null || occupiedIntervals.length == 0) {
            return result;
        }

        Arrays.sort(occupiedIntervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();
        int[] currentInterval = occupiedIntervals[0];

        for (int i = 1; i < occupiedIntervals.length; i++) {
            if (currentInterval[1] + 1 >= occupiedIntervals[i][0]) {
                currentInterval[1] = Math.max(currentInterval[1], occupiedIntervals[i][1]);
            } else {
                merged.add(currentInterval);
                currentInterval = occupiedIntervals[i];
            }
        }
        merged.add(currentInterval);

        for (int[] interval : merged) {
            int start = interval[0];
            int end = interval[1];

            if (end < freeStart || start > freeEnd) {
                result.add(Arrays.asList(start, end));
            } 
            else {
                if (start < freeStart) {
                    result.add(Arrays.asList(start, freeStart - 1));
                }
                if (end > freeEnd) {
                    result.add(Arrays.asList(freeEnd + 1, end));
                }
            }
        }

        return result;
    }
}