int kadane(int arr[], int n) {
    int max_sum = INT_MIN;
    int current_sum = 0;

    for (int i = 0; i < n; i++) {
        current_sum = (arr[i] > current_sum + arr[i]) ? arr[i] : current_sum + arr[i];
        max_sum = (max_sum > current_sum) ? max_sum : current_sum;
    }

    return max_sum;
}

int maxSubarraySumCircular(int* nums, int numsSize) {
    int max_inner_sum = kadane(nums, numsSize);

    int total_sum = 0;
    int inverted_nums[numsSize];

    for (int i = 0; i < numsSize; i++) {
        total_sum += nums[i];
        inverted_nums[i] = -nums[i];
    }

    int max_wrap_sum = total_sum + kadane(inverted_nums, numsSize);

    return (max_wrap_sum != 0) ? ((max_inner_sum > max_wrap_sum) ? max_inner_sum : max_wrap_sum) : max_inner_sum;
}