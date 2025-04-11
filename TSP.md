--
## 🧭 **Classic TSP (Overview)**
- **Goal**: Visit all cities exactly once and return to the starting point with minimum cost.
- **NP-Hard**: No polynomial-time solution for large N.
- **Approach**: DP with Bitmasking, Branch and Bound, or Approximation.

---

## 🔀 **TSP Variations**

### 1. **Classic TSP (Return to Origin)**
- **Requirement**: Must return to the starting city.
- **Approach**: DP + Bitmask  
- ✅ Leetcode-style: Not directly available, but custom implementations exist.  
- 🔧 DP State: `dp[mask][i]` = min cost to reach `i` with visited cities in `mask`.

---

### 2. **Asymmetric TSP**
- **Description**: Cost from city A to B ≠ cost from B to A.
- 🧠 Think: Real-world roads with one-way paths or different costs in each direction.
- 🔧 Same approach (bitmask DP), but with a non-symmetric cost matrix.

---

### 3. **TSP without Return (Hamiltonian Path)**
- **Description**: Visit all cities once, but don't need to return.
- ✅ **Leetcode 847 – Shortest Path Visiting All Nodes**  
  - Graph-based version of TSP (unweighted).
  - Solve with BFS + Bitmask.

---

### 4. **TSP with Constraints (Limited Fuel, Visitation Rules)**
- 🧩 Variation: Can't go to a node unless prerequisites met or cost ≤ budget.
- ✅ **Leetcode 943 – Find the Shortest Superstring**  
  - TSP variation with strings. Overlap of strings = path cost.
  - Solve using DP + Bitmasking.

---

### 5. **Multiple Travelling Salesmen (mTSP)**
- **Description**: More than one salesman; each handles a subset of cities.
- **Used in**: Logistics, delivery planning, drone routing.
- ✅ Not on Leetcode directly; seen in operations research.
- 🧠 Split cities into `m` groups & solve TSP individually or cooperatively.

---

### 6. **Prize Collecting TSP**
- **Description**: Visit cities to collect rewards. Not all cities are mandatory.
- **Goal**: Maximize prize – cost, or meet a minimum threshold.
- ✅ Similar to **Leetcode 1293 – Shortest Path in a Grid with Obstacles Elimination**

---

### 7. **TSP with Time Windows**
- **Description**: Must reach each city within a given time window.
- 🕓 Often used in delivery services (e.g., reach between 2 PM–4 PM).
- ✅ Custom implementation only. Closest: VRP solvers like OR-Tools.

---

### 8. **Vehicle Routing Problem (VRP)**
- **Extension of TSP**: Multiple vehicles, capacity constraints, depot return.
- ✅ Not on Leetcode; Google OR-Tools has real-world datasets & solvers.

---

### 9. **TSP with Pickups and Deliveries**
- **Description**: Some nodes require you to pickup first, then deliver.
- ✅ Real-world: Courier services, ride-sharing apps.
- ✅ Similar concepts in **Leetcode Pickup and Delivery Problems**.

---

### 10. **TSP with Revisit Allowed (Relaxed TSP)**
- **Description**: You may revisit cities, or nodes multiple times.
- ✅ Leetcode 847 variation can be adjusted this way.

---

## 📚 Leetcode-Style Problems Inspired by TSP

| Problem Name | Leetcode ID | Concept |
|--------------|-------------|---------|
| Shortest Path Visiting All Nodes | 847 | TSP without return |
| Find the Shortest Superstring | 943 | TSP with strings |
| Traveling Salesman Bitmask DP | – | Custom implementation |
| Robot Room Cleaner | 489 | DFS exploration similar to TSP |
| Reconstruct Itinerary | 332 | Path-based graph traversal |
| Cheapest Flights Within K Stops | 787 | Constrained path finding |
| Shortest Path to Get All Keys | 864 | TSP with stateful conditions |

---

                          +------------------+
                          |   Classic TSP    |
                          +--------+---------+
                                   |
       +---------------------------+---------------------------+
       |                           |                           |
+--------------+      +----------------------+     +---------------------------+
| Hamiltonian  |      | Asymmetric TSP       |     |  TSP with Constraints      |
| Path (No RTN)|      | (Cost A→B ≠ B→A)      |     |  (Time, fuel, etc.)       |
+--------------+      +----------------------+     +---------------------------+
       |                                                     |
       |                                                     |
