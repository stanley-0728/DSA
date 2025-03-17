package DP.FrontPartition;

import java.util.Arrays;

/*
 * Determine if a given string ‘S’ is a palindrome using recursion. Return a Boolean value of true if it is a palindrome and false if it is not.
Note: You are not required to print anything, just implement the function. Example:
Input: s = "racecar"
Output: true
Explanation: "racecar" is a palindrome.
 */
public class CheckPalindrome {
  public static boolean isPalindrome(String s) {
    int i = 0, j = s.length() - 1;
    return recursion(i, j, s);
  }

  static boolean recursion(int i, int j, String s) {
    if (i > j)
      return true;
    if (i == j)
      return true;
    boolean f = false;
    if (s.charAt(i) == s.charAt(j)) {
      f = f | recursion(i + 1, j - 1, s);
    }
    return f;
  }
  /*
   * determine if a given substring of a string is a palindrome. A palindrome is a
   * sequence of characters that reads the same forward and backward.
   * public boolean isPalindromeRecursive(String s, int i, int j)
   * Input:
   * A string s consisting of lowercase English letters (1 ≤ s.length() ≤ 1000).
   * Two integers i and j representing the starting and ending indices of the
   * substring to check.
   * Output:
   * Return true if the substring s[i:j] is a palindrome, otherwise return false.
   * Constraints:
   * A single character is always a palindrome (i == j).
   * A pair of characters is a palindrome if both characters are equal (s[i] ==
   * s[j] and j == i + 1).
   * For longer substrings, the first and last character must be equal and the
   * inner substring must also be a palindrome.
   * Examples:
   * Example 1
   * Input:
   * 
   * java
   * Copy
   * Edit
   * s = "ababa"
   * i = 0, j = 4
   * Output:
   * 
   * java
   * Copy
   * Edit
   * true
   * Explanation: "ababa" reads the same forward and backward.
   * 
   * Example 2
   * Input:
   * 
   * java
   * Copy
   * Edit
   * s = "racecar"
   * i = 1, j = 5
   * Output:
   * 
   * java
   * Copy
   * Edit
   * true
   * Explanation: The substring "aceca" is a palindrome.
   * 
   * Example 3
   * Input:
   * 
   * java
   * Copy
   * Edit
   * s = "abcdef"
   * i = 1, j = 4
   * Output:
   * 
   * java
   * Copy
   * Edit
   * false
   * Explanation: The substring "bcde" is not a palindrome.
   * 
   * 
   */

  static boolean recursion1(int i, int j, String s) {
    if (i == j)
      return true;
    if (i + 1 == j)
      return s.charAt(i + 1) == s.charAt(j);
    boolean f = false;
    if (s.charAt(i) == s.charAt(j)) {
      f = f | recursion1(i + 1, j - 1, s);
    }
    return f;
  }

  static boolean memo(int i, int j, String s, int[][] dp) {
    if (i == j)
      return true;
    if (i + 1 == j)
      return s.charAt(i) == s.charAt(j);
    if (dp[i][j] != -1)
      return dp[i][j] == 1;
    boolean f = false;
    if (s.charAt(i) == s.charAt(j)) {
      f = f | memo(i + 1, j - 1, s, dp);
    }
    dp[i][j] = f == true ? 1 : 0;
    return dp[i][j] == 1;
  }

  static boolean tabulation(int start, int end, String s) {
    boolean[][] dp = new boolean[s.length()][s.length()];
    for (int i = 0; i < s.length(); i++)
      dp[i][i] = true;
    for (int i = s.length() - 1; i >= 0; i--) {
      for (int j = i + 1; j < s.length(); j++) {
        if (j - i == 1) {
          dp[i][j] = s.charAt(i) == s.charAt(j);
        } else {
          dp[i][j] = (s.charAt(i) == s.charAt(j)) && dp[i + 1][j - 1];
        }
      }
    }
    return dp[start][end];
  }

  /*
   * Given a string s, return the number of palindromic substrings in it.
   * 
   * A string is a palindrome when it reads the same backward as forward.
   * 
   * A substring is a contiguous sequence of characters within the string.
   * 
   * 
   * 
   * Example 1:
   * 
   * Input: s = "abc"
   * Output: 3
   * Explanation: Three palindromic strings: "a", "b", "c".
   * Example 2:
   * 
   * Input: s = "aaa"
   * Output: 6
   * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
   */
  public int countSubstrings(String s) {
    int n = s.length();
    int[][] dp = new int[n][n]; // DP table for memoization
    for (int[] row : dp)
      Arrays.fill(row, -1); // Initialize DP with -1 (uncomputed state)

    return countPalindromes(0, s, dp);
  }

  private int countPalindromes(int start, String s, int[][] dp) {
    if (start == s.length())
      return 0; // Base case: End of string

    int count = 0;
    for (int end = start; end < s.length(); end++) {
      if (isPalindrome(start, end, s, dp)) {
        count++; // If it's a palindrome, count it
      }
    }

    return dp[start][s.length() - 1] = count + countPalindromes(start + 1, s, dp);
  }

  private boolean isPalindrome(int i, int j, String s, int[][] dp) {
    if (i >= j)
      return true; // Base case: Single character or empty substring

    if (dp[i][j] != -1)
      return dp[i][j] == 1;

    if (s.charAt(i) == s.charAt(j)) {
      dp[i][j] = isPalindrome(i + 1, j - 1, s, dp) ? 1 : 0;
    } else {
      dp[i][j] = 0;
    }

    return dp[i][j] == 1;
  }

  public int tabulation(String s) {
    int n = s.length();
    boolean[] ahead = new boolean[n];
    boolean[] curr = new boolean[n];

    int count = 0;

    // Start from the last index and move backward
    for (int i = n - 1; i >= 0; i--) {
      for (int j = i; j < n; j++) {
        if (s.charAt(i) == s.charAt(j)) {
          if (j - i <= 1) { // Single character or two-character substrings
            curr[j] = true;
          } else {
            curr[j] = ahead[j - 1]; // Expand from smaller substrings
          }
        }

        if (curr[j])
          count++;
      }
      ahead = curr.clone();
    }

    return count;
  }

  public String longestPalindrome(String s) {
    int n = s.length();
    int[][] dp = new int[n][n]; // DP table for memoization
    for (int[] row : dp)
      Arrays.fill(row, -1); // Initialize DP with -1 (uncomputed state)

    return countPalindromes2(0, s, dp);
  }

  private String countPalindromes2(int start, String s, int[][] dp) {
    if (start == s.length())
      return ""; // Base case: End of string

    int maxi = Integer.MIN_VALUE;
    int index = 0;
    for (int end = start; end < s.length(); end++) {
      if (isPalindrome(start, end, s, dp)) {
        if (end - start + 1 > maxi) {
          index = start;
          maxi = end - start + 1;
        }
      }
    }
    String temp = s.substring(index, index + maxi);
    String res = countPalindromes2(start + 1, s, dp);
    if (temp.length() > res.length())
      return temp;
    return res;
  }
}
