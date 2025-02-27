package TwoPointer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.print.attribute.HashPrintServiceAttributeSet;

public class Questions {
  public static void main(String[] args) {
    // System.out.println(clearDigits(""));
    // System.out.println(pivotInteger(8));
    int arr[] = { 1, 2, 3 };
    int k = 2;
    // System.out.println(FirstNegativeInteger(arr, k));
    System.out.println(shortestSubarray(arr, 0));
  }

  public static String clearDigits(String s) {
    StringBuilder sb = new StringBuilder();
    for (char c : s.toCharArray()) {
      if (Character.isLowerCase(c)) {
        sb.append(c);
      } else if (sb.length() > 0) {
        sb.setLength(sb.length() - 1);
      }
    }
    return sb.toString();
  }

  public static int pivotInteger(int n) {
    if (n == 1)
      return n;
    int leftSum = 1;
    int rightSum = n;
    int i = leftSum, j = rightSum;
    while (i < j) {
      if (leftSum < rightSum) {
        i++;
        leftSum += i;
      } else {
        j--;
        rightSum += j;
      }
      if (leftSum == rightSum && leftSum + i + 1 == rightSum + j - 1)
        return i + 1;
    }
    return -1;
  }

  public int maxScore(String s) {
    int zero = 0;
    int one = 0;
    int ans = Integer.MIN_VALUE;
    for (int i = 0; i < s.length() - 2; i++) {
      if (s.charAt(i) == '0')
        zero++;
      else {
        one++;
      }
      ans = Math.max(ans, zero - one);
    }
    if (s.charAt(s.length() - 1) == '1')
      one++;
    return ans + one;
  }

  public int isPrefixOfWord(String sentence, String searchWord) {
    int i = 0;
    char[] s = sentence.toCharArray();
    int currentWordPosition = 0;
    while (i < s.length) {
      while (s[i] == ' ') {
        i++;
        currentWordPosition++;
      }
      int j = 0;
      while (i < s.length && j < searchWord.length() && s[i] == searchWord.charAt(j)) {
        i++;
        j++;
      }
      if (j == searchWord.length())
        return currentWordPosition + 1;
      while (i < s.length && s[i] != ' ')
        i++;
    }
    return -1;
  }

  public int numSubarrayProductLessThanK(int[] nums, int k) {
    int ans = 0;
    int j = 0;
    int i = 0;
    int p = 1;
    while (j < nums.length) {
      p *= nums[j];
      while (p >= k) {
        p /= nums[i];
        i++;
      }
      ans += j - i + 1;
    }
    return ans;
  }

  public long countSubarrays(int[] nums, int k) {
    int freq = 0;
    int maxi = Integer.MIN_VALUE;
    for (int i : nums) {
      maxi = Math.max(maxi, i);
    }
    long ans = 0;
    int i = 0, j = 0;
    while (j < nums.length) {
      if (nums[j] == maxi)
        freq++;
      while (freq >= k) {
        ans += nums.length - j;
        if (nums[i] == maxi)
          freq--;
        i++;
      }
      j++;
    }
    return ans;
  }

  public int maxSubarrayLength(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    int i = 0, j = 0, ans = 0;
    while (j < nums.length) {
      map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
      while (map.get(nums[j]) > k) {
        map.put(nums[i], map.get(nums[i]) - 1);
        i++;
      }
      ans = Math.max(ans, j - i + 1);
      j++;
    }
    return ans;
  }

  public int appendCharacters(String s, String t) {
    int i = 0, j = 0;
    while (i < s.length() && j < t.length()) {
      if (s.charAt(i) == t.charAt(j)) {
        i++;
        j++;
      } else
        i++;
    }
    return t.length() - j;
  }

  public List<String> buildArray(int[] target, int n) {
    List<String> res = new ArrayList<>();
    int j = 0;
    for (int i = 1; i <= n && j < target.length; i++) {
      res.add("Push");

      if (i != target[j]) {
        res.add("Pop");
        continue;
      }
      j++;

    }
    return res;
  }

  public String addSpaces(String s, int[] spaces) {
    StringBuilder sb = new StringBuilder();
    int j = 0, i = 0;
    int lastIdx = 0;
    for (i = 0; i < s.length(); i++) {
      if (i == spaces[j]) {
        sb.append(s.substring(lastIdx, i)).append(" ");
        j++;
        lastIdx = i;
      }
    }
    if (lastIdx < s.length()) {
      sb.append(s.substring(lastIdx, s.length()));
    }
    return sb.toString();
  }

