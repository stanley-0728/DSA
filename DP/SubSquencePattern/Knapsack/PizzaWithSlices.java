package DP.SubSquencePattern.Knapsack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*here is a pizza with 3n slices of varying size, you and your friends will take slices of pizza as follows:
You will pick any pizza slice.
Your friend Alice will pick the next slice in the anti-clockwise direction of your pick.
Your friend Bob will pick the next slice in the clockwise direction of your pick.
Repeat until there are no more slices of pizzas.
Given an integer array slices that represent the sizes of the pizza slices in a clockwise direction, return the maximum possible sum of slice sizes that you can pick. */
public class PizzaWithSlices {
  public int maxSizeSlices(int[] nums) {
    int k = nums.length;
    List<Integer> temp1 = new ArrayList<>();
    List<Integer> temp2 = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
      if (i != 0)
        temp1.add(nums[i]);
      if (i != nums.length - 1)
        temp2.add(nums[i]);
    }
    int[][] dp = new int[k][(k / 3) + 1];
    for (int[] i : dp)
      Arrays.fill(i, -1);
    return Math.max(recursion(0, k / 3, temp1, dp), recursion(0, k / 3, temp2, dp));
    // return Math.max(tabulation(temp1),tabulation(temp2));

  }

  int recursion(int i, int n, List<Integer> nums, int[][] dp) {
    if (i >= nums.size() || n == 0)
      return 0;
    if (dp[i][n] != -1)
      return dp[i][n];
    int notPick = recursion(i + 1, n, nums, dp);
    int pick = nums.get(i) + recursion(i + 2, n - 1, nums, dp);
    return dp[i][n] = Math.max(notPick, pick);
  }

  int tabulation(List<Integer> nums) {
    int k = nums.size();
    int[][] dp = new int[k + 2][(k / 3) + 2];

    for (int i = k - 1; i >= 0; i--) {
      for (int j = 1; j <= (k / 3) + 1; j++) {
        int notPick = dp[i + 1][j];
        int pick = 0;
        if (i + 2 < k + 2)
          pick = nums.get(i) + dp[i + 2][j - 1];
        dp[i][j] = Math.max(notPick, pick);
      }
    }
    return dp[0][(k / 3) + 1];
  }

  int spaceOptimized(List<Integer> nums) {
    int k = nums.size();
    int maxSize = (k / 3) + 1;

    int[] ahead2 = new int[maxSize + 1];
    int[] ahead1 = new int[maxSize + 1]; 

    for (int i = k - 1; i >= 0; i--) {
      int[] curr = new int[maxSize + 1]; 

      for (int j = 1; j <= maxSize; j++) {
        int notPick = ahead1[j]; 
        int pick = 0;
        if (i + 2 < k + 2)
          pick = nums.get(i) + ahead2[j - 1]; 
        curr[j] = Math.max(notPick, pick);
      }

      ahead2 = ahead1;
      ahead1 = curr;
    }

    return ahead1[maxSize];
  }
}
