package DP.LIS;

import java.util.Arrays;

/*
 * A Bitonic Sequence is a sequence of numbers that is first strictly increasing and then strictly decreasing.
A strictly ascending order sequence is also considered bitonic, with the decreasing part as empty, and same for a strictly descending order sequence.
For example, the sequences [1, 3, 5, 3, 2], [1, 2, 3, 4] are bitonic, whereas the sequences [5, 4, 1, 4, 5] and [1, 2, 2, 3] are not.
You are given an array 'arr' consisting of 'n' positive integers.
Find the length of the longest bitonic subsequence of 'arr'.
Example :
Input: 'arr' = [1, 2, 1, 2, 1]
Output: 3

Explanation: The longest bitonic subsequence for this array will be [1, 2, 1]. Please note that [1, 2, 2, 1] is not a valid bitonic subsequence, because the consecutive 2's are neither strictly increasing, nor strictly decreasing.
Detailed explanation ( Input/output format, Notes, Images )

Sample Input 1 :
5 
1 2 1 2 1
Sample Output 1:
3
Explanation For Sample Input 1:
The longest bitonic subsequence for this array will be [1, 2, 1]. Please note that [1, 2, 2, 1] is not a valid bitonic subsequence, because the consecutive 2's are neither strictly increasing, nor strictly decreasing.


Sample Input 2 :
5
1 2 1 3 4

Sample Output 2 :
4
Explanation For Sample Input 2:
The longest bitonic sequence for this array will be [1, 2, 3, 4].


Expected time complexity :
The expected time complexity is O(n ^ 2).
Constraints:
1 <= 'n' <= 10^3
1 <= 'arr[i]' <= 10^5

Time Limit: 1sec
 */
public class LongestBitonicSubSquence {
  public static int longestBitonicSequence(int[] arr, int n) {
    // Write your code here.
    return tabulation1(arr);

  }

  static int tabulation1(int[] nums) {
    int dp[] = new int[nums.length];
    int dp2[] = new int[nums.length];
    Arrays.fill(dp, 1);
    Arrays.fill(dp2, 1);
    int maxi = 0;
    for (int i = 0; i < nums.length; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j] && dp[j] + 1 > dp[i]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }

    }
    for (int i = nums.length - 1; i >= 0; i--) {
      for (int j = nums.length - 1; j > i; j--) {
        if (nums[i] > nums[j] && dp2[j] + 1 > dp2[i]) {
          dp2[i] = Math.max(dp2[i], dp2[j] + 1);
        }
      }

    }
    maxi = 0;
    for (int i = 0; i < dp.length; i++) {
      maxi = Math.max(maxi, dp[i] + dp2[i] - 1);
    }
    return maxi;
  }

  /*
   * 
   * Code
   * Testcase
   * Test Result
   * Test Result
   * 1671. Minimum Number of Removals to Make Mountain Array
   * Hard
   * Topics
   * Companies
   * Hint
   * You may recall that an array arr is a mountain array if and only if:
   * 
   * arr.length >= 3
   * There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
   * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
   * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
   * Given an integer array nums​​​, return the minimum number of elements to
   * remove to make nums​​​ a mountain array.
   * 
   * Example 1:
   * 
   * Input: nums = [1,3,1]
   * Output: 0
   * Explanation: The array itself is a mountain array so we do not need to remove
   * any elements.
   * Example 2:
   * 
   * Input: nums = [2,1,1,5,6,2,3,1]
   * Output: 3
   * Explanation: One solution is to remove the elements at indices 0, 1, and 5,
   * making the array nums = [1,5,6,3,1].
   */

  public int minimumMountainRemovals(int[] nums) {
    int n = nums.length;
    if (n < 3)
      return 0;

    int[] dp = new int[n];
    int[] dp2 = new int[n];
    Arrays.fill(dp, 1);
    Arrays.fill(dp2, 1);

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
    }

    for (int i = n - 1; i >= 0; i--) {
      for (int j = n - 1; j > i; j--) {
        if (nums[i] > nums[j]) {
          dp2[i] = Math.max(dp2[i], dp2[j] + 1);
        }
      }
    }

    int maxMountain = 0;
    for (int i = 0; i < n; i++) {
      if (dp[i] > 1 && dp2[i] > 1) {
        maxMountain = Math.max(maxMountain, dp[i] + dp2[i] - 1);
      }
    }

    return maxMountain > 0 ? n - maxMountain : -1;
  }

}
