package DP.MCM;

import java.util.Arrays;

/*
 * Given a chain of matrices A1, A2, A3,.....An. Your task is to find out the minimum cost to multiply these matrices. The cost of matrix multiplication is defined as the number of scalar multiplications. A Chain of matrices A1, A2, A3,.....An is represented by a sequence of numbers in an array ‘arr’ where the dimension of 1st matrix is equal to arr[0] * arr[1] , 2nd matrix is arr[1] * arr[2], and so on.

For example:
For arr[ ] = { 10, 20, 30, 40}, matrix A1 = [10 * 20], A2 = [20 * 30], A3 = [30 * 40]

Scalar multiplication of matrix with dimension 10 * 20 is equal to 200.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
2
4
4 5 3 2
4
10 15 20 25
Sample Output 1:
70
8000
Sample Output Explanation 1:
In the first test case, there are three matrices of dimensions A = [4 5], B = [5 3] and C = [3 2]. The most efficient order of multiplication is A * ( B * C).
Cost of ( B * C ) = 5 * 3 * 2 = 30  and (B * C) = [5 2] and A * (B * C) = [ 4 5] * [5 2] = 4 * 5 * 2 = 40. So the overall cost is equal to 30 + 40 =70.

In the second test case, there are two ways to multiply the chain - A1*(A2*A3) or (A1*A2)*A3.

If we multiply in order- A1*(A2*A3), then the number of multiplications required is 11250.

If we multiply in order- (A1*A2)*A3, then the number of multiplications required is 8000.

Thus a minimum number of multiplications required is 8000. 
Sample Input 2:
1
4
1 4 3 2
Sample Output 2:
18
Explanation of Sample Output 2:
In the first test case, there are three matrices of dimensions A = [1 4], B = [4 3] and C = [3 2]. The most efficient order of multiplication is (A *  B) * C .
 */
public class MCM {
  public static int matrixMultiplication(int[] arr, int N) {
    // Write your code here
    int[][] dp = new int[N][N];
    for (int i = 0; i < N; i++)
      Arrays.fill(dp[i], -1);
    // return recursion(1, arr.length - 1, arr);
    return memo(1, arr.length - 1, arr, dp);

  }

  // T.C more than exponential and S.C o(n)
  static int recursion(int i, int j, int[] arr) {
    if (i == j)
      return 0;
    int mini = Integer.MAX_VALUE;
    for (int k = i; k <= Math.min(arr.length, j - 1); k++) {
      int steps = arr[i - 1] * arr[k] * arr[j] + recursion(i, k, arr) + recursion(k + 1, j, arr);
      mini = Math.min(mini, steps);
    }
    return mini;
  }

  // T.C o(n*n) * n and S.C o(n)
  static int memo(int i, int j, int[] arr, int[][] dp) {
    if (i == j)
      return 0;
    int mini = Integer.MAX_VALUE;
    if (dp[i][j] != -1)
      return dp[i][j];
    for (int k = i; k <= Math.min(arr.length, j - 1); k++) {
      int steps = arr[i - 1] * arr[k] * arr[j] + memo(i, k, arr, dp) + memo(k + 1, j, arr, dp);
      mini = Math.min(mini, steps);
    }
    return dp[i][j] = mini;
  }

  // T.C o(n*n) * n and S.C o(n*n)
  static int tabulation(int[] arr, int N) {
    int[][] dp = new int[N][N];
    for (int i = N - 1; i >= 1; i--) {
      for (int j = i + 1; j < N; j++) { // why i+1 because in recursion we thought i is left of j so here if you start
                                        // from 1 to N-1 your assumption will be wrong so in order to maintain the order
                                        // we need j to be first head of i.
        int mini = Integer.MAX_VALUE;
        for (int k = i; k <= Math.min(N, j - 1); k++) {
          int steps = arr[i - 1] * arr[k] * arr[j] + dp[i][k] + dp[k + 1][j];
          mini = Math.min(mini, steps);
        }
        dp[i][j] = mini;
      }
    }
    return dp[1][N - 1];
  }
}