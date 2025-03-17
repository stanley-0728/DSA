package DP.FrontPartition;

import java.util.Arrays;

/*
 * Given an integer array arr, partition the array into (contiguous) subarrays of length at most k. After partitioning, each subarray has their values changed to become the maximum value of that subarray.

Return the largest sum of the given array after partitioning. Test cases are generated so that the answer fits in a 32-bit integer.
Example 1:

Input: arr = [1,15,7,9,2,5,10], k = 3
Output: 84
Explanation: arr becomes [15,15,15,9,10,10,10]
Example 2:

Input: arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
Output: 83
Example 3:

Input: arr = [1], k = 1
Output: 1
 
 */
public class PartitionArrayMaxSm {
  public int maxSumAfterPartitioning(int[] arr, int k) {
    int[] dp = new int[arr.length + 1];
    Arrays.fill(dp, -1);
    return recursion(0, k, arr, dp);
  }

  int recursion(int index, int k, int[] arr) {
    if (index == arr.length)
      return 0;
    int len = 0, maxi = Integer.MIN_VALUE;
    int sum = 0;
    for (int j = index; j < Math.min(arr.length, index + k); j++) {
      len++;
      maxi = Math.max(arr[j], maxi);
      sum = Math.max(sum, (len * maxi) + recursion(j + 1, k, arr));
    }
    return sum;
  }

  int recursion(int index, int k, int[] arr, int[] dp) {
    if (index == arr.length)
      return 0;
    if (dp[index] != -1)
      return dp[index];
    int len = 0, maxi = Integer.MIN_VALUE;
    int sum = 0;
    for (int j = index; j < Math.min(arr.length, index + k); j++) {
      len++;
      maxi = Math.max(arr[j], maxi);
      sum = Math.max(sum, (len * maxi) + recursion(j + 1, k, arr, dp));
    }
    return dp[index] = sum;
  }

  int tabulation(int[] arr, int k) {
    int[] dp = new int[arr.length + 1];
    for (int index = arr.length - 1; index >= 0; index--) {
      int len = 0, maxi = Integer.MIN_VALUE;
      int sum = 0;
      for (int j = index; j < Math.min(arr.length, index + k); j++) {
        len++;
        maxi = Math.max(arr[j], maxi);
        sum = Math.max(sum, (len * maxi) + dp[j + 1]);
      }
      dp[index] = sum;
    }
    return dp[0];
  }
}
