package Graph.ShortestPath;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class UndirectedGraph {
  /*
   * Apply Simple bfs on graph and relax the edges . Because queue ensures
   * shortest dist 1,2,3, it goes in sorted manner so no need of any min heap
   * T.C O(N+2E)
   */
  public int[] shortestPath(ArrayList<ArrayList<Integer>> adj, int src) {
    int[] dist = new int[adj.size()];
    Arrays.fill(dist, Integer.MAX_VALUE);
    Queue<Integer> queue = new LinkedList<>();
    dist[src] = 0;
    queue.add(src);
    while (!queue.isEmpty()) {
      int node = queue.poll();
      for (int i : adj.get(node)) {
        if (1 + dist[node] < dist[i]) {
          dist[i] = 1 + dist[node];
          queue.add(i);
        }
      }
    }
    for (int i = 0; i < adj.size(); i++) {
      if (dist[i] == Integer.MAX_VALUE)
        dist[i] = -1;
    }
    return dist;
  }
}
