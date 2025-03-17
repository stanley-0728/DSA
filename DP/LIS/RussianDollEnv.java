package DP.LIS;

import java.util.Arrays;

/*
 * You are given a 2D array of integers envelopes where envelopes[i] = [wi, hi] represents the width and the height of an envelope.
One envelope can fit into another if and only if both the width and height of one envelope are greater than the other envelope's width and height.

Return the maximum number of envelopes you can Russian doll (i.e., put one inside the other).

Note: You cannot rotate an envelope.

Example 1:

Input: envelopes = [[5,4],[6,4],[6,7],[2,3]]
Output: 3
Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
Example 2:

Input: envelopes = [[1,1],[1,1],[1,1]]
Output: 1
 
 */
public class RussianDollEnv {
  public int maxEnvelopes(int[][] nums) {
    Arrays.sort(nums, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
    int[] height = new int[nums.length];
    for (int i = 0; i < nums.length; i++)
      height[i] = nums[i][1];
    return LIS(height);
  }

  int LIS(int[] height) {
    int[] dp = new int[height.length];
    int len = 0;
    for (int i = 0; i < height.length; i++) {
      int idx = Arrays.binarySearch(dp, 0, len, height[i]);
      System.out.println(idx + " " + height[i]);
      if (idx < 0)
        idx = -(idx + 1);
      dp[idx] = height[i];
      if (idx == len)
        len++;
    }
    return len;
  }
}
