package DP.Stocks;

import java.util.Arrays;

/*
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.

On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can buy it then immediately sell it on the same day.

Find and return the maximum profit you can achieve.

 

Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
Total profit is 4 + 3 = 7.
Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Total profit is 4.
Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.
 
 */
public class Stocks2 {
  public int maxProfit(int[] prices) {
    // return recursion(0, 1, prices);// this means give me the maxium profit
    // starting from index 0 if i can buy.
    int[][] dp = new int[prices.length][2];
    for (int i = 0; i < prices.length; i++)
      Arrays.fill(dp[i], -1);
    return memo(0, 1, prices, dp);
  }

  int recursion(int index, int buy, int[] prices) {
    if (index == prices.length)
      return 0;
    int profit = 0;
    if (buy == 1) {
      profit = Math.max(-prices[index] + recursion(index + 1, 0, prices), recursion(index + 1, 1, prices));
    } else {
      profit = Math.max(prices[index] + recursion(index + 1, 1, prices), recursion(index + 1, 0, prices));
    }
    return profit;
  }

  int memo(int index, int buy, int[] prices, int[][] dp) {
    if (index == prices.length)
      return 0;
    if (dp[index][buy] != -1)
      return dp[index][buy];
    int profit = 0;
    if (buy == 1) {
      profit = Math.max(-prices[index] + memo(index + 1, 0, prices, dp), memo(index + 1, 1, prices, dp));
    } else {
      profit = Math.max(prices[index] + memo(index + 1, 1, prices, dp), memo(index + 1, 0, prices, dp));
    }
    return dp[index][buy] = profit;
  }

  int tabulation(int[] prices) {
    int[][] dp = new int[prices.length + 1][3];
    for (int index = prices.length - 1; index >= 0; index--) {
      for (int buy = 0; buy < 2; buy++) {
        int profit = 0;
        if (buy == 1) {
          profit = Math.max(-prices[index] + dp[index + 1][0], dp[index + 1][1]);
        } else {
          profit = Math.max(prices[index] + dp[index + 1][1], dp[index + 1][0]);
        }
        dp[index][buy] = profit;
      }
    }
    return dp[0][1];
  }

  int spaceOptimized(int[] prices) {
    int[] ahead = new int[3];
    int[] curr = new int[3];
    for (int index = prices.length - 1; index >= 0; index--) {
      for (int buy = 0; buy < 2; buy++) {
        int profit = 0;
        if (buy == 1) {
          profit = Math.max(-prices[index] + ahead[0], ahead[1]);
        } else {
          profit = Math.max(prices[index] + ahead[1], ahead[0]);
        }
        curr[buy] = profit;
      }
      ahead = Arrays.copyOf(curr, curr.length);
    }
    return ahead[1];
  }

  int moreOptimized(int[] prices) {
    int aheadNotBuy = 0, aheadBuy = 0, currBuy = 0, currNotBuy = 0;
    for (int index = prices.length - 1; index >= 0; index--) {
      int profit = 0;
      profit = Math.max(-prices[index] + aheadNotBuy, aheadBuy);
      currBuy = profit;
      profit = Math.max(prices[index] + aheadBuy, aheadNotBuy);
      currNotBuy = profit;
      aheadBuy = currBuy;
      aheadNotBuy = currNotBuy;
    }
    return aheadBuy;
  }
}
