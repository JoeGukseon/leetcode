/**
 * Return an array of arrays of size *returnSize.
 * The sizes of the arrays are returned as *returnColumnSizes array.
 * Note: Both returned array and *columnSizes array must be malloced, assume caller calls free().
 */

void backtrack(int start, int n, int k, int *path, int path_len, int **result, int *result_len) {
    if (path_len == k) {
        result[*result_len] = malloc(k * sizeof(int));
        for (int i = 0; i < k; i++) {
            result[*result_len][i] = path[i];
        }
        (*result_len)++;
        return;
    }

    for (int i = start; i <= n; i++) {
        path[path_len] = i;
        backtrack(i + 1, n, k, path, path_len + 1, result, result_len);
    }
}

int** combine(int n, int k, int* returnSize, int** returnColumnSizes) {
    *returnSize = 1;
    for (int i = 1; i <= k; i++) {
        *returnSize *= (n - i + 1);
        *returnSize /= i;
    }

    int **result = malloc(*returnSize * sizeof(int*));
    *returnColumnSizes = malloc(*returnSize * sizeof(int));

    int *path = malloc(k * sizeof(int));
    int result_len = 0;
    backtrack(1, n, k, path, 0, result, &result_len);

    for (int i = 0; i < *returnSize; i++) {
        (*returnColumnSizes)[i] = k;
    }

    free(path);

    return result;
}