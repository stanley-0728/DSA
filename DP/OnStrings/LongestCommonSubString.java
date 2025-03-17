
/*
 * You are given two strings, 'str1' and 'str2'. You have to find the length of the longest common substring.
A substring is a continuous segment of a string. For example, "bcd" is a substring of "abcd", while "acd" or "cda" are not.
Example:
Input: ‘str1’ = “abcjklp” , ‘str2’ = “acjkp”.

Output: 3

Explanation:  The longest common substring between ‘str1’ and ‘str2’ is “cjk”, of length 3.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
wasdijkl
wsdjkl
Sample Output 1:
 3
Explanation Of Sample Input 1 :
 The longest common substring is “jkl”, of length 3.
Sample Input 2:
tyfg
cvbnuty
 */
public class LongestCommonSubString {
  // We just followed LCS tabulation method as it easy we can also do with
  // recursion but we need require third variable
  public static int lcs(String s, String t) {
    // te your code here.
    int[][] dp = new int[s.length() + 1][t.length() + 1];
    int maxi = Integer.MIN_VALUE;
    for (int i = 1; i <= s.length(); i++) {
      for (int j = 1; j <= t.length(); j++) {
        if (s.charAt(i - 1) == t.charAt(j - 1))
          dp[i][j] = 1 + dp[i - 1][j - 1];
        maxi = Math.max(maxi, dp[i][j]);
      }
    }
    return maxi;
  }

}
