package Graph;

import java.util.*;

public class FloodFill {
  // S.C o(m*n) T.C o(m*n)
  void bfs(int[][] nums, int[][] grid, int r, int c, int color, int[][] visited) {
    Queue<AbstractMap.SimpleEntry<Integer, Integer>> queue = new LinkedList<>();
    visited[r][c] = 1;
    queue.add(new AbstractMap.SimpleEntry<Integer, Integer>(r, c));
    while (!queue.isEmpty()) {
      AbstractMap.SimpleEntry<Integer, Integer> top = queue.poll();
      nums[top.getKey()][top.getValue()] = color;
      int[] dRow = { 0, 1, 0, -1 };
      int[] dCol = { 1, 0, -1, 0 };
      for (int i = 0; i < 4; i++) {
        int newRow = top.getKey() + dRow[i];
        int newCol = top.getValue() + dCol[i];
        if (newRow >= 0 && newCol >= 0 && newRow < grid.length && newCol < grid[0].length
            && visited[newRow][newCol] == -1 && grid[newRow][newCol] == grid[r][c]) {
          visited[newRow][newCol] = 1;
          queue.add(new AbstractMap.SimpleEntry<>(newRow, newCol));
        }
      }
    }
  }

  public int[][] floodFill(int[][] image, int sr, int sc, int color) {
    int[][] visited = new int[image.length][image[0].length];
    int[][] nums = new int[image.length][image[0].length];
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        nums[i][j] = image[i][j];
      }
    }
    for (int i = 0; i < image.length; i++)
      Arrays.fill(visited[i], -1);
    bfs(nums, image, sr, sc, color, visited);
    return nums;

  }
}
