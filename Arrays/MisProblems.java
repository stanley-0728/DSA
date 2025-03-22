package Arrays;

import java.util.ArrayList;
import java.util.List;

public class MisProblems {
  public void reverseString(char[] s) {
    int i = 0;
    int j = s.length - 1;
    while (i < j) {
      char temp = s[j];
      s[j] = s[i];
      s[i] = temp;
      i++;
      j--;
    }
  }

  public List<String> fizzBuzz(int n) {
    List<String> ans = new ArrayList<>();
    while (n >= 1) {
      if (n % 5 == 0 && n % 3 == 0)
        ans.add("FizzBuzz");
      else if (n % 5 == 0)
        ans.add("Buzz");
      else if (n % 3 == 0)
        ans.add("Fizz");
      else
        ans.add(n + "");
    }
    return ans.reversed();

  }
}
