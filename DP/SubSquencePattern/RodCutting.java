package DP.SubSquencePattern;

import java.util.Arrays;

/*
 * Given a rod of length ‘N’ units. The rod can be cut into different sizes and each size has a cost associated with it. Determine the maximum cost obtained by cutting the rod and selling its pieces.

Note:
1. The sizes will range from 1 to ‘N’ and will be integers.

2. The sum of the pieces cut should be equal to ‘N’.

3. Consider 1-based indexing.
Detailed explanation ( Input/output format, Notes, Images )
Constraints:
1 <= T <= 50
1 <= N <= 100
1 <= A[i] <= 100

Where ‘T’ is the total number of test cases, ‘N’ denotes the length of the rod, and A[i] is the cost of sub-length.

Sample Input 1:
2
5
2 5 7 8 10
8
3 5 8 9 10 17 17 20
Sample Output 1:
12
24
Explanation of sample input 1:
Test case 1:

All possible partitions are:
1,1,1,1,1           max_cost=(2+2+2+2+2)=10
1,1,1,2             max_cost=(2+2+2+5)=11
1,1,3               max_cost=(2+2+7)=11
1,4                 max_cost=(2+8)=10
5                   max_cost=(10)=10
2,3                 max_cost=(5+7)=12
1,2,2               max _cost=(1+5+5)=12    

Clearly, if we cut the rod into lengths 1,2,2, or 2,3, we get the maximum cost which is 12.
 */
public class RodCutting {
  public static int cutRod(int price[], int n) {
    // Write your code here.

    int[][] dp = new int[n][n + 1];
    for (int i = 0; i < n; i++)
      Arrays.fill(dp[i], -1);
    // return recursion(price.length - 1, n, price);
    return memo(price.length - 1, n, price, dp);

  }

  static int recursion(int index, int k, int[] nums) {
    if (k == 0)
      return 0;
    if (index == 0) {
      if (k % (index + 1) == 0)
        return (k / (index + 1)) * nums[0];
      else
        return Integer.MIN_VALUE + 1;
    }
    int notPick = recursion(index - 1, k, nums);
    int rodLength = index + 1;
    int pick = Integer.MIN_VALUE + 1;
    if (rodLength <= k)
      pick = nums[index] + recursion(index, k - rodLength, nums);
    return Math.max(pick, notPick);
  }

  static int memo(int index, int k, int[] nums, int[][] dp) {
    if (k == 0)
      return 0;
    if (index == 0) {
      if (k % (index + 1) == 0)
        return (k / (index + 1)) * nums[0];
      else
        return Integer.MIN_VALUE + 1;
    }
    if (dp[index][k] != -1)
      return dp[index][k];
    int notPick = memo(index - 1, k, nums, dp);
    int rodLength = index + 1;
    int pick = Integer.MIN_VALUE + 1;
    if (rodLength <= k)
      pick = nums[index] + memo(index, k - rodLength, nums, dp);
    return dp[index][k] = Math.max(pick, notPick);
  }

  static int tabulation(int[] nums, int k) {
    int n = nums.length;
    int[][] dp = new int[n][k + 1];

    for (int i = 0; i <= k; i++) {
      if (i % 1 == 0)
        dp[0][i] = i * nums[0];
    }
    for (int i = 1; i < n; i++) {
      for (int j = 0; j <= k; j++) {
        int notPick = dp[i - 1][j];
        int pick = 0;
        int rodLength = i + 1;
        if (rodLength <= j)
          pick = nums[i] + dp[i][j - rodLength];
        dp[i][j] = Math.max(pick, notPick);
      }
    }
    return dp[n - 1][k];
  }

  static int spaceOptimized(int[] nums, int k) {
    int n = nums.length;
    int[] prev = new int[k + 1];
    int[] curr = new int[k + 1];
    for (int i = 0; i <= k; i++) {
      if (i % 1 == 0)
        prev[i] = i * nums[0];
    }
    for (int i = 1; i < n; i++) {
      for (int j = 0; j <= k; j++) {
        int notPick = prev[j];
        int pick = 0;
        int rodLength = i + 1;
        if (rodLength <= j)
          pick = nums[i] + curr[j - rodLength];
        curr[j] = Math.max(pick, notPick);
      }
      prev = curr.clone();
    }
    return prev[k];
  }
}
