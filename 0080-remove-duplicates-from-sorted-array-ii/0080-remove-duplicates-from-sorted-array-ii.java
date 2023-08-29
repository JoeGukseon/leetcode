class Solution {
    public int removeDuplicates(int[] nums) {
        int i = 0;
        int cnt = 1;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                i++;
                cnt = 1;
                nums[i] = nums[j];

            } else if (nums[i] == nums[j] && cnt == 1) {
                cnt++;
                i++;
                nums[i] = nums[j];
            } 
        }
        return i+1;
    }
}