package TwoPointer;

import java.util.*;

/*
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

Example 1:

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
Example 2:

Input: nums = [3,2,4], target = 6
Output: [1,2]
Example 3:

Input: nums = [3,3], target = 6
Output: [0,1]
 */
public class TwoSum {
  public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      if (map.containsKey(target - nums[i]))
        return new int[] { map.get(target - nums[i]), i };
      if (!map.containsKey(nums[i])) {
        map.put(nums[i], i);
      }
    }
    return new int[] { -1, -1 };
  }
  /*
   * Given an integer array nums, return all the triplets [nums[i], nums[j],
   * nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] +
   * nums[k] == 0.
   * 
   * Notice that the solution set must not contain duplicate triplets.
   * 
   * Example 1:
   * 
   * Input: nums = [-1,0,1,2,-1,-4]
   * Output: [[-1,-1,2],[-1,0,1]]
   * Explanation:
   * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
   * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
   * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
   * The distinct triplets are [-1,0,1] and [-1,-1,2].
   * Notice that the order of the output and the order of the triplets does not
   * matter.
   * Example 2:
   * 
   * Input: nums = [0,1,1]
   * Output: []
   * Explanation: The only possible triplet does not sum up to 0.
   * Example 3:
   * 
   * Input: nums = [0,0,0]
   * Output: [[0,0,0]]
   * Explanation: The only possible triplet sums up to 0.
   */
}
