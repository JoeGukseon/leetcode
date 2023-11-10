/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int findPosition(int* nums, int numsSize, int target, int isFirst) {
    int left = 0;
    int right = numsSize - 1;
    int result = -1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (nums[mid] == target) {
            result = mid;

            if (isFirst) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        } else if (nums[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return result;
}


int* searchRange(int* nums, int numsSize, int target, int* returnSize) {
    int* result = (int*)malloc(2 * sizeof(int));

    result[0] = findPosition(nums, numsSize, target, 1);
    result[1] = findPosition(nums, numsSize, target, 0);

    *returnSize = 2;

    return result;
}