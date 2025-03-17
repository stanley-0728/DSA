package DP.LinearDp;

import java.util.Arrays;

public class WiggleSubsquence {
  public int wiggleMaxLength(int[] nums) {
    int n = nums.length;

    int[][][] dp = new int[n][n + 1][3]; // prev=-1 mapped to n, lastSign shifted to [0,1,2]
    for (int[][] matrix : dp) {
      for (int[] row : matrix) {
        Arrays.fill(row, -1);
      }
    }
    // return f(nums.length - 1, -1, 0, nums);
    return helper(nums, n - 1, n, 1, dp); // Start with prev=n (mapped from -1), lastSign=1 (neutral)
  }

  private int helper(int[] nums, int i, int prev, int lastSign, int[][][] dp) {
    if (i < 0)
      return 0;

    if (dp[i][prev][lastSign] != -1)
      return dp[i][prev][lastSign];

    // Exclude current index
    int exclude = helper(nums, i - 1, prev, lastSign, dp);

    // Include current index if it forms a valid wiggle
    int include = 0;
    if (prev == nums.length || lastSign == 1 || // Neutral case
        (lastSign == 2 && nums[i] < nums[prev]) || // Last sign was positive, need a decrease
        (lastSign == 0 && nums[i] > nums[prev])) { // Last sign was negative, need an increase

      if (prev == nums.length || nums[i] != nums[prev]) { // Ensure no duplicate selection
        int newSign = (prev == nums.length) ? 1 : (nums[i] > nums[prev] ? 2 : 0); // Shifted sign
        include = 1 + helper(nums, i - 1, i, newSign, dp);
      }
    }

    return dp[i][prev][lastSign] = Math.max(include, exclude);
  }

  int f(int index, int prev, int lastSign, int[] nums) {
    if (index < 0)
      return 0;
    int exclude = f(index - 1, prev, lastSign, nums);
    int include = 0;
    if (prev == -1 || lastSign == 0 ||
        (lastSign > 0 && nums[index] < nums[prev]) ||
        (lastSign < 0 && nums[index] > nums[prev])) {

      // Calculate new sign based on actual difference
      int newSign = (prev == -1) ? 0 : Integer.compare(nums[index], nums[prev]);
      include = 1 + f(index - 1, index, newSign, nums);
    }
    return Math.max(exclude, include);
  }
}