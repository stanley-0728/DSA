
package Graph.ShortestPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Pair {
  int edge, weight;

  Pair(int edge, int weight) {
    this.edge = edge;
    this.weight = weight;
  }

  public int getEdge() {
    return edge;
  }

  public int getWeight() {
    return weight;
  }

}

/*
 * This Problem is related Shortest Path in Directed Acyclic Graph .
 * Why topo sort?
 * Since the graph is a DAG, we can take advantage of its structure. Topological
 * sorting gives a valid processing order where each node appears before its
 * dependent nodes. This allows us to process each node efficiently without
 * needing a priority queue (like Dijkstra) or multiple passes (like
 * Bellman-Ford). The result is a fast O(V + E) solution that computes the
 * shortest paths in a single pass through the topologically sorted order.
 * What's the algo ?
 * 1 . Create adj grapy with ArrayList of pairs
 * 2 . generate topo sort
 * 3. Relax the edges from src
 */
public class DirectedGraph {
  static ArrayList<Integer> topologicalSort(ArrayList<ArrayList<Pair>> adj) {
    int V = adj.size();
    int inDegree[] = new int[V];
    for (ArrayList<Pair> i : adj) {
      for (Pair pair : i) {
        inDegree[pair.getEdge()]++;
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
      for (Pair pair : adj.get(node)) {
        inDegree[pair.getEdge()]--;
        if (inDegree[pair.getEdge()] == 0)
          queue.add(pair.getEdge());
      }
    }
    return top;
  }

  public int[] shortestPath(int V, int E, int[][] edges, int src) {
    ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
    for (int i = 0; i < V; i++)
      adj.add(new ArrayList<>());
    for (int[] i : edges) {
      adj.get(i[0]).add(new Pair(i[1], i[2]));
    }
    ArrayList<Integer> top = topologicalSort(adj);
    int[] dist = new int[V];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[src] = 0;
    for (int node : top) {
      if (dist[node] != Integer.MAX_VALUE) {
        for (Pair pair : adj.get(node)) {
          int newDist = dist[node] + pair.getWeight();
          if (newDist < dist[pair.getEdge()]) {
            dist[pair.getEdge()] = newDist;
          }
        }
      }
    }
    for (int i = 0; i < V; i++) {
      if (dist[i] == Integer.MAX_VALUE)
        dist[i] = -1;
    }
    return dist;
  }

  public int[] shortestPath(int V, int E, int[][] edges) {
    return shortestPath(V, E, edges, 0);
  }
}
