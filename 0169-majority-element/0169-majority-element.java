class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums) {
            int val = hashMap.getOrDefault(num, 0) + 1;
            if (val > nums.length / 2) {
                return num;
            }
            hashMap.put(num, val);
        }
        return 0;
    }
}