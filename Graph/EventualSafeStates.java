package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventualSafeStates {
  boolean dfs(int src, List<List<Integer>> adj, int[] visited, int[] pathVisited, int[] check) {
    visited[src] = 1;
    pathVisited[src] = 1;
    for (int i : adj.get(src)) {
      if (visited[i] == -1) {
        if (dfs(i, adj, visited, pathVisited, check))
          return true;
      } else if (pathVisited[i] == 1) {
        return true;
      }
    }
    check[src] = 1;
    pathVisited[src] = 0;
    return false;
  }

  // S.C o(n) and T.C o(n+2e)
  List<Integer> eventualSafeNodes(int V, List<List<Integer>> adj) {

    // Your code here
    int[] check = new int[adj.size()];
    int[] visited = new int[adj.size()];
    int[] pathVisited = new int[adj.size()];
    List<Integer> safeNodes = new ArrayList<>();
    Arrays.fill(visited, -1);
    for (int i = 0; i < adj.size(); i++) {
      if (visited[i] == -1) {
        dfs(i, adj, visited, pathVisited, check);
      }
    }
    for (int i = 0; i < adj.size(); i++) {
      if (check[i] == 1)
        safeNodes.add(i);
    }
    return safeNodes;
  }
}
