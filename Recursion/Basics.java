package Recursion;
public class Basics{
  public  void main(String[] args) {
    // input - bcadah skip the letter a and output should be bcdh 
    String s = "bcdappleha ";
    // skip("",s);
    // Passing without a variable in the argument
    // System.out.println(skipWithoutVariable(s));
    // input - bcdappleha now we need to skip apple 
    // skipApple("",s);

    // input - skip app but apple bcdappappleha 
    skipAppNotApple("", " bcdappappleha ");
  }
  // we are using passing a variable 
   void  skip(String p,String up){
    if(up.isEmpty()){
      System.out.println(p);
      return;
    }
    char ch = up.charAt(0);
    if(ch=='a'){
      skip(p,up.substring(1));
    }
    else{
      skip(p+ch,up.substring(1));
    }
  }
  // without passing a variable
  String skipWithoutVariable(String up){
     if(up.isEmpty()){
      return "";
    }
    char ch = up.charAt(0);
    if(ch =='a'){
     return  skipWithoutVariable(up.substring(1));
    }
    else{
      return ch + skipWithoutVariable(up.substring(1));
    }
  }
  void skipApple(String p,String up){
    if(up.isEmpty()){
      System.out.println(p);
      return;
    }
    char ch = up.charAt(0);
    if(ch =='a' && up.startsWith("apple")){
      skipApple(p,up.substring(5));
    }
    else{
      skipApple(p+ch,up.substring(1));
    }
  }

  void skipAppNotApple(String p,String up){
    if(up.isEmpty()){
      System.out.println(p);
      return;
    }
    char ch = up.charAt(0);
    if(ch =='a' && up.startsWith("app") && !up.startsWith("apple")){
      skipAppNotApple(p,up.substring(3));
    }
    else{
      skipAppNotApple(p+ch,up.substring(1));
    }
  }
}