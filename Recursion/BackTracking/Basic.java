package Recursion.BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Basic {
  public static void main(String[] args) {
    int[][] board = {
     {1, 1, 1}, 
     {1, 1, 1}, 
     {1, 1, 1}
    };
    boolean[][] visited=new boolean[board.length][board[0].length];
    // this is a board start 0,0 and  end at 2,2 give me all possible ways to reach such that you are allowed only to move down and right.
    // System.out.println(findWays(0, 0));
    // System.out.println(findPathInDiagonal(0, 0, ""));
    int[][] path = new int[board.length][board[0].length];

    System.out.println(findAllPaths(0,0,"",board,visited,path,1));
    
  }

  static int findWays(int r,int c){
    if(r==2||c==2){
      return 1;
    }
    int ans=0;
    ans+=findWays(r+1, c);
    ans+=findWays(r, c+1);
    return ans;
  }
   static List<String> findPath(int r,int c,String p){
    if(r==2&&c==2){
      List<String> list=new ArrayList<>();
      list.add(new String(p));
      return list;
    }
    List<String> l=new ArrayList<>();
    if(r<3)
    l.addAll(findPath(r+1, c,p+'D'));
    if(c<3)
    l.addAll(findPath(r, c+1,p+'R'));
    return l;
  }

   static List<String> findPathInDiagonal(int r,int c,String p){
    if(r==2&&c==2){
      List<String> list=new ArrayList<>();
      list.add(new String(p));
      return list;
    }
    List<String> l=new ArrayList<>();
    if(r<3 && c<3){
      l.addAll(findPathInDiagonal(r+1,c+1,p+"D"));
    }
    if(r<3)
    l.addAll(findPathInDiagonal(r+1, c,p+'V'));
    if(c<3)
    l.addAll(findPathInDiagonal(r, c+1,p+'H'));
    return l;

  }

  static List<String> findAllPaths(int r,int c,String p,int[][] board,boolean[][] visited,int[][] path,int step){
    if(r>=board.length||c>=board[0].length){
      return new ArrayList<>();
    }
    if(r==board.length-1&&c==board[0].length-1){
      if(board[r][c]==0) return new ArrayList<>();
       path[r][c] = step;
            for(int[] arr : path) {
                System.out.println(Arrays.toString(arr));
            }
            System.out.println();
      List<String> list=new ArrayList<>();
      list.add(new String(p));
      return list;
    }
    List<String> l=new ArrayList<>();
    if(r<board.length&&c<board[0].length&&board[r][c]==0) return l;
    if(r<board.length&&c<board[0].length&&visited[r][c]) return l;
    visited[r][c]=true;
    path[r][c]=step;
    if(r<board.length)
    l.addAll(findAllPaths(r+1, c,p+'D',board,visited,path,step+1));
    if(c<board[0].length)
    l.addAll(findAllPaths(r, c+1,p+'R',board,visited,path,step+1));
    if(r>0){
      l.addAll(findAllPaths(r-1, c, p+"U", board,visited,path,step+1)); 
    }
    if(c>0){
      l.addAll(findAllPaths(r,c-1,p+"L",board,visited,path,step+1));
    }
    visited[r][c]=false;
    path[r][c]=0;
    return l;

  }

}
