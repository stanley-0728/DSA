package Arrays;

import java.util.*;

public class ReOrderDataInLogs {
  public String[] reorderLogFiles(String[] logs) {
    List<AbstractMap.SimpleEntry<String, String>> letterLog = new ArrayList<>();
    List<AbstractMap.SimpleEntry<String, String>> digitLog = new ArrayList<>();
    for (String s : logs) {
      StringBuffer first = new StringBuffer();
      StringBuffer second = new StringBuffer();
      boolean f = false;
      for (char c : s.toCharArray()) {
        if (c == ' ' && f == false) {
          f = true;
          continue;
        }
        if (f == false)
          first.append(c);
        else
          second.append(c);
      }
      if (second.charAt(0) >= 'a' && s.charAt(0) <= 'z')
        letterLog.add(new AbstractMap.SimpleEntry<>(second.toString(), first.toString()));
      else
        digitLog.add(new AbstractMap.SimpleEntry<>(second.toString(), first.toString()));
    }
    letterLog
        .sort((a, b) -> a.getKey().equals(b.getKey())
            ? a.getValue().compareTo(b.getValue())
            : a.getKey().compareTo(b.getKey()));
    String[] ans = new String[logs.length];
    int i = 0;
    for (AbstractMap.SimpleEntry<String, String> pair : letterLog) {
      StringBuffer temp = new StringBuffer();
      temp.append(pair.getValue());
      temp.append(" ");
      temp.append(pair.getKey());
      ans[i++] = temp.toString();
    }
    for (AbstractMap.SimpleEntry<String, String> pair : digitLog) {
      StringBuffer temp = new StringBuffer();
      temp.append(pair.getValue());
      temp.append(" ");
      temp.append(pair.getKey());
      ans[i++] = temp.toString();
    }
    return ans;
  }
}
