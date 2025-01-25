package Recursion.BackTracking;

public class MathWithMaxGold {
  public static void main(String[] args) {
    int[][] arr = {
        { 0, 6, 0 },
        { 5, 8, 7 },
        { 0, 9, 0 }
    };
    System.out.println(getMaximumGold(arr));

  }

  static int getMaximumGold(int[][] arr) {
    int maxGold = 0;
    boolean[][] visited = new boolean[arr.length][arr[0].length];

    // Start from every cell
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[0].length; j++) {
        if (arr[i][j] != 0) {
          maxGold = Math.max(maxGold, backTrack(i, j, arr, visited));
        }
      }
    }
    return maxGold;
  }

  static int backTrack(int row, int col, int[][] arr, boolean[][] visited) {
    // Check bounds and visited status
    if (row < 0 || col < 0 || row >= arr.length || col >= arr[0].length || arr[row][col] == 0 || visited[row][col]) {
      return 0;
    }

    visited[row][col] = true;

    // Explore all 4 directions
    int left = backTrack(row, col - 1, arr, visited);
    int right = backTrack(row, col + 1, arr, visited);
    int up = backTrack(row - 1, col, arr, visited);
    int down = backTrack(row + 1, col, arr, visited);

    // Calculate the maximum gold from this cell
    int maxGold = arr[row][col] + Math.max(Math.max(left, right), Math.max(up, down));

    visited[row][col] = false;
    return maxGold;
  }

}
