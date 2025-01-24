package BinarySearch;

import java.util.Arrays;

/*
 * Given an integer array nums and an integer k, split nums into k non-empty subarrays such that the largest sum of any subarray is minimized.

Return the minimized largest sum of the split.

A subarray is a contiguous part of the array.

 

Example 1:

Input: nums = [7,2,5,10,8], k = 2
Output: 18
Explanation: There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8], where the largest sum among the two subarrays is only 18.
Example 2:

Input: nums = [1,2,3,4,5], k = 2
Output: 9
Explanation: There are four ways to split nums into two subarrays.
The best way is to split it into [1,2,3] and [4,5], where the largest sum among the two subarrays is only 9.
 */

/*
 * Idea subarray first idea comes is sliding window algo 
 * Binary Search from  the example i can see the sorted and not sorted which is also bitoninc in nature increasing and decreasing .  
 * 
 * What if k=1 that means you can partition array into only half in that case what is the largest sum that can be minimized is sum of array elements. 7+5+2+10+8=32 
 * what if k=5 then [7],[2],[5],[10],[8] in this case largest sum of sub array is max element in the array.
 * These are the two thoughts i got when k is set to max value and min value then what can be my answer.
 * Input: nums = [7,2,5,10,8], k = 2
   Output: 18
   I can partition in possible but it should be contiginous because sub array.
   [7], [2,5,10,8] = In this what is max(7,25) =25
   [7,2],[5,10,8] = max(9,23)=                  23
   [7,2,5],[10,8] = max(14,18)=                 18
   [7,2,5,10].[8] = max(24,8) =                 24
 *  
 * So the question is asking us to find max value of sub array after partiton we can partition in different possible ways and we got the max sum but out of these max sum which subarray is having minium sum . 18 
 * 
 * So we got the lower bound has max element and upper bound has sum of all numbers . can we apply binary search in this range ?
 * 
 *  
 */
public class SplitArrayLargestSum {
  public static void main(String[] args) {
    int[] arr= {7,2,5,10,8};
    int k=1;
  System.out.println(maxSubArraySum(arr, k));    
  }

 static int maxSubArraySum(int[] arr,int k){
 int minSum = Arrays.stream(arr).max().getAsInt();
 int maxSum = Arrays.stream(arr).sum();
 if(k==1) return maxSum;
 if(k==arr.length) return minSum;
 while(minSum<maxSum){
  int midSum=minSum +(maxSum-minSum)/2;
  int sum =0 ;int pieces=1;
    for(int i=0;i<arr.length;i++){
      if(arr[i]+sum>midSum){
        pieces++;
        sum=arr[i];
        continue;
      }
      sum+=arr[i];
    }
    if(pieces<=k){
      maxSum =midSum;
    }
    else {
      minSum=midSum+1;
    }
 } 
 return minSum;
 }


}
