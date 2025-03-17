package DP.LinearDp;

import java.util.Arrays;

public class DivisibleBythree {
  public int maxSumDivThree(int[] nums) {
    int sum = Arrays.stream(nums).sum();
    int[][] dp = new int[nums.length + 1][sum + 1];
    for (int i = 0; i < nums.length + 1; i++)
      Arrays.fill(dp[i], -1);
    return recursion(nums.length, nums, 0, dp);
  }

  int recursion(int i, int[] nums, int sum, int[][] dp) {
    if (i == 0) {
      if (sum % 3 == 0)
        return sum;
      else
        return Integer.MIN_VALUE;
    }
    if (dp[i][sum] != -1)
      return dp[i][sum];
    int notPick = recursion(i - 1, nums, sum, dp);
    int pick = recursion(i - 1, nums, sum + nums[i - 1], dp);
    return Math.max(notPick, pick);
  }
}
