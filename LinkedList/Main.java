package LinkedList;

public class Main {
  public static void main(String[] args) {
    LL ll = new LL();
    ll.insertAtFirst(1);
    ll.insertAtFirst(3);
    ll.insertAtFirst(5);
    ll.display();
    ll.insertAtLast(10);
    ll.display();
    ll.insert(6, 3);
    ll.display();
    System.out.println(ll.deleteFirst());
  }
}
