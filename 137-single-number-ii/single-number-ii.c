int singleNumber(int* nums, int numsSize) {
    int result = 0;
    for (int i = 0; i < 32; i++) {
        int bit_sum = 0;
        for (int j = 0; j < numsSize; j++) {
            bit_sum += (nums[j] >> i) & 1;
        }
        result |= ((bit_sum % 3) & 1U) << i;
    }

    if (result & (1U << 31)) {
        result |= ~((1U << 31) - 1);
    }

    return result;
}