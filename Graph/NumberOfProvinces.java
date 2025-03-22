package Graph;

import java.util.ArrayList;

public class NumberOfProvinces {
  static void dfs(int node, ArrayList<ArrayList<Integer>> adj, int[] visited) {
    visited[node] = 1;
    for (int i : adj.get(node)) {
      if (visited[i] == 0) {
        dfs(i, adj, visited);
      }
    }
  }
// t.c o(n+2e) s.c o(n)
  static int numProvinces(ArrayList<ArrayList<Integer>> adj, int V) {
    // code here
    ArrayList<ArrayList<Integer>> array = new ArrayList<>();
    for (int i = 0; i < V; i++) {
      array.add(new ArrayList<>());
    }
    for (int i = 0; i < adj.size(); i++) {
      for (int j = 0; j < adj.get(0).size(); j++) {
        if (adj.get(i).get(j) == 1) {
          array.get(i).add(j);
          array.get(j).add(i);
        }
      }
    }
    int[] visited = new int[V];
    int count = 0;
    for (int i = 0; i < V; i++) {
      if (visited[i] == 0) {
        count++;
        dfs(i, array, visited);
      }
    }
    return count;
  }
}
