package BitMasking;

import java.util.ArrayList;
import java.util.List;

public class SubSet {
  public static void main(String[] args) {
    System.out.println(6&1<<0);
    int N = 3;
    int arr[] = {
      10,
      12,
      12
    };
    subSet(arr, N);
  }

   static void subSet(int arr[], int N) {

    List < String > list = new ArrayList < > ();
    int size = (int) Math.pow((double) 2, (double) N);
    System.out.println("size" + size);
    for (int i = 0; i < size; i++) {
      String s = "";
      for (int j = 0; j < N; j++) {
        if ((i & (1 << j)) > 0)
          s += arr[j] + " ";
      }

      if (!(list.contains(s))) {
        list.add(s);
      }
    }
    System.out.println(list);

    for (int ii = 0; ii < list.size(); ii++) {
      String s = list.get(ii);

      String str[] = s.split(" ");
      System.out.print("{ ");
      for (int jj = 0; jj < str.length; jj++) {
        if (ii == 0)
          System.out.print(str[jj]);
        else
          System.out.print(Integer.parseInt(str[jj]) + " ");
      }
      System.out.print(" }\n");
    }
  }

}
// 0 - 000 no element
// 1 - 001 1st
// 2 - 010 2nd
// 3 - 011 1st 2nd
// 4 - 100
// 5 - 101
// 6 - 110
// 7 - 111
