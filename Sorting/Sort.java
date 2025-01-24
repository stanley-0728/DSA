package Sorting;

import java.util.Arrays;

public class Sort {
  public static void main(String[] args) {
    int[] arr={5,4,3,2,1};
    System.out.println(Arrays.toString(insertionSort(arr)));
  }

  static int[] bubbleSort(int[] arr){
    /*
     * Bubble sort is also know sinking sort or exchange sort . You compare the adjacent elements and swap the elements so that at elements are in correct position. It is an in place sort what do you mean by it ?
     * Does not require any additional array also it is stable [1,2,3,1] after sorting the second will be only after first 1 -[1,1,2,3]
     * There are basically two loops the first loop no of times you need to sort the and the second loop goes like no of comparisons you need to make 
     * T.C - Best case if array is sorted in asc then o(n) worst case if array is sorted in desc then O(n^2)
     */

     for(int i=0;i<arr.length;i++){
      boolean isSwapped=false;
      for(int j=1;j<arr.length-i;j++){
        if(arr[j]<arr[j-1]){
          int temp = arr[j];
          arr[j]=arr[j-1];
          arr[j-1]=temp;
          isSwapped=true;
        }
        else continue;
      }
      if(!isSwapped) break;
     }
     return arr;
  }

  static int[] selectionSort(int[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
        // Find the index of the maximum element in the unsorted part of the array
        int maxIndex = findMaxElement(arr, 0, arr.length - i);

        // Swap the found maximum element with the last element of the unsorted part
        int temp = arr[arr.length - 1 - i];
        arr[arr.length - 1 - i] = arr[maxIndex];
        arr[maxIndex] = temp;
    }
    return arr;
}

static int findMaxElement(int[] arr, int start, int end) {
    int maxIndex = start;
    for (int i = start + 1; i < end; i++) {
        if (arr[i] > arr[maxIndex]) {
            maxIndex = i;
        }
    }
    return maxIndex;
}

static int[] insertionSort(int [] arr){
  /* 
   * Partially sort the array like sorted and un sorted same time complexity 
   */
  for( int i=0;i<arr.length-1;i++){
    for(int j=i+1;j>0;j--){
      if(arr[j]<arr[j-1]){
          int temp = arr[j];
          arr[j]=arr[j-1];
          arr[j-1]=temp;
      }
      else break;
    }
  }
  return arr;
}


  
}
