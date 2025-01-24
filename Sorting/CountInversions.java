package Sorting;

public class CountInversions {
  public static void main(String[] args) {
    /*
     * Given an array of integers arr[]. Find the Inversion Count in the array.
      Two elements arr[i] and arr[j] form an inversion if arr[i] > arr[j] and i < j.

      Inversion Count: For an array, inversion count indicates how far (or close) the array is from being sorted. If the array is already sorted then the inversion count is 0.
      If an array is sorted in the reverse order then the inversion count is the maximum. 

      Examples:

      Input: arr[] = [2, 4, 1, 3, 5]
      Output: 3
      Explanation: The sequence 2, 4, 1, 3, 5 has three inversions (2, 1), (4, 1), (4, 3).
     */
    int[] arr= {5,1,6,2,3,1};
    // 4+3+1+1=9
   System.out.println( mergeSort(arr,0,arr.length-1));
  }
  static int  mergeSort(int[] arr,int s,int e){
    int c=0;
    if(e-s==0) return 0;
    int mid = s+(e-s)/2;
      c+=mergeSort(arr, s, mid);
      c+=mergeSort(arr, mid+1, e);
      c+=mergeInPlace(arr, s, mid, e);
      return c;
  }
   static int mergeInPlace(int[]arr ,int s,int m,int e){
      int[] mix = new int[e - s + 1];
      int c=0;
        int i = s;
        int j = m+1;
        int k = 0;

        while (i <= m && j <= e) {
            if (arr[i] <= arr[j]) {
                mix[k] = arr[i];
                i++;
            } else {
                    c+=(m-i+1);
                mix[k] = arr[j];
                j++;
            }
            k++;
        }
        while (i <= m) {
            mix[k] = arr[i];
            i++;
            k++;
        }

        while (j <= e) {
            mix[k] = arr[j];
            j++;
            k++;
        }

        for (int l = 0; l < mix.length; l++) {
            arr[s+l] = mix[l];
        }
            return c;

    }

  }
