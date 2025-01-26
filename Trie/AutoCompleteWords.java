import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/*Design a search query autocomplete system for a search engine.

The users will input a sentence ( which may have multiple words and ends with special character '#').

For each character they type except '#', you need to return the top 3 previously entered and most frequently queried sentences that have prefix the same as the part of sentence already typed.

Here are the specific rules:

The frequency for a sentence is defined as the number of times a user typed the exactly same sentence before.
The returned top 3 sentences should be sorted by frequency (The first is the most frequent).  If several sentences have the same frequency, you need to use ASCII-code order (smaller one appears first).
If less than 3 valid sentences exist, then just return as many as you can.
When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
 

Your job is to implement the methods of the AutoCompleteSystem:

AutoCompleteSystem(String[] sentences, int[] times): This is the constructor. The input is previously used data. Sentences is a string array consists of previously typed sentences. Times is the corresponding times a sentence has been typed. Your system should record these historical sentences.
Now, the user wants to input a new sentence. The following function will provide the next character the user types:

String[] input(char c): The input c is the next character typed by the user. The character will only be lower-case letters ('a' to 'z'), blank space (' ') or a special character ('#'). Also, the previously typed sentence should be recorded in your system. The output an array will be the top 3 historical sentences that have prefix the same as the part of sentence already typed.
 

Example:

Operation:
AutoCompleteSystem(["i love you", "island",
"ironman", "i love geeksforgeeks"], [5,3,2,2])

The system have already tracked down the 
following sentences and their corresponding 
times: 
"i love you" : 5 times 
"island" : 3 times 
"ironman" : 2 times 
"i love geeksforgeeks" : 2 times 

Now, the user begins another search: 

Operation: input('i') 
Output: 
["i love you", "island","i love 
                       geeksforgeeks"] 

Explanation: 
There are four sentences that have prefix 
"i". Among them, "ironman" and "i love 
geeksforgeeks" have same frequency. Since 
' ' has ASCII code 32 and 'r' has ASCII code
 114, "i love geeksforgeeks" should be in 
front of "ironman". Also we only need to 
output top 3 most frequent sentences, so 
"ironman" will be ignored. 

Operation: input(' ') 
Output: ["i love you","i love geeksforgeeks"] 
Explanation: 
There are only two sentences that have prefix 
"i ". 

Operation: input('a') 
Output: [] 
Explanation: 
There are no sentences that have prefix "i a"

Operation: input('#') 
Output: [] 
Explanation: 
The user finished the input, the sentence 
"i a" should be saved as a historical 
sentence in system. And the next input 
will be counted as a new search. */
class AutoCompleteSystem {
  private Trie5 trie;
  private StringBuilder currentInput;

  public AutoCompleteSystem(String sentences[], int times[]) {
    trie = new Trie5();
    currentInput = new StringBuilder();
    for (int i = 0; i < sentences.length; i++) {
      trie.insert(sentences[i], times[i]);
    }
  }

  List<String> input(char c) {
    List<String> autocompleteResult = new ArrayList<>();
    if (c == '#') {
      trie.insert(currentInput.toString(), 1);
      currentInput = new StringBuilder();
      return autocompleteResult;
    }
    currentInput.append(c);
    Trie5.Node node = trie.search(currentInput.toString());
    if (node == null)
      return autocompleteResult;
    PriorityQueue<Trie5.Node> minHeap = new PriorityQueue<>((a, b) -> {
      return a.frequency == b.frequency ? b.word.compareTo(a.word) : a.frequency - b.frequency;
    });
    dfs(node, minHeap);
    while (!minHeap.isEmpty()) {
      autocompleteResult.add(0, minHeap.poll().word);
    }
    return autocompleteResult;
  }

  void dfs(Trie5.Node node, PriorityQueue<Trie5.Node> minHeap) {
    if (node == null)
      return;
    if (node.isEnd()) {
      minHeap.offer(node);
      if (minHeap.size() > 3)
        minHeap.poll();
    }
      for (int i = 0; i < 27; i++) {
        dfs(node.links[i], minHeap);
      }
    
  }
}

class Trie5 {
  public class Node {
    Node links[] = new Node[27];
    boolean flag = false;
    int frequency = 0;
    String word;

    void put(char ch, Node node) {
      int index = ch == ' ' ? 26 : ch - 'a';
      links[index] = node;

    }

    Node get(char ch) {
      int index = ch == ' ' ? 26 : ch - 'a';
      return links[index];
    }

    boolean isEnd() {
      return flag;
    }

    void setEnd() {
      flag = true;
    }

    void upDateFrequency(int i) {
      frequency += i;
    }

    boolean containsKey(char ch) {
      int index = ch == ' ' ? 26 : ch - 'a';
      return links[index] != null;
    }

    void setWord(String s) {
      word = s;
    }

    String getWord() {
      return word;
    }
  }

  private Node root;

  public Trie5() {
    root = new Node();
  }

  void insert(String word, int times) {
    Node node = root;
    for (char ch : word.toCharArray()) {
      if (!node.containsKey(ch)) {
        node.put(ch, new Node());
      }
      node = node.get(ch);
    }
    node.upDateFrequency(times);
    node.setWord(word);
    node.setEnd();
  }

  Node search(String prefix) {
    Node node = root;
    for (char ch : prefix.toCharArray()) {
      if (!node.containsKey(ch)) {
        return null;
      }
      node = node.get(ch);
    }
    return node;
  }

}

public class AutoCompleteWords {
  public static void main(String[] args) {
    // String[] sentences = { "i love you", "island", "i love coding", "i love java" };
    // int[] times = { 5, 3, 2, 4 };

    // AutoCompleteSystem autoCompleteSystem = new AutoCompleteSystem(sentences, times);

    // System.out.println("Input: i");
    // System.out.println("Suggestions: " + autoCompleteSystem.input('i'));

    // System.out.println("Input:  ");
    // System.out.println("Suggestions: " + autoCompleteSystem.input(' '));

    // System.out.println("Input: l");
    // System.out.println("Suggestions: " + autoCompleteSystem.input('l'));

    // System.out.println("Input: o");
    // System.out.println("Suggestions: " + autoCompleteSystem.input('o'));

    // System.out.println("Input: v");
    // System.out.println("Suggestions: " + autoCompleteSystem.input('v'));

    // System.out.println("Input: e");
    // System.out.println("Suggestions: " + autoCompleteSystem.input('e'));

    // // Finish the input with '#', indicating the end of the current sentence
    // System.out.println("Input: #");
    // System.out.println("Suggestions: " + autoCompleteSystem.input('#'));

    // // Start a new query
    // System.out.println("Input: i");
    // System.out.println("Suggestions: " + autoCompleteSystem.input('i'));

    // System.out.println("Input: l");
    // System.out.println("Suggestions: " + autoCompleteSystem.input('l'));

    // System.out.println("Input: o");
    // System.out.println("Suggestions: " + autoCompleteSystem.input('o'));

    // System.out.println("Input: v");
    // System.out.println("Suggestions: " + autoCompleteSystem.input('v'));

    // System.out.println("Input: e");
    // System.out.println("Suggestions: " + autoCompleteSystem.input('e'));

    // System.out.println("Input: #");
    // System.out.println("Suggestions: " + autoCompleteSystem.input('#'));

    String s = "abc";
    String s1 = "acd";
    System.out.println(s1.compareTo(s));
    System.out.println(s.compareTo(s1));

  }
}
