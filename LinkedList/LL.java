package LinkedList;

// most of the linked list questions goes with either slow and fast pointer or reverse the linked list .
// Whenever cycle appears in arrays or linkedlist apply slow and fast pointer . It is used in cycle detection and find midddle element or any node
// Reversing Linked List can slove multiple problems 
public class LL {
  private Node head;
  private Node tail;
  private int size;

  public LL() {
    size = 0;
    head = null;
    tail = null;
  }

  public void insertAtFirst(int val) {
    Node node = new Node(val);
    node.next = head;
    head = node;
    if (tail == null) {
      tail = head;
    }
    size++;
  }

  public void display() {
    Node node = head;
    while (node != null) {
      System.out.print(node.val + "->");
      node = node.next;
    }
    System.out.print("End");
  }

  public void insertAtLast(int val) {
    Node node = new Node(val);
    if (tail == null) {
      insertAtFirst(val);
    }
    tail.next = node;
    tail = node;
    size++;
  }

  public void insert(int val, int index) {
    if (index == 0) {
      insertAtFirst(val);
      return;
    }
    if (index == size) {
      insertAtLast(val);
      return;
    }
    Node temp = head;
    for (int i = 1; i < index - 1; i++) {
      temp = temp.next;
    }
    Node node = new Node(val, temp.next);
    temp.next = node;
    size++;

  }

  public int deleteFirst() {
    int val = head.val;
    head = head.next;
    if (head == null) {
      tail = null;
    }
    size--;
    return val;
  }

  public int deleteLast() {
    if (size <= 1)
      return deleteFirst();
    Node prev = get(size - 2);
    int val = prev.next.val;
    tail = prev;
    tail.next = null;
    size--;
    return val;
  }

  public int delete(int index) {
    if (size <= 1)
      return deleteFirst();
    Node prev = get(index - 1);
    int val = prev.next.val;
    prev.next = prev.next.next;
    size--;
    return val;
  }

  public Node get(int index) {
    Node temp = head;
    for (int i = 0; i < index; i++) {
      temp = temp.next;
    }
    return temp;
  }

  public void insertRec(int val, int index) {
    head = insertRec(val, index, head);
  }

  private Node insertRec(int val, int index, Node node) {
    if (index == 0) {
      Node temp = new Node(val, node);
      size++;
      return temp;
    }
    node.next = insertRec(val, index--, node.next);
    return node;
  }

  public void duplicates() {
    Node node = head;
    while (node.next != null) {
      if (node.val == node.next.val) {
        node.next = node.next.next;
        size--;
      } else
        node = node.next;

    }
    tail = node;
    tail.next = null;

  }

  // merge two linked list into one single list

  public void merge(LL first, LL second) {
    Node f = first.head;
    Node s = second.head;
    LL ans = new LL();
    while (f != null && s != null) {
      if (f.val < s.val) {
        ans.insertAtLast(f.val);
        f = f.next;
      } else
        ans.insertAtLast(s.val);
      s = s.next;
    }
    while (f != null) {
      ans.insertAtLast(f.val);
      f = f.next;
    }
    while (s != null) {
      ans.insertAtLast(s.val);
      s = s.next;
    }
  }

