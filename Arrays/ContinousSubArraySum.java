package Arrays;

import java.util.HashMap;

public class ContinousSubArraySum {
  public boolean checkSubarraySum(int[] nums, int k) {
    int[] prefix = new int[nums.length + 1];
    prefix[0] = 0;

    for (int i = 0; i < nums.length; i++) {
      prefix[i + 1] = prefix[i] + nums[i];
    }

    if (nums.length < 2)
      return false;
    if (nums.length == 2) {
      return (prefix[2] % k == 0);
    }

    for (int i = 1; i < nums.length; i++) {
      for (int j = 0; j < i; j++) {
        if ((i - j >= 1) && ((prefix[i + 1] - prefix[j]) % k == 0)) {
          return true;
        }
      }
    }

    return false;
  }

    public boolean checkSubarraySum2(int[] nums, int k) {
        HashMap<Integer, Integer> remainderMap = new HashMap<>();
        remainderMap.put(0, -1);  // Edge case for subarray starting at index 0
        int prefixSum = 0;

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            int remainder = prefixSum % k;

            // Normalize remainder to handle negative values
            if (remainder < 0) remainder += k;

            // If remainder was seen before & subarray length >= 2, return true
            if (remainderMap.containsKey(remainder)) {
                if (i - remainderMap.get(remainder) > 1) return true;
            } else {
                // Store first occurrence of this remainder
                remainderMap.put(remainder, i);
            }
        }
        return false;
    }
}
