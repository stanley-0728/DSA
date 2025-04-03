package Greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * You are given two positive integer arrays nums and target, of the same length.

In a single operation, you can select any subarray of nums and increment each element within that subarray by 1 or decrement each element within that subarray by 1.

Return the minimum number of operations required to make nums equal to the array target.

Example 1:

Input: nums = [3,5,1,2], target = [4,6,2,4]

Output: 2

Explanation:

We will perform the following operations to make nums equal to target:
- Increment nums[0..3] by 1, nums = [4,6,2,3].
- Increment nums[3..3] by 1, nums = [4,6,2,4].

Example 2:

Input: nums = [1,3,2], target = [2,1,4]

Output: 5

Explanation:

We will perform the following operations to make nums equal to target:
- Increment nums[0..0] by 1, nums = [2,3,2].
- Decrement nums[1..1] by 1, nums = [2,2,2].
- Decrement nums[1..1] by 1, nums = [2,1,2].
- Increment nums[2..2] by 1, nums = [2,1,3].
- Increment nums[2..2] by 1, nums = [2,1,4].
 */
public class MakeArraytoZero {

  public
  static void main(String args[]) throws Exception {
    // BufferedReader
    BufferedReader ;br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    System.out.println(n);
    Sttring[] input = br.readLine().split(" ");
    int arr[] = nenw int[n];
    for(int i=0;i<n;i+=) arr[i] =input[i];
    int operations = minOperationsToMakeArrayToZero(arr);
    System.out.println(operations);
  }

  public int minOperationsToMakeArrayToZero(int[] nums) {
    int prev = -1, curr = 0, operation = 0;
    if (prev == -1) {
      operation += Math.abs(curr);
      prev++;
      curr++;
    }
    while (curr < nums.length) {
      if ((nums[curr] > 0 && nums[prev] < 0) || (nums[curr] < 0 && nums[prev] > 0)) {
        operation += Math.abs(nums[curr]);
      }
      if (Math.abs(nums[curr]) > Math.abs(nums[prev])) {
        operation += Math.abs(nums[curr]) - Math.abs(nums[prev]);
      }
      curr++;
      prev++;
    }
    return operation;
  }

  public long minimumOperations(int[] nums, int[] target) {
    int[] diff = new int[nums.length];
    for (int i = 0; i < nums.length; i++)
      diff[i] = target[i] - nums[i];
    return minOperationsToMakeArrayToZero(diff);
  }
}
