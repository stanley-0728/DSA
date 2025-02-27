package Tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

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
  public int val;
  public Node left;
  public Node right;
  public Node next;

  public Node() {
  }

  public Node(int _val) {
    val = _val;
  }

  public Node(int _val, Node _left, Node _right, Node _next) {
    val = _val;
    left = _left;
    right = _right;
    next = _next;
  }
};

public class BFS {
  public List<List<Integer>> levelOrder(TreeNode root) {
    if (root == null)
      return null;
    List<List<Integer>> ans = new ArrayList<>();
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      int levelSize = queue.size();
      List<Integer> currentList = new ArrayList<>();
      for (int i = 0; i < levelSize; i++) {
        TreeNode node = queue.poll();
        currentList.add(node.val);
        if (node.left != null)
          queue.add(node.left);
        if (node.right != null)
          queue.add(node.right);
      }
      ans.add(currentList);
    }
    return ans;
  }

  public List<Double> averageOfLevels(TreeNode root) {
    List<Double> ans = new ArrayList<>();
    if (root == null)
      return ans;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      int levelSize = queue.size();
      int levelSum = 0;
      for (int i = 0; i < levelSize; i++) {
        TreeNode node = queue.poll();
        levelSum += node.val;
        if (node.left != null)
          queue.add(node.left);
        if (node.right != null)
          queue.add(node.right);
      }
      ans.add((double) levelSum / levelSize);
    }
    return ans;
  }

  public TreeNode findSuccessor(TreeNode root, int key) {
    if (root == null)
      return root;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      TreeNode node = queue.poll();
      if (node.left != null)
        queue.add(node.left);
      if (node.right != null)
        queue.add(node.right);
      if (node.val == key)
        break;
    }
    return queue.peek();
  }

  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> ans = new ArrayList<>();
    if (root == null)
      return ans;
    Deque<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    boolean reverse = false;
    while (!queue.isEmpty()) {
      int levelSize = queue.size();
      List<Integer> currentList = new ArrayList<>();
      for (int i = 0; i < levelSize; i++) {
        if (!reverse) {
          TreeNode node = queue.pollFirst();
          currentList.add(node.val);
          if (node.left != null)
            queue.addLast(node.left);
          if (node.right != null)
            queue.addLast(node.right);
        } else {
          TreeNode node = queue.pollLast();
          currentList.add(node.val);
          if (node.left != null)
            queue.addFirst(node.right);
          if (node.right != null)
            queue.addFirst(node.left);
        }
      }
      reverse = !reverse;
      ans.add(currentList);
    }
    return ans;
  }

  public List<Integer> rightSideView(TreeNode root) {
    List<Integer> ans = new ArrayList<>();
    if (root == null)
      return ans;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      int levelSize = queue.size();

      for (int i = 0; i < levelSize; i++) {
        TreeNode node = queue.poll();
        if (i == levelSize - 1)
          ans.add(node.val);
        if (node.left != null)
          queue.add(node.left);
        if (node.right != null)
          queue.add(node.right);
      }
    }
    return ans;
  }

  public boolean isSymmetric(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root.left);
    queue.add(root.right);
    while (!queue.isEmpty()) {
      TreeNode left = queue.poll();
      TreeNode right = queue.poll();
      if (left == null && right == null)
        continue;
      if (left == null || right == null)
        return false;
      if (left.val != right.val)
        return false;
      queue.add(left.left);
      queue.add(right.right);
      queue.add(left.right);
      queue.add(right.left);
    }
    return true;
  }

  public Node connect(Node root) {
    if (root == null)
      return null;
    Node leftMost = root;
    while (leftMost.left != null) {
      Node current = leftMost;
      while (current != null) {
        current.left.next = current.right;
        if (current.next != null) {
          current.right.next = current.next.left;
        }
        current = current.next;
      }
      leftMost = leftMost.left;
    }
    return root;
  }

  public boolean isCousins(TreeNode root, int x, int y) {
    TreeNode xx = findNode(root, x);
    TreeNode yy = findNode(root, y);

    return (level(root, xx, 0) == level(root, yy, 0) && (!isSibling(root, xx, yy)));
  }

  TreeNode findNode(TreeNode node, int val) {
    if (node == null)
      return null;
    if (node.val == val)
      return node;
    TreeNode n = findNode(node.left, val);
    if (n != null)
      return n;
    return findNode(node.right, val);
  }

  boolean isSibling(TreeNode node, TreeNode x, TreeNode y) {
    if (node == null)
      return false;
    return (node.left == x && node.right == y) || (node.left == y && node.right == x) ||
        isSibling(node.left, x, y) || isSibling(node.right, x, y);
  }

  int level(TreeNode node, TreeNode x, int level) {
    if (node == null)
      return 0;
    if (node == x)
      return level;
    int l = level(node.left, x, level + 1);
    if (l != 0)
      return l;
    return level(node.right, x, level + 1);
  }

  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    Set<String> wordSet = new HashSet<>(wordList);

    if (!wordSet.contains(endWord))
      return 0;
    int length = 0;
    Queue<String> queue = new LinkedList<>();
    Set<String> visited = new HashSet<>();
    queue.add(beginWord);
    visited.add(beginWord);
    while (!queue.isEmpty()) {
      int size = queue.size();
      length++;
      for (int i = 0; i < size; i++) {
        String current = queue.poll();
        for (int j = 0; j < current.length(); j++) {
          char[] temp = current.toCharArray();
          for (char ch = 'a'; ch <= 'z'; ch++) {
            temp[j] = ch;
            String newWord = new String(temp);
            if (newWord.equals(endWord)) {
              return length + 1;
            }
            if (wordSet.contains(newWord) && !visited.contains(newWord)) {
              System.out.println(newWord);
              queue.offer(newWord);
              visited.add(newWord);
            }
          }
        }
      }
    }
    return 0;
  }
}
