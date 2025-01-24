package FirstTutorial;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    System.out.println("Hello World");

    Scanner sc=new Scanner(System.in);
    // System.out.println(sc.next());
    binarySearch();
    Arrays();
  }
  static void binarySearch() {
    int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int key = 3;
    int index = Arrays.binarySearch(arr, key);
    System.out.println(index);
  }

  static void Arrays(){
    int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int[] arr2 ={1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    System.out.println(Arrays.equals(arr, arr2));
    System.out.println(Arrays.toString(arr));
    System.out.println(Arrays.toString(arr2));
    System.out.println(Arrays.hashCode(arr));
    System.out.println(Arrays.hashCode(arr2));
    // Arrays.fill(arr2,0) ;
  //   System.out.println(Arrays.toString(arr2));
  //  System.out.println( Arrays.compare(arr, arr2));
  //  System.out.println(Arrays.mismatch(arr, arr2) );
  }

}
