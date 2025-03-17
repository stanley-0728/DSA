package DP.FibonacciPattern;

import java.util.Arrays;

/*
 * Given an integer array height[] where height[i] represents the height of the i-th stair, a frog starts from the first stair and wants to reach the top. From any stair i, the frog has two options: it can either jump to the (i+1)th stair or the (i+2)th stair. The cost of a jump is the absolute difference in height between the two stairs. Determine the minimum total cost required for the frog to reach the top.

Example:

Input: heights[] = [20, 30, 40, 20] 
Output: 20
Explanation:  Minimum cost is incurred when the frog jumps from stair 0 to 1 then 1 to 3:
jump from stair 0 to 1: cost = |30 - 20| = 10
jump from stair 1 to 3: cost = |20-30|  = 10
Total Cost = 10 + 10 = 20
 */
public class FrogJump {
  public static void main(String[] args) {

  }

  int minCost(int[] height) {
    // return recursion(height.length - 1, height);
    int[] dp = new int[height.length];
    Arrays.fill(dp, -1);
    return memo(height.length - 1, height, dp);
  }

  // T.C exponential and S.C o(n)
  int recursion(int index, int[] height) {
    if (index == 0) {
      return 0;
    }
    int left = Math.abs(height[index] - height[index - 1]) + recursion(index - 1, height);
    int right = Integer.MAX_VALUE;
    if (index - 2 >= 0) {
      right = Math.abs(height[index] - height[index - 2]) + recursion(index - 2, height);
    }
    return Math.min(left, right);
  }
  // T.C o(n) and S.C o(n)+o(n)

  int memo(int index, int[] height, int[] dp) {
    if (index == 0) {
      return 0;
    }
    if (dp[index] != -1)
      return dp[index];
    int left = Math.abs(height[index] - height[index - 1]) + memo(index - 1, height, dp);
    int right = Integer.MAX_VALUE;
    if (index - 2 >= 0) {
      right = Math.abs(height[index] - height[index - 2]) + memo(index - 2, height, dp);
    }
    return dp[index] = Math.min(left, right);
  }

  // T.C o(n) and S.C o(n)
  int tabulation(int[] height) {
    int[] dp = new int[height.length];
    dp[0] = 0;
    for (int index = 1; index < height.length; index++) {
      int left = Math.abs(height[index] - height[index - 1]) + dp[index - 1];
      int right = Integer.MAX_VALUE;
      if (index - 2 >= 0) {
        right = Math.abs(height[index] - height[index - 2]) + dp[index - 2];

      }
      dp[index] = Math.min(left, right);
    }
    return dp[height.length - 1];
  }

  // T.C o(n) and S.C o(1)
  int spaceOptimized(int[] height) {
    int[] dp = new int[height.length];
    dp[0] = 0;
    int prev1 = 0;
    int prev2 = 0;
    for (int index = 1; index < height.length; index++) {
      int curr;
      int left = Math.abs(height[index] - height[index - 1]) + prev1;
      int right = Integer.MAX_VALUE;
      if (index - 2 >= 0) {
        right = Math.abs(height[index] - height[index - 2]) + prev2;

      }
      curr = Math.min(left, right);
      prev2 = prev1;
      prev1 = curr;
    }
    return prev1;
  }

}
