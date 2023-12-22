class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;

        int maxInnerSum = kadane(nums);

        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        int[] invertedNums = new int[n];
        for (int i = 0; i < n; i++) {
            invertedNums[i] = -nums[i];
        }

        int maxWrapSum = totalSum + kadane(invertedNums);

        return Math.max(maxInnerSum, (maxWrapSum == 0) ? maxInnerSum : maxWrapSum);
    }

    private int kadane(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        int currentSum = 0;

        for (int num : arr) {
            currentSum = Math.max(num, currentSum + num);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }
}