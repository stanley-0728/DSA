package DP.Stocks;

/*
 * You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee representing a transaction fee.
Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.
Note:
You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
The transaction fee is only charged once for each stock purchase and sale.
 
Example 1:
Input: prices = [1,3,2,8,4,9], fee = 2
Output: 8
Explanation: The maximum profit can be achieved by:
- Buying at prices[0] = 1
- Selling at prices[3] = 8
- Buying at prices[4] = 4
- Selling at prices[5] = 9
The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
Example 2:

Input: prices = [1,3,7,5,10,3], fee = 3
Output: 6
 */
public class Stocks6 {
  public int maxProfit(int[] prices, int fee) {
    int[][] dp = new int[prices.length + 1][3];
    for (int index = prices.length - 1; index >= 0; index--) {
      for (int buy = 0; buy < 2; buy++) {
        int profit = 0;
        if (buy == 1) {
          profit = Math.max(-prices[index] + dp[index + 1][0], dp[index + 1][1]);
        } else {
          profit = Math.max(prices[index] - fee + dp[index + 1][1], dp[index + 1][0]);
        }
        dp[index][buy] = profit;
      }
    }
    return dp[0][1];
  }
}
