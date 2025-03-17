package DP.LIS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
Example 1:

Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
Example 2:

Input: nums = [0,1,0,3,2,3]
Output: 4
Example 3:

Input: nums = [7,7,7,7,7,7,7]
Output: 1
 */
public class LIS {
  public int lengthOfLIS(int[] nums) {
    int[][] dp = new int[nums.length][nums.length + 1];
    for (int i = 0; i < nums.length; i++)
      Arrays.fill(dp[i], -1);
    return memo(0, -1, nums, dp);
  }

  // T.C expoential and S.C o(n)
  int recursion(int index, int prev_index, int[] nums) {
    if (index == nums.length)
      return 0;
    int len = recursion(index + 1, prev_index, nums);
    if (prev_index == -1 || nums[index] > nums[prev_index]) {
      len = Math.max(len, 1 + recursion(index + 1, index, nums));
    }
    return len;
  }

  // T.C o(n*n) and S.C o(n*n) + o(n)
  int memo(int index, int prev_index, int[] nums, int[][] dp) {
    if (index == nums.length)
      return 0;
    if (dp[index][prev_index + 1] != -1)
      return dp[index][prev_index + 1];
    int len = memo(index + 1, prev_index, nums, dp);
    if (prev_index == -1 || nums[index] > nums[prev_index]) {
      len = Math.max(len, 1 + memo(index + 1, index, nums, dp));
    }
    return dp[index][prev_index + 1] = len;
  }

  // T.C o(n*N)and S.C o(n*n)
  int tabulation(int[] nums) {
    int[][] dp = new int[nums.length + 1][nums.length + 1];
    for (int i = nums.length - 1; i >= 0; i--) {
      for (int prev_index = i - 1; prev_index >= -1; prev_index--) {
        int len = dp[i + 1][prev_index + 1];
        if (prev_index == -1 || nums[i] > nums[prev_index]) {
          len = Math.max(len, 1 + dp[i + 1][i + 1]);
        }
        dp[i][prev_index + 1] = len;
      }
    }
    return dp[0][0];
  }

  // T.C o(n*N)and S.C o(n)
  int spaceOptimized(int[] nums) {
    int[] next = new int[nums.length + 1];
    int[] curr = new int[nums.length + 1];
    for (int i = nums.length - 1; i >= 0; i--) {
      for (int prev_index = i - 1; prev_index >= -1; prev_index--) {
        int len = next[prev_index + 1];
        if (prev_index == -1 || nums[i] > nums[prev_index]) {
          len = Math.max(len, 1 + next[i + 1]);
        }
        curr[prev_index + 1] = len;
      }
      next = curr.clone();
    }
    return next[0];
  }

