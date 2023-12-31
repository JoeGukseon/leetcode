/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
 typedef struct Node {
    int data;
    struct Node* next;
} Node;

typedef struct Queue {
    Node* front;
    Node* rear;
} Queue;

void initQueue(Queue* queue) {
    queue->front = NULL;
    queue->rear = NULL;
}

void enqueue(Queue* queue, int data) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->data = data;
    newNode->next = NULL;

    if (queue->rear == NULL) {
        queue->front = newNode;
        queue->rear = newNode;
    } else {
        queue->rear->next = newNode;
        queue->rear = newNode;
    }
}

int dequeue(Queue* queue) {
    if (queue->front == NULL) {
        return -1;
    }

    Node* temp = queue->front;
    int data = temp->data;

    queue->front = temp->next;
    free(temp);

    if (queue->front == NULL) {
        queue->rear = NULL;
    }

    return data;
}

int* findOrder(int numCourses, int** prerequisites, int prerequisitesSize, int* prerequisitesColSize, int* returnSize) {
    int* indegree = (int*)malloc(numCourses * sizeof(int));
    int* resultOrder = (int*)malloc(numCourses * sizeof(int));
    int resultIndex = 0;

    for (int i = 0; i < numCourses; i++) {
        indegree[i] = 0;
    }

    for (int i = 0; i < prerequisitesSize; i++) {
        indegree[prerequisites[i][0]]++;
    }

    Queue queue;
    initQueue(&queue);

    for (int i = 0; i < numCourses; i++) {
        if (indegree[i] == 0) {
            enqueue(&queue, i);
        }
    }

    while (queue.front != NULL) {
        int currentCourse = dequeue(&queue);
        resultOrder[resultIndex++] = currentCourse;

        for (int i = 0; i < prerequisitesSize; i++) {
            if (prerequisites[i][1] == currentCourse) {
                indegree[prerequisites[i][0]]--;
                if (indegree[prerequisites[i][0]] == 0) {
                    enqueue(&queue, prerequisites[i][0]);
                }
            }
        }
    }

    if (resultIndex == numCourses) {
        *returnSize = numCourses;
        return resultOrder;
    } else {
        *returnSize = 0;
        return NULL;
    }
}