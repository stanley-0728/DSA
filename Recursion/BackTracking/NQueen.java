package Recursion.BackTracking;

import java.util.ArrayList;
import java.util.List;

public class NQueen {
  public static void main(String[] args) {
    boolean[][] board = new boolean[4][4];
    // System.out.println(backTrack(new ArrayList<>(), board, 0,0));
    System.out.println(countNqueens(board, 0, 0));
  }

  static List<List<String>> backTrack(List<Integer> p, boolean[][] board, int row, int q) {

    if (row >= board.length) {
      List<List<String>> res = new ArrayList<>();
      if (q == board.length) {
        for (int i = 0; i < board.length; i++) {
          for (int j = 0; j < board[0].length; j++) {
            if (board[i][j] && !isValid(board, i, j))
              new ArrayList<>();
            ;
          }
        }
        List<String> list = display(board);
        res.add(list);
      }
      return res;
    }
    List<List<String>> res = new ArrayList<>();
    for (int col = 0; col < board[0].length; col++) {
      if (!isValid(board, row, col))
        continue;
      board[row][col] = true;
      res.addAll(backTrack(p, board, row + 1, q + 1));
      board[row][col] = false;
    }
    return res;
  }

  static int countNqueens(boolean[][] board, int row, int q) {

    if (row >= board.length) {
      int ans = 0;
      if (q == board.length) {
        for (int i = 0; i < board.length; i++) {
          for (int j = 0; j < board[0].length; j++) {
            if (board[i][j] && !isValid(board, i, j))
              new ArrayList<>();
            ;
          }
        }
        ans++;
      }
      return ans;
    }
    int c = 0;
    for (int col = 0; col < board[0].length; col++) {
      if (!isValid(board, row, col))
        continue;
      board[row][col] = true;
      c += countNqueens(board, row + 1, q + 1);
      board[row][col] = false;
    }
    return c;
  }

  static boolean isValid(boolean[][] board, int row, int col) {
    // check for horizontal
    for (int i = 0; i < col; i++) {
      if (board[row][i])
        return false;
      else
        continue;
    }
    // check for vertical
    for (int i = 0; i < row; i++) {
      if (board[i][col])
        return false;
    }
    // diagonal left
    int maxLeft = Math.min(row, col);
    for (int i = 1; i <= maxLeft; i++) {
      if (board[row - i][col - i]) {
        return false;
      }
    }

    // diagonal right
    int maxRight = Math.min(row, board.length - col - 1);
    for (int i = 1; i <= maxRight; i++) {
      if (board[row - i][col + i]) {
        return false;
      }
    }
    return true;
  }

  static List<String> display(boolean[][] board) {
    List<String> list = new ArrayList<>();
    for (boolean[] i : board) {
      String s = "";
      for (boolean j : i) {
        if (j) {
          s += "Q";
        } else {
          s += ".";
        }
      }
      list.add(new String(s));
    }

    return list;
  }
}
