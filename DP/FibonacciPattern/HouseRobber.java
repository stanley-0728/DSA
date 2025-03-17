package DP.FibonacciPattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TreeNode {

  int val;
  TreeNode left;
  TreeNode right;

  TreeNode() {
  }

  TreeNode(int val) {
    this.val = val;
  }

  TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}

/*
 * You are a professional robber planning to rob houses along a street. Each
 * house has a certain amount of money stashed, the only constraint stopping you
 * from robbing each of them is that adjacent houses have security systems
 * connected and it will automatically contact the police if two adjacent houses
 * were broken into on the same night.
 * 
 * Given an integer array nums representing the amount of money of each house,
 * return the maximum amount of money you can rob tonight without alerting the
 * police.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * Example 2:
 * 
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5
 * (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 */
public class HouseRobber {
  public static void main(String[] args) {

  }

  public int rob(int[] nums) {
    // return recursion(nums.length - 1, nums);
    int[] dp = new int[nums.length];
    Arrays.fill(dp, -1);
    return memo(nums.length - 1, nums, dp);

  }

  int recursion(int index, int[] nums) {
    if (index == 0) {
      return nums[0];
    }
    if (index < 0)
      return 0;
    int pick = Integer.MIN_VALUE;

    pick = nums[index] + recursion(index - 2, nums);
    int notPick = recursion(index - 1, nums);
    return Math.max(pick, notPick);
  }

  int memo(int index, int[] nums, int[] dp) {
    if (index == 0) {
      return nums[0];
    }
    if (index < 0)
      return 0;

    if (dp[index] != -1)
      return dp[index];
    int pick = Integer.MIN_VALUE;
    if (index - 2 >= 0)
      pick = nums[index] + memo(index - 2, nums, dp);
    int notPick = memo(index - 1, nums, dp);
    return dp[index] = Math.max(pick, notPick);
  }

  int tabulation(int[] nums) {
    int[] dp = new int[nums.length];
    dp[0] = nums[0];
    for (int index = 1; index < nums.length; index++) {
      int pick = Integer.MIN_VALUE;
      pick = nums[index];
      if (index - 2 >= 0)
        pick += dp[index - 2];
      int notPick = dp[index - 1];
      dp[index] = Math.max(pick, notPick);
    }
    return dp[nums.length - 1];
  }

  int spaceOptimized(int[] nums) {
    int prev1 = nums[0], prev2 = nums[0];
    for (int index = 1; index < nums.length; index++) {
      int curr;
      int pick = Integer.MIN_VALUE;
      pick = nums[index];
      if (index - 2 >= 0)
        pick += prev2;
      int notPick = prev1;
      curr = Math.max(pick, notPick);
      prev2 = prev1;
      prev1 = curr;
    }
    return prev1;
  }

  public int rob(TreeNode root) {
    Map<TreeNode, Integer> map = new HashMap<>();
    return helper(root, map);
  }

  int helper(TreeNode root, Map<TreeNode, Integer> map) {
    if (root == null)
      return 0;
    if (map.get(root) != null)
      return map.get(root);
    // Case 1: Rob the current node, but skip its direct children
    int robCurrent = root.val;
    if (root.left != null) {
      robCurrent += helper(root.left.left, map) + helper(root.left.right, map);
    }
    if (root.right != null) {
      robCurrent += helper(root.right.left, map) + helper(root.right.right, map);
    }

    // Case 2: Skip the current node and rob its direct children
    int skipCurrent = helper(root.left, map) + helper(root.right, map);

    // Return the maximum amount that can be robbed
    int ans = Math.max(robCurrent, skipCurrent);
    map.put(root, ans);
    return ans;
  }

}