+--------------+                                  +----------------------------+
| Leetcode 847 |                                  | Prize Collecting TSP       |
| Shortest Path|                                  | TSP with Time Windows      |
+--------------+                                  | Pickups & Deliveries       |
                                                  +----------------------------+

                             +---------------------------+
                             | Multiple Salesman (mTSP)  |
                             | + VRP + Capacity Limits   |
                             +---------------------------+

Here's your 🔥 **TSP Variants Practice Sheet** — sorted by concept, difficulty, and linked directly to problems you can try:

---

## 🚀 Travelling Salesman Problem – Practice Sheet

| ✅ | **Topic**                        | **Problem**                                                                 | **Platform**   | **Concept**                          |
|----|----------------------------------|------------------------------------------------------------------------------|----------------|--------------------------------------|
| 🔰 | Hamiltonian Path                 | [847. Shortest Path Visiting All Nodes](https://leetcode.com/problems/shortest-path-visiting-all-nodes) | Leetcode       | TSP without return, Bitmask + BFS    |
| 🔰 | Reconstruct Path                 | [332. Reconstruct Itinerary](https://leetcode.com/problems/reconstruct-itinerary) | Leetcode       | Graph traversal + lexicographic sort |
| 🔰 | Pickup & Delivery Combinations   | [1359. Count All Valid Pickup and Delivery Options](https://leetcode.com/problems/count-all-valid-pickup-and-delivery-options) | Leetcode       | Combinatorics + DP                  |
| 🔰 | TSP with Locks & Keys            | [864. Shortest Path to Get All Keys](https://leetcode.com/problems/shortest-path-to-get-all-keys) | Leetcode       | BFS + Bitmask State                 |
| 🔰 | Cheapest TSP with Stops         | [787. Cheapest Flights Within K Stops](https://leetcode.com/problems/cheapest-flights-within-k-stops) | Leetcode       | BFS / Dijkstra Variant              |
| 🔰 | TSP via String Concatenation     | [943. Find the Shortest Superstring](https://leetcode.com/problems/find-the-shortest-superstring) | Leetcode       | Bitmask DP + Greedy Overlaps        |
| 🔰 | TSP with Probabilities           | [1514. Path with Maximum Probability](https://leetcode.com/problems/path-with-maximum-probability) | Leetcode       | Max Heap + Graph Probabilities      |
| 🔰 | Maze TSP                         | [499. The Maze III](https://leetcode.com/problems/the-maze-iii)             | Leetcode       | DFS / PriorityQueue                 |
| 🔰 | Path Coverage (no cycle)         | [690. Employee Importance](https://leetcode.com/problems/employee-importance) | Leetcode       | DFS / BFS                           |
| 🔰 | Minimal TSP Path Length          | [Held-Karp TSP (Custom)](https://cp-algorithms.com/graph/held-karp.html)    | CP-Algorithms  | DP + Bitmask                        |

---

## 🔧 Advanced TSP (Google OR-Tools Recommended)

| ✅ | **Topic**                         | **Guide / Tool**                                               | **Concept**                         |
|-----|-----------------------------------|------------------------------------------------------------------|-------------------------------------|
| 🔧 | TSP with Time Windows              | [Google OR-Tools TSP TW](https://developers.google.com/optimization/routing/vrptw) | Vehicle Routing + Time Constraints |
| 🔧 | Multiple Salesmen (mTSP)          | [Google OR-Tools VRP](https://developers.google.com/optimization/routing/vrp) | Capacitated VRP                    |
| 🔧 | TSP with Pickup & Deliveries      | [Google OR-Tools PDP](https://developers.google.com/optimization/routing/pdp) | Pair Constraints + Route Handling  |
| 🔧 | TSP with Multiple Depots          | [Google OR-Tools Multi-Depot](https://developers.google.com/optimization/routing/multi_depot) | VRP with multiple starts/ends      |

---

## ✍️ Suggested Practice Order

1. 847 → 332 → 1359  
2. 943 → 864 → 787  
3. 1514 → 499  
4. Then go to OR-Tools for real-world applications

---

