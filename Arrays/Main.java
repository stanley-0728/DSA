package Arrays;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc=new Scanner(System.in);
    int[ ] arr=new int[5];
    for(int i=0;i<arr.length;i++){
      arr[i]=sc.nextInt();
    }
    System.out.println(Arrays.toString(arr));
    int[] arr2= {1,2,34,56};
    System.out.println(Arrays.equals(arr,arr2));

  }
}
