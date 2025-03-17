package DP.SubSquencePattern.Knapsack;

import java.util.Arrays;

/*A thief is robbing a store and can carry a maximal weight of W into his knapsack. There are N items and the ith item weighs wi and is of value vi. Considering the constraints of the maximum weight that a knapsack can carry, you have to find and return the maximum value that a thief can generate by stealing items.

Detailed explanation ( Input/output format, Notes, Images )
Constraints:
1 <= T <= 10
1 <= N <= 10^2
1<= wi <= 50
1 <= vi <= 10^2
1 <= W <= 10^3
 * 
 */
public class BoundedKnapsack {
  static int knapsack(int[] weight, int[] value, int n, int maxWeight) {

    // return recursion(weight, value, n - 1, maxWeight);
    int[][] dp = new int[n][maxWeight + 1];
    for (int i = 0; i < n; i++)
      Arrays.fill(dp[i], -1);
    return memo(weight, value, n - 1, maxWeight, dp);
  }

  static int recursion(int[] weight, int[] value, int index, int maxWeight) {
    if (index == 0) {
      if (weight[index] <= maxWeight)
        return value[index];
      else
        return 0;
    }
    int notPick = recursion(weight, value, index - 1, maxWeight);
    int pick = 0;
    if (weight[index] <= maxWeight)
      pick = value[index] + recursion(weight, value, index - 1, maxWeight - weight[index]);
    return Math.max(pick, notPick);
  }

  static int memo(int[] weight, int[] value, int index, int maxWeight, int[][] dp) {
    if (index == 0) {
      if (weight[index] <= maxWeight)
        return value[index];
      else
        return 0;
    }
    if (dp[index][maxWeight] != -1)
      return dp[index][maxWeight];
    int notPick = memo(weight, value, index - 1, maxWeight, dp);
    int pick = 0;
    if (weight[index] <= maxWeight)
      pick = value[index] + memo(weight, value, index - 1, maxWeight - weight[index], dp);
    return dp[index][maxWeight] = Math.max(pick, notPick);
  }

  static int tabulation(int[] weight, int[] value, int n, int maxWeight) {
    int[][] dp = new int[n][maxWeight + 1];
    for (int i = weight[0]; i <= maxWeight; i++)
      dp[0][i] = value[0];
    for (int i = 1; i < n; i++) {
      for (int j = 0; j <= maxWeight; j++) {
        int notPick = dp[i - 1][j];
        int pick = 0;
        if (weight[i] <= j)
          pick = value[i] + dp[i - 1][j - weight[i]];
        dp[i][j] = Math.max(pick, notPick);
      }
    }
    return dp[n - 1][maxWeight];
  }

  static int spaceOptimized(int[] weight, int[] value, int n, int maxWeight) {
    int[] prev = new int[maxWeight + 1];
    for (int i = weight[0]; i <= maxWeight; i++)
      prev[i] = value[0];
    for (int i = 1; i < n; i++) {
      for (int j = maxWeight; j >= 0; j--) {
        int notPick = prev[j];
        int pick = 0;
        if (weight[i] <= j)
          pick = value[i] + prev[j - weight[i]];
        prev[j] = Math.max(pick, notPick);
      }
    }
    return prev[maxWeight];
  }
}
