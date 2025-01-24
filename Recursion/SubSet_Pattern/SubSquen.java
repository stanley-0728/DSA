package Recursion.SubSet_Pattern;

import java.util.ArrayList;
import java.util.List;

public class SubSquen {
  public static void main(String[] args) {
    String s = "bca";
    // findSubSqeu("", s);
    List<List<String>> list = findSubSqueWithoutVariable("", s);
        System.out.println(list);
    }
  static void findSubSqeu(String p,String up){
    if(up.isEmpty()){
      System.out.println(p);
      return ;
    }
    char ch = up.charAt(0);

    findSubSqeu(p, up.substring(1));
    findSubSqeu(p + ch , up.substring(1));

  }
  // find sub sets without variable 
  static List< List<String> > findSubSqueWithoutVariable(String p,String up){
     if(up.isEmpty()){
      List<List<String>> list = new ArrayList<>();
      List<String> i= new ArrayList<>();
      i.add(p);
      list.add(i);
      return list;
    }
    char ch = up.charAt(0);

   List< List<String>> left= findSubSqueWithoutVariable(p,up.substring(1));
    List<List<String> >  right=findSubSqueWithoutVariable(p+ch,up.substring(1));
    left.addAll(right);
    return left;
  }

}
