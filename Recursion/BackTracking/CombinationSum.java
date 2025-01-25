package Recursion.BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
  public static void main(String[] args) {
    int[] arr = { 2, 5, 2, 1, 2 };
    int k = 5;
    Arrays.sort(arr);
    // System.out.println(findCombinationWithDuplicates(new ArrayList<>(), arr,
    // 0,k));
    System.out.println(backTrack(new ArrayList<>(), 4, 1, 1));
  }

  // you can use same element multiple elements here arr contains unique elements
  static List<List<Integer>> findCombination(List<Integer> p, int[] arr, int start, int k, int target) {
    System.out.println(start + " " + k);
    if (k < 0 || start >= arr.length)
      return new ArrayList<>();
    if (k == 0) {
      List<List<Integer>> list = new ArrayList<>();
      list.add(new ArrayList<>(p));
      return list;
    }
    int i = arr[start];
    p.add(i);
    List<List<Integer>> right = findCombination(new ArrayList<>(p), arr, start, k - i, target);
    p.remove(p.size() - 1);
    List<List<Integer>> left = findCombination(new ArrayList<>(p), arr, start + 1, k, target);

    left.addAll(right);
    return left;
  }
  // elements can be used only once and here arr contains duplicates

  static List<List<Integer>> findCombinationWithDuplicates(List<Integer> p, int[] arr, int start, int k) {
    if (k == 0) {
      List<List<Integer>> list = new ArrayList<>();
      list.add(new ArrayList<>(p));
      return list;
    }
    if (k < 0 || start >= arr.length)
      return new ArrayList<>();

    List<List<Integer>> result = new ArrayList<>();

    for (int i = start; i < arr.length; i++) {
      if (i > start && arr[i] == arr[i - 1])
        continue;
      p.add(arr[i]);
      result.addAll(findCombinationWithDuplicates(p, arr, i + 1, k - arr[i]));
      p.remove(p.size() - 1);
    }

    return result;
  }

  // Combination 3 Find all valid combinations of k numbers that sum up to n such
  // that the following conditions are true:
  // Only numbers 1 through 9 are used.
  // Each number is used at most once.
  // Return a list of all possible valid combinations. The list must not contain
  // the same combination twice, and the combinations may be returned in any
  // order.

  static List<List<Integer>> backTrack(List<Integer> p, int s, int k, int start) {

    if (s == 0 && k == 0) {
      System.out.println(p);
      List<List<Integer>> list = new ArrayList<>();
      list.add(new ArrayList<>(p));
      return list;
    }
    if (s < 0 || k < 0) {
      return new ArrayList<>();
    }
    List<List<Integer>> res = new ArrayList<>();
    for (int i = start; i <= 9; i++) {
      p.add(i);
      res.addAll(backTrack(p, s - 1, k - i, i + 1));
      p.remove(p.size() - 1);
    }
    return res;

  }

}
