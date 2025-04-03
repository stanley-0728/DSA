package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
 * A graph that can be coloured with 2 colors such that no 2 adj nodes have the same color .
 * One observation is that every linear can bipartite graph you can colour them somehow .But happens for cyclic graph it is observed that even length cycle can be bipartite but for odd length we cannot color them .
 * So how do we start of brute force traversal either bfs or dfs 
 */
public class BipartiteGraph {
  private boolean check(ArrayList<ArrayList<Integer>> adj, int[] color, int start) {
    color[start] = 0;
    Queue<Integer> queue = new LinkedList<>();
    queue.add(start);
    while (!queue.isEmpty()) {
      int node = queue.poll();
      int currColor = color[node];
      for (int i : adj.get(node)) {
        if (color[i] == -1) {
          color[i] = (currColor == 0) ? 1 : 0;
          queue.add(i);
        } else if (color[i] == currColor)
          return false;
      }
    }
    return true;
  }

  private dfs(ArrayList<ArrayList<Integer>> adj,int[] color,int node,int col){
    color[node] = col;
    for(int i:adj.get(node)){
      if(color[i]==-1){
        if(dfs(adj,color,i,(col==1)?0:1 ) ==false) return false;
      }
      else if (color[i] == col) return false;
    }
    return true;

  }

  public boolean isBipartite(int[][] graph) {
    int V = graph.length;
    int[] color = new int[V];
    for (int i = 0; i < V; i++)
      color[i] = -1;
    ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    for (int i = 0; i < V; i++)
      adj.add(new ArrayList<>());
    for (int u = 0; u < graph.length; u++) {
      for (int v : graph[u]) {
        adj.get(u).add(v);
        adj.get(v).add(u);
      }
    }
    boolean res = true;
    for (int i = 0; i < V; i++) {
      if (color[i] == -1) {
        res = dfs(adj, color, i, 0);
        if (res == false)
          break;
      }
    }
    return res;
  }
}
