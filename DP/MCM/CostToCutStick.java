package DP.MCM;

import java.util.Arrays;

/*
 * Given a wooden stick of length n units. The stick is labelled from 0 to n.
 * For example, a stick of length 6 is labelled as follows:
 * 
 * 
 * Given an integer array cuts where cuts[i] denotes a position you should
 * perform a cut at.
 * 
 * You should perform the cuts in order, you can change the order of the cuts as
 * you wish.
 * 
 * The cost of one cut is the length of the stick to be cut, the total cost is
 * the sum of costs of all cuts. When you cut a stick, it will be split into two
 * smaller sticks (i.e. the sum of their lengths is the length of the stick
 * before the cut). Please refer to the first example for a better explanation.
 * 
 * Return the minimum total cost of the cuts.
 * 
 */
public class CostToCutStick {
  public int minCost(int n, int[] cuts) {
    Arrays.sort(cuts);
    int size = cuts.length;
    int[] newCuts = new int[size + 2];

    newCuts[0] = 0;
    newCuts[size + 1] = n;

    for (int i = 0; i < size; i++) {
      newCuts[i + 1] = cuts[i];
    }
    // int[][] dp = new int[size + 2][size + 2];
    // for (int i = 0; i < size + 2; i++)
    // Arrays.fill(dp[i], -1);
    // // return recursion(1, size, newCuts, dp);

    return tabulation(n, size, newCuts);
  }

  int recursion(int i, int j, int[] cuts) {
    if (i > j)
      return 0;
    int mini = Integer.MAX_VALUE;
    for (int k = i; k <= j; k++) {
      int cost = cuts[j + 1] - cuts[i - 1] + recursion(i, k - 1, cuts) + recursion(k + 1, j, cuts);
      mini = Math.min(mini, cost);
    }
    return mini;
  }

  int recursion(int i, int j, int[] cuts, int[][] dp) {
    if (i > j)
      return 0;
    if (dp[i][j] != -1)
      return dp[i][j];
    int mini = Integer.MAX_VALUE;
    for (int k = i; k <= j; k++) {
      int cost = cuts[j + 1] - cuts[i - 1] + recursion(i, k - 1, cuts, dp) + recursion(k + 1, j, cuts, dp);
      mini = Math.min(mini, cost);
    }
    return dp[i][j] = mini;
  }
  int tabulation(int n, int c, int[] cuts) {
    int[][] dp = new int[c + 2][c + 2];
    for (int i = c; i >= 1; i--) {
      for (int j = i ; j <= c; j++) {
        int mini = Integer.MAX_VALUE;
        for (int k = i; k <= j; k++) {
          int cost = cuts[j + 1] - cuts[i - 1] + dp[i][k - 1] + dp[k + 1][j];
          mini = Math.min(mini, cost);
        }
        dp[i][j] = mini;
      }
    }
    return dp[1][c];
  }
}
