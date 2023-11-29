/**
 * Return an array of arrays of size *returnSize.
 * The sizes of the arrays are returned as *returnColumnSizes array.
 * Note: Both returned array and *columnSizes array must be malloced, assume caller calls free().
 */

typedef struct {
    int *arr;
    int size;
    int capacity;
} DynamicArray;

void initArray(DynamicArray *array, int capacity) {
    array->arr = (int *)malloc(sizeof(int) * capacity);
    array->size = 0;
    array->capacity = capacity;
}

void push(DynamicArray *array, int value) {
    if (array->size == array->capacity) {
        array->capacity *= 2;
        array->arr = (int *)realloc(array->arr, sizeof(int) * array->capacity);
    }
    array->arr[array->size++] = value;
}

void pop(DynamicArray *array) {
    if (array->size > 0) {
        array->size--;
    }
}

void combinationSumHelper(int *candidates, int candidatesSize, int target, DynamicArray *current, int start, int **result, int *resultSize, int *returnColumnSizes) {
    if (target < 0) {
        return;
    } else if (target == 0) {
        result[*resultSize] = (int *)malloc(sizeof(int) * current->size);
        returnColumnSizes[*resultSize] = current->size;
        for (int i = 0; i < current->size; i++) {
            result[*resultSize][i] = current->arr[i];
        }
        (*resultSize)++;
    } else {
        for (int i = start; i < candidatesSize; i++) {
            push(current, candidates[i]);
            combinationSumHelper(candidates, candidatesSize, target - candidates[i], current, i, result, resultSize, returnColumnSizes);
            pop(current);
        }
    }
}

int** combinationSum(int* candidates, int candidatesSize, int target, int* returnSize, int** returnColumnSizes) {
    DynamicArray current;
    initArray(&current, 10);

    int **result = (int **)malloc(sizeof(int *) * 1000);
    *returnColumnSizes = (int *)malloc(sizeof(int) * 1000);
    *returnSize = 0;

    combinationSumHelper(candidates, candidatesSize, target, &current, 0, result, returnSize, *returnColumnSizes);

    free(current.arr);
    return result;
}