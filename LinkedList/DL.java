package LinkedList;

public class DL {
  private Node head;

  public void insertFirst(int val) {
    Node node = new Node(val);
    node.next = head;
    node.prev = null;
    if (head != null)
      head.prev = node;
    head = node;

  }

  public void insertAtLast(int val) {
    Node node = new Node(val);
    Node last = head;
    if (head == null) {
      node.prev = null;
      head = node;
    }
    while (last.next != null)
      last = last.next;
    last.next = node;
    node.prev = last;
    node.next = null;
  }

  public void insertAfterVal(int val, int value) {
    Node p = findNodeByVal(val);
    if (p == null)
      System.out.println("No value found");
    Node node = new Node(value);
    node.next = p.next;
    p.next = node;
    node.prev = p;
    if (node.next != null) {
      node.next.prev = node;
    }
  }

  public Node findNodeByVal(int val) {
    Node temp = head;
    while (temp != null) {
      if (temp.val == val)
        return temp;
      temp = temp.next;
    }
    return null;
  }

  public class Node {
    private int val;
    private Node next;
    private Node prev;

    public Node(int val) {
      this.val = val;
    }

    public Node(int val, Node next, Node prev) {
      this.val = val;
      this.next = next;
      this.prev = prev;
    }
  }

}
