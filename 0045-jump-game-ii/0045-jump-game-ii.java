class Solution {
    public int jump(int[] nums) {
        int cnt = 0;
        int goal = 0;
        int max = 0;
        int n = nums.length - 1;

        for (int i = 0; i < nums.length - 1; i++) {
            max = Math.max(max, i + nums[i]);
            if (max >= n) {
                ++cnt;
                break;
            }
            if (i == goal) {
                ++cnt;
                goal = max;
            }
        }
        return cnt;
    }
}