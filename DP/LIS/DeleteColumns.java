package DP.LIS;

import java.util.Arrays;

public class DeleteColumns {
  public int minDeletionSize(String[] s) {
    int c = s[0].length();
    int count = 0;
    for (int i = 0; i < c; i++) {
      for (int j = 1; j < s.length; j++) {
        if (((int) s[j].charAt(i) - 'a') < ((int) s[j - 1].charAt(i) - 'a')) {
          count++;
          break;
        }
      }
    }
    return count;
  }

  public int minDeletionSize2(String[] strs) {
    int rows = strs.length;
    int cols = strs[0].length();
    int deletions = 0;

    boolean[] sorted = new boolean[rows - 1];

    for (int col = 0; col < cols; col++) {
      boolean needToDelete = false;

      for (int row = 0; row < rows - 1; row++) {
        if (!sorted[row] && strs[row].charAt(col) > strs[row + 1].charAt(col)) {
          needToDelete = true;
          break;
        }
      }

      if (needToDelete) {
        deletions++;
      } else {
        for (int row = 0; row < rows - 1; row++) {
          if (strs[row].charAt(col) < strs[row + 1].charAt(col)) {
            sorted[row] = true;
          }
        }
      }
    }

    return deletions;
  }

  /*
   * You are given an array of n strings strs, all of the same length.
   * 
   * We may choose any deletion indices, and we delete all the characters in those
   * indices for each string.
   * 
   * For example, if we have strs = ["abcdef","uvwxyz"] and deletion indices {0,
   * 2, 3}, then the final array after deletions is ["bef", "vyz"].
   * 
   * Suppose we chose a set of deletion indices answer such that after deletions,
   * the final array has every string (row) in lexicographic order. (i.e.,
   * (strs[0][0] <= strs[0][1] <= ... <= strs[0][strs[0].length - 1]), and
   * (strs[1][0] <= strs[1][1] <= ... <= strs[1][strs[1].length - 1]), and so on).
   * Return the minimum possible value of answer.length.
   * 
   * Example 1:
   * 
   * Input: strs = ["babca","bbazb"]
   * Output: 3
   * Explanation: After deleting columns 0, 1, and 4, the final array is strs =
   * ["bc", "az"].
   * Both these rows are individually in lexicographic order (ie. strs[0][0] <=
   * strs[0][1] and strs[1][0] <= strs[1][1]).
   * Note that strs[0] > strs[1] - the array strs is not necessarily in
   * lexicographic order.
   * Example 2:
   * 
   * Input: strs = ["edcba"]
   * Output: 4
   * Explanation: If we delete less than 4 columns, the only row will not be
   * lexicographically sorted.
   * Example 3:
   * 
   * Input: strs = ["ghi","def","abc"]
   * Output: 0
   * Explanation: All rows are already lexicographically sorted.
   */

  public int minDeletionSize3(String[] strs) {
    int m = strs[0].length();
    int[] dp = new int[m];
    Arrays.fill(dp, 1);
    int maxLIS = 1;

    for (int i = 1; i < m; i++) {
      for (int j = 0; j < i; j++) {
        if (isIncreasingColumn(strs, j, i) && dp[j] + 1 > dp[i]) {
          dp[i] = dp[j] + 1;
        }
      }
      maxLIS = Math.max(maxLIS, dp[i]);
    }

    return m - maxLIS;
  }

  private boolean isIncreasingColumn(String[] strs, int col1, int col2) {
    for (String str : strs) {
      if (str.charAt(col1) > str.charAt(col2)) {
        return false;
      }
    }
    return true;
  }

}
