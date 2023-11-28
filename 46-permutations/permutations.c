/**
 * Return an array of arrays of size *returnSize.
 * The sizes of the arrays are returned as *returnColumnSizes array.
 * Note: Both returned array and *columnSizes array must be malloced, assume caller calls free().
 */
void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

void backtrack(int *nums, int numsSize, int start, int **result, int *resultSize) {
    if (start == numsSize) {
        result[*resultSize] = (int *)malloc(numsSize * sizeof(int));
        memcpy(result[*resultSize], nums, numsSize * sizeof(int));
        (*resultSize)++;
        return;
    }

    for (int i = start; i < numsSize; i++) {
        swap(&nums[i], &nums[start]);

        backtrack(nums, numsSize, start + 1, result, resultSize);

        swap(&nums[i], &nums[start]);
    }
}

int** permute(int* nums, int numsSize, int* returnSize, int** returnColumnSizes) {
    int factorial = 1;
    for (int i = 1; i <= numsSize; i++) {
        factorial *= i;
    }

    int **result = (int **)malloc(factorial * sizeof(int *));
    *returnSize = 0;

    backtrack(nums, numsSize, 0, result, returnSize);

    *returnColumnSizes = (int *)malloc(*returnSize * sizeof(int));
    for (int i = 0; i < *returnSize; i++) {
        (*returnColumnSizes)[i] = numsSize;
    }

    return result;
}