package Recursion.SubSet_Pattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subset {
  public static void main(String[] args) {
    /*
     * find all sub sets in array
     */
    int[] arr = { -3, -2, -1, 0, 0, 1, 2, 3 };
    // System.out.println( findSubSetsWithoutDuplicates(new ArrayList<>(), arr, 0,
    // arr.length-1));
    System.out.println(iterativeSubSetDuplicates(arr));
  }

  static List<List<Integer>> findSubSets(List<Integer> p, int[] up, int start, int end) {
    if (start > end) {
      List<List<Integer>> list = new ArrayList<>();
      list.add(p);
      return list;
    }
    int a = up[start];
    List<List<Integer>> left = findSubSets(new ArrayList<>(p), up, start + 1, end);
    p.add(a);
    List<List<Integer>> right = findSubSets(new ArrayList<>(p), up, start + 1, end);
    left.addAll(right);

    return left;
  }

  static List<List<Integer>> findSubSetSum(List<Integer> p, int[] up, int start, int end) {
    if (start > end) {
      List<List<Integer>> list = new ArrayList<>();
      int sum = p.stream().mapToInt(Integer::intValue).sum();
      List<Integer> b = new ArrayList<>();
      b.add(sum);
      list.add(b);
      return list;
    }
    int a = up[start];
    List<List<Integer>> left = findSubSetSum(new ArrayList<>(p), up, start + 1, end);
    p.add(a);
    List<List<Integer>> right = findSubSetSum(new ArrayList<>(p), up, start + 1, end);
    p.removeLast();
    left.addAll(right);
    return left;
  }

  /// How will handle duplicates 
  static List<List<Integer>> findSubSetsWithoutDuplicates(List<Integer> p, int[] up, int start, int end) {
    if (start > end) {
      List<List<Integer>> list = new ArrayList<>();
      list.add(p);
      return list;
    }
    int a = up[start];
    List<List<Integer>> left = new ArrayList<>();
    System.out.println(p);
    if (!p.contains(a))
      left = findSubSetsWithoutDuplicates(new ArrayList<>(p), up, start + 1, end);
    p.add(a);
    List<List<Integer>> right = findSubSetsWithoutDuplicates(new ArrayList<>(p), up, start + 1, end);
    left.addAll(right);

    return left;
  }

  // We have seen recursive approach how can we do this in iterative approach
  // first we will look into without duplicates then we can proceed with
  // duplicates

  static List<List<Integer>> iterativeSubSet(int[] arr) {
    List<List<Integer>> outer = new ArrayList<>();
    outer.add(new ArrayList<>());
    for (int i : arr) {
      int size = outer.size();
      for (int j = 0; j < size; j++) {
        List<Integer> list = new ArrayList<>(outer.get(j));
        list.add(i);
        outer.add(list);
      }
    }
    return outer;
  }

  static List<List<Integer>> iterativeSubSetDuplicates(int[] arr) {
    List<List<Integer>> outer = new ArrayList<>();
    Arrays.sort(arr);
    int[] ans = new int[arr.length];
    outer.add(new ArrayList<>());
    int start = 0;
    int end = 0;
    for (int i = 0; i < arr.length; i++) {
      start = 0;
      if (i > 0 && arr[i] == arr[i - 1]) {
        start = end + 1;
      }
      end = outer.size() - 1;
      for (int j = start; j <= end; j++) {
        List<Integer> list = new ArrayList<>(outer.get(j));
        list.add(arr[i]);
        // int sum = list.stream().mapToInt(Integer::intValue).sum();
        // if(sum==0 && list.size()>1){
        // ans = list.stream().mapToInt(Integer::intValue).toArray();

        // System.out.println(list);
        // return ans;
        // }
        outer.add(list);
      }
    }
    return outer;
  }

}
