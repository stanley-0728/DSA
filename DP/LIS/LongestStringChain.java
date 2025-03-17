package DP.LIS;

import java.util.Arrays;

/*
 * You are given an array of words where each word consists of lowercase English letters.

wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA without changing the order of the other characters to make it equal to wordB.

For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1, where word1 is a predecessor of word2, word2 is a predecessor of word3, and so on. A single word is trivially a word chain with k == 1.

Return the length of the longest possible word chain with words chosen from the given list of words.

Example 1:

Input: words = ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: One of the longest word chains is ["a","ba","bda","bdca"].
Example 2:

Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
Output: 5
Explanation: All the words can be put in a word chain ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].
Example 3:

Input: words = ["abcd","dbqca"]
Output: 1
Explanation: The trivial word chain ["abcd"] is one of the longest word chains.
["abcd","dbqca"] is not a valid word chain because the ordering of the letters is changed.
 
 */
public class LongestStringChain {
  public int longestStrChain(String[] nums) {
    Arrays.sort(nums, (a, b) -> a.length() - b.length());
    int dp[] = new int[nums.length];
    Arrays.fill(dp, 1);
    int maxi = Integer.MIN_VALUE;
    for (int i = 1; i < nums.length; i++) {
      for (int j = 0; j < i; j++) {
        if (checkPossible(nums[i], nums[j]) && dp[j] + 1 > dp[i]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
      maxi = Math.max(maxi, dp[i]);

    }
    return maxi;
  }

  boolean checkPossible(String a, String b) {
    if (a.length() != b.length() + 1)
      return false;
    int f = 0, s = 0;
    while (f < a.length()) {
      if (s < b.length() && a.charAt(f) == b.charAt(s)) {
        f++;
        s++;
      } else {
        f++;
      }
    }
    if (f == a.length() && s == b.length())
      return true;
    return false;
  }
}
