package DP.SubSquencePattern.Knapsack;

import java.util.Arrays;

public class ReducingDishes {
  public int maxSatisfaction(int[] satisfaction) {
    Arrays.sort(satisfaction);
    int currSum = 0, maxSum = 0;
    for (int i = satisfaction.length - 1; i >= 0; i--) {
      currSum += satisfaction[i];
      System.out.println(currSum);
      if (currSum < 0)
        break;
      maxSum += currSum;
      System.out.println(maxSum);
    }
    return maxSum;
  }

}
