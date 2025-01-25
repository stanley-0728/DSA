package Trie;

/*
 * Time Complexity:

  Insertion: O(N) where N is the length of the word being inserted. This is because we have to iterate over each letter of the word to find its corresponding node or create a node accordingly.
  Search: O(N) where N is the length of the word being searched for. This is because in Trie search we traverse over each letter for the word from the root, checking if the current node contains a node at the index of the next letter. This process repeats until we reach the end of the word or encounter a node without the next letter.
  Prefix Search: O(N) where N is the length of the prefix being searched for. Similar to searching for words, in prefix search we also iterate over each letter of the word to find its corresponding node.
  Space Complexity: O(N) where N is the total number of characters across all unique words inserted into the Trie. For each character in a word, a new node may need to be created leading to space proportional to the number of characters.
 */
public class Trie {
  public class Node {
    Node[] links = new Node[26];
    boolean flag = false;

    boolean containsKey(char ch) {
      return links[ch - 'a'] != null;
    }

    void put(char ch, Node node) {
      links[ch - 'a'] = node;
    }

    Node get(char ch) {
      return links[ch - 'a'];
    }

    void setEnd() {
      flag = true;
    }

    boolean isEnd() {
      return flag;
    }
  }

  private Node root;

  public Trie() {
    root = new Node();
  }

  public void insert(String word) {
    Node node = root;
    for (int i = 0; i < word.length(); i++) {
      if (!node.containsKey(word.charAt(i))) {
        // Create a new node for
        // the letter if not present
        node.put(word.charAt(i), new Node());
      }
      // Move to the next node
      node = node.get(word.charAt(i));
    }
    // Mark the end of the word
    node.setEnd();
  }

  public boolean search(String word) {
    Node node = root;
    for (int i = 0; i < word.length(); i++) {
      if (!node.containsKey(word.charAt(i))) {
        // If a letter is not found,
        // the word is not in the Trie
        return false;
      }
      // Move to the next node
      node = node.get(word.charAt(i));
    }
    // Check if the last node
    // marks the end of a word
    return node.isEnd();
  }

  public boolean startsWith(String prefix) {
    Node node = root;
    for (int i = 0; i < prefix.length(); i++) {
      if (!node.containsKey(prefix.charAt(i))) {
        // If a letter is not found, there is
        // no word with the given prefix
        return false;
      }
      // Move to the next node
      node = node.get(prefix.charAt(i));
    }
    // The prefix is found in the Trie
    return true;
  }
}
