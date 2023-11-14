#define MAX_LEN 300

int memo[MAX_LEN];

bool inDictionary(char *s, char **wordDict, int wordDictSize) {
    for (int i = 0; i < wordDictSize; i++) {
        int len = strlen(wordDict[i]);
        if (len <= strlen(s) && strncmp(s, wordDict[i], len) == 0) {
            return true;
        }
    }
    return false;
}

bool wordBreakHelper(char *s, char **wordDict, int wordDictSize, int start, int len) {
    if (start == len) {
        return true;
    }

    if (memo[start] != -1) {
        return memo[start];
    }

    for (int i = 0; i < wordDictSize; i++) {
        int wordLen = strlen(wordDict[i]);
        if (wordLen <= len - start && strncmp(s + start, wordDict[i], wordLen) == 0) {
            if (wordBreakHelper(s, wordDict, wordDictSize, start + wordLen, len)) {
                memo[start] = 1;
                return true;
            }
        }
    }

    memo[start] = 0;
    return false;
}

bool wordBreak(char *s, char **wordDict, int wordDictSize) {
    int len = strlen(s);
    memset(memo, -1, sizeof(memo));
    return wordBreakHelper(s, wordDict, wordDictSize, 0, len);
}