package Recursion.SubSet_Pattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class PermutationOfStrings {
  public static void main(String[] args) {
    String s="ABC";
    // System.out.println(s.substring(0,3)+'c'+s.substring(3, s.length()));
    // findPermutations("", s);
    // System.out.println(s.substring(1));
    List<String> permutations = findPermutationListDuplicates("", s);
    Collections.sort(permutations); 
    System.out.println(permutations);
  }
  static String sortString(String s){
    char[] c = s.toCharArray();
    Arrays.sort(c);
    return new String(c);
  }
  static void findPermutations(String p,String up){
    if(up.isEmpty()){
      System.out.println(p);
      return;
    }
    char c = up.charAt(0);
    int size=p.length();
    for(int i=0;i<size+1;i++){
      findPermutations(p.substring(0, i)+c+p.substring(i,p.length()), up.substring(1));
    }
  }
  
  static List<String> findPermutationList(String p,String up){
      if(up.isEmpty()){
      System.out.println(p);
      List<String> list = new ArrayList<>();
      list.add(p);
      return list;
    }
    char c = up.charAt(0);
    int size=p.length();
    List<String> local= new ArrayList<>();
    for(int i=0;i<size+1;i++){
        String newPermutation = p.substring(0, i) + c + p.substring(i, size);
       List<String> ans=findPermutationList(newPermutation, up.substring(1));
           local.addAll(ans);
    }
    return local;
  }

    static List<String> findPermutationListDuplicates(String p,String up){
      if(up.isEmpty()){
      List<String> list = new ArrayList<>();
      list.add(p);
      return list;
    }
    char c = up.charAt(0);
    int size=p.length();
    List<String> local= new ArrayList<>();
    for(int i=0;i<size+1;i++){
        String newPermutation = p.substring(0, i) + c + p.substring(i, size);
        if (i > 0 && p.charAt(i - 1) == c) continue;
       List<String> ans=findPermutationListDuplicates(newPermutation, up.substring(1));
        local.addAll(ans);
    }
    return new ArrayList<>(new HashSet<>(local));
  }

  
}
