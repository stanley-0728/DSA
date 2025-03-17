package DP.SubSquencePattern;

import java.util.Arrays;

/*
 * You are given an array 'arr' of size 'n' containing positive integers and a target sum 'k'.
Find the number of ways of selecting the elements from the array such that the sum of chosen elements is equal to the target 'k'.
Since the number of ways can be very large, print it modulo 10 ^ 9 + 7.
Example:
Input: 'arr' = [1, 1, 4, 5]
Output: 3
Explanation: The possible ways are:
[1, 4]
[1, 4]
[5]
Hence the output will be 3. Please note that both 1 present in 'arr' are treated differently.
 */
public class CountSubSet {
  static final int MOD = 1000000007; // To prevent overflow

  public static int findWays(int num[], int tar) {
    // Write your code here.
    // return recursion(num.length - 1, num, tar);
    int[][] dp = new int[num.length][tar + 1];
    for (int i = 0; i < num.length; i++)
      Arrays.fill(dp[i], -1);
    return (int) memo(num.length - 1, num, tar, dp);
  }

  static int recursion(int index, int num[], int tar) {
    if (tar < 0)
      return 0;
    System.out.println(tar);
    if (index == 0) {
      if (num[0] == 0 && tar == 0)
        return 2;
      else if (tar == 0 || num[0] == tar)
        return 1;
      else
        return 0;
    }
    int notPick = recursion(index - 1, num, tar);
    int pick = 0;
    if (num[index] <= tar)
      pick = recursion(index - 1, num, tar - num[index]);
    return pick + notPick;
  }

  static long memo(int index, int num[], int tar, int[][] dp) {
    if (tar == 0)
      return 1;

    if (index == 0) {
      if (num[0] == 0 && tar == 0)
        return 2;
      return (tar == 0 || num[0] == tar) ? 1 : 0;
    }

    if (dp[index][tar] != -1)
      return dp[index][tar];

    long notPick = memo(index - 1, num, tar, dp) % MOD;
    long pick = 0;
    if (num[index] <= tar)
      pick = memo(index - 1, num, tar - num[index], dp) % MOD;

    dp[index][tar] = (int) ((pick + notPick) % MOD);
    return dp[index][tar];
  }

  // In all the problems we have seen j or the second loop starts from 1 but here
  // it starting from 0 . The reason as you can see in all other for every row we
  // are filling 0 values but here if we are not so if we start from 1 then we
  // would loose the count of some subsets which can lead to wrong answers.
  public static int tabulation(int num[], int tar) {
    // Write your code here.
    int n = num.length;
    int[][] dp = new int[n][tar + 1];

    // ? Base Case: When target is 0, always 1 valid subset (empty subset)
    dp[0][0] = (num[0] == 0) ? 2 : 1; // Handle zero case

    // ? If first element is within the target range, mark it
    if (num[0] != 0 && num[0] <= tar) {
      dp[0][num[0]] = 1;
    }

    // * Fill the DP Table
    for (int i = 1; i < n; i++) {
      for (int j = 0; j <= tar; j++) {
        int notPick = dp[i - 1][j]; // Exclude current number
        int pick = (num[i] <= j) ? dp[i - 1][j - num[i]] : 0; // Include current number

        dp[i][j] = (pick + notPick) % MOD;
      }
    }

    return dp[n - 1][tar]; // Return final answer
  }

  public static int spaceOptimized(int num[], int tar) {
    // Write your code here.
    int n = num.length;
    int[] prev = new int[tar + 1];

    // ? Base Case: When target is 0, always 1 valid subset (empty subset)
    prev[0] = (num[0] == 0) ? 2 : 1; // Handle zero case

    // ? If first element is within the target range, mark it
    if (num[0] != 0 && num[0] <= tar) {
      prev[num[0]] = 1;
    }

    // * Fill the DP Table
    for (int i = 1; i < n; i++) {
      int[] curr = new int[tar + 1];
      for (int j = 0; j <= tar; j++) {
        int notPick = prev[j]; // Exclude current number
        int pick = (num[i] <= j) ? prev[j - num[i]] : 0; // Include current number

        curr[j] = (pick + notPick) % MOD;
      }
      prev = curr;
    }

    return prev[tar]; // Return final answer
  }

  public static int countPartitions(int n, int d, int[] arr) {
    // Write your code here.
    int sum = Arrays.stream(arr).sum();
    if ((sum - d) < 0 || (sum - d) % 2 != 0)
      return 0;
    return spaceOptimized(arr, (sum - d) / 2);
  }

}
