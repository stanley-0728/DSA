package Sorting;
import java.util.Arrays;


public class CountNumberAfterItSelf{
  public static void main(String[] args) {
    /*
     * Given an integer array nums, return an integer array counts where counts[i] is the number of smaller elements to the right of nums[i].
        Example 1:
        Input: nums = [5,2,6,1]
        Output: [2,1,1,0]
        Explanation:
        To the right of 5 there are 2 smaller elements (2 and 1).
        To the right of 2 there is only 1 smaller element (1).
        To the right of 6 there is 1 smaller element (1).
        To the right of 1 there is 0 smaller element.
        Example 2:
        Input: nums = [-1]
        Output: [0]
        Example 3:
        Input: nums = [-1,-1]
        Output: [0,0]
     */
    int[] nums={5,2,6,1};
    int n=nums.length;
    int[] ans = new int[n];
    Items[] items = new Items[n];
    for(int i=0;i<n;i++){
      items[i] = new Items(nums[i], i);
    }
    // mergeSort(items,0,n-1,ans);
    // Arrays.stream(ans).boxed().collect(Collectors.toList());
    }
    private record Items(int val,int index){}
  static void mergeSort(int[] arr,int s,int e,int[] items,int[] ans){
    if(s>=e) return ;
    int m = s+(e-s)/2;
    mergeSort(arr, s, m, items,ans);
    mergeSort(arr,m+1,e,items,ans);
    merge(arr,s,m,e,items,ans);
  }
  static void merge(int[] arr,int s,int m ,int e,int[] frequencies,int[] ans){

  }

  
}