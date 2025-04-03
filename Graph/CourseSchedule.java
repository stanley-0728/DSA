package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CourseSchedule {

  public int topologicalSort(ArrayList<ArrayList<Integer>> adj) {
    // Your code here
    int V = adj.size();
    int inDegree[] = new int[V];
    for (ArrayList<Integer> i : adj) {
      for (int node : i) {
        inDegree[node]++;
      }
    }
    int cnt = 0;
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < V; i++) {
      if (inDegree[i] == 0)
        queue.add(i);
    }
    ArrayList<Integer> top = new ArrayList<>();
    while (!queue.isEmpty()) {
      int node = queue.poll();
      cnt++;
      for (int i : adj.get(node)) {
        inDegree[i]--;
        if (inDegree[i] == 0)
          queue.add(i);
      }
    }
    return cnt;
  }

  public ArrayList<Integer> topologicalSort(int numCourses, ArrayList<ArrayList<Integer>> adj) {
    // Your code here
    int V = adj.size();
    int inDegree[] = new int[V];
    for (ArrayList<Integer> i : adj) {
      for (int node : i) {
        inDegree[node]++;
      }
    }
    int cnt = 0;
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
    return top.size() == numCourses ? top : new ArrayList<>();
  }

  public boolean canFinish(int numCourses, int[][] prerequisites) {
    ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    for (int i = 0; i < numCourses; i++)
      adj.add(new ArrayList<>());
    for (int[] nums : prerequisites) {
      adj.get(nums[1]).add(nums[0]);
    }

    int cnt = topologicalSort(adj);
    return (cnt == numCourses - 1) ? true : false;
  }

  public int[] findOrder(int numCourses, int[][] prerequisites) {
    ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    for (int i = 0; i < numCourses; i++)
      adj.add(new ArrayList<>());
    for (int[] nums : prerequisites) {
      adj.get(nums[1]).add(nums[0]);
    }

    ArrayList<Integer> top = topologicalSort(numCourses, adj);
    if (top.size() == 0)
      return new int[0];
    int[] res = new int[numCourses];
    for (int i = 0; i < numCourses; i++) {
      res[i] = top.removeFirst();
    }
    return res;
  }

}
