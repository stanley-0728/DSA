package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// S.C o(n) T.C o(n+V)
/*
 * This topo sort using bfs . But this modified bfs since we are using indegree why in degree because , what is top sort if there is an edge b/w u and v , u should appear before v so first we will calcualte indegree of all nodes, which nodes have indegree zero is our first elements since no one can before them.
 * 
 * * This Algo we can apply to detect cycle in Directed Graph . What we know is top sort is only valid in dag but we will still apply topo sort on non acyclic at a point in the queue if see when we  are drecreasing indegree if adj nodes did not come to zero and your queue is empty . now you top sort does not contain n elements which means there is cycle.
 */
public class KhansAlgo {
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

  public boolean isCyclic(ArrayList<ArrayList<Integer>> adj) {
    // code here
    int V = adj.size();
    ArrayList<Integer> top = topologicalSort(adj);

    return (top.size() == V) ? false : true;
  }

}
