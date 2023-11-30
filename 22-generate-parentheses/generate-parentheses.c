/**
 * Note: The returned array must be malloced, assume caller calls free().
 */

void backtrack(char* s, int left, int right, int n, int index, char*** result, int* returnSize) {
    if (index == 2 * n) {
        (*result)[(*returnSize)] = strdup(s);
        (*returnSize)++;
        return;
    }

    if (left < n) {
        s[index] = '(';
        backtrack(s, left + 1, right, n, index + 1, result, returnSize);
    }

    if (right < left) {
        s[index] = ')';
        backtrack(s, left, right + 1, n, index + 1, result, returnSize);
    }
}

char** generateParenthesis(int n, int* returnSize) {
    char** result = (char**)malloc(sizeof(char*) * 10000);
    *returnSize = 0;

    char* s = (char*)malloc(sizeof(char) * (2 * n + 1));
    s[2 * n] = '\0';

    backtrack(s, 0, 0, n, 0, &result, returnSize);

    free(s);

    return result;
}