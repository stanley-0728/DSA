package Greedy;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import Greedy.Misc.Pair;
import Greedy.Misc.Pair1;
import Greedy.Misc.Pair3;

public class Misc {
  public static void main(String[] args) {
    int[] tokens = { 100, 200, 300, 400 };
    int power = 200;
    System.out.println(bagOfTokensScore(tokens, power));
  }

  public int maximum69Number(int num) {
    int x = 10000;
    while (x > 0) {
      System.out.println(num / x);
      if ((num / x) % 10 == 6) {
        num += 3 * x;
        break;
      }
      x /= 10;
    }
    return num;
  }

  public int findContentChildren(int[] g, int[] s) {
    int i = 0, j = 0;
    int c = 0;
    Arrays.sort(s);
    Arrays.sort(g);
    while (i < g.length && j < s.length) {
      if (s[j] >= g[i]) {
        i++;
        c++;
      }
      j++;

    }
    return c;
  }

  public boolean lemonadeChange(int[] bills) {
    int five = 0, ten = 0;
    for (int i : bills) {
      if (i == 5)
        five++;
      else if (i == 10 && five > 0) {
        ten++;
        five--;
      } else if (i == 20) {
        if (ten > 0 && five > 0) {
          ten--;
          five--;
        } else if (five > 3) {
          five -= 3;
        } else
          return false;
      } else
        return false;
    }
    return true;
  }

