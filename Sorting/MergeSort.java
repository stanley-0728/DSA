package Sorting;

import java.util.Arrays;

public class MergeSort {
  public static void main(String[] args) {
    int arr[]={5,2,1,4};
   //  System.out.println(Arrays.toString(mergeSort(arr)));
    mergeSortInPlace(arr, 0, arr.length-1);
    System.out.println(Arrays.toString(arr));
  }
  static int[] mergeSort(int[] arr){
    /*
     * 

### Merge Sort: A Detailed Explanation

Merge Sort is a **divide-and-conquer** algorithm that splits the input array into smaller subarrays, sorts them, and then merges them back together in a sorted order.

---

### Steps of Merge Sort:

1. **Divide**:
   - The array is repeatedly divided into two halves until each subarray contains only one element.
   - This step continues recursively, splitting the problem into smaller and smaller pieces.

2. **Conquer**:
   - Once the array is divided into single-element subarrays, the merging process begins.
   - During merging, two sorted subarrays are combined into one sorted array.

3. **Merge**:
   - Two sorted subarrays are compared element-by-element.
   - The smaller element is picked and added to the result array.
   - This process continues until one of the subarrays is exhausted, after which the remaining elements of the other subarray are added.

---

### Time Complexity:

#### 1. **Breaking Down the Array (Divide Phase)**:
   - At each level of recursion, the array is divided into two halves.
   - This process takes \(O(\log n)\) steps, as the array size is halved with each division.

#### 2. **Merging (Combine Phase)**:
   - Merging two sorted arrays takes \(O(n)\) time because each element is visited and compared once.
   - For each level of recursion, \(O(n)\) work is done to merge all subarrays at that level.

#### 3. **Overall Complexity**:
   - Since there are \(O(\log n)\) levels of recursion and \(O(n)\) work is done at each level, the total time complexity is:
     \[
     O(n \log n)
     \]

#### **Best Case, Worst Case, and Average Case**:
   - **Best Case**: \(O(n \log n)\) (even if the array is already sorted, the algorithm still divides and merges).
   - **Worst Case**: \(O(n \log n)\) (same process regardless of input).
   - **Average Case**: \(O(n \log n)\).

---

### Space Complexity:

1. **Auxiliary Array**:
   - Merge Sort uses an auxiliary array to store intermediate results during the merge process.
   - This requires \(O(n)\) additional space.

2. **Recursion Stack**:
   - Merge Sort performs recursive calls to divide the array, with each recursive call requiring space on the stack.
   - The maximum depth of the recursion stack is \(O(\log n)\).

3. **Total Space Complexity**:
   \[
   O(n) \, \text{(auxiliary array)} + O(\log n) \, \text{(recursion stack)} = O(n)
   \]

---

### Working Example:

#### Input Array: \([38, 27, 43, 3, 9, 82, 10]\)

1. **Divide**:
   - Split the array recursively:
     - \([38, 27, 43, 3]\) and \([9, 82, 10]\)
     - \([38, 27]\), \([43, 3]\), \([9, 82]\), \([10]\)
     - \([38]\), \([27]\), \([43]\), \([3]\), \([9]\), \([82]\), \([10]\)

2. **Conquer and Merge**:
   - Merge pairs:
     - \([27, 38]\), \([3, 43]\), \([9, 10, 82]\)
   - Merge again:
     - \([3, 27, 38, 43]\), \([9, 10, 82]\)
   - Final merge:
     - \([3, 9, 10, 27, 38, 43, 82]\)

---

### Characteristics of Merge Sort:

1. **Stable**:
   - Maintains the relative order of equal elements.
   
2. **Non-Adaptive**:
   - Performs the same number of comparisons regardless of the initial order of elements.

3. **Divide-and-Conquer**:
   - The problem is broken into smaller subproblems recursively.

---

### Advantages:

1. **Predictable Time Complexity**:
   - Always \(O(n \log n)\), regardless of input.

2. **Suitable for Large Data**:
   - Particularly useful for data that cannot fit entirely into memory (external sorting).

---

### Disadvantages:

1. **Space Usage**:
   - Requires extra space for the auxiliary array, which can be a drawback compared to in-place algorithms like Quick Sort.

2. **Not In-Place**:
   - It cannot sort the array within its input space.

---

### Visualizing Merge Sort:

#### At Each Step:

1. **Initial Array**:
   \([38, 27, 43, 3, 9, 82, 10]\)

2. **Divide Phase**:
   ```
   Step 1: Divide into halves
   [38, 27, 43, 3]    [9, 82, 10]

   Step 2: Divide further
   [38, 27]   [43, 3]    [9, 82]   [10]

   Step 3: Divide to single elements
   [38] [27] [43] [3] [9] [82] [10]
   ```

3. **Merge Phase**:
   ```
   Step 1: Merge pairs
   [27, 38] [3, 43] [9, 82] [10]

   Step 2: Merge larger groups
   [3, 27, 38, 43] [9, 10, 82]

   Step 3: Merge final groups
   [3, 9, 10, 27, 38, 43, 82]
   ```

This completes the sorting process.
     */
 
    if(arr.length==1) return arr;
    int mid = arr.length/2;
    int left[]= mergeSort(Arrays.copyOfRange(arr, 0, mid));
    int right[]=mergeSort(Arrays.copyOfRange(arr, mid, arr.length));
    return merge(left,right);

    }
    static int[] merge(int[] left,int[] right){
      int i=0;
      int j=0;
      int k=0;
      int output[]=new int[left.length+right.length];
      while(i<left.length && j<right.length){
        if(left[i]<right[j]){
          output[k]=left[i];
          i++;
          k++;
        }
        else
        {
          output[k]=right[j];
          j++;
          k++;
        }
      }
      while(i<left.length){
        output[k++]=left[i++];
      }
      while(j<right.length){
        output[k++]=right[j++];
      }
      return output;
    }


    static void  mergeSortInPlace(int[] arr,int s ,int e){
        
        
              if (e - s == 0) {
            return;
        }

        int mid = (s + e) / 2;

        mergeSortInPlace(arr, s, mid);
        mergeSortInPlace(arr, mid+1, e);

        mergeInPlace(arr, s, mid, e);
      
      
    }
    static void mergeInPlace(int[]arr ,int s,int m,int e){
      int[] mix = new int[e - s + 1];

        int i = s;
        int j = m+1;
        int k = 0;

        while (i <= m && j <= e) {
            if (arr[i] < arr[j]) {
                mix[k] = arr[i];
                i++;
            } else {
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
    }
  }
