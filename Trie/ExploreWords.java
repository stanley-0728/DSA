import java.util.ArrayList;
import java.util.List;

public class ExploreWords {
  public static void main(String[] args) {
    Trie4 trie = new Trie4();
    trie.insert("bad");
    trie.insert("dad");
    trie.insert("mad");
    trie.insert("pad");
    System.out.println(trie.search(".ad"));
  }
}

class Trie4{
  public class Node{
    Node links[] = new Node[26];
    boolean flag = false;
    String word ;
    boolean containsKey(char ch){
      return links[ch-'a'] != null;
    }
    void put(char ch , Node node){
      links[ch-'a'] = node ;
    }
    Node get(char ch){
      return links[ch-'a'];
    }
    void setEnd(){
      flag = true;
    }
    void setEndWord(String s){
      word = s;
    }
    boolean isEnd(){
      return flag;
    }
    String getEndWord(){
      return word;
    }
    boolean checkChildExist(int i){
      return links[i] != null;
    }
  }
  private Node root;
  public Trie4(){
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
    node.setEndWord(word);
  }

  public boolean search(String word) {
    Node node = root;
    // for (int i = 0; i < word.length(); i++) {
    //   if (!node.containsKey(word.charAt(i))) {
    //     // If a letter is not found,
    //     // the word is not in the Trie
    //     return false;
    //   }
    //   // Move to the next node
    //   node = node.get(word.charAt(i));
    // }
    // // Check if the last node
    // // marks the end of a word
    // return node.isEnd();
    return dfs(node, word, 0);
  }
  
  public List<String> startsWith(String prefix) {
    Node node = root;
    for (int i = 0; i < prefix.length(); i++) {
      if (!node.containsKey(prefix.charAt(i))) {
        // If a letter is not found, there is
        // no word with the given prefix
        return new ArrayList<>();
      }
      // Move to the next node
      node = node.get(prefix.charAt(i));
    }
    // Explore all words for this node 
    List<String> list = new ArrayList<>();
    list.addAll(dfs(node));
    return list;
  }

  List<String> dfs(Node node){
    if(node==null) return new ArrayList<>();
    if(node.isEnd()){
      List<String> list = new ArrayList<>();
      list.add(node.getEndWord());
      return list;
    }
    List<String> list = new ArrayList<>();
    for(int i=0; i<26; i++){
      list.addAll( dfs(node.links[i]));
    }
    return list;
  }
  boolean dfs(Node node,String word,int i){
    if(i>=word.length()) return node.isEnd();
    char ch = word.charAt(i);
    if(ch =='.'){
      boolean flag=false;
      for(int j=0;j<26;j++){
        if(node.checkChildExist(j)){
               flag=flag || dfs(node.links[j],word,i+1);

        }
      }
      return flag;
    }
    else{
      if(!node.containsKey(ch)) return false;
      return dfs(node.get(ch),word,i+1);
    }
  }
}