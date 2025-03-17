package DP.LinearDp;

import java.util.*;

public class DistinctSubSquence {
  public int distinctSubseqII(String s) {
    Map<Character, Integer> map = new HashMap<>();
    int[] dp = new int[s.length()];
    dp[0] = 1;
    map.put(s.charAt(0), 0);
    for (int i = 1; i < s.length(); i++) {
      dp[i] = 2 * dp[i - 1] + 1;
      if (map.containsKey(s.charAt(i))) {
        int j = map.get(s.charAt(i));
        dp[i] = dp[i] - dp[j];
      }
      System.out.println(dp[i]);
      map.put(s.charAt(i), i);
    }
    return dp[s.length() - 1];
  }

}
