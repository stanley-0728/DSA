package DP.Stocks;

/*
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:

After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

Example 1:

Input: prices = [1,2,3,0,2]
Output: 3
Explanation: transactions = [buy, sell, cooldown, buy, sell]
Example 2:

Input: prices = [1]
Output: 0
 */
public class Stocks5 {
  public int maxProfit(int[] prices) {
    int[][] dp = new int[prices.length + 1][3];
    for (int index = prices.length - 1; index >= 0; index--) {
      for (int buy = 0; buy < 2; buy++) {
        int profit = 0;
        if (buy == 1) {
          profit = Math.max(-prices[index] + dp[index + 1][0], dp[index + 1][1]);
        } else {
          profit = Math.max(prices[index] + dp[index + 2][1], dp[index + 1][0]);
        }
        dp[index][buy] = profit;
      }
    }
    return dp[0][1];
  }
}
