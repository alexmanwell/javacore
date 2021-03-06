package algorithms.arrays;
/*
        Link: https://leetcode.com/explore/learn/card/fun-with-arrays/521/introduction/3238/

        Given a binary array nums, return the maximum number of consecutive 1's in the array.

        Example 1:

        Input: nums = [1,1,0,1,1,1]
        Output: 3
        Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.

        Example 2:

        Input: nums = [1,0,1,1,0,1]
        Output: 2

        Constraints:

        1 <= nums.length <= 105
        nums[i] is either 0 or 1.
*/

class MaxConsecutiveOnes {

  int findMaxConsecutiveOnes(int[] nums) {
    int sum = 0;
    int max = 0;
    boolean isZero = false;
    for (int num : nums) {
      if (isZero && num == 1) {
        sum = 0;
      }
      if (num == 1) {
        sum += 1;
        isZero = false;
      } else {
        isZero = true;
        max = (max <= sum) ? sum : max;
      }
    }

    return (max <= sum) ? sum : max;
  }
}
