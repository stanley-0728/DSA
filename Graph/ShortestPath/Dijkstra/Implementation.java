import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Implementation {
  /*
   * Notes - Dijkstra as Shortest path from a single source to all the vertices in
   * the graph.
   * Why min heap ,suppose if you use queue also it will give you the same answer
   * but you will do more computation unnecessary exploring paths.(brute force
   * way)
   * Instead of queue if you use min heap you are following greddy approach you
   * always want to explore minium path first in that case computation will be
   * less.
   * Even if you replace minheap with set also same thing happens a miniute change
   * in time complexity suppose in queue there (4,10) but now node 4 you got 8 in
   * min heap we just add new dist but in set you delete the old val since we got
   * better minium so why to explore for (4,10)
   * 
   * Now what is the time complexity
   * while (!pq.isEmpty()) { O(V) where is vertices
   * Pair temp = pq.poll(); O(heap size)
   * int currDist = temp.getWeight();
   * int node = temp.getEdge();
   * for (Pair pair : adj.get(node)) { let us this ne edges
   * if (currDist + pair.getWeight() < dist[pair.getEdge()]) {
   * dist[pair.getEdge()] = currDist + pair.getWeight();
   * pq.add(new Pair(pair.getEdge(), dist[pair.getEdge()])); O(heapSize)
   * }
   * }
   * }
   * T.C (E Log(V)) where is total edges and v is vertices
   */

  public static ArrayList<Integer> dijkstra(ArrayList<ArrayList<Integer>> vec, int vertices, int edges, int source) {
    // Write your code here.
    ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
    for (int i = 0; i < vertices; i++)
      adj.add(new ArrayList<>());
    for (int i = 0; i < vec.size(); i++) {
      ArrayList<Integer> temp = vec.get(i);
      adj.get(temp.get(0)).add(new Pair(temp.get(1), temp.get(2)));
      adj.get(temp.get(1)).add(new Pair(temp.get(0), temp.get(2)));

    }
    PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.getWeight() - b.getWeight());
    pq.add(new Pair(source, 0));
    int[] dist = new int[vertices];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[source] = 0;
    while (!pq.isEmpty()) {
      Pair temp = pq.poll();
      int currDist = temp.getWeight();
      int node = temp.getEdge();
      for (Pair pair : adj.get(node)) {
        if (currDist + pair.getWeight() < dist[pair.getEdge()]) {
          dist[pair.getEdge()] = currDist + pair.getWeight();
          pq.add(new Pair(pair.getEdge(), dist[pair.getEdge()]));
        }
      }
    }

    ArrayList<Integer> res = new ArrayList<>();
    for (int i : dist)
      res.add(i);
    return res;
  }
}

class Pair {
  int edge, weight;

  Pair(int edge, int weight) {
    this.edge = edge;
    this.weight = weight;
  }

  int getEdge() {
    return edge;
  }

  int getWeight() {
    return weight;
  }
}