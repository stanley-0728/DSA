package Graph.Traversals;

import java.util.*;

public class dfs {
  //S.C o(n) T.C o(N+2E)
  void dfs(int node, ArrayList<ArrayList<Integer>> adj, int[] visited, ArrayList<Integer> ans) {
    visited[node] = 1;
    ans.add(node);
    for (int i : adj.get(node)) {
      if (visited[i] == 0)
        dfs(i, adj, visited, ans);
    }
  }

  public ArrayList<Integer> dfsOfGraph(ArrayList<ArrayList<Integer>> adj) {
    // Code here
    int[] visited = new int[adj.size()];
    ArrayList<Integer> ans = new ArrayList<>();
    dfs(0, adj, visited, ans);
    return ans;
  }
}
