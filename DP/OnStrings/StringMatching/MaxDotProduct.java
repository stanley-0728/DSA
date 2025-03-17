
import java.util.Arrays;

/*
 * Given two arrays nums1 and nums2.

Return the maximum dot product between non-empty subsequences of nums1 and nums2 with the same length.

A subsequence of a array is a new array which is formed from the original array by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, [2,3,5] is a subsequence of [1,2,3,4,5] while [1,5,3] is not).

Example 1:

Input: nums1 = [2,1,-2,5], nums2 = [3,0,-6]
Output: 18
Explanation: Take subsequence [2,-2] from nums1 and subsequence [3,-6] from nums2.
Their dot product is (2*3 + (-2)*(-6)) = 18.
Example 2:

Input: nums1 = [3,-2], nums2 = [2,-6,7]
Output: 21
Explanation: Take subsequence [3] from nums1 and subsequence [7] from nums2.
Their dot product is (3*7) = 21.
Example 3:

Input: nums1 = [-1,-1], nums2 = [1,1]
Output: -1
Explanation: Take subsequence [-1] from nums1 and subsequence [1] from nums2.
Their dot product is -1.
 
 */
public class MaxDotProduct {
  public int maxDotProduct(int[] nums1, int[] nums2) {
    Integer[][][] dp = new Integer[nums1.length][nums2.length][2];
    return recursion(0, 0, nums1, nums2, 0, dp);

  }

  int recursion(int i, int j, int[] nums1, int[] nums2, boolean picked) {
    if (i == nums1.length || j == nums2.length)
      return picked ? 0 : Integer.MIN_VALUE;

    int pickBoth = nums1[i] * nums2[j] + recursion(i + 1, j + 1, nums1, nums2, true);

    int skipFirst = recursion(i + 1, j, nums1, nums2, picked);

    int skipSecond = recursion(i, j + 1, nums1, nums2, picked);

    return Math.max(pickBoth, Math.max(skipFirst, skipSecond));
  }

  int recursion(int i, int j, int[] nums1, int[] nums2, int picked, Integer[][][] dp) {
    if (i == nums1.length || j == nums2.length)
      return picked == 1 ? 0 : Integer.MIN_VALUE;
    if (dp[i][j][picked] != null)
      return dp[i][j][picked];
    int pickBoth = nums1[i] * nums2[j] + recursion(i + 1, j + 1, nums1, nums2, 1, dp);

    int skipFirst = recursion(i + 1, j, nums1, nums2, picked, dp);

    int skipSecond = recursion(i, j + 1, nums1, nums2, picked, dp);

    return dp[i][j][picked] = Math.max(pickBoth, Math.max(skipFirst, skipSecond));
  }

  public int tabulation(int[] nums1, int[] nums2) {
    int m = nums1.length, n = nums2.length;
    int[][][] dp = new int[m + 1][n + 1][2];

    for (int i = 0; i <= m; i++) {
      for (int picked = 0; picked <= 1; picked++) {
        dp[i][n][picked] = (picked == 1) ? 0 : Integer.MIN_VALUE;
      }
    }
    for (int j = 0; j <= n; j++) {
      for (int picked = 0; picked <= 1; picked++) {
        dp[m][j][picked] = (picked == 1) ? 0 : Integer.MIN_VALUE;
      }
    }

    for (int i = m - 1; i >= 0; i--) {
      for (int j = n - 1; j >= 0; j--) {
        for (int picked = 1; picked >= 0; picked--) {
          int pickBoth = nums1[i] * nums2[j] + dp[i + 1][j + 1][1];
          int skipFirst = dp[i + 1][j][picked];
          int skipSecond = dp[i][j + 1][picked];

          dp[i][j][picked] = Math.max(pickBoth, Math.max(skipFirst, skipSecond));
        }
      }
    }

    return dp[0][0][0];
  }

  public int spaceOptimized(int[] nums1, int[] nums2) {
    int m = nums1.length, n = nums2.length;

    int[][] curr = new int[n + 1][2];
    int[][] next = new int[n + 1][2];
    for (int j = 0; j <= n; j++) {
      next[j] = new int[] { Integer.MIN_VALUE, 0 };
    }

    for (int i = m - 1; i >= 0; i--) {
      curr[n] = new int[] { Integer.MIN_VALUE, 0 };

      for (int j = n - 1; j >= 0; j--) {
        int pickBoth = nums1[i] * nums2[j] + next[j + 1][1];
        int skipFirst = next[j][0];
        int skipSecond = curr[j + 1][0];
        int skipFirst1 = next[j][1];
        int skipSecond1 = curr[j + 1][1];
        curr[j][0] = Math.max(pickBoth, Math.max(skipFirst, skipSecond));
        curr[j][1] = Math.max(pickBoth, Math.max(skipFirst1, skipSecond1));
      }
      int[][] temp = next;
      next = curr;
      curr = temp;
    }

    return next[0][0];
  }
}
