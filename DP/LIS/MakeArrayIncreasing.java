package DP.LIS;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * Given two integer arrays arr1 and arr2, return the minimum number of operations (possibly zero) needed to make arr1 strictly increasing.

In one operation, you can choose two indices 0 <= i < arr1.length and 0 <= j < arr2.length and do the assignment arr1[i] = arr2[j].

If there is no way to make arr1 strictly increasing, return -1.
Example 1:

Input: arr1 = [1,5,3,6,7], arr2 = [1,3,2,4]
Output: 1
Explanation: Replace 5 with 2, then arr1 = [1, 2, 3, 6, 7].
Example 2:

Input: arr1 = [1,5,3,6,7], arr2 = [4,3,1]
Output: 2
Explanation: Replace 5 with 3 and then replace 3 with 4. arr1 = [1, 3, 4, 6, 7].
Example 3:

Input: arr1 = [1,5,3,6,7], arr2 = [1,6,3,3]
Output: -1
Explanation: You can't make arr1 strictly increasing.
 
 */
public class MakeArrayIncreasing {
  public int makeArrayIncreasing(int[] arr1, int[] arr2) {
    Arrays.sort(arr2);
    Map<String, Integer> map = new HashMap<>();
    int ans = recursion(0, -(int) 1e9, arr1, arr2, map);
    return ans >= (int) 1e9 ? -1 : ans;
  }

  int recursion(int i, int prev, int[] a, int[] b) {
    if (i >= a.length)
      return 0;

    int opt1 = (int) 1e9;
    if (a[i] > prev) {
      opt1 = recursion(i + 1, a[i], a, b);
    }

    int index = upperBound(b, prev);
    int opt2 = (int) 1e9;
    if (index < b.length) {
      opt2 = 1 + recursion(i + 1, b[index], a, b);
    }

    return Math.min(opt1, opt2);
  }

  int recursion(int i, int prev, int[] a, int[] b, Map<String, Integer> map) {
    if (i >= a.length)
      return 0;
    String key = i + "," + prev;
    if (map.containsKey(key))
      return map.get(key);
    int opt1 = (int) 1e9;
    if (a[i] > prev) {
      opt1 = recursion(i + 1, a[i], a, b, map);
    }

    int index = upperBound(b, prev);
    int opt2 = (int) 1e9;
    if (index < b.length) {
      opt2 = 1 + recursion(i + 1, b[index], a, b, map);
    }
    if (!map.containsKey(key))
      map.put(key, Math.min(opt1, opt2));
    return map.get(key);
  }

  private int upperBound(int[] arr, int key) {
    int lo = 0, hi = arr.length - 1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if (arr[mid] <= key) {
        lo = mid + 1;
      } else {
        hi = mid - 1;
      }
    }
    return lo; // First index where arr[index] > key
  }
}
