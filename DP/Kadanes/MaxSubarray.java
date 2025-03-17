package DP.Kadanes;

import java.util.Arrays;

/*
 * Given an integer array nums, find the subarray with the largest sum, and return its sum.
Example 1:

Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: The subarray [4,-1,2,1] has the largest sum 6.
Example 2:

Input: nums = [1]
Output: 1
Explanation: The subarray [1] has the largest sum 1.
Example 3:

Input: nums = [5,4,-1,7,8]
Output: 23
Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 
 */
public class MaxSubarray {
  public int maxSubArray(int[] nums) {
    int curr = 0;
    int ans = -(int) 1e9;
    for (int i : nums) {
      curr = Math.max(i, curr + i);
      ans = Math.max(ans, curr);
    }
    System.out.println(ans);
    return ans;
  }

  /*
   * Given an integer array nums, find a subarray that has the largest product,
   * and return the product.
   * 
   * The test cases are generated so that the answer will fit in a 32-bit integer.
   * 
   * Example 1:
   * 
   * Input: nums = [2,3,-2,4]
   * Output: 6
   * Explanation: [2,3] has the largest product 6.
   * Example 2:
   * 
   * Input: nums = [-2,0,-1]
   * Output: 0
   * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
   * 
   */
  public int maxProduct(int[] nums) {
    int prefix = 1, suffix = 1;
    int ans = -(int) 1e9;
    for (int i = 0; i < nums.length; i++) {
      if (prefix == 0)
        prefix = 1;
      if (suffix == 0)
        suffix = 1;
      prefix = prefix * nums[i];
      suffix = suffix * nums[nums.length - 1 - i];
      ans = Math.max(ans, Math.max(prefix, suffix));
    }
    return ans;
  }

  public int maxTurbulenceSize(int[] arr) {
    int n = arr.length;
    if (n == 1)
      return 1;
    int max = 1;
    int start = 0;
    int end = 0;
    while (start < arr.length - 1) {
      if (arr[start] == arr[start + 1]) {
        start++;
        continue;
      }
      end = start + 1;
      while (end + 1 < arr.length && isTurbulent(end, arr)) {
        end++;
      }
      max = Math.max(max, end - start + 1);
      start = end;
    }
    return max;
  }

  private boolean isTurbulent(int k, int[] arr) {
    return (arr[k] > arr[k + 1] && arr[k] > arr[k - 1]) || (arr[k] < arr[k + 1] && arr[k] < arr[k - 1]);
  }

  /*
   * Given an array of integers, return the maximum sum for a non-empty subarray
   * (contiguous elements) with at most one element deletion. In other words, you
   * want to choose a subarray and optionally delete one element from it so that
   * there is still at least one element left and the sum of the remaining
   * elements is maximum possible.
   * 
   * Note that the subarray needs to be non-empty after deleting one element.
   * Example 1:
   * 
   * Input: arr = [1,-2,0,3]
   * Output: 4
   * Explanation: Because we can choose [1, -2, 0, 3] and drop -2, thus the
   * subarray [1, 0, 3] becomes the maximum value.
   * Example 2:
   * 
   * Input: arr = [1,-2,-2,3]
   * Output: 3
   * Explanation: We just choose [3] and it's the maximum sum.
   * Example 3:
   * 
   * Input: arr = [-1,-1,-1,-1]
   * Output: -1
   * Explanation: The final subarray needs to be non-empty. You can't choose [-1]
   * and delete -1 from it, then get an empty subarray to make the sum equals to
   * 0.
   */

  public int maximumSum(int[] arr) {
    int n = arr.length;
    int[] prefix = new int[arr.length];
    prefix[0] = arr[0];
    int[] suffix = new int[n];
    suffix[n - 1] = arr[n - 1];
    int maxPrefixSum = arr[0];
    for (int i = 1, j = n - 2; i < n; i++, j--) {
      prefix[i] = Math.max(prefix[i - 1] + arr[i], arr[i]);
      maxPrefixSum = Math.max(maxPrefixSum, prefix[i]);

      suffix[j] = Math.max(suffix[j + 1] + arr[j], arr[j]);
    }
    System.out.println(suffix[0]);
    int ans = maxPrefixSum;
    for (int i = 1; i < arr.length - 1; i++) {
      if (arr[i] < 0) {
        ans = Math.max(ans, prefix[i - 1] + suffix[i + 1]);
      }
    }
    return ans;
  }

