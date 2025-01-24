package Sorting;

import java.util.Arrays;

public class CycleSort {
  public static void main(String[] args) {
    int[] arr={2,5,3,1,4};
    /* 
     * What is cycle sort?
     * Bubble, selection,insertion sorts have taken time of o(n^2) in worst case what if i want to sort this in only single pass also cyclic sort is can only be used if given an arr range 1 to N only 
     */
    System.out.println(Arrays.toString(cyclicSort(arr)));
  }
  static int[] cyclicSort(int[] arr){
    int i=0;
    while(i<arr.length){
      int correctIndex =arr[i]-1;
      if(arr[i]!=arr[correctIndex]){
        swap(arr, i, correctIndex);
      }
      else 
        i++;

    }
    return arr;
  }
  static void swap(int[] arr,int a,int b){
    int temp =arr[a];
    arr[a]=arr[b];
    arr[b]=temp;
  }
  
}
