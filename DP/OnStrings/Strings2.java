
import java.net.ContentHandlerFactory;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * A string s is called happy if it satisfies the following conditions:

s only contains the letters 'a', 'b', and 'c'.
s does not contain any of "aaa", "bbb", or "ccc" as a substring.
s contains at most a occurrences of the letter 'a'.
s contains at most b occurrences of the letter 'b'.
s contains at most c occurrences of the letter 'c'.
Given three integers a, b, and c, return the longest possible happy string. If there are multiple longest happy strings, return any of them. If there is no such string, return the empty string "".

A substring is a contiguous sequence of characters within a string.
Example 1:

Input: a = 1, b = 1, c = 7
Output: "ccaccbcc"
Explanation: "ccbccacc" would also be a correct answer.
Example 2:

Input: a = 7, b = 1, c = 0
Output: "aabaa"
Explanation: It is the only correct answer in this case.
 */
public class Strings2 {

  public String longestDiverseString(int a, int b, int c) {
    Queue<AbstractMap.SimpleEntry<Character, Integer>> pq = new PriorityQueue<>(
        (e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()));

    if (a > 0)
      pq.add(new AbstractMap.SimpleEntry<>('a', a));
    if (b > 0)
      pq.add(new AbstractMap.SimpleEntry<>('b', b));
    if (c > 0)
      pq.add(new AbstractMap.SimpleEntry<>('c', c));

    StringBuilder s = new StringBuilder();

    while (!pq.isEmpty()) {
      AbstractMap.SimpleEntry<Character, Integer> curr = pq.poll();
      char currChar = curr.getKey();
      int currVal = curr.getValue();

      if (s.length() >= 2 && s.charAt(s.length() - 1) == s.charAt(s.length() - 2)
          && s.charAt(s.length() - 1) == currChar) {

        if (pq.isEmpty())
          break;

        AbstractMap.SimpleEntry<Character, Integer> next = pq.poll();
        char nextChar = next.getKey();
        int nextVal = next.getValue();

        s.append(nextChar);
        if (--nextVal > 0)
          pq.add(new AbstractMap.SimpleEntry<>(nextChar, nextVal));

        pq.add(new AbstractMap.SimpleEntry<>(currChar, currVal));
      } else {
        s.append(currChar);
        if (--currVal > 0)
          pq.add(new AbstractMap.SimpleEntry<>(currChar, currVal));
      }
    }

    return s.toString();
  }
  /*
   * We define the string base to be the infinite wraparound string of
   * "abcdefghijklmnopqrstuvwxyz", so base will look like this:
   * 
   * "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
   * Given a string s, return the number of unique non-empty substrings of s are
   * present in base.
   * 
   * Example 1:
   * 
   * Input: s = "a"
   * Output: 1
   * Explanation: Only the substring "a" of s is in base.
   * Example 2:
   * 
   * Input: s = "cac"
   * Output: 2
   * Explanation: There are two substrings ("a", "c") of s in base.
   * Example 3:
   * 
   * Input: s = "zab"
   * Output: 6
   * Explanation: There are six substrings ("z", "a", "b", "za", "ab", and "zab")
   * of s in base.
   * 
   */

  public int findSubstringInWraproundString(String s) {
    int[] dp = new int[26];
    int c = 1;
    dp[s.charAt(0) - 'a'] = c;
    for (int i = 1; i < s.length(); i++) {
      if (s.charAt(i) - s.charAt(i - 1) == 1 || (s.charAt(i - 1) == 'z' && s.charAt(i) == 'a')) {
        c++;
      } else {
        c = 1;
      }

      dp[s.charAt(i) - 'a'] = Math.max(dp[s.charAt(i) - 'a'], c);
    }
    int ans = 0;
    for (int val : dp) {
      ans += val;
    }
    return ans;
  }

  /*
   * Given two strings s1 and s2, return the lowest ASCII sum of deleted
   * characters to make two strings equal.
   * 
   * 
   * 
   * Example 1:
   * 
   * Input: s1 = "sea", s2 = "eat"
   * Output: 231
   * Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the
   * sum.
   * Deleting "t" from "eat" adds 116 to the sum.
   * At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum
   * possible to achieve this.
   * Example 2:
   * 
   * Input: s1 = "delete", s2 = "leet"
   * Output: 403
   * Explanation: Deleting "dee" from "delete" to turn the string into "let",
   * adds 100[d] + 101[e] + 101[e] to the sum.
   * Deleting "e" from "leet" adds 101[e] to the sum.
   * At the end, both strings are equal to "let", and the answer is
   * 100+101+101+101 = 403.
   * If instead we turned both strings into "lee" or "eet", we would get answers
   * of 433 or 417, which are higher.
   */

  public int minimumDeleteSum(String s1, String s2) {
    int n = s1.length(), m = s2.length();
    int[][] dp = new int[n + 1][m + 1];

    for (int i = 1; i <= n; i++)
      dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
    for (int j = 1; j <= m; j++)
      dp[0][j] = dp[0][j - 1] + s2.charAt(j - 1);

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.min(
              dp[i - 1][j] + s1.charAt(i - 1),
              dp[i][j - 1] + s2.charAt(j - 1));
        }
      }
    }

    return dp[n][m];
  }

}
