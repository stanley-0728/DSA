package SegmentTree;

public class Implement {
  public static void main(String[] args) {
    SegmentTree st = new SegmentTree(6);
    int[] a = { 1, 2, 3, 4, 5, 6 };
    st.build(0, 0, 5, a);
    System.out.println(st.query(0, 0, 5, 1, 2));
  }
}
