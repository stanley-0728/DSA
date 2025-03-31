package Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// This only exist in DAG which directed acyclic graph.
/*
 * What is topo sort ?
 * Linear ordering of vertices such that if there is an edge between u and v ,u appears before v in that ordering.
 * 
 
 */
public class TopoSort {
  static void dfs(int node, ArrayList<ArrayList<Integer>> adj, int[] visited, Stack<Integer> st) {
    visited[node] = 1;
    for (int i : adj.get(node)) {
      if (visited[i] == 0) {
        dfs(i, adj, visited, st);
      }
    }
    st.add(node);
  }

  static ArrayList<Integer> topologicalSort(ArrayList<ArrayList<Integer>> adj) {
    // Your code here
    int[] visited = new int[adj.size()];
    Stack<Integer> st = new Stack<>();
    for (int i = 0; i < adj.size(); i++) {
      if (visited[i] == 0) {
        dfs(i, adj, visited, st);
      }
    }
    ArrayList<Integer> res = new ArrayList<>();
    while (!st.isEmpty()) {
      int i = (int) st.pop();
      res.add(i);
    }
    return res;
  }
}
