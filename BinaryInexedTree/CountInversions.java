package BinaryInexedTree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CountInversions {
  public int countInversions(int[] nums) {
    int n = nums.length;
    int[] sorted = nums.clone();
    Arrays.sort(sorted);

    // Assign ranks to numbers (coordinate compression)
    Map<Integer, Integer> rank = new HashMap<>();
    int r = 1;
    for (int num : sorted) {
      if (!rank.containsKey(num)) {
        rank.put(num, r++);
      }
    }

    Integer[] result = new Integer[n];
    BIT bit = new BIT(r - 1); // BIT for ranks
    int totalInversions = 0;

    // Traverse from right to left
    for (int i = n - 1; i >= 0; i--) {
      int pos = rank.get(nums[i]);
      int count = bit.query(pos - 1); // Count elements smaller than nums[i]
      result[i] = count;
      bit.update(pos, 1);
      totalInversions += count; // Sum up the counts to get total inversions
    }

    return totalInversions;
  }

  public static void main(String[] args) {
    CountInversions ci = new CountInversions();
    int[] nums = { 2, 4, 1, 3, 5 };
    System.out.println("Total Inversions: " + ci.countInversions(nums)); // Output: 3
  }
}
