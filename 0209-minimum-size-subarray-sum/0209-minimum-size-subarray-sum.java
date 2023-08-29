class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int right = 0;

        int cnt = Integer.MAX_VALUE;
        int sum = 0;
        boolean flag = false;

        while (right < nums.length) {
            sum += nums[right];
            while (sum >= target) {
                flag = true;
                cnt = Math.min(cnt, (right - left)+1);
                sum -= nums[left];
                left++;
            }
            right++;
        }
        if(!flag) return 0;
        else return cnt;
    }
}