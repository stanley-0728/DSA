package Sorting;

import java.util.Arrays;

public class RadixSort {
  public static void main(String[] args) {
    int[] arr ={170, 45, 75, 90, 802, 24, 2, 66};
    // System.out.println(arr);
    radixSort(arr);
  }
  static void radixSort(int[] arr){
    /*
     * What is Radix Sort?
      Radix Sort is a non-comparison-based sorting algorithm.
      It sorts numbers digit by digit, starting from the least significant digit (LSD) to the most significant digit (MSD).
      It uses a stable sorting algorithm (like Counting Sort) as a subroutine to sort digits.
      Key Concepts
      Digit-by-Digit Sorting:

      Numbers are sorted based on each digit, starting with the least significant digit (LSD).
      Stable Sorting:

      Stability is crucial because it ensures the order of elements with the same digit remains unchanged across passes.
      Base (Radix):

      For decimal numbers, the radix is 10 (0 to 9).
      Number of Passes:

      Equal to the number of digits in the largest number.
      Steps of Radix Sort
      Find the maximum number in the array to determine the number of digits.
      Perform digit-wise sorting using Counting Sort as a subroutine:
      First, sort by the least significant digit (units place).
      Then by the next digit (tens place), and so on.

      Example Walkthrough
      Input:
      [170, 45, 75, 90, 802, 24, 2, 66]

      Step 1: Sort by Units Place (place = 1)
      Digits: [0, 5, 5, 0, 2, 4, 2, 6]
      Sorted by units: [170, 90, 802, 2, 24, 45, 75, 66]
      Step 2: Sort by Tens Place (place = 10)
      Digits: [7, 9, 0, 0, 2, 4, 7, 6]
      Sorted by tens: [802, 2, 24, 45, 66, 170, 75, 90]
      Step 3: Sort by Hundreds Place (place = 100)
      Digits: [8, 0, 0, 0, 0, 1, 0, 0]
      Sorted by hundreds: [2, 24, 45, 66, 75, 90, 170, 802]
      Output:
      [2, 24, 45, 66, 75, 90, 170, 802]

      
     */
     /*
      * So the algorithm is saying pick the max the element that is 802 and count the digits -3 
      so three times we need to do counting sort 
      first time we sort on one place next tens place third hundred place 
      */
      int max = Arrays.stream(arr).max().getAsInt();
      int place=1;
      while(max/place>0){
        countingSort(arr,place,max);
        place*=10;
      }
    }
    static void countingSort(int[] arr,int place,int max){
      int[] output=new int[arr.length];
      int []count =new int[10];
      Arrays.fill(count,0);
      System.out.println(" Before " +" "+Arrays.toString(count));

      // Step 1 extract the digit and store their frequency in count  
      for(int num :arr){
        int digit = (num/place)%10;
        count[digit]++;
      }
      System.out.println(" count " +" "+Arrays.toString(count));

      // Step 2 Cumulative sum of count 
      for(int i=1;i<count.length;i++){
        count[i]+=count[i-1];
      }

     System.out.println(" After " +" "+Arrays.toString(count));


      // Build the output array 
      for (int i= arr.length-1;i>=0;i--){
        int digit = (arr[i]/place)%10;
        output[count[digit]-1]=arr[i];
        count[digit]--;
      }
      System.arraycopy(output, 0, arr, 0, arr.length);
      System.out.println(place +" "+Arrays.toString(arr));
      System.out.println();

    
    }
}
