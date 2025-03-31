package Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Pair {
  int row;
  int col;
  int time;

  Pair(int row, int col, int time) {
    this.row = row;
    this.col = col;
    this.time = time;
  }

  int getRow() {
    return row;
  }

  int getCol() {
    return col;
  }

  int getTime() {
    return time;
  }
}

public class RottenOranges {
  int bfs(int[][] nums, int[][] grid, int[][] visited) {
    Queue<Pair> queue = new LinkedList<>();
    int rows = nums.length;
    int cols = nums[0].length;
    int freshOranges = 0;
    int timeElapsed = 0;

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid[i][j] == 2) {
          visited[i][j] = 1;
          queue.add(new Pair(i, j, 0));
        } else if (grid[i][j] == 1) {
          freshOranges++;
        }
      }
    }
    int[] dRow = { 0, 1, 0, -1 };
    int[] dCol = { 1, 0, -1, 0 };

    while (!queue.isEmpty()) {
      Pair current = queue.poll();
      int row = current.row, col = current.col, time = current.time;
      timeElapsed = Math.max(timeElapsed, time);

      for (int i = 0; i < 4; i++) {
        int newRow = row + dRow[i];
        int newCol = col + dCol[i];

        if (newRow >= 0 && newCol >= 0 && newRow < rows && newCol < cols
            && grid[newRow][newCol] == 1 && visited[newRow][newCol] == -1) {
          nums[newRow][newCol] = 2;
          visited[newRow][newCol] = 1;
          freshOranges--;
          queue.add(new Pair(newRow, newCol, time + 1));
        }
      }
    }
    return freshOranges == 0 ? timeElapsed : -1;

  }

  // T.C o(m*n) S.c o(m*n)
  public int orangesRotting(int[][] image) {
    int[][] visited = new int[image.length][image[0].length];
    int[][] nums = new int[image.length][image[0].length];
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        nums[i][j] = image[i][j];
      }
    }
    for (int i = 0; i < image.length; i++)
      Arrays.fill(visited[i], -1);
    return bfs(nums, image, visited);
  }
}
