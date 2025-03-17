package DP.MCM;

import java.util.Arrays;

/*
 * You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with a number on it represented by an array nums. You are asked to burst all the balloons.

If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins. If i - 1 or i + 1 goes out of bounds of the array, then treat it as if there is a balloon with a 1 painted on it.
Return the maximum coins you can collect by bursting the balloons wisely.
Example 1:

Input: nums = [3,1,5,8]
Output: 167
Explanation:
nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
Example 2:

Input: nums = [1,5]
Output: 10
 */
public class BurstBallons {
  public int maxCoins(int[] nums) {
    int size = nums.length;
    int[] newCuts = new int[size + 2];

    newCuts[0] = 1;
    newCuts[size + 1] = 1;

    for (int i = 0; i < size; i++) {
      newCuts[i + 1] = nums[i];
    }
    // int[][] dp = new int[size + 2][size + 2];
    // for (int i = 0; i < size + 2; i++)
    // Arrays.fill(dp[i], -1);
    // return recursion(1, size, newCuts, dp);
    return tabulation( size, newCuts);

  }

  int recursion(int i, int j, int[] nums) {
    if (i > j)
      return 0;
    int mini = Integer.MIN_VALUE;
    for (int k = i; k <= j; k++) {
      int cost = nums[i - 1] * nums[k] * nums[j + 1] + recursion(i, k - 1, nums) + recursion(k + 1, j, nums);
      mini = Math.max(mini, cost);
    }
    return mini;
  }

  int recursion(int i, int j, int[] nums, int[][] dp) {
    if (i > j)
      return 0;
    if (dp[i][j] != -1)
      return dp[i][j];
    int mini = Integer.MIN_VALUE;
    for (int k = i; k <= j; k++) {
      int cost = nums[i - 1] * nums[k] * nums[j + 1] + recursion(i, k - 1, nums, dp) + recursion(k + 1, j, nums, dp);
      mini = Math.max(mini, cost);
    }
    return dp[i][j] = mini;
  }

  int tabulation(int c, int[] nums) {
    int[][] dp = new int[c + 2][c + 2];
    for (int i = c; i >= 1; i--) {
      for (int j = i; j <= c; j++) {
        int mini = Integer.MIN_VALUE;
        for (int k = i; k <= j; k++) {
          int cost = nums[i - 1] * nums[k] * nums[j + 1] + dp[i][k - 1] + dp[k + 1][j];
          mini = Math.max(mini, cost);
        }
        dp[i][j] = mini;
      }
    }
    return dp[1][c];
  }
}
