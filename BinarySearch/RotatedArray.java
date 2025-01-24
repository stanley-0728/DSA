package BinarySearch;

public class RotatedArray {
  public static void main(String[] args) {
    int[] arr= {4,5,6,7,8,1,3};
    int target =1;
    System.out.println(search(arr, target));
  }
  static int search (int[] arr,int target){
     int pivotIndex = findPivot(arr);
    if(pivotIndex==-1){
      int index= binarySearch(arr, target, 0, arr.length-1);
      return arr[index]==target?index:-1;
    }

    if(target>arr[0]){
        int indexLeft= binarySearch(arr,target,0 ,pivotIndex);
        if(indexLeft!=-1) return arr[indexLeft]==target?indexLeft:-1;
    }
    else{
            int indexRight = binarySearch( arr,target,pivotIndex+1,arr.length-1);
            if(indexRight!=-1) return arr[indexRight]==target?indexRight:-1;
    } 


    return -1;
    
  }

   static int findPivotWithDuplicates(int[] arr){
      int start=0;
      int end=arr.length-1;
      while(start<=end){
        int mid = start+ (end-start)/2;
        // 4 cases
        // 1st case when arr[mid]>arr[mid+1] i.e the only decreasing part 7,1 our pivot =7
        if(mid<end && arr[mid]>arr[mid+1]) return mid;
        // 2nd case if mid = 1 then arr[mid]<arr[mid-1] then mid-1 is our pivot 7,1
        else if (mid>start && arr[mid]<arr[mid-1]) return mid-1;
        else if (arr[start]==arr[mid]&& arr[mid]==arr[end]){
          // we just just need to ignore the values of start and end but before ignoring check whether they are peak or not 
          if(start+1<arr.length &&  arr[start]>arr[start+1]) return start;
          else if (end-1>0 && arr[end]<arr[end-1])return end-1;
          start++;
          end--;
        }
  
      else if(arr[start]<arr[mid]||(arr[mid]==arr[start]&& arr[mid]>arr[end])) start=mid+1;
      else end = mid-1;
      }
    return -1;
  }

  static int findPivot(int[] arr){
      int start=0;
      int end=arr.length-1;
      while(start<=end){
        int mid = start+ (end-start)/2;
        // 4 cases
        // 1st case when arr[mid]>arr[mid+1] i.e the only decreasing part 7,1 our pivot =7
        if(mid<end && arr[mid]>arr[mid+1]) return mid;
        // 2nd case if mid = 1 then arr[mid]<arr[mid-1] then mid-1 is our pivot 7,1
        else if (mid>start && arr[mid]<arr[mid-1]) return mid-1;
        // 3rd case let us say mid =6 then how do we know if should shift in that case compare with the start 4 ,
        // 4<6 which means there can be largest element that i can lie in right side of 6 so start=mid+1 why +1 because if there is no point of checking 6  again it should be caught in 1st or 2nd case
        else if(arr[start]<arr[mid]) start =mid+1;
        // 4th case suppose mid -2 compare with start 4>2 there is no point in checking right side because they will be less than start so largest no lies in left why -1 same like 3rd case
        else 
          end =mid-1;
      }
    return -1;
  }

   static int binarySearch(int[] arr,int target,int start,int end){
    while(start<=end){
      int mid = start + (end-start)/2;
      if(arr[mid]==target){
        return mid;
      }
      else if(arr[mid]>target){
        end = mid-1;
      }
      else{
        start = mid+1;
      }
    }
    return -1;
  }
}
