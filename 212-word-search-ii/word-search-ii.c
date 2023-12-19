// Trie 노드 정의
typedef struct TrieNode {
    bool isEndOfWord;   // 단어의 끝인지 여부
    struct TrieNode **children;   // 다음 노드를 가리키는 포인터 배열
} TrieNode;

// Trie 구조체 정의
typedef struct Trie {
    TrieNode *root;   // 루트 노드
} Trie;

// 새로운 TrieNode를 생성하는 함수
TrieNode *createTrieNode() {
    TrieNode *node = (TrieNode *)malloc(sizeof(TrieNode));
    node->isEndOfWord = false;
    node->children = (TrieNode **)calloc(26, sizeof(TrieNode *));   // 알파벳 개수만큼의 배열 할당
    return node;
}

// Trie를 초기화하는 함수
Trie *initializeTrie() {
    Trie *trie = (Trie *)malloc(sizeof(Trie));
    trie->root = createTrieNode();   // 루트 노드 생성
    return trie;
}

// Trie에 단어를 삽입하는 함수
void insertWord(Trie *trie, char *word) {
    TrieNode *node = trie->root;
    while (*word) {
        int index = *word - 'a';
        if (!node->children[index]) {
            node->children[index] = createTrieNode();
        }
        node = node->children[index];
        word++;
    }
    node->isEndOfWord = true;
}

// Trie에 사용된 메모리를 해제하는 함수
void freeTrie(Trie *trie) {
    if (!trie || !trie->root) {
        return;
    }
    free(trie->root->children);
    free(trie->root);
    free(trie);
}

// 결과 배열에 단어를 추가하는 함수
void addToResult(char **result, int *returnSize, char *word) {
    int length = strlen(word);
    result[*returnSize] = (char *)malloc((length + 1) * sizeof(char));
    strcpy(result[*returnSize], word);
    (*returnSize)++;
}

// 보드에서 단어를 찾아 결과를 업데이트하는 함수
void lookupWords(TrieNode *node, char **board, int boardSize, int *boardColSize,
                 int row, int col, char **result, int *returnSize, char *word, int wordIndex) {
    if (board[row][col] == '!' || !node->children[board[row][col] - 'a']) {
        return;
    }

    char currentChar = board[row][col];
    TrieNode *nextNode = node->children[currentChar - 'a'];

    word[wordIndex] = currentChar;

    if (nextNode->isEndOfWord) {
        nextNode->isEndOfWord = false;
        word[wordIndex + 1] = '\0';
        addToResult(result, returnSize, word);
    }

    board[row][col] = '!';

    if (row < boardSize - 1) {
        lookupWords(nextNode, board, boardSize, boardColSize, row + 1, col, result, returnSize, word, wordIndex + 1);
    }
    if (row > 0) {
        lookupWords(nextNode, board, boardSize, boardColSize, row - 1, col, result, returnSize, word, wordIndex + 1);
    }
    if (col < *boardColSize - 1) {
        lookupWords(nextNode, board, boardSize, boardColSize, row, col + 1, result, returnSize, word, wordIndex + 1);
    }
    if (col > 0) {
        lookupWords(nextNode, board, boardSize, boardColSize, row, col - 1, result, returnSize, word, wordIndex + 1);
    }

    board[row][col] = currentChar;
}


char **findWords(char **board, int boardSize, int *boardColSize, char **words, int wordsSize, int *returnSize) {
    *returnSize = 0;

    // 예외 처리
    if (wordsSize <= 0) {
        return NULL;
    }

    // Trie 초기화
    Trie *trie = initializeTrie();

    // Trie에 단어 삽입
    for (int i = 0; i < wordsSize; i++) {
        insertWord(trie, words[i]);
    }

    // 결과 배열 메모리 할당
    char **result = (char **)malloc(wordsSize * sizeof(char *));
    for (int i = 0; i < wordsSize; i++) {
        result[i] = NULL;
    }

    // 보드에서 단어 찾아 결과 업데이트
    for (int i = 0; i < boardSize; i++) {
        for (int j = 0; j < *boardColSize; j++) {
            char word[boardSize * (*boardColSize) + 1];
            lookupWords(trie->root, board, boardSize, boardColSize, i, j, result, returnSize, word, 0);
        }
    }

    // Trie에 사용된 메모리 해제
    freeTrie(trie);

    return result;
}