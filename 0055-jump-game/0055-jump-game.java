class Solution {
    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        int goal = nums.length;
        int max = -1;

        for (int i = 0; i < nums.length - 1; i++) {
            if ((i + 1) + nums[i] >= goal) {
                return true;
            } else if (i > max && nums[i] == 0) {
                return false;
            }
            max = Math.max(max,nums[i] + i - 1);//갈수 있는 최대 범위
        }
        return false;
    }
}