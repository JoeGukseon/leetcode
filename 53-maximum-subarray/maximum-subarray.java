class Solution {
    public int maxSubArray(int[] nums) {
        return maxSubArrayHelper(nums, 0, nums.length - 1);
    }

    private int maxSubArrayHelper(int[] nums, int left, int right) {
        // 기저 조건
        if (left == right) {
            return nums[left];
        }
        
        // 중간 지점 계산
        int mid = left + (right - left) / 2;
        
        // 왼쪽, 오른쪽, 중앙에 걸친 부분 배열의 최대 부분합 계산
        int leftMax = maxSubArrayHelper(nums, left, mid);
        int rightMax = maxSubArrayHelper(nums, mid + 1, right);
        int crossMax = maxCrossingSum(nums, left, mid, right);
        
        // 세 부분 중에서 가장 큰 값 반환
        return Math.max(Math.max(leftMax, rightMax), crossMax);
    }

    private int maxCrossingSum(int[] nums, int left, int mid, int right) {
        // 왼쪽 부분 배열의 최대 부분합 계산
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            leftSum = Math.max(leftSum, sum);
        }
        
        // 오른쪽 부분 배열의 최대 부분합 계산
        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            rightSum = Math.max(rightSum, sum);
        }
        
        // 왼쪽과 오른쪽을 합친 최대 부분합 반환
        return leftSum + rightSum;
    }
}