  public int minimumLength(String s) {
    int i = 0, j = s.length() - 1;
    while (i < j && s.charAt(i) == s.charAt(j)) {
      char c = s.charAt(i);
      while (i < j && s.charAt(i) == c)
        i++;
      while (i <= j && s.charAt(j) == c)
        j--;
    }
    return j - i + 1;
  }

  public boolean canMakeSubsequence(String str1, String str2) {
    int i = 0, j = 0;
    while (i < str1.length() && j < str2.length()) {
      if ((str1.charAt(i) - 'a' + 1) % 26 == (str2.charAt(j) - 'a') || str1.charAt(i) == str2.charAt(j)) {
        i++;
        j++;
      } else
        i++;
    }
    if (j < str2.length())
      return false;
    return true;
  }

  public int[] decrypt(int[] code, int k) {
    int[] ans = new int[code.length];
    if (k == 0)
      return ans;
    int start = 1;
    int end = k;
    int sum = 0;
    if (k < 0) {
      end = code.length - 1;
      start = code.length - Math.abs(k);
    }
    for (int i = start; i <= end; i++)
      sum += code[i];
    for (int i = 0; i < code.length; i++) {
      ans[i] = sum;
      sum -= code[start % code.length];
      sum += code[(end + 1) % code.length];
      start++;
      end++;
    }
    return ans;
  }

  public int lengthOfLongestSubstring(String s) {
    if (s.length() == 0)
      return 0;
    Map<Character, Integer> map = new HashMap<>();
    int ans = Integer.MIN_VALUE;
    int i = 0, j = 0;
    while (j < s.length()) {
      map.put(s.charAt(j), map.getOrDefault(s.charAt(j), 0) + 1);
      while (i < j && map.get(s.charAt(j)) > 1) {
        map.put(s.charAt(i), map.get(s.charAt(i)) - 1);
        i++;
      }
      System.out.println(j + " " + i);
      ans = Math.max(ans, j - i + 1);
      j++;
    }
    return ans;
  }

  public int numSubarraysWithSum(int[] nums, int goal) {
    return helper(nums, goal) - helper(nums, goal - 1);
  }

  int helper(int[] nums, int sum) {
    int i = 0, j = 0;
    int ans = 0;
    int runningSum = 0;
    while (j < nums.length) {
      if (sum < 0)
        return 0;
      runningSum += nums[j];
      while (i <= j && runningSum > sum) {
        runningSum -= nums[i];
        i++;
      }
      ans += j - i + 1;
      j++;
    }
    System.out.println(ans);
    return ans;
  }

  public int maxVowels(String s, int k) {
    String v = "aeiou";
    int i = 0, j = 0;
    int n = s.length();
    int count = 0, ans = 0;

    for (j = 0; j < k; j++) {
      if (v.indexOf(s.charAt(j)) != -1) {
        count++;
      }
    }
    ans = count;
    while (j < n) {
      if (v.indexOf(s.charAt(i)) != -1) {
        count--;
      }
      if (v.indexOf(s.charAt(j)) != -1) {
        count++;
      }
      ans = Math.max(ans, count);
      i++;
      j++;
    }
    return ans;
  }

  public int equalSubstring(String s, String t, int maxCost) {
    int i = 0, j = 0, ans = 0, currentCost = 0;
    while (j < s.length()) {
      currentCost += Math.abs((s.charAt(j) - 'a') - (t.charAt(j) - 'a'));
      while (i <= j && currentCost > maxCost) {
        currentCost -= Math.abs((s.charAt(i) - 'a') - (t.charAt(i) - 'a'));
        i++;
      }
      ans = Math.max(ans, j - i + 1);
      j++;
    }
    return ans;
  }

  public List<Integer> sequentialDigits(int low, int high) {
    List<Integer> a = new ArrayList<>();

    for (int i = 1; i <= 9; ++i) {
      int num = i;
      int nextDigit = i + 1;

      while (num <= high && nextDigit <= 9) {
        num = num * 10 + nextDigit;
        if (low <= num && num <= high) {
          a.add(num);
        }
        ++nextDigit;
      }
    }

    a.sort(null);
    return a;
  }

  public int maximumBeauty(int[] nums, int k) {
    Arrays.sort(nums);
    int j = 0, i = 0;
    int ans = 0;
    while (j < nums.length) {
      while (nums[j] - k > nums[i] + k)
        i++;
      ans = Math.max(ans, j - i + 1);
      ;
      j++;
    }
    return ans;
  }