  // we can do in other way the tabulation part
  /*
   * 5 4 11 1 16 8 these are elements and dp[i] tells longest lis that ends at
   * index i
   * dp 1 1 1 1 1 1 Intially everyone is of length 1.
   * 1 1 2 1 3 2 so what i can see here for 11 i can see 5,11 and 4,11 can form
   * lis of length 2 and for 16 4,11,16 or 5,11,16 max length is 3
   * Now you how do you calculate this
   */
  // T.C o(n*n) and S.C o(n) This is required to print LIS
  int tabulation1(int[] nums) {
    int dp[] = new int[nums.length];
    Arrays.fill(dp, 1);
    int maxi = Integer.MIN_VALUE;
    for (int i = 1; i < nums.length; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j] && dp[j] + 1 > dp[i]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
      maxi = Math.max(maxi, dp[i]);

    }
    return maxi;
  }

  int printLIS(int[] nums) {
    int dp[] = new int[nums.length];
    Arrays.fill(dp, 1);
    int maxi = Integer.MIN_VALUE;
    int[] hash = new int[nums.length];
    int lastIndex = 0;
    for (int i = 0; i < nums.length; i++)
      hash[i] = i;
    for (int i = 1; i < nums.length; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
          hash[i] = j;
        }
      }
      if (dp[i] > maxi) {
        lastIndex = i;
        maxi = dp[i];
      }
    }
    List<Integer> ans = new ArrayList<>();
    ans.add(nums[lastIndex]);
    while (hash[lastIndex] != lastIndex) {
      lastIndex = hash[lastIndex];
      ans.add(nums[lastIndex]);
    }
    System.out.println(ans);
    return maxi;
  }

  // For all the above approaches we can see the best T.C is o(n*n) and if
  // constraints are 10^5 and the T.c o(10^10) which will give tle so we need
  // somewhere nlogn sol i think we can use binary search.

  public static int tabulation2(int[] nums) {
    List<Integer> list = new ArrayList<>();
    list.add(nums[0]);

    for (int i = 1; i < nums.length; i++) {
      if (nums[i] > list.get(list.size() - 1)) {
        list.add(nums[i]); // Extend LIS
      } else {
        int index = Collections.binarySearch(list, nums[i]);
        if (index < 0) {
          index = -(index + 1); // Get the first greater element's position
        }
        list.set(index, nums[i]); // Replace the element at `index`
      }
    }
    System.out.println(list);
    return list.size();
  }

  /*
   * Given a set of distinct positive integers nums, return the largest subset
   * answer such that every pair (answer[i], answer[j]) of elements in this subset
   * satisfies:
   * 
   * answer[i] % answer[j] == 0, or
   * answer[j] % answer[i] == 0
   * If there are multiple solutions, return any of them.
   * 
   * Example 1:
   * 
   * Input: nums = [1,2,3]
   * Output: [1,2]
   * Explanation: [1,3] is also accepted.
   * Example 2:
   * 
   * Input: nums = [1,2,4,8]
   * Output: [1,2,4,8]
   * 
   */
  public List<Integer> largestDivisibleSubset(int[] nums) {
    int dp[] = new int[nums.length];
    Arrays.sort(nums);

    Arrays.fill(dp, 1);
    int maxi = Integer.MIN_VALUE;
    int[] hash = new int[nums.length];
    int lastIndex = 0;
    for (int i = 0; i < nums.length; i++)
      hash[i] = i;
    for (int i = 1; i < nums.length; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]) {
          dp[i] = dp[j] + 1;
          hash[i] = j;
        }
      }
      if (dp[i] > maxi) {
        lastIndex = i;
        maxi = dp[i];

      }
    }
    List<Integer> ans = new ArrayList<>();
    ans.add(nums[lastIndex]);
    while (hash[lastIndex] != lastIndex) {
      lastIndex = hash[lastIndex];
      ans.add(nums[lastIndex]);
    }
    return ans;
  }

  /*
   * Given an integer array arr and an integer difference, return the length of
   * the longest subsequence in arr which is an arithmetic sequence such that the
   * difference between adjacent elements in the subsequence equals difference.
   * 
   * A subsequence is a sequence that can be derived from arr by deleting some or
   * no elements without changing the order of the remaining elements.
   * 
   * 
   * 
   * Example 1:
   * 
   * Input: arr = [1,2,3,4], difference = 1
   * Output: 4
   * Explanation: The longest arithmetic subsequence is [1,2,3,4].
   * Example 2:
   * 
   * Input: arr = [1,3,5,7], difference = 1
   * Output: 1
   * Explanation: The longest arithmetic subsequence is any single element.
   * Example 3:
   * 
   * Input: arr = [1,5,7,8,5,3,4,2,1], difference = -2
   * Output: 4
   * Explanation: The longest arithmetic subsequence is [7,5,3,1].
   */
  public int longestSubsequence(int[] nums, int difference) {
    int dp[] = new int[nums.length];
    Arrays.fill(dp, 1);
    Map<Integer, Integer> map = new HashMap<>();
    map.put(nums[0], 0);
    int maxi = Integer.MIN_VALUE;
    for (int i = 1; i < nums.length; i++) {
      if (map.containsKey(nums[i] - difference) && dp[map.get(nums[i] - difference)] + 1 > dp[i]) {
        dp[i] = Math.max(dp[i], dp[map.get(nums[i] - difference)] + 1);
      }
      map.put(nums[i], i);
      maxi = Math.max(maxi, dp[i]);
    }
    return maxi;
  }
}
