package Trie;

public class Basic {
  public static void main(String[] args) {
    /*
     * Implement trie with insert,search and startsWith methods
     */
    // Trie trie = new Trie();
    // System.out.println("Inserting words: Striver, Striving, String, Strike");
    // trie.insert("striver");
    // trie.insert("striving");
    // trie.insert("string");
    // trie.insert("strike");

    // System.out.println("Search if Strawberry exists in trie: " +
    //     (trie.search("strawberry") ? "True" : "False"));

    // System.out.println("Search if Strike exists in trie: " +
    //     (trie.search("strike") ? "True" : "False"));

    // System.out.println("If words in Trie start with Stri: " +
    //     (trie.startsWith("stri") ? "True" : "False"));

    /*
     * Implement Trie - II
     * Problem Statement: Implement a Trie data structure that supports the
     * following methods:
     * Insert (word): To insert a string `word` in the Trie.
     * Count Words Equal To (word): Return the count of occurrences of the string
     * word in the Trie.
     * Count Words Starting With (prefix): Return the count of words in the Trie
     * that have the string “prefix” as a prefix.
     * Erase (word): Delete one occurrence of the string word from the Trie.
     */

     Trie2 trie = new Trie2();
    trie.insert("apple");
    trie.insert("app");
    System.out.println("Inserting strings 'apple', 'app' into Trie");
    System.out.print("Count Words Equal to 'apple': ");
    System.out.println(trie.countWordsEqualTo("apple"));
    System.out.print("Count Words Starting With 'app': ");
    System.out.println(trie.countWordsStartingWith("app"));
    System.out.println("Erasing word 'app' from trie");
    trie.erase("app");
    System.out.print("Count Words Equal to 'apple': ");
    System.out.println(trie.countWordsEqualTo("apple"));
    System.out.print("Count Words Starting With 'apple': ");
    System.out.println(trie.countWordsStartingWith("app"));
  }
}
