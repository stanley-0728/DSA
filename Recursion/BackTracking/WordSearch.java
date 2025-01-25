package Recursion.BackTracking;

import java.util.ArrayList;
import java.util.List;

public class WordSearch {
  public static void main(String[] args) {
    /*
     * Given an m x n grid of characters board and a string word, return true if
     * word exists in the grid.
     * The word can be constructed from letters of sequentially adjacent cells,
     * where adjacent cells are horizontally or vertically neighboring. The same
     * letter cell may not be used more than once.
     * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word
     * = "ABCCED"
     * Output: true
     */
    char[][] word = {
        // {'A','B','C','E'},
        // {'S','F','C','S'},
        // {'A','D','E','E'}
        { 'o', 'a', 'a', 'n' },
        { 'e', 't', 'a', 'e' },
        { 'i', 'h', 'k', 'r' },
        { 'i', 'f', 'l', 'v' }
    };
    String[] s = { "oath", "pea", "eat", "rain" };
    // System.out.println(wordSearch(word, s));
    System.out.println(wordSearch2(word, s));
  }

  static boolean wordSearch(char[][] arr, String target) {
    boolean[][] visited = new boolean[arr.length][arr[0].length];

    // Start from every cell
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[0].length; j++) {
        boolean res = backTrack(i, j, arr, visited, target);
        if (res)
          return true;
        else
          continue;

      }
    }
    return false;
  }
// This is will give TLE Need to optimize the search we need to use Trie
  static List<String> wordSearch2(char[][] arr, String[] target) {
    boolean[][] visited = new boolean[arr.length][arr[0].length];
    List<String> ans = new ArrayList<>();
    // Start from every cell
    for (int k = 0; k < target.length; k++) {
      boolean f = false;
      for (int i = 0; i < arr.length; i++) {
        for (int j = 0; j < arr[0].length; j++) {
          boolean res = backTrack(i, j, arr, visited, target[k]);
          if (res) {
            ans.add(target[k]);
            f = true;
            break;
          } else
            continue;
        }
      }
    }
    return ans;
  }

  static boolean backTrack(int row, int col, char[][] arr, boolean[][] visited, String target) {
    // Check bounds and visited status
    if (target.isEmpty()) {
      return true;
    }
    if (row < 0 || col < 0 || row >= arr.length || col >= arr[0].length || arr[row][col] == 0 || visited[row][col]) {
      return false;
    }
    if (!target.isEmpty() && target.charAt(0) != arr[row][col])
      return false;

    System.out.println(row + " " + col);
    System.out.println(target);

    visited[row][col] = true;
    String up = target.length() > 1 ? target.substring(1) : "";
    // Explore all 4 directions
    boolean left = backTrack(row, col - 1, arr, visited, up);
    boolean right = backTrack(row, col + 1, arr, visited, up);
    boolean top = backTrack(row - 1, col, arr, visited, up);
    boolean down = backTrack(row + 1, col, arr, visited, up);

    // Calculate the maximum gold from this cell

    visited[row][col] = false;
    if (left || right || top || down)
      return true;
    else
      return false;
  }

}
