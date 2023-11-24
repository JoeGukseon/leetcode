/**
 * Note: The returned array must be malloced, assume caller calls free().
 */

const char *digitMapping[] = {
    "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
};

void generateCombinations(char *current, char *digits, int index, char ***result, int *resultSize) {
    if (index == strlen(digits)) {
        *result = realloc(*result, (*resultSize + 1) * sizeof(char *));
        (*result)[*resultSize] = strdup(current);
        (*resultSize)++;
        return;
    }

    int digit = digits[index] - '0';
    for (int i = 0; i < strlen(digitMapping[digit]); i++) {
        current[index] = digitMapping[digit][i];
        current[index + 1] = '\0';
        generateCombinations(current, digits, index + 1, result, resultSize);
    }
}

char** letterCombinations(char* digits, int* returnSize) {
    char **result = NULL;  // 초기화
    *returnSize = 0;

    if (digits == NULL || strlen(digits) == 0) {
        return result;
    }

    char current[5];  // 최대 조합 길이로 조정
    generateCombinations(current, digits, 0, &result, returnSize);

    return result;
}