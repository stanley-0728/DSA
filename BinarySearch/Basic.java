package BinarySearch;

class Basic {
  public static void main(String[] args) {
    int[] arr = { 1, 2, 3, 4 };
    int key = 4;
    // int value = binarySearch(arr, key);
    // if (value == -1) {
    // System.out.println("Not found");
    // } else {
    // System.out.println("Found at index " + value);
    // }
    // // what if arr is descending order
    // int[] arr2 = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
    // value = binarySearchDescending(arr2, key);
    // if (value == -1) {
    // System.out.println("Not found");
    // } else {
    // System.out.println("Found at index " + value);
    // }
    System.out.println(lowerBound(arr, key));
  }

  static int binarySearch(int[] arr, int target) {
    int start = 0;
    int end = arr.length - 1;
    while (start <= end) {
      int mid = start + (end - start) / 2;
      if (arr[mid] == target) {
        return mid;
      } else if (arr[mid] > target) {
        end = mid - 1;
      } else {
        start = mid + 1;
      }
    }
    return -1;
  }

  static int binarySearchDescending(int[] arr, int target) {
    int start = 0;
    int end = arr.length - 1;
    while (start <= end) {
      int mid = start + (end - start) / 2;
      if (arr[mid] == target) {
        return mid;
      } else if (arr[mid] < target) {
        end = mid - 1;
      } else {
        start = mid + 1;
      }
    }
    return -1;
  }

  static int upperBound(int[] a, int target) {
    int start = 0;
    int end = a.length - 1;
    while (start <= end) {
      int mid = start + (end - start) / 2;
      if (a[mid] <= target) {
        start = mid + 1;
      } else {

        end = mid - 1;
      }
    }
    return start >= a.length ? -1 : start;
  }

  static int lowerBound(int[] a, int target) {
    int start = 0;
    int end = a.length - 1;
    while (start <= end) {
      int mid = start + (end - start) / 2;
      if (a[mid] < target) {
        start = mid + 1;
      } else {
        end = mid - 1;
      }
    }
    return start >= a.length ? -1 : start;
  }

}