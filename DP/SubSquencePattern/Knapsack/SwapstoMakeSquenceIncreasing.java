package DP.SubSquencePattern.Knapsack;

import java.util.Arrays;

/*
 * You are given two integer arrays of the same length nums1 and nums2. In one operation, you are allowed to swap nums1[i] with nums2[i].

For example, if nums1 = [1,2,3,8], and nums2 = [5,6,7,4], you can swap the element at i = 3 to obtain nums1 = [1,2,3,4] and nums2 = [5,6,7,8].
Return the minimum number of needed operations to make nums1 and nums2 strictly increasing. The test cases are generated so that the given input always makes it possible.

An array arr is strictly increasing if and only if arr[0] < arr[1] < arr[2] < ... < arr[arr.length - 1].
Example 1:

Input: nums1 = [1,3,5,4], nums2 = [1,2,3,7]
Output: 1
Explanation: 
Swap nums1[3] and nums2[3]. Then the sequences are:
nums1 = [1, 3, 5, 7] and nums2 = [1, 2, 3, 4]
which are both strictly increasing.
Example 2:

Input: nums1 = [0,3,5,8,9], nums2 = [2,1,4,6,9]
Output: 1
 */

public class SwapstoMakeSquenceIncreasing {
  public int minSwap(int[] nums1, int[] nums2) {
    int[] temp1 = new int[nums1.length + 1];
    int[] temp2 = new int[nums2.length + 1];
    temp1[0] = -1;
    temp2[0] = -1;
    for (int i = 0; i < nums1.length; i++)
      temp1[i + 1] = nums1[i];
    for (int i = 0; i < nums2.length; i++)
      temp2[i + 1] = nums2[i];
    int[][] dp = new int[nums1.length + 1][2];
    for (int i = 0; i < nums1.length; i++)
      Arrays.fill(dp[i], -1);
    return recursion(1, 0, temp1, temp2, dp);
  }

  int recursion(int i, int isSwapped, int[] nums1, int[] nums2, int[][] dp) {
    if (i == nums1.length)
      return 0;
    if (dp[i][isSwapped] != -1)
      return dp[i][isSwapped];
    int notSwap = Integer.MAX_VALUE, swap = Integer.MAX_VALUE;
    int prev1 = nums1[i - 1], prev2 = nums2[i - 1];
    if (isSwapped == 1) {
      int temp = prev2;
      prev2 = prev1;
      prev1 = temp;
    }
    if (prev1 < nums1[i] && prev2 < nums2[i]) {
      notSwap = recursion(i + 1, 0, nums1, nums2, dp);
    }

    if (prev1 < nums2[i] && prev2 < nums1[i]) {
      swap = 1 + recursion(i + 1, 1, nums1, nums2, dp);
    }
    return dp[i][isSwapped] = Math.min(notSwap, swap);
  }
}
