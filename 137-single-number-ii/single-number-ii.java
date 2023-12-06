class Solution {
    public int singleNumber(int[] nums) {
        int[] bitCount = new int[32];

        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                bitCount[i] += (num >> i) & 1;
                bitCount[i] %= 3;
            }
        }

        int result = 0;

        for (int i = 0; i < 32; i++) {
            result |= (bitCount[i] << i);
        }

        return result;
    }
}