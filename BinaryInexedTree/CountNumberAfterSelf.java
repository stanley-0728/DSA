package BinaryInexedTree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Given an integer array nums, return an integer array counts where counts[i] is the number of smaller elements to the right of nums[i].

Example 1:
Input: nums = [5,2,6,1]
Output: [2,1,1,0]
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
 */
public class CountNumberAfterSelf {
  public List<Integer> countSmaller(int[] nums) {
    int n = nums.length;
    int[] sorted = nums.clone();
    Arrays.sort(sorted);
    Map<Integer, Integer> rank = new HashMap<>();
    int r = 1;
    for (int num : sorted) {
      if (!rank.containsKey(num)) {
        rank.put(num, r++);
      }
    }
    System.out.println(r);
    Integer[] result = new Integer[n];
    BIT bit = new BIT(r - 1);
    for (int i = n - 1; i >= 0; i--) {
      int pos = rank.get(nums[i]);
      int count = bit.query(pos - 1);
      result[i] = count;
      bit.update(pos, 1);
    }
    return Arrays.asList(result);
  }
}
