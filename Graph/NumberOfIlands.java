package Graph;

import java.util.Arrays;

public class NumberOfIlands {
  void dfs(int i, int j, char[][] adj, int[][] visited) {
    visited[i][j] = 1;
    int[] dRow = { 0, 1, 0, -1 };
    int[] dCol = { 1, 0, -1, 0 };
    for (int k = 0; k < 4; k++) {
      int newRow = i + dRow[k];
      int newCol = j + dCol[k];
      if (newRow >= 0 && newCol >= 0 && newRow < adj.length && newCol < adj[0].length && adj[newRow][newCol] == '1'
          && visited[newRow][newCol] == -1) {
        dfs(newRow, newCol, adj, visited);
      }

    }
  }

  public int numIslands(char[][] grid) {
    int[][] visited = new int[grid.length][grid[0].length];
    for (int i = 0; i < visited.length; i++)
      Arrays.fill(visited[i], -1);
    int count = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == '1' && visited[i][j] == -1) {
          count++;
          dfs(i, j, grid, visited);
        }
      }
    }
    return count;
  }
}
