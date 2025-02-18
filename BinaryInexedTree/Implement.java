package BinaryInexedTree;

public class Implement {
  // Construct binary Indexed Tree and perform Range queries .
  public static void main(String[] args) {
    BIT bit = new BIT(5); // Initialize BIT for 5 elements

    bit.update(1, 3); // BIT: [3, 0, 0, 0, 0]
    bit.update(2, 2); // BIT: [3, 2, 0, 0, 0]
    bit.update(3, -1); // BIT: [3, 2, -1, 0, 0]
    bit.update(4, 6); // BIT: [3, 2, -1, 6, 0]
    bit.update(5, 5); // BIT: [3, 2, -1, 6, 5]

    System.out.println("Prefix sum up to index 3: " + bit.query(3)); // 3 + 2 - 1 = 4
    System.out.println("Sum from index 2 to 4: " + bit.rangeQuery(2, 4)); // 2 + (-1) + 6 = 7
  }
}
