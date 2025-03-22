package Graph.Traversals;

import java.util.*;

/*
 * Given a undirected graph represented by an adjacency list adj, which is a vector of vectors where each adj[i] represents the list of vertices connected to vertex i. Perform a Breadth First Traversal (BFS) starting from vertex 0, visiting vertices from left to right according to the adjacency list, and return a list containing the BFS traversal of the graph.

Note: Do traverse in the same order as they are in the adjacency list.
 */
/*Step 1: Understanding BFS Execution
The BFS algorithm processes nodes level by level, ensuring each node is visited only once. During this traversal:

Each node is dequeued once → 
𝑂
(
𝑉
)
O(V).
Each edge is processed twice (since it appears in adjacency lists of both its endpoints) → 
𝑂
(
2
𝐸
)
O(2E).
Thus, we need to count:

The number of times we enter the while loop.
The number of times the for loop iterates over edges.
Step 2: Dry Run Analysis
We analyze the BFS traversal using the given graph:

rust
Copy
Edit
0 -> 1 ,2, 3
1 -> 0
2 -> 0, 4
3 -> 0
4 -> 2
Total vertices: V = 5
Total edges: E = 4

Tracking the while and for loop executions:
First iteration (while loop executes once for node 0)

queue.pop() → O(1)
for loop iterates over adj[0] → 3 neighbors → O(3)
Total operations: 
1
+
3
=
4
1+3=4.
Second iteration (while loop executes for node 1)

queue.pop() → O(1)
for loop iterates over adj[1] → 1 neighbor → O(1)
Total operations: 
1
+
1
=
2
1+1=2.
Third iteration (while loop executes for node 2)

queue.pop() → O(1)
for loop iterates over adj[2] → 2 neighbors → O(2)
Total operations: 
1
+
2
=
3
1+2=3.
Fourth iteration (while loop executes for node 3)

queue.pop() → O(1)
for loop iterates over adj[3] → 1 neighbor → O(1)
Total operations: 
1
+
1
=
2
1+1=2.
Fifth iteration (while loop executes for node 4)

queue.pop() → O(1)
for loop iterates over adj[4] → 1 neighbor → O(1)
Total operations: 
1
+
1
=
2
1+1=2.
Summing Up the Operations
(
1
+
3
)
+
(
1
+
1
)
+
(
1
+
2
)
+
(
1
+
1
)
+
(
1
+
1
)
=
5
+
8
=
13
(1+3)+(1+1)+(1+2)+(1+1)+(1+1)=5+8=13
This follows the pattern:

𝑂
(
𝑉
)
+
𝑂
(
2
𝐸
)
O(V)+O(2E)
Since each node is dequeued once and each edge is processed twice (once from each end), we get the final time complexity:

𝑂
(
𝑉
+
2
𝐸
)
O(V+2E)
Step 3: Generalizing the Formula
For any undirected graph:

Each node enters the while loop once → O(V)
Each edge is counted twice in adjacency lists → O(2E)
Thus, the total time complexity is:

𝑂
(
𝑉
+
2
𝐸
)
O(V+2E)
In a directed graph, each edge appears only once, so the complexity reduces to:

𝑂
(
𝑉
+
𝐸
)
O(V+E)
Key Takeaways
Why not 
𝑂
(
𝑉
×
2
𝐸
)
O(V×2E)?

BFS does not iterate over all edges for each vertex.
Instead, it processes each edge only twice (once from each endpoint).
Why 
𝑂
(
2
𝐸
)
O(2E) instead of 
𝑂
(
𝐸
)
O(E)?

In an undirected graph, each edge appears twice in the adjacency list.
Final Formula for BFS Complexity

Undirected Graph: 
𝑂
(
𝑉
+
2
𝐸
)
O(V+2E)
Directed Graph: 
𝑂
(
𝑉
+
𝐸
)
O(V+E) */
public class bfs {
  // S.C o(3N)
  // T.C o(N+2V)
  public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
    // code here
    int[] visited = new int[V];
    Queue<Integer> queue = new LinkedList<>();
    visited[0] = 1;
    queue.add(0);
    ArrayList<Integer> ans = new ArrayList<>();
    while (!queue.isEmpty()) {
      int node = queue.poll();
      ans.add(node);
      for (int i : adj.get(node)) {
        if (visited[i] == 0) {
          visited[i] = 1;
          queue.add(i);
        }
      }
    }
    return ans;

  }
}
