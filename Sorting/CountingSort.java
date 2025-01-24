package Sorting;
import java.util.Arrays;

public class CountingSort {
  public static void main(String[] args) {
    int[] arr ={2,3,5,1,4};
    System.out.println(Arrays.toString(countingSort(arr)));
  }
  static int[] countingSort(int[] arr){
    /*
     * Counting sort is simple understand the working of it some variations can be solved 
     * Step maintain count array store the frequencies of each element in this array 
     * Find the cumulative sum of count array because to get the index 
     * then build the output array
     * This is good for smaller inputs.
     * S.C -O(n+k)
     * T.C O(n+k)
     */
    /*
     * index 0 1 2 3 4 
     * value 2 3 5 1 4
     * count  0 1 2 3 4 5
     *        0 1 1 1 1 1
     *  sum   0 1 2 3 4 5
     * element 1 is ending at 1st index which from there is another value that is gonna start element 1 position is 1-1 0;
     * element is 2 is ending at index 2 which means other element is starting from here so index it has go to is 2-1=1
     */
    int max = Arrays.stream(arr).max().getAsInt();
    int[] count = new int [max+1];
    Arrays.fill(count ,0);
    System.out.println(" Before " +" "+Arrays.toString(count));
    for(int i : arr){
      count[i]++; 
    }
        System.out.println(" Count " +" "+Arrays.toString(count));
    for ( int i= 1;i<count.length;i++){
        count[i]+=count[i-1];
    }
        System.out.println(" After " +" "+Arrays.toString(count));

    int sorted[] =new int[max];
    for(int i= arr.length-1;i>=0;i--){
      // Why back wards for this will guarantee us stable sorting 
      sorted[count[arr[i]]-1]=arr[i];
      count[arr[i]]--;
    }
    return sorted;

  }
}
