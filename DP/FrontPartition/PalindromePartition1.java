package DP.FrontPartition;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartition1 {
  public List<List<String>> partition(String s) {
    List<List<String>> result = new ArrayList<>();
    List<String> path = new ArrayList<>();
    int n = s.length();
    recursion(0, n, s, path, result);
    return result;
  }

  void recursion(int index, int n, String s, List<String> path, List<List<String>> result) {
    if (index == n) {
      result.add(new ArrayList<>(path)); // If we reach the end, add the partition to the result
      return;
    }

    for (int j = index; j < n; j++) {
      if (isPalindrome(index, j, s)) {
        path.add(s.substring(index, j + 1)); // Add palindrome substring to path
        recursion(j + 1, n, s, path, result); // Recur for remaining string
        path.remove(path.size() - 1); // Backtrack
      }
    }
  }

  boolean isPalindrome(int i, int j, String s) {
    while (i < j) {
      if (s.charAt(i) != s.charAt(j))
        return false;
      i++;
      j--;
    }
    return true;
  }
}
