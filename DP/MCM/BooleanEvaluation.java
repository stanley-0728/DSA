package DP.MCM;

import java.util.Arrays;

/*
 * You are given an expression 'exp' in the form of a string where operands will be : (TRUE or FALSE), and operators will be : (AND, OR or XOR).
Now you have to find the number of ways we can parenthesize the expression such that it will evaluate to TRUE.
As the answer can be very large, return the output modulo 1000000007.
Note :

‘T’ will represent the operand TRUE.
‘F’ will represent the operand FALSE.
‘|’ will represent the operator OR.
‘&’ will represent the operator AND.
‘^’ will represent the operator XOR.
Example :

Input: 'exp’ = "T|T & F".

Output: 1

Explanation:
There are total 2  ways to parenthesize this expression:
    (i) (T | T) & (F) = F
    (ii) (T) | (T & F) = T
Out of 2 ways, one will result in True, so we will return 1.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1 :
T^T^F    
Sample Output 1 :
0
Explanation For Sample Input 1:
There are total 2  ways to parenthesize this expression:
(i) (T^T)^(F) = F
(ii) (T)^(T^F) = F
Both ways will result in False, so we will return 0.
Sample Input 2 :
F|T^F
Sample Output 2 :
2
Explanation For Sample Input 2:
For the first test case:
There are total 2  ways to parenthesize this expression:
(i) (F|T)^(F) = T
(ii) (F)|(T^F) = T
Both ways will result in True, so we will return 2.
Expected time complexity
The expected time complexity is O(n ^ 3), where 'n' denotes the length of 'exp'.
Constraints:
3 <= |‘exp’| <= 200
Where |'exp'| denotes the length of 'exp'.

Time Limit: 1 sec
 */
public class BooleanEvaluation {
  static final int mod = 1000000007;

  public static int evaluateExp(String exp) {
    // Write your code here.
    long[][][] dp = new long[exp.length()][exp.length()][2];
    for (int i = 0; i < exp.length(); i++) {
      for (int j = 0; j < exp.length(); j++)
        Arrays.fill(dp[i][j], -1);

    }
    return (int) recursion(0, exp.length() - 1, 1, exp, dp);
  }

  static long recursion(int i, int j, int isTrue, String exp) {
    if (i > j)
      return 0;
    if (i == j) {
      if (isTrue == 1)
        return exp.charAt(i) == 'T' ? 1 : 0;
      else
        return exp.charAt(i) == 'F' ? 1 : 0;
    }
    long ways = 0;

    for (int k = i + 1; k <= j - 1; k += 2) {
      long lt = recursion(i, k - 1, 1, exp);
      long lf = recursion(i, k - 1, 0, exp);
      long rt = recursion(k + 1, j, 1, exp);
      long rf = recursion(k + 1, j, 0, exp);
      if (exp.charAt(k) == '&') {
        if (isTrue == 1)
          ways = (ways + (rt * lt) % mod) % mod;
        else
          ways = (ways + (rf * lt) % mod + (lf * rt) % mod + (lf * rf) % mod) % mod;
      } else if (exp.charAt(k) == '|') {
        if (isTrue == 1)
          ways = (ways + (rf * lt) % mod + (lf * rt) % mod + (rt * lt) % mod) % mod;
        else
          ways = (ways + (lf * rf) % mod) % mod;
      } else {
        if (isTrue == 1)
          ways = (ways + (rf * lt) % mod + (rt * lf) % mod) % mod;
        else
          ways = (ways + (rt * lt) % mod + (rf * lf) % mod) % mod;
      }
    }
    return ways;
  }

  static long recursion(int i, int j, int isTrue, String exp, long[][][] dp) {
    if (i > j)
      return 0;
    if (i == j) {
      if (isTrue == 1)
        return exp.charAt(i) == 'T' ? 1 : 0;
      else
        return exp.charAt(i) == 'F' ? 1 : 0;
    }
    if (dp[i][j][isTrue] != -1)
      return dp[i][j][isTrue];
    long ways = 0;

    for (int k = i + 1; k <= j - 1; k += 2) {
      long lt = recursion(i, k - 1, 1, exp, dp);
      long lf = recursion(i, k - 1, 0, exp, dp);
      long rt = recursion(k + 1, j, 1, exp, dp);
      long rf = recursion(k + 1, j, 0, exp, dp);
      if (exp.charAt(k) == '&') {
        if (isTrue == 1)
          ways = (ways + (rt * lt) % mod) % mod;
        else
          ways = (ways + (rf * lt) % mod + (lf * rt) % mod + (lf * rf) % mod) % mod;
      } else if (exp.charAt(k) == '|') {
        if (isTrue == 1)
          ways = (ways + (rf * lt) % mod + (lf * rt) % mod + (rt * lt) % mod) % mod;
        else
          ways = (ways + (lf * rf) % mod) % mod;
      } else {
        if (isTrue == 1)
          ways = (ways + (rf * lt) % mod + (rt * lf) % mod) % mod;
        else
          ways = (ways + (rt * lt) % mod + (rf * lf) % mod) % mod;
      }
    }
    return dp[i][j][isTrue] = ways;
  }
}
