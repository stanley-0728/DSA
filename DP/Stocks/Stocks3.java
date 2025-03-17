package DP.Stocks;

import java.util.Arrays;

/*
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
Find the maximum profit you can achieve. You may complete at most two transactions.
Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

Example 1:

Input: prices = [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */
public class Stocks3 {
  public int maxProfit(int[] prices) {
    // return recursion(0, 1, 2, prices);// this means give me the maxium profit
    // starting from index 0 if i can buy and
    // // with capacity 2.
    int[][][] dp = new int[prices.length][2][3];
    for (int i = 0; i < prices.length; i++) {
      for (int j = 0; j < 2; j++) {
        Arrays.fill(dp[i][j], -1);
      }
    }
    return memo(0, 1, 2, prices, dp);
  }

  int recursion(int index, int buy, int cap, int[] prices) {
    if (cap == 0)
      return 0;
    if (index == prices.length)
      return 0;
    int profit = 0;
    if (buy == 1) {
      profit = Math.max(-prices[index] + recursion(index + 1, 0, cap, prices), recursion(index + 1, 1, cap, prices));
    } else {
      profit = Math.max(prices[index] + recursion(index + 1, 1, cap - 1, prices), recursion(index + 1, 0, cap, prices));
    }
    return profit;
  }

  int memo(int index, int buy, int cap, int[] prices, int[][][] dp) {
    if (cap == 0)
      return 0;
    if (index == prices.length)
      return 0;
    if (dp[index][buy][cap] != -1)
      return dp[index][buy][cap];
    int profit = 0;
    if (buy == 1) {
      profit = Math.max(-prices[index] + memo(index + 1, 0, cap, prices, dp), memo(index + 1, 1, cap, prices, dp));
    } else {
      profit = Math.max(prices[index] + memo(index + 1, 1, cap - 1, prices, dp), memo(index + 1, 0, cap, prices, dp));
    }
    return dp[index][buy][cap] = profit;
  }

  int tabulation(int[] prices) {
    int[][][] dp = new int[prices.length + 1][3][3];
    for (int index = prices.length - 1; index >= 0; index--) {
      for (int buy = 0; buy < 2; buy++) {
        for (int cap = 1; cap < 3; cap++) {
          int profit = 0;
          if (buy == 1) {
            profit = Math.max(-prices[index] + dp[index + 1][0][cap], dp[index + 1][1][cap]);
          } else {
            profit = Math.max(prices[index] + dp[index + 1][1][cap - 1], dp[index + 1][0][cap]);
          }
          dp[index][buy][cap] = profit;
        }
      }
    }
    return dp[0][1][2];
  }

  int spaceOptimized(int[] prices) {
    int[][] ahead = new int[3][3];
    int[][] curr = new int[3][3];
    for (int index = prices.length - 1; index >= 0; index--) {
      for (int cap = 1; cap < 3; cap++) {
        int profit = 0;
        profit = Math.max(-prices[index] + ahead[0][cap], ahead[1][cap]);
        curr[1][cap] = profit;
        profit = Math.max(prices[index] + ahead[1][cap - 1], ahead[0][cap]);
        curr[0][cap] = profit;
      }

      ahead = curr.clone();
    }
    return ahead[1][2];
  }

  // B S B S
  // 0 1 2 3
  int moreOptimized(int[] prices) {
    int[][] dp = new int[prices.length + 1][5];

    for (int index = prices.length - 1; index >= 0; index--) {
      for (int cap = 3; cap >= 0; cap--) {
        int profit = 0;
        if (cap % 2 == 0) {
          profit = Math.max(-prices[index] + dp[index + 1][cap + 1], dp[index + 1][cap]);
        } else {
          profit = Math.max(prices[index] + dp[index + 1][cap + 1], dp[index + 1][cap]);
        }
        dp[index][cap] = profit;
      }
    }
    return dp[0][0];
  }

}
