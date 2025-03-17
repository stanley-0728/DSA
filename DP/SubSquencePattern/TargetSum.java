package DP.SubSquencePattern;

import java.util.Arrays;

/*
 * You are given an array ‘ARR’ of ‘N’ integers and a target number, ‘TARGET’. Your task is to build an expression out of an array by adding one of the symbols '+' and '-' before each integer in an array, and then by concatenating all the integers, you want to achieve a target. You have to return the number of ways the target can be achieved.

For Example :
You are given the array ‘ARR’ = [1, 1, 1, 1, 1], ‘TARGET’ = 3. The number of ways this target can be achieved is:
1. -1 + 1 + 1 + 1 + 1 = 3
2. +1 - 1 + 1 + 1 + 1 = 3
3. +1 + 1 - 1 + 1 + 1 = 3
4. +1 + 1 + 1 - 1 + 1 = 3
5. +1 + 1 + 1 + 1 - 1 = 3
These are the 5 ways to make. Hence the answer is 5.
Detailed explanation ( Input/output format, Notes, Images )
Constraints :
1 <= T <= 10
1 <= N <= 25
-1000 <= TARGET <= 1000
0 <= ARR[i] <= 1000

 */
public class TargetSum {
  static final int MOD = 1000000007; // To prevent overflow

  public static int targetSum(int n, int target, int[] arr) {
    // Write your code here.

    int sum = Arrays.stream(arr).sum();
    if ((sum - target) % 2 != 0 || sum - target < 0)
      return 0;
    target = (sum - target) / 2;
    int[][] dp = new int[n][target + 1];
    for (int i = 0; i < n; i++)
      Arrays.fill(dp[i], -1);
    return (int) memo(n - 1, arr, target, dp);
    // return recursion(n - 1, arr, (sum - target) / 2);
  }

  static int recursion(int index, int num[], int tar) {
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

  public int tabulation(int[] num, int tar) {
    int n = num.length;
    int sum = Arrays.stream(num).sum();
    if ((sum - tar) % 2 != 0 || sum - tar < 0)
      return 0;
    tar = (sum - tar) / 2;
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

  public int spaceOptimized(int[] num, int tar) {
    int n = num.length;
    int sum = Arrays.stream(num).sum();
    if ((sum - tar) % 2 != 0 || sum - tar < 0)
      return 0;
    tar = (sum - tar) / 2;
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
}
