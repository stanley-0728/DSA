package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Pair {
  int node;
  int parent;

  Pair(int node, int parent) {
    this.node = node;
    this.parent = parent;
  }

  int getNode() {
    return node;
  }

  int getParent() {
    return parent;
  }
}

public class DetectCycleInGraphUn {
  public boolean bfs(int src, ArrayList<ArrayList<Integer>> adj, int[] visited) {
    Queue<Pair> queue = new LinkedList<>();
    visited[src] = 1;
    queue.add(new Pair(src, -1));
    while (!queue.isEmpty()) {
      int node = queue.peek().getNode();
      int parent = queue.peek().getParent();
      queue.poll();
      for (int i : adj.get(node)) {
        if (visited[i] == -1) {
          visited[i] = 1;
          queue.add(new Pair(i, node));
        } else {
          if (parent != i)
            return true;
        }
      }
    }
    return false;
  }

  // SC o(n) TC o(n+2E) which is same as bfs
  // this approach we can do with dfs also but was the main intitution here when
  // we are bfs if a node is already visited but it did not come parent then we
  // found a cycle
  public boolean isCycle(ArrayList<ArrayList<Integer>> adj) {
    // Code here
    int[] visited = new int[adj.size()];
    Arrays.fill(visited, -1);
    for (int i = 0; i < adj.size(); i++) {
      if (visited[i] == -1) {
        if (bfs(i, adj, visited))
          return true;
      }
    }
    return false;
  }

  // Does the above logic for work directed graph no because here we are dealing
  // with paths so if any node appears to come again in the same path then we know
  // it is a cycle.
  boolean dfs(int src, ArrayList<ArrayList<Integer>> adj, int[] visited, int[] pathVisited) {
    visited[src] = 1;
    pathVisited[src] = 1;
    for (int i : adj.get(src)) {
      if (visited[i] == -1) {
        if (dfs(i, adj, visited, pathVisited))
          return true;
      } else if (pathVisited[i] == 1) {
        return true;
      }
    }
    pathVisited[src] = 0;
    return false;
  }

  // S.c o(n) and T.C o(n+2E) same as dfs
  public boolean isCyclicDir(ArrayList<ArrayList<Integer>> adj) {
    // code here
    int[] visited = new int[adj.size()];
    int[] pathVisited = new int[adj.size()];
    Arrays.fill(visited, -1);
    for (int i = 0; i < adj.size(); i++) {
      if (visited[i] == -1) {
        if (dfs(i, adj, visited, pathVisited))
          return true;
      }
    }
    return false;
  }

}
