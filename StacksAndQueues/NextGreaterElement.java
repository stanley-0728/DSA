package StacksAndQueues;

import java.util.*;

public class NextGreaterElement {
  public static void main(String[] args) {
    int[] a = { 4, 1, 2 };
    int[] b = { 1, 3, 4, 2 };
    int[] ans = nextGreaterElement(a, b);
    for (int i = 0; i < a.length; i++) {
      System.out.println(ans[i]);
    }
  }

  public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
    Stack<Integer> st = new Stack<>();
    Map<Integer, Integer> map = new HashMap<>();

    for (int i = nums2.length - 1; i >= 0; i--) {
      while (!st.isEmpty() && nums2[i] >= st.peek()) {
        st.pop();
      }
      map.put(nums2[i], st.isEmpty() ? -1 : st.peek());
      st.push(nums2[i]);

    }
    for (int i = 0; i < nums1.length; i++) {
      nums1[i] = map.get(nums1[i]);
    }
    return nums1;
  }

  public int[] nextGreaterElements(int[] nums) {
    Stack<Integer> st = new Stack<>();
    int[] a = new int[nums.length];
    for (int i = (nums.length * 2) - 1; i >= 0; i--) {
      while (!st.isEmpty() && st.peek() <= nums[i % nums.length])
        st.pop();
      if (i < nums.length) {
        a[i] = st.isEmpty() ? -1 : st.peek();
      }
      st.push(nums[i % nums.length]);
    }
    return a;
  }
}