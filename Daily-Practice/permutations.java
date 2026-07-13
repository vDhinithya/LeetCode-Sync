import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // Start the recursive backtracking with an empty temporary list
        backtrack(result, new ArrayList<>(), nums);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] nums) {
        // Base Case: If the temporary list is the same size as nums, we've found a valid permutation
        if (tempList.size() == nums.length) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        // Recursive Step: Try adding every number from the array
        for (int i = 0; i < nums.length; i++) {
            // Skip the number if it is already in our current permutation path
            if (tempList.contains(nums[i])) {
                continue; 
            }
            
            //  Choose the number
            tempList.add(nums[i]);
            
            //  Explore further with this number included
            backtrack(result, tempList, nums);
            
            // Un-choose the number (backtrack) to try the next possibility
            tempList.remove(tempList.size() - 1);
        }
    }
}