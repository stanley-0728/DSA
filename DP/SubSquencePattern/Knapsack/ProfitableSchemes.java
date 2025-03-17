package DP.SubSquencePattern.Knapsack;

import java.util.Arrays;

/*There is a group of n members, and a list of various crimes they could commit. The ith crime generates a profit[i] and requires group[i] members to participate in it. If a member participates in one crime, that member can't participate in another crime.

Let's call a profitable scheme any subset of these crimes that generates at least minProfit profit, and the total number of members participating in that subset of crimes is at most n.

Return the number of schemes that can be chosen. Since the answer may be very large, return it modulo 109 + 7.
Example 1:

Input: n = 5, minProfit = 3, group = [2,2], profit = [2,3]
Output: 2
Explanation: To make a profit of at least 3, the group could either commit crimes 0 and 1, or just crime 1.
In total, there are 2 schemes.
Example 2:

Input: n = 10, minProfit = 5, group = [2,3,5], profit = [6,7,8]
Output: 7
Explanation: To make a profit of at least 5, the group could commit any crimes, as long as they commit one.
There are 7 possible schemes: (0), (1), (2), (0,1), (0,2), (1,2), and (0,1,2).
  */
public class ProfitableSchemes {
  int MOD = (int) 1e9 + 7;

  public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
    int[][][] memo = new int[profit.length][n + 1][minProfit + 1];
    for (int[][] arr : memo) {
      for (int[] sub : arr)
        Arrays.fill(sub, -1);
    }
    return recursion(profit.length - 1, n, 0, minProfit, group, profit, memo);
  }

  int recursion(int i, int remaining, int currProfit, int minProfit, int[] group, int[] profit, int[][][] dp) {
    if (i < 0)
      return currProfit >= minProfit ? 1 : 0;
    if (dp[i][remaining][currProfit] != -1)
      return dp[i][remaining][currProfit];
    int notPick = (recursion(i - 1, remaining, currProfit, minProfit, group, profit, dp)) % MOD;
    int pick = 0;
    if (group[i] <= remaining) {
      int newProfit = Math.min(minProfit, currProfit + profit[i]);
      pick = (recursion(i - 1, remaining - group[i], newProfit, minProfit, group, profit, dp)) % MOD;
    }
    return dp[i][remaining][currProfit] = (pick + notPick) % MOD;
  }

}
