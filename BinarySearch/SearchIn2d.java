package BinarySearch;

public class SearchIn2d {
  public static void main(String[] args) {
    int[][] matrix={
     {1,3,5,7},
     {10,11,16,20},
     {23,30,34,50}
    };
    int[] index=searchInSortedMatrix(matrix,34);
    if(index[0]==-1 && index[1]==-1) System.out.println("Not found");
    else 
    System.out.println("Found"+index[0]+" " + index[1]);

    
    
  }
/*
 * How are searching here . The matrix is not strictly sorted which means first element of row need not be greater than last element in the previous row. But it is sorted row wise and column wise .
 * Let us say we are searching for 37 .
 * suppose my mid is 29 <37 we need to reduce for search space since it is less are gonna ignore that column and move into next column in that case 34 just below it we will be loosing that . 
 * if my lower bound 10 first element and upperbound is last element in first row 
 * then 37<40 which means below 40 every element is greater than 37 so there is not point in that column i can do c--l
 * now 30<37 which means elements left of 30 are smaller than 37 so there is no point in checking we ignore the row so row ++
 * 35 <37 same  r++
 * 37==37 ans
 */
  static int[]search(int[][] matrix,int target){
    int row =0;
    int column=matrix.length-1;
    while(row<matrix.length && column>=0){
        if(matrix[row][column]==target){
          return new int[] {row,column};
        }
        else if(target < matrix[row][column]){
          column--;
        }
        else
        row++;
    }
    return new int[] {-1,-1};
  }

  /*
   * How to search in strictly sorted array 
   * 1 2 3 
   * 4 5 6
   * 7 8 9 
   * Every first element is actually greater than the last element in the previous row .
   * I just can't follow the above approach why? It return the correct result but we take more steps in the above we are assuming row and column are sorted independently hence we left and down but we leverage the usage of strictly sorted by applying binary search on entire array 
   * 
   * let us rStart =0 and rEnd =2 and will take cMid =column.length/2 = 1 
   * rMid= 1 arr[rMid][cMid] 5 target = 2  6 7 8 9 i can clearly ignore or in simple words ignore the below rows
   * now my search space is reduced to 
   * 1 2 3
   * 4 5 6 this is our final set of two arrays on applying binary Search on entire array we end up in two rows now in these two rows our answer exists.
   * it can be arr[rMid][cMid] or arr[rMid+1][cMid].
   * if not then now we need to binary on four half's 
   * before 2 and after 3 
   * before 5 and after 6 
   * check where target cab lie in which half and apply binary search
   */

   static int[] binarySearch(int[][] matrix,int rStart ,int cStart,int cEnd,int target){
    System.out.println(rStart+" " + cStart+ " " +cEnd);
      while (cStart<=cEnd){
        int cMid= cStart +(cEnd-cStart)/2;
        if(matrix[rStart][cMid]==target) return new int[] {rStart,cMid};
        else if(matrix[rStart][cMid]<target) cStart=cMid+1;
        else
        cEnd=cMid-1;
      }
      return new int[]{-1,-1};
   }

   static int[] searchInSortedMatrix(int[][] matrix,int target){
    if(matrix.length==1){
      return binarySearch(matrix, 0, 0, matrix[0].length-1, target);
    }
    int rStart=0;
    int rEnd=matrix.length-1;
    int cMid = (matrix[0].length-1)/2;
    while (rStart<(rEnd-1)){
      int rMid= rStart+ (rEnd-rStart)/2;
      if(matrix[rMid][cMid]==target) return new int[]{rMid,cMid};
      else if (matrix[rMid][cMid]<target) rStart=rMid;
      else rEnd=rMid;
    }
    System.out.println(matrix[rStart+1][cMid+1]);
    System.out.println(matrix[rStart][matrix[0].length-1]);
    // now we are left with two arrays
    if(matrix[rStart][cMid]==target) return new int[] {rStart,cMid};
    else if(rStart+1<matrix.length && matrix[rStart+1][cMid]==target) return new int[]{rStart+1,cMid};
    else if(cMid-1>=0 && target<=matrix[rStart][cMid-1]) {
      return binarySearch(matrix, rStart, 0, cMid-1, target);
    }
    else if(cMid+1<matrix[0].length && target>=matrix[rStart][cMid+1]&& target<=matrix[rStart][matrix[0].length-1]){
      return binarySearch(matrix, rStart, cMid+1, matrix[0].length-1,  target);
    }
     else if(rStart+1<matrix.length  && cMid-1>=0 && target<=matrix[rStart+1][cMid-1]) {
      return binarySearch(matrix, rStart+1, 0, cMid-1, target);
    }
    else if( rStart+1<matrix.length && cMid+1<matrix[0].length &&  target>=matrix[rStart+1][cMid+1]&& target<=matrix[rStart+1][matrix[0].length-1]){
      System.out.println("hello");
      return binarySearch(matrix, rStart+1, cMid+1, matrix[0].length-1,  target);
    }
    else
      return new int[] {-1,-1};
   }
}