  public int maxFrequency(int[] nums, int k) {
    Arrays.sort(nums);
    int i = 0, j = 0;
    long ans = 0, total = 0;
    while (j < nums.length) {
      total += (long) nums[j];

      while (i < j && (long) nums[j] * (j - i + 1) > total + k) {
        total -= (long) nums[i];
        i++;
      }
      ans = Math.max(ans, j - i + 1);
      j++;
    }
    return (int) ans;

  }

  public int subarraySum(int[] nums, int goal) {
    Map<Integer, Integer> prefixSumFreq = new HashMap<>();
    prefixSumFreq.put(0, 1);
    int prefixSum = 0, count = 0;
    for (int num : nums) {
      prefixSum += num;
      if (prefixSumFreq.containsKey(prefixSum - goal)) {
        count += prefixSumFreq.get(prefixSum - goal);
      }
      prefixSumFreq.put(prefixSum, prefixSumFreq.getOrDefault(prefixSum, 0) + 1);
    }
    return count;
  }

  public int subarraysWithKDistinct(int[] nums, int goal) {
    return helper2(nums, goal) - helper2(nums, goal - 1);
  }

  int helper2(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    int i = 0, j = 0;
    int ans = 0;
    if (k == 0)
      return 0;
    while (j < nums.length) {
      map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
      while (i < j && map.size() > k) {
        map.put(nums[i], map.get(nums[i]) - 1);
        if (map.get(nums[i]) == 0) {
          map.remove(nums[i]);
        }
        i++;
      }
      ans += j - i + 1;
      j++;
    }
    System.out.println(ans);
    return ans;
  }

