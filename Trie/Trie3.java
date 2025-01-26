public class Trie3 {
  public class Node{
    Node links[] = new Node[2];
    boolean flag = false;
    Node get(int i){
      return links[i];
    }
    void put(int i,Node node){
      links[i] = node;
    }
    void setEnd(){
      flag = true;
    }
    boolean isEnd(){
      return flag;
    }
    boolean containsKey(int i){
      return links[i]!=null;
    }
  }
  private Node root;

  public Trie3(){
    root = new Node();
  }
  void insert(int num){
    Node node = root;
    for(int i=31;i>=0;i--){
      int bit = (num>>i) & 1;
      if(!node.containsKey(bit)){
        node.put(bit, new Node());
      }
      node = node.get(bit);
    }
  }

  int getMax(int num){
    Node node = root;
    int ans = 0;
    for(int i=31;i>=0;i--){
      int bit = (num>>i) & 1;
      if(node.containsKey(1-bit)){
        ans = ans | (1<<i);
        node = node.get(1-bit);
      }
      else{
        node = node.get(bit);
      }
    }
    return ans;
  }
}
