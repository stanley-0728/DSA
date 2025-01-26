
public class CompleteString {
  public static void main(String[] args) {
    /*
     * Longest String with All Prefixes
     * Problem Statement: Given an array of strings, 'A', of size 'N', each element
     * being a string. A string is considered complete if every prefix of this
     * string is also present in the array 'A'.
     * 
     * Find the longest complete string in the array 'A'. If there are multiple
     * strings with the same length, return the lexicographically smallest one. If
     * no such string exists, return "None".
     * 
     * Note: A string 'P' is lexicographically smaller than string 'Q' if:
     * 
     * There exists some index 'i' such that for all 'j' < 'i', 'P[j] = Q[j]' and
     * 'P[i] < Q[i]'. For example, "apple" < "banana".
     * If 'P' is a prefix of the string 'Q', for instance, "car" < "carriage".
     * Example 1:
     * Input:Input Words: [‘apple’, ‘appl’, ‘applet’]
     * Output: Complete String: ‘applet’
     */
    String[] words = { "a", "banana", "app", "appl", "ap", "apply", "apple" };
    Trie trie = new Trie();
    for (String s : words) {
      trie.insert(s);
    }
    String longest = "";
    for (int i = 0; i < words.length; i++) {
      boolean f = trie.checkPrefix(words[i]);
      if (f) {
        System.out.println(f);
        if (words[i].length() == longest.length() && words[i].compareTo(longest) < 0) {
          longest = words[i];
        } else if (words[i].length() > longest.length()) {
          longest = words[i];
        }
      }
    }
    System.out.println(longest);
  }
}