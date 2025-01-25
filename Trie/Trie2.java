package Trie;

public class Trie2 {
  public class Node {
    Node[] links = new Node[26];
    int cntEndWith = 0;
    int cntPrefix = 0;

    boolean containsKey(char ch) {
      return links[ch - 'a'] != null;
    }

    void put(char ch, Node node) {
      links[ch - 'a'] = node;
    }

    void increasePrefix() {
      cntPrefix++;
    }

    void increaseEnd() {
      cntEndWith++;
    }

    Node get(char ch) {
      return links[ch - 'a'];
    }

    void deleteEnd() {
      cntEndWith--;
    }

    void reducePrefix() {
      cntPrefix--;
    }
  }

  private Node root;

  public Trie2() {
    root = new Node();
  }

  public void insert(String word) {
    Node node = root;
    for (int i = 0; i < word.length(); i++) {
      if (!node.containsKey(word.charAt(i))) {
        node.put(word.charAt(i), new Node());
      }
      node = node.get(word.charAt(i));
      node.increasePrefix();
    }
    node.increaseEnd();
  }

  int countWordsEqualTo(String word) {
    Node node = root;
    for (int i = 0; i < word.length(); i++) {
      if (node.containsKey(word.charAt(i))) {
        node = node.get(word.charAt(i));
      } else {
        System.out.println(word.charAt(i));

        return 0;
      }
    }
    System.out.println(node.cntEndWith);
    return node.cntEndWith;
  }

  int countWordsStartingWith(String word) {
    Node node = root;
    for (int i = 0; i < word.length(); i++) {
      if (node.containsKey(word.charAt(i))) {
        node = node.get(word.charAt(i));
      } else {

        return 0;
      }
    }
    return node.cntPrefix;
  }

  void erase(String word) {
    Node node = root;
    for (int i = 0; i < word.length(); i++) {
      if (node.containsKey(word.charAt(i))) {
        node = node.get(word.charAt(i));
        node.reducePrefix();
      } else {
        return;
      }
    }
    node.deleteEnd();
  }

}