  /*
   * There are n gas stations along a circular route, where the amount of gas at
   * the ith station is gas[i].
   * 
   * You have a car with an unlimited gas tank and it costs cost[i] of gas to
   * travel from the ith station to its next (i + 1)th station. You begin the
   * journey with an empty tank at one of the gas stations.
   * 
   * Given two integer arrays gas and cost, return the starting gas station's
   * index if you can travel around the circuit once in the clockwise direction,
   * otherwise return -1. If there exists a solution, it is guaranteed to be
   * unique.
   * 
   * 
   * 
   * Example 1:
   * 
   * Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
   * Output: 3
   * Explanation:
   * Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 +
   * 4 = 4
   * Travel to station 4. Your tank = 4 - 1 + 5 = 8
   * Travel to station 0. Your tank = 8 - 2 + 1 = 7
   * Travel to station 1. Your tank = 7 - 3 + 2 = 6
   * Travel to station 2. Your tank = 6 - 4 + 3 = 5
   * Travel to station 3. The cost is 5. Your gas is just enough to travel back to
   * station 3.
   * Therefore, return 3 as the starting index.
   * Example 2:
   * 
   * Input: gas = [2,3,4], cost = [3,4,3]
   * Output: -1
   * Explanation:
   * You can't start at station 0 or 1, as there is not enough gas to travel to
   * the next station.
   * Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 =
   * 4
   * Travel to station 0. Your tank = 4 - 3 + 2 = 3
   * Travel to station 1. Your tank = 3 - 3 + 3 = 3
   * You cannot travel back to station 2, as it requires 4 unit of gas but you
   * only have 3.
   * Therefore, you can't travel around the circuit once no matter where you
   * start.
   */
  public int canCompleteCircuit(int[] gas, int[] cost) {
    int totalGas = 0, currGas = 0;
    int start = 0;
    for (int i = 0; i < gas.length; i++) {
      int diff = gas[i] - cost[i];
      totalGas += diff;
      currGas += diff;
      if (currGas < 0) {
        currGas = 0;
        start = i + 1;
      }
    }
    return totalGas < 0 ? -1 : start;
  }

  /*
   * Given an integer array arr and an integer k, modify the array by repeating it
   * k times.
   * 
   * For example, if arr = [1, 2] and k = 3 then the modified array will be [1, 2,
   * 1, 2, 1, 2].
   * 
   * Return the maximum sub-array sum in the modified array. Note that the length
   * of the sub-array can be 0 and its sum in that case is 0.
   * 
   * As the answer can be very large, return the answer modulo 109 + 7.
   * 
   * Example 1:
   * 
   * Input: arr = [1,2], k = 3
   * Output: 9
   * Example 2:
   * 
   * Input: arr = [1,-2,1], k = 5
   * Output: 2
   * Example 3:
   * 
   * Input: arr = [-1,-2], k = 7
   * Output: 0
   * 
   */
  public class Solution {
    public int kConcatenationMaxSum(int[] arr, int k) {
      int MOD = (int) 1e9 + 7;
      int n = arr.length;

      long kadane = maxSubArray(arr);
      kadane = Math.max(0, kadane);
      if (k == 1)
        return (int) (kadane % MOD);

      long total = 0;
      for (int num : arr)
        total += num;

      long maxPrefix = Long.MIN_VALUE, prefixSum = 0;
      for (int i = 0; i < n; i++) {
        prefixSum += arr[i];
        maxPrefix = Math.max(maxPrefix, prefixSum);
      }

      long maxSuffix = Long.MIN_VALUE, suffixSum = 0;
      for (int i = n - 1; i >= 0; i--) {
        suffixSum += arr[i];
        maxSuffix = Math.max(maxSuffix, suffixSum);
      }

      if (total > 0) {
        return (int) (Math.max(kadane, maxPrefix + maxSuffix + (k - 2) * total) % MOD);
      } else {
        return (int) (Math.max(kadane, maxPrefix + maxSuffix) % MOD);
      }
    }

    public long maxSubArray(int[] nums) {
      long curr = 0, ans = Long.MIN_VALUE;
      for (int i : nums) {
        curr = Math.max(i, curr + i);
        ans = Math.max(ans, curr);
      }
      return ans;
    }
  }

}
