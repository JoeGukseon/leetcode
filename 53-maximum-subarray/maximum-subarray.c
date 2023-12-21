int max_crossing_sum(int nums[], int left, int mid, int right) {
    int left_sum = INT_MIN;
    int sum = 0;
    for (int i = mid; i >= left; i--) {
        sum += nums[i];
        left_sum = (left_sum > sum) ? left_sum : sum;
    }

    int right_sum = INT_MIN;
    sum = 0;
    for (int i = mid + 1; i <= right; i++) {
        sum += nums[i];
        right_sum = (right_sum > sum) ? right_sum : sum;
    }

    return left_sum + right_sum;
}

int max_subarray_helper(int nums[], int left, int right) {
    if (left == right) {
        return nums[left];
    }

    int mid = left + (right - left) / 2;

    int left_max = max_subarray_helper(nums, left, mid);
    int right_max = max_subarray_helper(nums, mid + 1, right);
    int cross_max = max_crossing_sum(nums, left, mid, right);

    return (left_max > right_max) ? ((left_max > cross_max) ? left_max : cross_max)
                                  : ((right_max > cross_max) ? right_max : cross_max);
}

int maxSubArray(int* nums, int numsSize) {
    return max_subarray_helper(nums, 0, numsSize - 1);
}