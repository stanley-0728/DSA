
public class LPS {
  public int longestPalindromeSubseq(String s) {
    StringBuffer s2 = new StringBuffer();
    for (int i = s.length() - 1; i >= 0; i--)
      s2.append(s.charAt(i));
    return recursion(s.length(), s2.length(), s, s2.toString());
  }

  static int recursion(int index1, int index2, String s, String t) {
    if (index1 == 0 || index2 == 0) // we need to start from f(i,j) because we cannot store -1 in dp array
      return 0;

    int ans = 0;
    if (s.charAt(index1 - 1) == t.charAt(index2 - 1))
      ans = 1 + recursion(index1 - 1, index2 - 1, s, t);
    else {
      ans = Math.max(recursion(index1 - 1, index2, s, t), recursion(index1, index2 - 1, s, t));
    }
    return ans;
  }

  static int memo(int index1, int index2, String s, String t, int[][] dp) {
    if (index1 == 0 || index2 == 0)
      return 0;
    if (dp[index1][index2] != -1)
      return dp[index1][index2];
    int ans = 0;
    if (s.charAt(index1 - 1) == t.charAt(index2 - 1))
      ans = 1 + memo(index1 - 1, index2 - 1, s, t, dp);
    else {
      ans = Math.max(memo(index1 - 1, index2, s, t, dp), memo(index1, index2 - 1, s, t, dp));
    }
    return dp[index1][index2] = ans;
  }

  public static int[][] tabulation(String s, String t) {
    int[][] dp = new int[s.length() + 1][t.length() + 1];

    for (int i = 1; i <= s.length(); i++) {
      for (int j = 1; j <= t.length(); j++) {
        if (s.charAt(i - 1) == t.charAt(j - 1))
          dp[i][j] = 1 + dp[i - 1][j - 1];
        else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }
    return dp;
  }

  static int spaceOptimized(String s, String t) {
    int[] prev = new int[t.length() + 1];
    int[] curr = new int[t.length() + 1];

    for (int i = 1; i <= s.length(); i++) {
      for (int j = 1; j <= t.length(); j++) {
        if (s.charAt(i - 1) == t.charAt(j - 1))
          curr[j] = 1 + prev[j - 1];
        else {
          curr[j] = Math.max(prev[j], curr[j - 1]);
        }
      }
      prev = curr.clone();
    }
    return prev[t.length()];
  }

  /*
   * Given a string s. In one step you can insert any character at any index of
   * the string.
   * 
   * Return the minimum number of steps to make s palindrome.
   * 
   * A Palindrome String is one that reads the same backward as well as forward.
   * 
   * 
   * 
   * Example 1:
   * 
   * Input: s = "zzazz"
   * Output: 0
   * Explanation: The string "zzazz" is already palindrome we do not need any
   * insertions.
   * Example 2:
   * 
   * Input: s = "mbadm"
   * Output: 2
   * Explanation: String can be "mbdadbm" or "mdbabdm".
   * Example 3:
   * 
   * Input: s = "leetcode"
   * Output: 5
   * Explanation: Inserting 5 characters the string becomes "leetcodocteel".
   * 
   */
  public int minInsertions(String s) {
    StringBuffer s2 = new StringBuffer();
    for (int i = s.length() - 1; i >= 0; i--)
      s2.append(s.charAt(i));
    int l = spaceOptimized(s, s2.toString());
    return s.length() - l;
  }

  /*
   * Problem statement
   * You are given 2 non-empty strings 's1' and 's2' consisting of lowercase
   * English alphabets only.
   * In one operation you can do either of the following on 's1':
   * 
   * (1) Remove a character from any position in 's1'.
   * 
   * (2) Add a character to any position in 's1'.
   * Find the minimum number of operations required to convert string 's1' into
   * 's2'.
   * Example:
   * Input: 's1' = "abcd", 's2' = "anc"
   * 
   * Output: 3
   * 
   * Explanation:
   * Here, 's1' = "abcd", 's2' = "anc".
   * In one operation remove 's1[3]', after this operation 's1' becomes "abc".
   * In the second operation remove 's1[1]', after this operation 's1' becomes
   * "ac".
   * In the third operation add 'n' in 's1[1]', after this operation 's1' becomes
   * "anc".
   * 
   * Hence, the minimum operations required will be 3. It can be shown that
   * there's no way to convert s1 into s2 in less than 3 moves.
   * Detailed explanation ( Input/output format, Notes, Images )
   * Sample Input 1 :
   * aaa
   * aa
   * 
   * Expected Answer :
   * 1
   * Output on console :
   * 1
   * 
   * Explanation For Sample Output 1:
   * For this test case,
   * 's1' = "aaa", 's2' = "aa"
   * In one operation remove 's1[2]', after this operation 's1' becomes "aa".
   * 
   * Hence, the output will be 1.
   */

  public static int canYouMake(String s1, String s2) {
    // Write your code here.
    int l = spaceOptimized(s1, s2);
    return (s1.length() - l) + (s2.length() - l);
  }

}
