package Arrays;

import java.util.*;

public class MostCommonWord {
  public String mostCommonWord(String paragraph, String[] banned) {
    Map<String, Integer> map = new HashMap<>();
    StringBuffer sb = new StringBuffer();
    Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));
    for (int i = 0; i < paragraph.length(); i++) {
      char c = paragraph.charAt(i);

      if (Character.isLetter(c)) {
        sb.append(Character.toLowerCase(c));
      } else if (sb.length() > 0) {
        String word = sb.toString();
        if (!bannedSet.contains(word)) {
          map.put(word, map.getOrDefault(word, 0) + 1);
        }
        sb = new StringBuffer();
      }
    }

    if (sb.length() > 0) {
      String word = sb.toString();
      if (!bannedSet.contains(word)) {
        map.put(word, map.getOrDefault(word, 0) + 1);
      }
    }

    List<Map.Entry<String, Integer>> sortedMap = new ArrayList<>(map.entrySet());
    sortedMap.sort((a, b) -> b.getValue() - a.getValue());

    return sortedMap.get(0).getKey();
  }
}