  public int maxMeetings(int start[], int end[]) {
    // add your code here
    int n = start.length;
    List<Pair> list = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      list.add(new Pair(start[i], end[i]));
    }
    list.sort((a, b) -> a.second - b.second);
    int ans = 1;
    int prevEnd = list.get(0).second;
    int prevStart = list.get(0).first;
    for (int i = 1; i < n; i++) {
      int currStart = list.get(i).first;
      int currEnd = list.get(i).second;
      if (currStart > prevEnd && prevStart < currEnd) {

        prevEnd = currEnd;
        prevStart = currStart;
        ans++;
      }
    }
    return ans;
  }

  public int eraseOverlapIntervals(int[][] intervals) {
    int n = intervals.length;
    List<Pair> list = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      list.add(new Pair(intervals[i][0], intervals[i][1]));
    }
    list.sort((a, b) -> a.second - b.second);
    int ans = 1;
    int prevEnd = list.get(0).second;
    int prevStart = list.get(0).first;
    for (int i = 1; i < n; i++) {
      int currStart = list.get(i).first;
      int currEnd = list.get(i).second;
      if (currStart >= prevEnd && prevStart <= currEnd) {

        prevEnd = currEnd;
        prevStart = currStart;
        ans++;
      }
    }
    return intervals.length - ans;
  }

  static int findPlatform(int arr[], int dep[]) {
    // add your code here
    Queue<Pair1> queue = new PriorityQueue<>((a, b) -> {
      if (a.first == b.first) {
        return a.second - b.second;
      }
      return a.first - b.first;
    });
    for (int i = 0; i < arr.length; i++) {
      queue.add(new Pair1(arr[i], 'A'));
      queue.add(new Pair1(dep[i], 'D'));
    }
    int maxAns = 0;
    int ans = 0;
    while (!queue.isEmpty()) {
      Pair1 top = queue.poll();
      if (top.second == 'A') {
        ans++;
        maxAns = Math.max(maxAns, ans);
      } else
        ans--;
    }
    return maxAns;
  }

  public static int bagOfTokensScore(int[] tokens, int power) {
    Arrays.sort(tokens);
    int i = 0, j = tokens.length - 1;
    int score = 0;
    while (i < j) {

      if (tokens[i] <= power) {
        score++;
        power -= tokens[i];
        i++;
      } else {
        if (score >= 1) {
          score--;
          power += tokens[j];
          j--;
        } else
          break;
      }
    }
    if (i < tokens.length && tokens[i] <= power)
      score++;

    return score;
  }

  public int numRescueBoats(int[] people, int limit) {
    Arrays.sort(people);
    int i = 0, j = people.length - 1;
    int boats = 0;
    ;
    while (i < j) {
      if (people[i] + people[j] <= limit) {
        i++;
        j--;
      } else if (people[j] > people[i]) {
        j--;
      } else {
        i++;
      }
      boats++;
    }
    if (i == j)
      boats++;
    return boats;
  }

  public int minCost(String colors, int[] neededTime) {
    int j = colors.length();
    int time = 0;
    int prevMax = neededTime[0];
    for (int i = 1; i < j; i++) {
      if (colors.charAt(i) == colors.charAt(i - 1)) {
        System.out.println(i + " " + Math.min(neededTime[i], prevMax));
        time += Math.min(neededTime[i], prevMax);
        prevMax = Math.max(neededTime[i], prevMax);
      } else
        prevMax = neededTime[i];
    }
    return time;
  }

  public int maxIceCream(int[] costs, int coins) {
    int[] buckets = new int[(int) 1e5 + 1];
    for (int i = 0; i < costs.length; i++) {
      buckets[costs[i]]++;
    }
    int ans = 0;
    for (int i = 0; i < buckets.length; i++) {
      if (coins < i)
        break;
      if (buckets[i] > 0) {
        ans += Math.min(buckets[i], coins / i);
        coins -= Math.min(coins, i * buckets[i]);
      }
    }
    return ans;
  }

  public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
    ArrayList<Pair> list = new ArrayList<>();
    for (int i = 0; i < rocks.length; i++)
      list.add(new Pair(Math.abs(rocks[i] - capacity[i]), i));
    list.sort((a, b) -> a.first - b.first);
    int i = 0, total = 0;
    while (i < list.size()) {
      int needed = list.get(i).first;
      if (additionalRocks >= needed) {
        additionalRocks -= needed;
        total++;
      } else
        break;
      i++;
    }
    return total;
  }

  public int minimumRounds(int[] tasks) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i : tasks) {
      map.put(i, map.getOrDefault(i, 0) + 1);
    }
    int min = 0;
    for (Map.Entry<Integer, Integer> pair : map.entrySet()) {
      if (pair.getValue() <= 1)
        return -1;
      if (pair.getValue() % 3 == 0)
        min += pair.getValue() / 3;
      else
        min += pair.getValue() / 3 + 1;
    }
    return min;
  }

  public int partitionString(String s) {
    HashSet<Character> set = new HashSet<>();
    int count = 0;
    for (char c : s.toCharArray()) {
      if (set.contains(c)) {
        count++;
        set.clear();
        set.add(c);
      } else {
        set.add(c);
      }
    }
    if (set.size() > 0)
      count++;
    return count;
  }

  public int maxDistance(List<List<Integer>> arrays) {
    int prevMax = arrays.get(0).get(arrays.get(0).size() - 1);
    int prevMin = arrays.get(0).get(0);
    int maxDiff = 0;
    for (int i = 1; i < arrays.size(); i++) {
      int size = arrays.get(i).size();
      int currMax = arrays.get(i).get(size - 1);
      int currMin = arrays.get(i).get(0);
      int diff = Math.max(Math.abs(prevMax - currMin), Math.abs(currMax - prevMin));
      prevMax = Math.max(prevMax, currMax);
      prevMin = Math.min(currMin, prevMin);
      maxDiff = Math.max(maxDiff, diff);
    }
    return maxDiff;
  }

  public int minIncrementForUnique(int[] nums) {
    Arrays.sort(nums);
    int ans = 0;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] <= nums[i - 1]) {
        ans += nums[i - 1] - nums[i] + 1;
        nums[i] = nums[i - 1] + 1;
      }
    }
    return ans;
  }

  public int maxCoins(int[] piles) {
    Arrays.sort(piles);
    int i = 0;
    int j = piles.length - 1;
    int k = j - 1;
    int c = 0;
    while (i < k && k < j) {
      c += piles[k];
      k -= 2;
      j -= 2;
      i++;
    }
    return c;
  }

  public int minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
    horizontalCut = Arrays.stream(horizontalCut)
        .boxed() // Convert to Integer Stream
        .sorted((a, b) -> b - a) // Sort in reverse order
        .mapToInt(Integer::intValue) // Convert back to int[]
        .toArray();
    verticalCut = Arrays.stream(
        verticalCut)
        .boxed() // Convert to Integer Stream
        .sorted((a, b) -> b - a) // Sort in reverse order
        .mapToInt(Integer::intValue) // Convert back to int[]
        .toArray();
    int i = 0, j = 0;
    int verticalPieces = 1, horizontalPiece = 1;
    int cost = 0;
    while (i < horizontalCut.length && j < verticalCut.length) {
      if (verticalCut[j] >= horizontalCut[i]) {
        cost += verticalCut[i] * horizontalPiece;
        verticalPieces++;
        j++;
      } else {
        cost += horizontalCut[i] * verticalPieces;
        i++;
        horizontalPiece++;
      }
    }
    while (i < horizontalCut.length) {
      cost += horizontalCut[i] * verticalPieces;
      i++;
      horizontalPiece++;
    }
    while (j < verticalCut.length) {
      cost += verticalCut[i] * horizontalPiece;
      verticalPieces++;
      j++;
    }
    return cost;
  }

  public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
    Arrays.sort(arr);
    if (arr[0] != 1)
      arr[0] = 1;
    int value = arr[0];
    for (int i = 1; i < arr.length; i++) {
      if (Math.abs(arr[i] - arr[i - 1]) <= 1) {
        value = arr[i];
        continue;
      } else {
        arr[i] = value + 1;
        value = arr[i];
      }
    }
    return arr[arr.length - 1];
  }

  public int eliminateMaximum(int[] dist, int[] speed) {
    int count = 1, total = 1;
    int[] time = new int[dist.length];
    for (int i = 0; i < dist.length; i++) {
      time[i] = (int) Math.ceil((double) dist[i] / speed[i]);
    }
    Arrays.sort(time);
    for (int i = 1; i < time.length; i++) {
      if (time[i] - total <= 0)
        return count;
      else {
        count++;
        total++;
      }
    }
    return count;
  }

  public int maximumScore(int[] nums, int k) {
    int i = k, j = k;
    int currMin = nums[k];
    int result = nums[k];
    while (i > 0 || j < nums.length - 1) {
      int leftValue = (i > 0) ? nums[i - 1] : 0;
      int rightValue = (j < nums.length - 1) ? nums[j + 1] : 0;
      if (leftValue < rightValue) {
        j++;
        currMin = Math.min(currMin, nums[j]);
      } else {
        i--;
        currMin = Math.min(currMin, nums[i]);
      }
      result = Math.max(result, currMin * (j - i + 1));
    }
    return result;
  }

  public int maxCount(int[] banned, int n, int maxSum) {
    HashSet<Integer> set = new HashSet<>();
    for (int i : banned)
      set.add(i);
    int maxElements = 0, sum = 0;

    for (int i = 1; i <= n; i++) {
      if (set.contains(i)) {
        continue;
      }
      if (sum + i > maxSum)
        break;
      maxElements++;
      sum += i;
    }
    return maxElements;
  }

  public int minDeletions(String s) {
    int[] freq = new int[26];
    for (int i = 0; i < s.length(); i++) {
      freq[s.charAt(i) - 'a']++;
    }
    int result = 0;
    Arrays.sort(freq);
    for (int i = 24; i >= 0; i--) {
      if (freq[i] >= freq[i + 1]) {
        int prev = freq[i];
        freq[i] = Math.max(0, freq[i + 1] - 1);
        result += prev - freq[i];
      }
    }
    return result;
  }

  public int maxMeetings(int start[], int end[]) {
    // add your code here
    int n = start.length;
    List<Pair> list = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      list.add(new Pair(start[i], end[i]));
    }
    list.sort((a, b) -> a.second - b.second);
    int ans = 1;
    int prevEnd = list.get(0).second;
    int prevStart = list.get(0).first;
    for (int i = 1; i < n; i++) {
      int currStart = list.get(i).first;
      int currEnd = list.get(i).second;
      if (currStart > prevEnd && prevStart < currEnd) {
        prevEnd = currEnd;
        prevStart = currStart;
        ans++;
      }
    }
    return ans;
  }

  public boolean winnerOfGame(String colors) {
    int a = 0, b = 0;
    for (int i = 0; i < colors.length - 1; i++) {
      if (colors.charAt(i) == 'A') {
        if (colors.charAt(i - 1) == 'A' && colors.charAt(i + 1) == 'A')
          a++;
      }
      if (colors.charAt(i) == 'B') {
        if (colors.charAt(i - 1) == 'B' && colors.charAt(i + 1) == 'B')
          b++;
      }
    }
    return a > b ? true : false;
  }

  public double maxAverageRatio(int[][] classes, int extraStudents) {
    PriorityQueue<Pair3> maxHeap = new PriorityQueue<>((a, b) -> Double.compare(b.gain, a.gain));

    for (int[] c : classes) {
      double gain = ((double) (c[0] + 1) / (c[1] + 1)) - ((double) c[0] / c[1]);
      maxHeap.add(new Pair3(gain, c[0], c[1]));
    }
    double avg = 0;
    while (extraStudents > 0) {
      Pair3 obj = maxHeap.poll();
      obj.pass++;
      obj.total++;
      obj.gain = ((double) (obj.pass + 1) / (obj.total + 1)) - ((double) obj.pass / obj.total);
      maxHeap.add(obj);
      extraStudents--;
    }
    double avg = 0;
    while (!maxHeap.isEmpty()) {
      Pair3 obj = maxHeap.poll();
      avg += (double) obj.pass / obj.total;
    }
    return avg / classes.length;
  }

  public int minPatches(int[] nums, int n) {
    long maxReach = 0;
    int patch = 0;
    int i = 0;
    while (maxReach < n) {
      if (i < nums.length && nums[i] <= (int) maxReach + 1) {
        maxReach += nums[i];
        i++;
      } else {
        maxReach += maxReach + 1;
        patch++;
      }
    }
    return patch;
  }

  public long maximumValueSum(int[] nums, int k, int[][] edges) {
    Integer[] delta = new Integer[nums.length];
    int sum = 0;
    for (int i = 0; i < nums.length; i++) {
      delta[i] = (nums[i] ^ k) - nums[i];
      sum += nums[i];
    }
    Arrays.sort(delta, (a, b) -> Long.compare(b, a));

    for (int i = 0; i < nums.length - 1; i += 2) {
      if (delta[i] > 0 && delta[i + 1] > 0)
        sum += delta[i] + delta[i + 1];
    }
    return sum;
  }

  public long maximumHappinessSum(int[] happiness, int k) {
    Long[] nums = new Long[happiness.length];
    for (int i = 0; i < happiness.length; i++)
      nums[i] = (Long) happiness[i];
    Arrays.sort(nums, (a, b) -> Long.compare(b, a));
    long sum = 0, count = 0;
    int i = 0;
    while (k > 0) {
      nums[i] -= count;
      if (nums[i] < 0)
        break;
      sum += nums[i];
      count++;
      k--;
      i++;
    }
    return sum;
  }

  public int leastInterval(char[] tasks, int n) {
    Integer[] freq = new Integer[26];
    Arrays.fill(freq, 0);
    for (char c : tasks)
      freq[c - 'A']++;
    Arrays.sort(freq, (a, b) -> b - a);
    int idle = freq[0] * n;
    int total = freq[0] * n;
    for (int i = 1; i < 26; i++) {
      if (freq[i] == 0)
        continue;
      idle -= Math.min(idle / n, freq[i]);
    }
    return tasks.length + idle;
  }

  public int findLeastNumOfUniqueInts(int[] arr, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i : arr)
      map.putIfAbsent(i, map.getOrDefault(i, 0) + 1);
    PriorityQueue<Pair> minHeap = new PriorityQueue<>((a, b) -> a.second - b.second);
    for (Map.Entry<Integer, Integer> pair : map.entrySet()) {
      System.out.println(pair.getKey() + " " + pair.getValue());
      minHeap.add(new Pair(pair.getKey(), pair.getValue()));
    }
    while (k > 0) {
      Pair pair = minHeap.poll();
      pair.second -= Math.min(pair.second, k);

      k -= Math.min(pair.second, k);

      if (pair.second > 0)
        minHeap.add(new Pair(pair.first, pair.second));
    }
    return minHeap.size();
  }

  public int earliestFullBloom(int[] plantTime, int[] growTime) {
    ArrayList<Pair> list = new ArrayList<>();
    for (int i = 0; i < plantTime.length; i++) {
      list.add(new Pair(plantTime[i], growTime[i]));
    }
    list.sort((a, b) -> b.second - a.second);
    int prevPlantTime = 0;
    int maxBloomDays = Integer.MIN_VALUE;
    for (Pair pair : list) {
      int currPlantTime = pair.first;
      int currGrowTime = pair.second;
      int currBloomDay = prevPlantTime + currPlantTime + currGrowTime;
      prevPlantTime = currPlantTime;
      maxBloomDays = Math.max(maxBloomDays, currBloomDay);
    }
    return maxBloomDays;
  }

  public long minimumReplacement(int[] nums) {
    int operations = 0;
    for (int i = nums.length - 2; i >= 0; i--) {
      if (nums[i + 1] > nums[i])
        continue;
      else {
        int parts = nums[i] / nums[i + 1];
        if (nums[i] % nums[i + 1] != 0)
          parts++;
        operations += parts - 1;
        nums[i] = nums[i] / parts;
      }
    }
    return operations;
  }

  public int jump(int[] nums) {
    int left = 0, right = 0, jumps = 0;
    while (right < nums.length - 1) {
      int farthest = right;
      for (int i = left; i <= right; i++) {
        farthest = Math.max(nums[i] + i, farthest);
      }
      if (farthest == right)
        return -1;
      left = right + 1;
      right = farthest;
      jumps++;
    }
    return jumps;
  }

  public int minTaps(int n, int[] ranges) {
    int[] maxReach = new int[n + 1];

    // Convert ranges into a start-end format
    for (int i = 0; i <= n; i++) {
      int left = Math.max(0, i - ranges[i]);
      int right = Math.min(n, i + ranges[i]);
      maxReach[left] = Math.max(maxReach[left], right);
    }

    int taps = 0, left = 0, right = 0;
    while (right < n) {
      int farthest = 0;

      // Find the maximum reach within the current range
      for (int i = left; i <= right; i++) {
        farthest = Math.max(farthest, maxReach[i]);
      }

      // If we are stuck and cannot extend, return -1
      if (farthest == right)
        return -1;

      // Move to the next range and increment taps
      left = right + 1;
      right = farthest;
      taps++;
    }

    return taps;
  }

  public int videoStitching(int[][] clips, int time) {
    Arrays.sort(clips, (a, b) -> a[0] - b[0]);

    int left = 0, right = 0, clipsUsed = 0, i = 0;

    while (right < time) {
      int farthest = right;

      while (i < clips.length && clips[i][0] <= right) {
        farthest = Math.max(farthest, clips[i][1]);
        i++;
      }

      if (farthest == right)
        return -1;

      clipsUsed++;
      left = right + 1;
      right = farthest;
    }

    return clipsUsed;
  }

  public int minRefuelStops(int target, int startFuel, int[][] stations) {
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
    int fuel = startFuel, stops = 0, index = 0;

    while (fuel < target) {

      while (index < stations.length && stations[index][0] <= fuel) {
        maxHeap.add(stations[index][1]);
        index++;
      }

      if (maxHeap.isEmpty())
        return -1;

      fuel += maxHeap.poll();
      stops++;
    }

    return stops;
  }

  int min_sprinklers(int gallery[], int n) {
    List<int[]> sprinklers = new ArrayList<>();

    // Step 1: Convert sprinklers into [start, end] format
    for (int i = 0; i < n; i++) {
      if (gallery[i] != -1) {
        int left = Math.max(0, i - gallery[i]);
        int right = Math.min(n - 1, i + gallery[i]);
        sprinklers.add(new int[] { left, right });
      }
    }

    // Step 2: Sort sprinklers by start position, prioritize larger end
    sprinklers.sort((a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);

    int count = 0, covered = 0, i = 0;

    // Step 3: Greedily cover the gallery
    while (covered < n) {
      int maxReach = -1;

      // Select the sprinkler that extends the coverage the farthest
      while (i < sprinklers.size() && sprinklers.get(i)[0] <= covered) {
        maxReach = Math.max(maxReach, sprinklers.get(i)[1]);
        i++;
      }

      // If we cannot extend coverage, return -1
      if (maxReach == -1)
        return -1;

      // Move to the next uncovered position
      covered = maxReach + 1;
      count++;
    }

    return count;
  }

}

class Pair1 {
  int first;
  char second;

  Pair1(int first, char second) {
    this.first = first;
    this.second = second;
  }
}

class Pair {
  int first, second;

  Pair(int first, int second) {
    this.first = first;
    this.second = second;
  }
}

class Pair3 {
  double first;
  int second, third;

  Pair3(double first, int second, int third) {
    this.first = first;
    this.second = second;
    this.third = third;
  }
}