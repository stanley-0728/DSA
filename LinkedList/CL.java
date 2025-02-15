package LinkedList;

public class CL {
  private Node head;
  private Node tail;

  public CL() {
    this.head = null;
    this.tail = null;
  }

  public void insert(int val) {
    Node node = new Node(val);
    if (head == null) {
      head = node;
      tail = node;
    }
    tail.next = node;
    node.next = head;
    tail = head;
  }

  public class Node {
    private int val;
    private Node next;

    public Node(int val) {
      this.val = val;
    }

    public Node(int val, Node next) {
      this.val = val;
      this.next = next;
    }
  }
}
