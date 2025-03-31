package Greedy;

/*
 * You are given an integer array nums (0-indexed). In one operation, you can choose an element of the array and increment it by 1.

For example, if nums = [1,2,3], you can choose to increment nums[1] to make nums = [1,3,3].
Return the minimum number of operations needed to make nums strictly increasing.

An array nums is strictly increasing if nums[i] < nums[i+1] for all 0 <= i < nums.length - 1. An array of length 1 is trivially strictly increasing.

Example 1:

Input: nums = [1,1,1]
Output: 3
Explanation: You can do the following operations:
1) Increment nums[2], so nums becomes [1,1,2].
2) Increment nums[1], so nums becomes [1,2,2].
3) Increment nums[2], so nums becomes [1,2,3].
Example 2:

Input: nums = [1,5,2,4,1]
Output: 14
Example 3:

Input: nums = [8]
Output: 0
 
 */
public class MinOperationToIncrease {
  public int minOperations(int[] nums) {
    int prevValue = nums[0];
    int steps = 0;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] <= prevValue) {
        int diff = prevValue - nums[i];
        int newValue = nums[i] + 1 + diff;
        steps += diff + 1;
        prevValue = newValue;
      } else {
        prevValue = nums[i];
      }
    }
    return steps;
  }

  public String largestOddNumber(String nums) {
    int endIndex = 0;
    for (int i = nums.length() - 1; i >= 0; i--) {
      int n = Integer.parseInt(nums.charAt(i) + "");
      if (n % 2 != 0) {
        endIndex = i;
        break;
      }
    }
    if (endIndex == 0) {
      int n = Integer.parseInt(nums.charAt(0) + "");
      if (n % 2 == 0)
        return "";

    }
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i <= endIndex; i++) {
      sb.append(nums.charAt(i));
    }
    return sb.toString();
  }

  public String removeDigit(String number, char digit) {
    int lastIndex = -1;
    int N = number.length();

    for (int i = 0; i < N; i++)
      if (number.charAt(i) == digit) {
        lastIndex = i;
        if (i < N - 1 && digit < number.charAt(i + 1))
          return number.substring(0, i) + number.substring(i + 1);
      }
    return number.substring(0, lastIndex) + number.substring(lastIndex + 1);
  }
}
