package Arrays;

import java.util.*;

public class LongestPalindrome {
  public static void main(String[] args) {
    int a = longestPalindrome("Sumanth");
  }

  public static int longestPalindrome(String s) {
    Map<Character, Integer> map = new HashMap<>();
    for (Character it : s.toCharArray()) {
      map.put(it, map.getOrDefault(it, 0) + 1);
    }
    int res = 0;
    boolean isOdd = false;
    for (Map.Entry<Character, Integer> it : map.entrySet()) {
      int val = it.getValue();
      if (val % 2 == 0) {
        res += val;
      } else {
        res += val - 1;
        isOdd = true;
      }
    }
    if (isOdd)
      res += 1;
    return res;
  }
}
