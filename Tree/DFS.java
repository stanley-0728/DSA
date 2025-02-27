package Tree;

import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

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

class Node {
  int val;
  int left;
  int right;
}

public class DFS {
  int diameter = Integer.MIN_VALUE;
  int counter = 0;
  int globalSum = Integer.MIN_VALUE;

  public int heightOfBinaryTree(TreeNode root) {
    if (root == null)
      return 0;
    int left = heightOfBinaryTree(root.left);
    int right = heightOfBinaryTree(root.right);
    return Math.max(left, right) + 1;
  }

  public int diameterOfBinaryTree(TreeNode root) {
    helper(root);
    return diameter - 1;
  }

  int helper(TreeNode root) {
    if (root == null)
      return 0;
    int left = helper(root.left);
    int right = helper(root.right);
    int dia = left + right + 1;
    diameter = Math.max(diameter, dia);
    return Math.max(left, right) + 1;
  }

  public TreeNode invertTree(TreeNode root) {
    if (root == null)
      return null;
    invertTree(root.left);
    invertTree(root.right);
    TreeNode temp = root.right;
    root.right = root.left;
    root.left = temp;
    return root;
  }

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null)
      return null;
    if (root == p || root == q)
      return root;
    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);
    if (left != null && right != null)
      return root;
    if (left == null)
      return right;
    else
      return left;
  }

  public void flatten(TreeNode root) {
    if (root == null)
      return;
    TreeNode current = root;
    while (current != null) {
      TreeNode temp = current.left;
      if (temp != null) {
        while (temp.right != null) {
          temp = temp.right;
        }
        temp.right = current.right;
        current.right = current.left;
        current.left = null;
      }
      current = current.right;
    }
  }

  public int kthSmallest(TreeNode root, int k) {
    if (root == null)
      return 0;
    int left = kthSmallest(root.left, k);
    if (left != 0)
      return left;
    counter++;
    if (counter == k)
      return root.val;
    return kthSmallest(root.right, k);
  }

  public boolean hasPathSum(TreeNode root, int targetSum) {
    if (root.val > targetSum)
      return false;
    if (root.val == targetSum)
      return true;
    targetSum -= root.val;
    return hasPathSum(root.left, targetSum) || hasPathSum(root.right, targetSum);
  }

  public int sumNumbers(TreeNode root) {
    return helper(root, 0);
  }

  int helper(TreeNode root, int sum) {
    if (root == null)
      return 0;
    sum = sum * 10 + root.val;
    if (root.left == null && root.right == null)
      return sum;
    return helper(root.left, sum) + helper(root.right, sum);

  }

  public int maxPathSum(TreeNode root) {
    helper1(root);
    return globalSum;
  }

  int helper1(TreeNode root) {
    if (root == null)
      return 0;
    int left = helper1(root.left);
    int right = helper1(root.right);
    left = Math.max(0, left);
    right = Math.max(0, right);
    int pathSum = left + right + root.val;
    globalSum = Math.max(globalSum, pathSum);
    return Math.max(left, right) + root.val;
  }

  boolean findPath(Node node, int[] arr) {
    if (node == null)
      return arr.length == 0;
    return helper(node, arr, 0);
  }

  boolean helper(Node node, int[] arr, int index) {
    if (node == null)
      return false;
    if (index >= arr.length || arr[index] != node.val)
      return false;
    if (node.left == null || node.right == null && index == arr.length - 1)
      return true;
    return helper(node.left, arr, index + 1) || helper(node.right, arr, index + 1);
  }

  int countPaths(Node node, int sum) {
    List<Integer> path = new LinkedList<>();
    return helper(node, sum, path);
  }

  int helper(Node node, int target, List<Integer> path) {
    if (node == null)
      return 0;
    path.add(node.val);
    int count = 0;
    int sum = 0;
    ListIterator<Integer> itr = path.listIterator(path.size());
    while (itr.hasPrevious()) {
      sum += itr.previous();
      if (sum == target)
        count++;
    }
    count += helper(node.left, target, path) + helper(node.right, target, path);
    path.remove(path.size() - 1);
    return count;
  }

  List<List<Integer>> findPaths(Node node, int sum) {
    List<List<Integer>> paths = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    helper(node, sum, path, paths);
    return paths;
  }

  void helper(Node node, int target, List<Integer> path, List<List<Integer>> paths) {
    if (node == null)
      return;
    path.add(node.val);
    if (node.val == target && node.left == null && node.right == null) {
      paths.add(new ArrayList<>(path));
    } else {
      helper(node.left, target - node.val, path, paths);
      helper(node.right, target - node.val, path, paths);

    }
    path.remove(path.size() - 1);
  }

  /*
   * Given the root of a binary tree and an integer targetSum, return the number
   * of paths where the sum of the values along the path equals targetSum.
   * 
   * The path does not need to start or end at the root or a leaf, but it must go
   * downwards (i.e., traveling only from parent nodes to child nodes).
   */
  int c = 0;

  public int pathSumOfAll(TreeNode root, int targetSum) {
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);
    helperSum(root, 0, targetSum, map);
    return c;
  }

  void helperSum(TreeNode node, int currentSum, int target, Map<Integer, Integer> map) {
    if (node == null)
      return;
    currentSum += node.val;
    if (map.containsKey(currentSum - target)) {
      c += map.get(currentSum - target);
    }

    if (!map.containsKey(currentSum)) {
      map.put(currentSum, 1);
    } else {
      map.put(currentSum, map.get(currentSum) + 1);
    }
    helperSum(node.left, currentSum, target, map);
    helperSum(node.right, currentSum, target, map);
    map.put(currentSum, map.get(currentSum) - 1);
  }

  public TreeNode buildTree(int[] preorder, int[] inorder) {
    if (preorder.length == 0)
      return null;
    int r = preorder[0];
    int index = 0;
    for (int i = 0; i < inorder.length; i++) {
      if (inorder[i] == r) {
        index = i;
        break;
      }
    }
    TreeNode root = new TreeNode(r);
    root.left = buildTree(Arrays.copyOfRange(preorder, 1, index + 1), Arrays.copyOfRange(inorder, 0, index));
    root.right = buildTree(Arrays.copyOfRange(preorder, index + 1, preorder.length),
        Arrays.copyOfRange(inorder, index + 1, inorder.length));
    return root;
  }

  Map<Integer, TreeMap<Integer, List<Integer>>> map = new TreeMap<>();

  public List<List<Integer>> verticalTraversal(TreeNode root) {
    helper(root, 0, 0);
    List<List<Integer>> vertical = new ArrayList<>();
    for (int col : map.keySet()) {
      List<Integer> level = new ArrayList<>();
      for (List<Integer> list : map.get(col).values()) {
        Collections.sort(list);
        level.addAll(list);
      }
      vertical.add(level);
    }
    return vertical;
  }

  private void helper(TreeNode node, int col, int row) {
    if (node == null)
      return;
    map.putIfAbsent(col, new TreeMap<>());
    map.get(col).putIfAbsent(row, new ArrayList<>());
    map.get(col).get(row).add(node.val);
    helper(node.left, col - 1, row + 1);
    helper(node.right, col + 1, row + 1);
  }

  TreeNode prev;
  TreeNode first;
  TreeNode second;

  public void recoverTree(TreeNode root) {
    helper3(root);
    int temp = first.val;
    first.val = second.val;
    second.val = temp;
  }

  private void helper3(TreeNode node) {
    if (node == null)
      return;
    helper(node.left);

    if (prev != null && prev.val > node.val) {
      if (first == null)
        first = prev;
      second = node;
    }
    prev = node;

    helper(node.right);
  }

  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    StringBuffer sb = new StringBuffer();
    helper4(root, sb);
    System.out.println(sb);
    return sb.toString();
  }

  void helper4(TreeNode node, StringBuffer sb) {
    if (node == null) {
      sb.append("#");
      sb.append(" ");
    }
    sb.append(String.valueOf(node.val));
    sb.append(" ");
    helper4(node.left, sb);
    helper4(node.right, sb);
  }

  // // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    String[] arrays = data.split(" ");
    List<String> list = new ArrayList<>(Arrays.asList(arrays));
    ;
    Collections.reverse(list);
    return helper5(list);
  }

  TreeNode helper5(List<String> list) {
    String s = list.remove(list.size() - 1);
    if (s.equals("#"))
      return null;
    TreeNode root = new TreeNode(Integer.parseInt(s));
    root.left = helper5(list);
    root.right = helper5(list);
    return root;
  }

  public boolean findTarget(TreeNode root, int k) {
    HashSet<Integer> set = new HashSet<>();
    return helper6(root, k, set);
  }

  boolean helper6(TreeNode node, int k, HashSet set) {
    if (node == null)
      return false;

    if (set.contains(k - node.val))
      return true;
    set.add(node.val);
    return helper6(node.left, k, set) || helper6(node.right, k, set);
  }

  public boolean isValidBST(TreeNode root) {
    return helper7(root, Long.MIN_VALUE, Long.MAX_VALUE);
  }

  private boolean helper7(TreeNode node, long lb, long ub) {
    if (node == null)
      return true;

    if (node.val <= lb || node.val >= ub)
      return false;

    return helper7(node.left, lb, node.val) && helper7(node.right, node.val, ub);
  }

  Stack<TreeNode> stack = new Stack<>();

  public void BSTIterator(TreeNode root) {
    pushAll(root);
  }

  public int next() {
    TreeNode temp = stack.pop();
    pushAll(temp.right);
    return temp.val;
  }

  public boolean hasNext() {
    return !stack.isEmpty();
  }

  void pushAll(TreeNode node) {
    while (node != null) {
      stack.push(node);
      node = node.left;
    }
  }

}
