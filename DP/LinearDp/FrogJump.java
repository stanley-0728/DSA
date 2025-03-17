package DP.LinearDp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FrogJump {
  public boolean canCross(int[] stones) {
    if (stones == null || stones.length == 0)
      return false;

    Set<Integer> stoneSet = new HashSet<>();
    for (int stone : stones)
      stoneSet.add(stone);
    Map<String, Boolean> memo = new HashMap<>();

    return recursion(stones[0], 0, stoneSet, stones[stones.length - 1], memo);
  }

  private boolean recursion(int position, int lastJump, Set<Integer> stoneSet, int lastStone) {
    if (position == lastStone)
      return true; // Reached the last stone
    if (lastJump <= 0)
      return false; // Cannot stay in the same place or jump backwards

    for (int i = -1; i <= 1; i++) { // Try k-1, k, k+1 jumps
      int nextJump = lastJump + i;
      int nextPosition = position + nextJump;

      if (nextJump > 0 && stoneSet.contains(nextPosition)) {
        if (recursion(nextPosition, nextJump, stoneSet, lastStone)) {
          return true; // If any path works, return true
        }
      }
    }
    return false;
  }

  private boolean recursion(int position, int lastJump, Set<Integer> stoneSet, int lastStone,
      Map<String, Boolean> memo) {
    if (position == lastStone)
      return true; // Reached the last stone
    String key = position + "," + lastJump;
    if (memo.containsKey(key))
      return memo.get(key);
    boolean result = false;
    for (int i = -1; i <= 1; i++) { // Try k-1, k, k+1 jumps
      int nextJump = lastJump + i;
      int nextPosition = position + nextJump;

      if (nextJump > 0 && stoneSet.contains(nextPosition)) {
        result = recursion(nextPosition, nextJump, stoneSet, lastStone, memo);
        if (result)
          break; // No need to check further if true
      }
    }
    memo.put(key, result);

    return result;
  }
}
