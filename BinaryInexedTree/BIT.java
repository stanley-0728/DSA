package BinaryInexedTree;

public class BIT {
  int[] bit;
  int size = 0;

  public BIT(int n) {
    bit = new int[n + 1];
    this.size = n;
  }

  public void update(int index, int val) {
    while (index <= size) {
      bit[index] += val;
      index += (index & -index);
    }
  }

  public int query(int index) {
    int sum = 0;
    while (index > 0) {
      sum += bit[index];
      index -= (index & -index);
    }
    return sum;
  }

  public int rangeQuery(int l, int r) {
    return query(r) - query(l - 1);
  }
}