  public int numberOfSpecialChars(String word) {
    int left[] = new int[26];
    int right[] = new int[26];
    Arrays.fill(left, -1);
    Arrays.fill(right, -1);
    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      if (Character.isLowerCase(c)) {
        left[c - 'a'] = i;
      } else if (Character.isUpperCase(c) && right[c - 'A'] == -1) {
        right[c - 'A'] = i;
      }
    }
    int ans = 0;
    for (int i = 0; i < 26; i++) {
      if (left[i] != -1 && right[i] != -1 && right[i] > left[i])
        ans++;
    }
    return ans;
  }

  public int maximizeGreatness(int[] nums) {
    Arrays.sort(nums);
    int i = 0, j = 0;
    while (j < nums.length) {
      if (nums[j] > nums[i]) {
        i++;
      }
      j++;
    }
    return i;
  }

  public int minSubArrayLen(int s, int[] nums) {
    int sum = 0, from = 0, win = Integer.MAX_VALUE;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      while (sum >= s) {
        win = Math.min(win, i - from + 1);
        sum -= nums[from++];
      }
    }
    return (win == Integer.MAX_VALUE) ? 0 : win;
  }

  public char[][] rotateTheBox(char[][] box) {
    int r = box.length, c = box[0].length;
    char[][] finalBox = new char[c][r];
    for (int i = 0; i < r; i++) {
      int empty = c - 1;
      for (int j = c - 1; j >= 0; j--) {
        if (box[i][j] == '*') {
          empty = j - 1;
        } else if (box[i][j] == '#') {
          box[i][j] = '.';
          box[i][empty] = '#';
          empty--;
        }
      }
    }
    for (int i = 0; i < r; i++) {
      for (int j = c - 1; j >= 0; j--) {
        finalBox[j][r - i - 1] = box[i][j];
      }
    }
    return finalBox;
  }

  public boolean canChange(String start, String target) {
    int sId = 0, tId = 0;
    int n = start.length();
    while (sId < n || tId < n) {
      while (sId < n && start.charAt(sId) == '_')
        sId++;
      while (tId < n && target.charAt(tId) == '_')
        tId++;
      if (sId == n && tId == n)
        return true;
      if (sId == n || tId == n)
        return false;
      if (start.charAt(sId) != target.charAt(tId))
        return false;
      if (start.charAt(sId) == 'L' && sId < tId || (target.charAt(tId) == 'R' && tId > sId))
        return false;
      sId++;
      tId++;
    }
    return true;
  }

  public long maximumSubarraySum(int[] nums, int k) {
    int i = 0;
    Map<Integer, Integer> map = new HashMap<>();
    int sum = 0;
    int ans = 0;
    while (i < nums.length) {
      map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
      sum += nums[i];
      if (i >= k - 1) {
        if (map.size() == k)
          ans = Math.max(ans, sum);
        sum -= nums[i - k + 1];
        map.put(nums[i - k + 1], map.getOrDefault(nums[i - k + 1], 0) - 1);
        if (map.get(nums[i - k + 1]) == 0)
          map.remove(nums[i - k + 1]);

      }
      i++;
    }
    return ans;
  }

  static List<Integer> FirstNegativeInteger(int nums[], int k) {
    // write code here
    List<Integer> ans = new ArrayList<>();
    Queue<Integer> queue = new LinkedList<>();
    int i = 0;
    while (i < nums.length) {
      if (nums[i] < 0)
        queue.add(nums[i]);
      if (i >= k - 1) {
        if (queue.isEmpty())
          ans.add(0);
        else
          ans.add(queue.peek());
        if (!queue.isEmpty() && nums[i - k + 1] == queue.peek())
          queue.poll();
      }
      i++;
    }
    return ans;

  }

  public int longestSubstring(String s, int k) {
    int ans = Integer.MIN_VALUE;
    Map<Character, Integer> map = new HashMap<>();
    int j = 0, n = s.length();
    while (j < n) {

      j++;
    }

    if (ans == Integer.MIN_VALUE)
      return 0;
    return ans;
  }

  public int totalFruit(int[] fruits) {
    int j = 0, i = 0, n = fruits.length;
    Map<Integer, Integer> map = new HashMap<>();
    int ans = 0;
    while (j < n) {
      map.put(fruits[j], map.getOrDefault(fruits[j], 0) + 1);
      while (map.size() > 2) {
        map.put(fruits[i], map.get(fruits[i]) - 1);
        if (map.get(fruits[i]) == 0)
          map.remove(fruits[i]);
        i++;
      }
      System.out.println(j + " " + i);
      ans = Math.max(ans, j - i + 1);
      j++;
    }
    return ans;
  }

  public int numOfSubarrays(int[] arr, int k, int threshold) {
    int j = 0;
    int ans = 0;
    int sum = 0;
    while (j < arr.length) {
      sum += arr[j];
      if (j >= k - 1) {
        int avg = (int) sum / k;
        if (avg >= threshold)
          ans++;
        sum -= arr[j - k + 1];
      }
      j++;
    }
    return ans;
  }

  public int subarraysDivByK(int[] nums, int k) {
    int i = 0, j = 0, n = nums.length;
    int ans = 0;
    int sum = 0;
    while (j < n) {
      sum += nums[j];
      while (i <= j && sum % k != 0) {
        sum -= nums[i];
        i++;
      }
      ans += j - i + 1;
      j++;
    }
    if (ans == 0)
      return ans;
    return ans + 1;
  }

  // Complex sliding window problems where traditional approach might not work
  /*
   * Given an integer array nums and an integer k, return the length of the
   * shortest non-empty subarray of nums with a sum of at least k. If there is no
   * such subarray, return -1.
   */

  static int shortestSubarray(int[] nums, int k) {
    int n = nums.length;
    long[] prefix = new long[n + 1]; // Prefix sum array
    for (int i = 0; i < n; i++) {
      prefix[i + 1] = prefix[i] + nums[i]; // Compute prefix sum
    }

    Deque<Integer> dq = new LinkedList<>();
    int minLength = Integer.MAX_VALUE;

    for (int i = 0; i <= n; i++) {
      // Check if we have a valid subarray
      while (!dq.isEmpty() && prefix[i] - prefix[dq.peekFirst()] >= k) {
        minLength = Math.min(minLength, i - dq.pollFirst());
      }

      // Maintain increasing order in deque
      while (!dq.isEmpty() && prefix[i] <= prefix[dq.peekLast()]) {
        dq.pollLast();
      }

      dq.offerLast(i);
    }

    return minLength == Integer.MAX_VALUE ? -1 : minLength;
  }

  /*
   * You are given an integer array nums and an integer threshold.
   * 
   * Find any subarray of nums of length k such that every element in the subarray
   * is greater than threshold / k.
   * 
   * Return the size of any such subarray. If there is no such subarray, return
   * -1.
   * 
   * A subarray is a contiguous non-empty sequence of elements within an array.
   */
  public int validSubarraySize(int[] nums, int threshold) {
    int i = 0, j = 0, n = nums.length;
    Deque<Integer> deque = new ArrayDeque<>();
    int ans = Integer.MIN_VALUE;
    while (j < n) {
      while (!deque.isEmpty() && nums[deque.peekLast()] > nums[j])
        deque.offerLast(j);
      while (!deque.isEmpty() && nums[deque.peekFirst()] < (j - i + 1) * threshold) {
        if (nums[deque.peekFirst()] == nums[i])
          deque.pollFirst();
        i++;
      }

      ans = Math.max(ans, j - i + 1);
      j++;
    }

    return ans == Integer.MIN_VALUE ? -1 : ans;
  }
}