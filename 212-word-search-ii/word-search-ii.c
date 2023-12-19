typedef struct words {
    bool end_of_word;
    struct words **next;
} words_t;

#define NO_OF_ALPH 26
#define ALPH_POS(x) (x - 'a')
#define LOOKUP_WORD(x, y) lookup_word(next, board, boardSize, boardColSize, x, y,\
                        result, returnSize, word, word_index + 1)

words_t *init_words_trie(void) {
    words_t *root = calloc(1, sizeof *root);
    root -> end_of_word = false;
    return root;
}

void insert_word(words_t *root, char *word) {
    if (*word == '\0') { // reaching the end of word, return
        root -> end_of_word = true;
        return;
    }
    if (!root -> next) { // malloc if root -> next doesn't exist
        root -> next = calloc(NO_OF_ALPH, sizeof *(root -> next));
    }
    if (!root -> next[ALPH_POS(*word)]) { // malloc if required child node doesn't exist
        root -> next[ALPH_POS(*word)] = calloc(1, sizeof(words_t));
        root -> next[ALPH_POS(*word)] -> end_of_word = false;
    }
    insert_word(root -> next[ALPH_POS(*word)], word + 1);
}

void free_words_trie(words_t *root) {
    if (!root) {
        return;
    }
    if (root -> next) { // iterate through possible child nodes and free them
        for (int i = 0; i < NO_OF_ALPH; i++) {
            free_words_trie(root -> next[i]);
        }
        free(root -> next); // free the overall root -> next
    }
    free(root); // free the node itself
}

// add the word to the result array
void add_to_result(char **result, int *returnSize, char *word) {
    int i;
    for (i = 0; word[i] != '\0'; i++);
    memcpy(result[(*returnSize)++], word, i + 1);
}

void lookup_word(words_t *trie, char **board, int boardSize, int *boardColSize, int row, int col, 
                 char **result, int *returnSize, char *word, int word_index) {
    // if you got to the end of trie or reached a '!', i.e. used board position, stop now
    if (board[row][col] == '!' 
        || !trie -> next
        || !trie -> next[ALPH_POS(board[row][col])]) {
        return;
    }
    char current_char = board[row][col];
    words_t *next = trie -> next[ALPH_POS(current_char)];
    word[word_index] = current_char;
    /* adding the word to result based on "next" instead of "trie"
     * i.e. NOT if (trie -> end_of_word) {...}
     * to prevent extra executions in the following recursive calls
     */
    if (next -> end_of_word) { // if next node is end_of_word, add it in result
        next -> end_of_word = false;
        word[word_index + 1] = '\0';
        add_to_result(result, returnSize, word);
    }
    // prevent DFS to use added chars in the table, substitute with '!'
    board[row][col] = '!';
    // if the coordinate doesn't go out of bound of the board, continue
    if (row < boardSize - 1) LOOKUP_WORD(row + 1, col); 
    if (row > 0) LOOKUP_WORD(row - 1, col);
    if (col < *boardColSize - 1) LOOKUP_WORD(row, col + 1);
    if (col > 0) LOOKUP_WORD(row, col - 1);
    board[row][col] = current_char;
}

/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
char ** findWords(char** board, int boardSize, int* boardColSize, char ** words, int wordsSize, int* returnSize){
    *returnSize = 0;
    // edge case
    if (wordsSize <= 0) {
        return NULL;
    }
    // build up the words_trie
    words_t *words_trie = init_words_trie();
    for (int i = 0; i < wordsSize; i++) {
        insert_word(words_trie, words[i]);
    }
    // malloc the result
    // for the sake of easier management, magic number for calloc size
    char **result = calloc(111, sizeof *result);
    for (int i = 0; i < 111; i++) {
        // to accommodate the max possible length, including '\0' to terminate the string
        result[i] = calloc(boardSize * (*boardColSize) + 1, sizeof(char));
    }
    // lookup the words in the board and add them in if there is any
    for (int i = 0; i < boardSize; i++) {
        for (int j = 0; j < *boardColSize; j++) {
            /* temp word variable so that each possible result is recorded and added
             * if directly writing in result, then sth like a--b--c
             *                                                 |--d
             * will record only "abc" and subsequently "\0\0d", not "abd"
             */
            char word[boardSize * (*boardColSize) + 1];
            lookup_word(words_trie, board, boardSize, boardColSize, i, j, 
                        result, returnSize, word, 0);
        }
    }
    free_words_trie(words_trie);
    return result;
}