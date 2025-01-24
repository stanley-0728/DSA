package BinarySearch;

public class Problems {
  public static void main(String[] args) {
    int[] arr = { 1,2,4,5,10,12,14,16,19};
    int key=20;
    int value = ceiling(arr, key);
    if(value ==-1) System.out.println("Value not found"+ key);
   else System.out.println(arr[value]);
    int value2 = floor(arr, key);
    if(value2 ==-1) System.out.println("Value not found"+ key);
   else System.out.println(arr[value2]);
   
  }
  static int ceiling(int[] arr,int target){
    int start =0;
    int end = arr.length-1;
    while(start<=end){
      int mid = start + (end-start)/2;
       if(arr[mid]>=target)
          end= mid -1;
      else 
        start = mid+1;
    }

    if( start >= arr.length) return -1;

    return start;
  }
  static int floor(int[] arr,int target){
    int start =0;
    int end = arr.length-1 ;
    while(start<=end){
      int mid = start + (end-start)/2;
       if( arr[mid]>=target)
          end= mid -1;
      else 
        start = mid+1;
    }
    if( end <0) return -1;

      
    return end;
  }

}
