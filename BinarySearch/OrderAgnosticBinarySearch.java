package BinarySearch;

public class OrderAgnosticBinarySearch {
  public static void main(String[] args) {
    int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int key = 3;
    int value = binarySearch(arr, key);
    if(value==-1){
      System.out.println("Not found");
    }
    else{
      System.out.println("Found at index "+value);
    }
  }
    static int binarySearch(int[] arr,int target){
      int start =0;
      int end = arr.length-1;
      boolean isAscending = arr[start]<arr[arr.length-1];
       while(start<=end){
      int mid = start + (end-start)/2;
      if(arr[mid]==target){
        return mid;
      }
      if(isAscending){
        if(arr[mid]>target){
          end = mid-1;
        }
        else{
          start = mid+1;
        }
      }
       else{
        // Descending order
        if(arr[mid]<target){
          end = mid-1;
        }
        else{
          start = mid+1;
        }
       }
      }
      return -1;
    }
}
