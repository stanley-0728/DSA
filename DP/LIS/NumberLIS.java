package DP.LIS;

import java.util.Arrays;

/*
 * Given an integer array nums, return the number of longest increasing subsequences.
Notice that the sequence has to be strictly increasing.
Example 1:

Input: nums = [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].
Example 2:

Input: nums = [2,2,2,2,2]
Output: 5
Explanation: The length of the longest increasing subsequence is 1, and there are 5 increasing subsequences of length 1, so output 5.
Constraints:
1 <= nums.length <= 2000
-106 <= nums[i] <= 106
The answer is guaranteed to fit inside a 32-bit integer.
 */
public class NumberLIS {

  int tabulation1(int[] nums) {
    int dp[] = new int[nums.length];
    int count[] = new int[nums.length];

    Arrays.fill(dp, 1);
    Arrays.fill(count, 1);
    int maxi = 0;
    for (int i = 1; i < nums.length; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j] && dp[j] + 1 > dp[i]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
          count[i] = count[j];
        } else if (nums[i] > nums[j] && dp[j] + 1 == dp[i]) {
          count[i]++;
        }
      }
      maxi = Math.max(maxi, dp[i]);

    }
    int ans = 0;
    for (int i = 0; i < dp.length; i++) {
      if (dp[i] == maxi)
        ans += count[i];
    }
    return ans;

  }
}
