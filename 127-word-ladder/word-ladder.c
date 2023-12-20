typedef unsigned long DequeValueType;

struct DequeNode {
    struct DequeNode *next;
    struct DequeNode *prev;
    DequeValueType val;
};

typedef struct Deque {
    struct DequeNode *head;
    struct DequeNode *tail;
} DequeType;

typedef struct BFSNode {
    int index;
    int distance;
} BFSNodeType;

DequeType *deque_alloc();
void deque_free(DequeType *deque);
bool deque_is_empty(DequeType *deque);
void deque_push_front(DequeType *deque, DequeValueType value);
void deque_push_back(DequeType *deque, DequeValueType value);
DequeValueType deque_pop_front(DequeType *deque);
DequeValueType deque_pop_back(DequeType *deque);
DequeValueType deque_peek_front(DequeType *deque);
DequeValueType deque_peek_back(DequeType *deque);

int calculateHammingDistance(char *a, char *b);
int **buildAdjacencyMatrix(char **words, int size);
BFSNodeType *newBFSNode(int index, int distance);

typedef struct node_t {
    int n;
    int dist;
} node_t;

DequeType *deque_alloc() {
    DequeType *deque = malloc(sizeof(DequeType));
    if (deque != NULL)
        deque->head = deque->tail = NULL;
    return deque;
}

void deque_free(DequeType *deque) {
    free(deque);
}

bool deque_is_empty(DequeType *deque) {
    return deque->head == NULL;
}

void deque_push_front(DequeType *deque, DequeValueType value) {
    struct DequeNode *node = malloc(sizeof(struct DequeNode));
    assert(node != NULL);
    node->val = value;
    node->next = deque->head;
    node->prev = NULL;
    if (deque->tail == NULL) {
        deque->head = deque->tail = node;
    } else {
        deque->head->prev = node;
        deque->head = node;
    }
}

void deque_push_back(DequeType *deque, DequeValueType value) {
    struct DequeNode *node = malloc(sizeof(struct DequeNode));
    assert(node != NULL);
    node->val = value;
    node->prev = deque->tail;
    node->next = NULL;
    if (deque->head == NULL) {
        deque->head = deque->tail = node;
    } else {
        deque->tail->next = node;
        deque->tail = node;
    }
}

DequeValueType deque_pop_front(DequeType *deque) {
    DequeValueType value = deque->head->val;
    struct DequeNode *node = deque->head;
    if (deque->head == deque->tail)
        deque->head = deque->tail = NULL;
    else
        deque->head = node->next;
    free(node);
    return value;
}

DequeValueType deque_pop_back(DequeType *deque) {
    DequeValueType value = deque->tail->val;
    struct DequeNode *node = deque->tail;
    if (deque->head == deque->tail)
        deque->head = deque->tail = NULL;
    else
        deque->tail = node->prev;
    free(node);
    return value;
}

DequeValueType deque_peek_front(DequeType *deque) {
    return deque->head->val;
}

DequeValueType deque_peek_back(DequeType *deque) {
    return deque->tail->val;
}

int calculateHammingDistance(char *a, char *b) {
    int distance = 0;
    size_t length = strlen(a);
    assert(length == strlen(b));

    for (size_t i = 0; i < length; i++) {
        if (a[i] != b[i])
            distance++;
    }

    return distance;
}

int **buildAdjacencyMatrix(char **words, int size) {
    int **matrix = malloc(sizeof(int *) * size);
    for (int r = 0; r < size; r++)
        matrix[r] = calloc(size, sizeof(int));

    for (int i = 0; i < size; i++) {
        for (int j = i + 1; j < size; j++) {
            if (calculateHammingDistance(words[i], words[j]) == 1) {
                matrix[i][j] = 1;
                matrix[j][i] = 1;
            }
        }
    }

    return matrix;
}

BFSNodeType *newBFSNode(int index, int distance) {
    BFSNodeType *node = malloc(sizeof(BFSNodeType));
    node->index = index;
    node->distance = distance;
    return node;
}

int ladderLength(char *startWord, char *endWord, char **wordList, int wordListSize) {
    int size = wordListSize + 1;
    int startIdx = size - 1, endIdx;
    int *visited = calloc(size, sizeof(int));

    wordList = realloc(wordList, sizeof(char *) * size);
    assert(wordList);
    wordList[startIdx] = startWord;

    for (int i = 0; i < size; i++) {
        if (strcmp(endWord, wordList[i]) == 0)
            endIdx = i;
    }

    int **graph = buildAdjacencyMatrix(wordList, size);

    DequeType *deque = deque_alloc();
    deque_push_back(deque, newBFSNode(startIdx, 1));

    while (!deque_is_empty(deque)) {
        BFSNodeType *currentNode = deque_pop_front(deque);
        int neighbor;
        for (neighbor = 0; neighbor < size; neighbor++) {
            if (graph[currentNode->index][neighbor] == 1) {
                if (visited[neighbor])
                    continue;
                visited[neighbor] = 1;
                if (neighbor == endIdx) {
                    return currentNode->distance + 1;
                }
                deque_push_back(deque, newBFSNode(neighbor, currentNode->distance + 1));
            }
        }
        free(currentNode);
    }

    free(visited);
    return 0;
}