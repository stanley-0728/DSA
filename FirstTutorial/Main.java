package FirstTutorial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    // System.out.println("Hello World");

    // Scanner sc=new Scanner(System.in);
    // // System.out.println(sc.next());
    // binarySearch();
    Arrays();
  }

  static void binarySearch() {
    int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    int key = 3;
    int index = Arrays.binarySearch(arr, key);
    System.out.println(index);
  }

  static void Arrays() {
    // int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    // int[] arr2 ={1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    // System.out.println(Arrays.equals(arr, arr2));
    // System.out.println(Arrays.toString(arr));
    // System.out.println(Arrays.toString(arr2));
    // System.out.println(Arrays.hashCode(arr));
    // System.out.println(Arrays.hashCode(arr2));
    // Arrays.fill(arr2,0) ;
    // System.out.println(Arrays.toString(arr2));
    // System.out.println( Arrays.compare(arr, arr2));
    // System.out.println(Arrays.mismatch(arr, arr2) );

    Map<String, Integer> mp = new HashMap<>();
    mp.put("dpple", 2);
    mp.put("banana", 2);
    mp.put("cherry", 2);
    mp.put("date",2);
    // System.out.println(mp.entrySet());
    // for(Map.Entry<String,Integer> e : mp.entrySet()){
    // System.out.println(e.getKey()+" "+ e.getValue());
    // }

    List<Map.Entry<String, Integer>> l = new ArrayList<Map.Entry<String, Integer>>(
        mp.entrySet());

        // Collections.sort(l, new Comparator<Map.Entry<String,Integer>>() {
        //   public int compare(Map.Entry<String, Integer> o1, 
        //                        Map.Entry<String, Integer> o2){
        //                         return o1.getValue() - o2.getValue();
        //   }
        // });
        // System.out.println(l);

        Collections.sort(l, (o1, o2)->{
            return o2.getValue() == o1.getValue() ? o2.getKey().compareTo(o1.getKey()) : o2.getValue() -o1.getValue();
        });
        System.out.println(l);
  }

}