  public boolean detectCycle() {
    Node slow = head;
    Node fast = head;
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
      if (fast == slow)
        return true;
    }
    return false;
  }

  public int lengthCycle() {
    Node slow = head;
    Node fast = head;
    int length = 0;
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
      if (fast == slow) {
        Node temp = slow;
        do {
          temp = temp.next;
          length++;
        } while (temp != fast);
      }
      return length;
    }
    return 0;
  }

  public Node detectCycle(Node head) {
    Node slow = head;
    Node fast = head;
    int length = 0;
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
      if (fast == slow) {
        length = lengthCycle(slow);
        break;
      }
    }
    if (length == 0)
      return null;
    Node f = head;
    Node s = head;
    while (length > 0) {
      s = s.next;
      length--;
    }
    while (f != s) {
      f = f.next;
      s = s.next;
    }

    return s;
  }

  public int lengthCycle(Node slow) {
    Node temp = slow;
    int length = 0;
    do {
      temp = temp.next;
      length++;
    } while (temp != slow);
    return length;
  }

  public Node middleNode(Node head) {

    Node slow = head;
    Node fast = head;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;

  }

  public void reverse(Node node) {
    if (node == tail) {
      head = tail;
      return;
    }
    reverse(node.next);
    tail.next = node;
    tail = node;
    tail.next = null;
  }

  public Node reverseList(Node head) {
    if (head == null || head.next == null)
      return head;
    Node prev = null;
    Node current = head;
    Node next = current.next;

    while (current != null) {
      current.next = prev;
      prev = current;
      current = next;
      if (next != null)
        next = next.next;
    }
    return prev;
  }

  public Node reverseBetween(Node head, int left, int right) {

    Node prev = null;
    Node current = head;
    for (int i = 1; i < left; i++) {
      prev = current;
      current = current.next;
    }
    Node last = prev;
    Node newEnd = current;
    Node next = current.next;
    for (int i = 0; i < right - left + 1; i++) {
      current.next = prev;
      prev = current;
      current = next;
      if (next != null)
        next = next.next;
    }
    if (last != null)
      last.next = prev;
    else
      head = prev;
    newEnd.next = current;
    return head;
  }

  public boolean isPalindrome(Node head) {
    if (head == null)
      return false;
    Node mid = middleNode(head);
    Node headSecond = reverseList(mid);
    Node retrive = headSecond;
    while (head != null && headSecond != null) {
      if (head.val != headSecond.val) {
        break;
      }
      head = head.next;
      headSecond = headSecond.next;
    }
    reverseList(retrive);
    if (head == null || headSecond == null)
      return true;
    return false;

  }

  public void reorderList(Node head) {
    Node mid = middleNode(head);
    System.out.println(mid.val);
    Node second = reverseList(mid);
    Node first = head;
    while (first != null && second != null) {
      Node temp = first.next;
      first.next = second;
      first = temp;
      temp = second.next;
      second.next = first;
      second = temp;
    }
    if (first != null)
      first.next = null;

  }

  public Node rotateRight(Node head, int k) {
    if (head == null || head.next == null || k <= 0)
      return head;
    Node tail = head;
    int length = 1;
    while (tail.next != null) {
      tail = tail.next;
      length++;
    }
    int rotations = k % length;
    int skip = length - rotations;
    tail.next = head;
    Node newLast = head;
    for (int i = 0; i < skip - 1; i++)
      newLast = newLast.next;
    head = newLast.next;
    newLast.next = null;
    return head;

  }

  public Node reverseKGroup(Node head, int k) {
    Node prev = null;
    Node current = head;
    Node tail = head;
    int length = 1;
    while (tail.next != null) {
      tail = tail.next;
      length++;
    }
    if (length < k)
      return head;

    while (true) {
      Node last = prev;
      Node newEnd = current;
      Node next = current.next;
      for (int i = 0; current != null && i < k; i++) {
        current.next = prev;
        prev = current;
        current = next;
        if (next != null)
        
          next = next.next;
      }
      if (last != null)
        last.next = prev;
      else
        head = prev;
      newEnd.next = current;
      prev = newEnd;
      length -= k;
      if (length < k)
        break;
      if (current == null)
        break;
    }
    return head;

  }

  public Node reverseKAlternaNodes(Node head, int k) {
    Node prev = null;
    Node current = head;
    Node tail = head;
    int length = 1;
    while (tail.next != null) {
      tail = tail.next;
      length++;
    }
    if (length < k)
      return head;

    while (current != null) {
      Node last = prev;
      Node newEnd = current;
      Node next = current.next;
      for (int i = 0; current != null && i < k; i++) {
        current.next = prev;
        prev = current;
        current = next;
        if (next != null)
          next = next.next;
      }
      if (last != null)
        last.next = prev;
      else
        head = prev;
      newEnd.next = current;
      length -= k;
      if (length < k)
        break;
      for (int i = 0; current != null && i < k; i++) {
        prev = current;
        current = current.next;
      }
    }
    return head;

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
