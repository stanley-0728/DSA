package SegmentTree;

public class SegmentTree {
  private static final int DEFAULT_SIZE = 4;
  private int[] seg;

  public SegmentTree(int n) {
    this.seg = new int[DEFAULT_SIZE * n];
  }

  public void build(int index, int low, int high, int[] input) {
    if (low == high) {
      seg[index] = input[low];
      return;
    }
    int mid = low + (high - low) / 2;
    build(2 * index + 1, low, mid, input);
    build(2 * index + 2, mid + 1, high, input);
    seg[index] = Math.min(seg[2 * index + 1], seg[2 * index + 2]);
  }

  public int query(int index, int low, int high, int l, int r) {
    // 3cases
    // No overlap
    if (r < low || l > high)
      return Integer.MAX_VALUE;
    // exact overlap
    if (low >= l && high <= r)
      return seg[index];
    // partial overlap
    int mid = low + (high - low) / 2;
    return Math.min(query(2 * index + 1, low, mid, l, r), query(2 * index + 2, mid + 1, high, l, r));
  }

  public void update(int index, int low, int high, int i, int val) {
    // 3cases
    // exact match
    if (low == high) {
      seg[index] = val;
      return;
    }
    int mid = low + (high - low) / 2;
    if (i <= mid)
      update(2 * index + 1, low, mid, i, val);
    else
      update(2 * index + 2, mid + 1, high, i, val);
    seg[index] = Math.min(seg[2 * index + 1], seg[2 * index + 2]);
    return;
  }
}
