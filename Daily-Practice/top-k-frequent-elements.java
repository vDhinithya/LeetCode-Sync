import java.util.*;
class Solution {
  public int[] topKFrequent(int[] nums, int k) {

        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
             for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
         List<Integer>[] bucket = new ArrayList[nums.length + 1];
            for (int key : frequencyMap.keySet()) {

            int frequency = frequencyMap.get(key);

            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
                bucket[frequency].add(key);
        }
             int[] result = new int[k];
               int index = 0;
             for (int i = bucket.length - 1; i >= 0 && index < k; i--) {

            if (bucket[i] != null) {

                for (int num : bucket[i]) {
                   result[index++] = num;
                    if (index == k) {
                        break;
                    }
                }}}  return result;
    }
}