package DP.SubSquencePattern;

import java.util.Arrays;

public class SubSet {
  public static boolean subsetSumToK(int n, int k, int arr[]) {
    // return find(n - 1, k, arr);
    Boolean[][] dp = new Boolean[n][k + 1];
    return memo(n - 1, k, arr, dp);
  }

  // T.C exponential and S.C o(n)
  static boolean find(int index, int k, int[] arr) {
    if (k == 0)
      return true;
    if (index == 0) {
      return arr[index] == k;
    }
    boolean notPick = find(index - 1, k, arr);
    boolean pick = false;
    if (arr[index] <= k)
      pick = find(index - 1, k - arr[index], arr);
    return pick | notPick;
  }

  // T.C o(n*k) S.C o(n*k) +o(n)
  static boolean memo(int index, int k, int[] arr, Boolean[][] dp) {
    if (k == 0)
      return true;
    if (index == 0)
      return arr[index] == k;
    if (dp[index][k] != null)
      return dp[index][k];

    boolean notPick = memo(index - 1, k, arr, dp);
    boolean pick = false;
    if (arr[index] <= k)
      pick = memo(index - 1, k - arr[index], arr, dp);

    dp[index][k] = pick || notPick;
    return dp[index][k];
  }

  // T.C o(n*k) S.C o(n*k)
  static boolean tabulation(int n, int k, int[] arr) {
    boolean[][] dp = new boolean[n][k + 1];
    for (int i = 0; i < n; i++)
      dp[i][0] = true;
    if (arr[0] <= k)
      dp[0][arr[0]] = true;
    for (int i = 1; i < n; i++) {
      for (int target = 1; target <= k; target++) {
        dp[i][target] = dp[i - 1][target];
        if (arr[i] <= target)
          dp[i][target] = dp[i][target] || dp[i - 1][target - arr[i]];
      }
    }
    return dp[n - 1][k];
  }

  static boolean spaceOptimized(int n, int k, int[] arr) {
    boolean[] prev = new boolean[k + 1];
    boolean[] curr = new boolean[k + 1];
    prev[0] = true;
    curr[0] = true;
    if (arr[0] <= k) {
      prev[arr[0]] = true;
    }
    for (int i = 1; i < n; i++) {
      for (int target = 1; target <= k; target++) {
        curr[target] = prev[target];
        if (arr[i] <= target)
          curr[target] = curr[target] || prev[target - arr[i]];
      }
      prev = curr.clone();
    }
    return prev[k];
  }

  public boolean canPartition(int[] nums) {
    int sum = 0;
    for (int i : nums) {
      sum += i;
    }
    int k = sum / 2;
    if (sum % 2 != 0)
      return false;
    return spaceOptimized(nums.length, k, nums);
  }

  public boolean canPartitionKSubsets(int[] nums, int k) {
    int sum = Arrays.stream(nums).sum();

    // If the sum is not divisible by k, partitioning is impossible
    if (sum % k != 0)
      return false;
    int target = sum / k;

    Arrays.sort(nums); // Sort numbers in ascending order
    reverse(nums); // Reverse to start with the largest number (optimization)

    boolean[] visited = new boolean[nums.length];
    return backtrack(nums, visited, 0, k, 0, target);
  }

  private boolean backtrack(int[] nums, boolean[] visited, int startIndex, int k, int currSum, int target) {
    if (k == 1)
      return true; // If k-1 subsets are filled, the last one must be valid

    if (currSum == target)
      return backtrack(nums, visited, 0, k - 1, 0, target); // Start next subset

    for (int i = startIndex; i < nums.length; i++) {
      if (!visited[i] && currSum + nums[i] <= target) {
        visited[i] = true; // Mark the number as used
        if (backtrack(nums, visited, i + 1, k, currSum + nums[i], target))
          return true;
        visited[i] = false; // Backtrack
      }
    }
    return false;
  }

  private void reverse(int[] nums) {
    int left = 0, right = nums.length - 1;
    while (left < right) {
      int temp = nums[left];
      nums[left++] = nums[right];
      nums[right--] = temp;
    }
  }

}
