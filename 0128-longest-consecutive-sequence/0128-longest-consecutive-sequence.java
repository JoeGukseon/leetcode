class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return 1;
        }
        Arrays.sort(nums);
        int cnt = 1, max = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] == nums[i] - 1) {
                cnt++;
            } else if (nums[i - 1] == nums[i]) {
                
            } else {
                cnt = 1;
            }
            max = Math.max(max, cnt);
        }
        return max;
    }
}