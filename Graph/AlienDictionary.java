package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class AlienDictionary {
  static ArrayList<Integer> topologicalSort(ArrayList<ArrayList<Integer>> adj) {
    // Your code here
    int V = adj.size();
    int inDegree[] = new int[V];
    for (ArrayList<Integer> i : adj) {
      for (int node : i) {
        inDegree[node]++;
      }
    }
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < V; i++) {
      if (inDegree[i] == 0)
        queue.add(i);
    }
    ArrayList<Integer> top = new ArrayList<>();
    while (!queue.isEmpty()) {
      int node = queue.poll();
      top.add(node);
      for (int i : adj.get(node)) {
        inDegree[i]--;
        if (inDegree[i] == 0)
          queue.add(i);
      }
    }
    return top;
  }

  public static String getAlienLanguage(String[] dictionary, int k) {
    ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    for (int i = 0; i < k; i++)
      adj.add(new ArrayList<>());

    for (int i = 0; i < dictionary.length - 1; i++) {
      String s1 = dictionary[i];
      String s2 = dictionary[i + 1];

      for (int j = 0; j < Math.min(s1.length(), s2.length()); j++) {
        if (s1.charAt(j) != s2.charAt(j)) {
          adj.get(s1.charAt(j) - 'a').add(s2.charAt(j) - 'a');
          break;
        }
      }
    }

    ArrayList<Integer> top = topoSort(adj);

    if (top.size() == 0)
      return "";

    StringBuilder sb = new StringBuilder();
    for (int i : top)
      sb.append((char) (i + 'a'));

    return sb.toString();
  }

}
