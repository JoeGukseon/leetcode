class Solution {
    public int findPeakElement(int[] nums) {
        return findPeak(nums, 0, nums.length - 1);
    }

    private int findPeak(int[] nums, int left, int right) {
        if (left == right) {
            return left;
        }

        int mid = left + (right - left) / 2;

        if (nums[mid] > nums[mid + 1]) {
            return findPeak(nums, left, mid);
        } else {
            return findPeak(nums, mid + 1, right);
        }
    }
}