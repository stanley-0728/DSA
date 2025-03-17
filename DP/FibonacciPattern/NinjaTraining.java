package DP.FibonacciPattern;

import java.util.Arrays;

/*
 * Ninja is planing this ‘N’ days-long training schedule. Each day, he can perform any one of these three activities. (Running, Fighting Practice or Learning New Moves). Each activity has some merit points on each day. As Ninja has to improve all his skills, he can’t do the same activity in two consecutive days. Can you help Ninja find out the maximum merit points Ninja can earn?

You are given a 2D array of size N*3 ‘POINTS’ with the points corresponding to each day and activity. Your task is to calculate the maximum number of merit points that Ninja can earn.

For Example
If the given ‘POINTS’ array is [[1,2,5], [3 ,1 ,1] ,[3,3,3] ],the answer will be 11 as 5 + 3 + 3.
 */
// Intro to 2d Dp
// 0 means 1st task 
//1 means 2nd task and 2 means 3rd task and 3 means no task has not been done yet. 
public class NinjaTraining {

  public static int ninjaTraining(int n, int points[][]) {
    int[][] dp = new int[n][4];
    for (int i = 0; i < n; i++)
      Arrays.fill(dp[i], -1);
    return memo(n - 1, 3, points, dp);
    // return recursion(n - 1, 3, points);
  }

  // this exponenetial T.C and S.C o(n)
  static int recursion(int index, int lastTask, int[][] points) {
    if (index == 0) {
      int maxi = Integer.MIN_VALUE;
      for (int i = 0; i < 3; i++) {
        if (i != lastTask)
          maxi = Math.max(maxi, points[index][i]);
      }
      return maxi;
    }
    int maxi = Integer.MIN_VALUE;
    for (int i = 0; i < 3; i++) {
      if (i != lastTask)
        maxi = Math.max(maxi, points[index][i] + recursion(index - 1, i, points));
    }
    return maxi;
  }

  // T.C o(n*4)*3 ans S.C o(n)
  static int memo(int index, int lastTask, int[][] points, int[][] dp) {
    if (index == 0) {
      int maxi = Integer.MIN_VALUE;
      for (int i = 0; i < 3; i++) {
        if (i != lastTask)
          maxi = Math.max(maxi, points[index][i]);
      }
      return maxi;
    }
    if (dp[index][lastTask] != -1)
      return dp[index][lastTask];
    int maxi = Integer.MIN_VALUE;
    for (int i = 0; i < 3; i++) {
      if (i != lastTask)
        maxi = Math.max(maxi, points[index][i] + memo(index - 1, i, points, dp));
    }
    return dp[index][lastTask] = maxi;
  }
  // T.C o(n*4*3) and S.C o(n*4)

  static int tabulation(int n, int[][] points) {
    int[][] dp = new int[n][4];
    dp[0][0] = Math.max(points[0][1], points[0][2]);
    dp[0][1] = Math.max(points[0][0], points[0][2]);
    dp[0][2] = Math.max(points[0][1], points[0][0]);
    dp[0][3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

    for (int index = 1; index < n; index++) {
      for (int last = 0; last < 4; last++) {
        dp[index][last] = 0;
        for (int i = 0; i < 3; i++) {
          if (i != last)
            dp[index][last] = Math.max(dp[index][last], points[index][i] + dp[index - 1][i]);
        }
      }
    }
    return dp[n - 1][3];
  }

  // T.C o(n*4*3) and S.C o(4)
  static int spaceOptimized(int n, int[][] points) {
    int[] prev = new int[4];
    prev[0] = Math.max(points[0][1], points[0][2]);
    prev[1] = Math.max(points[0][0], points[0][2]);
    prev[2] = Math.max(points[0][1], points[0][0]);
    prev[3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

    for (int index = 1; index < n; index++) {
      int[] curr = new int[4];
      for (int last = 0; last < 4; last++) {
        curr[last] = 0;
        for (int i = 0; i < 3; i++) {
          if (i != last)
            curr[last] = Math.max(curr[last], points[index][i] + prev[i]);
        }
      }
      prev = curr;
    }
    return prev[3];
  }

}
