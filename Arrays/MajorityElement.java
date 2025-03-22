package Arrays;

import java.util.Arrays;

public class MajorityElement {
  public int majorityElement(int[] nums) {
    Arrays.sort(nums);
    int maxValue = nums[0], count = 1, maxCount = 1;
    int i = 1;
    for (; i < nums.length; i++) {
      if (nums[i - 1] == nums[i]) {
        count++;
      } else {
        if (count > maxCount) {
          maxCount = count;
          maxValue = nums[i - 1];
        }
        count = 1;
      }
    }
    if (count > maxCount) {
      maxCount = count;
      maxValue = nums[i - 1];
    }
    return maxValue;
  }
}
