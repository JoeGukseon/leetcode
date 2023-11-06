#include <stdio.h>
#include <stdlib.h>

#define MAX_COURSES 2000

// 그래프 노드 정의
typedef struct Node {
    int course;
    struct Node* next;
} Node;

// 그래프 정의
typedef struct {
    Node* adjList[MAX_COURSES];
    int inDegree[MAX_COURSES];
} Graph;

// 큐 정의
typedef struct {
    int items[MAX_COURSES];
    int front;
    int rear;
} Queue;

// 큐 초기화
void initQueue(Queue* q) {
    q->front = -1;
    q->rear = -1;
}

// 큐가 비어있는지 확인
int isEmpty(Queue* q) {
    return q->front == -1;
}

// 큐에 원소 추가
void enqueue(Queue* q, int item) {
    if (isEmpty(q)) {
        q->front = 0;
    }
    q->rear++;
    q->items[q->rear] = item;
}

// 큐에서 원소 제거
int dequeue(Queue* q) {
    if (isEmpty(q)) {
        exit(EXIT_FAILURE);
    }

    int item = q->items[q->front];
    q->front++;
    if (q->front > q->rear) {
        initQueue(q);
    }
    return item;
}

// 그래프 초기화
void initGraph(Graph* graph, int numCourses) {
    for (int i = 0; i < numCourses; i++) {
        graph->adjList[i] = NULL;
        graph->inDegree[i] = 0;
    }
}

// 그래프에 간선 추가
void addEdge(Graph* graph, int course, int prerequisite) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    if (newNode == NULL) {
        // 메모리 할당 실패 처리
        exit(EXIT_FAILURE);
    }
    newNode->course = course;
    newNode->next = NULL;  // 초기화 추가

    // 그래프의 연결리스트에 노드 추가
    newNode->next = graph->adjList[prerequisite];
    graph->adjList[prerequisite] = newNode;

    graph->inDegree[course]++;
}

// 위상 정렬 수행
bool topologicalSort(Graph* graph, int numCourses) {
    Queue q;
    initQueue(&q);

    // 진입 차수가 0인 노드를 큐에 추가
    for (int i = 0; i < numCourses; i++) {
        if (graph->inDegree[i] == 0) {
            enqueue(&q, i);
        }
    }

    // 위상 정렬 수행
    while (!isEmpty(&q)) {
        int currentCourse = dequeue(&q);

        // 현재 과목과 연결된 노드의 진입 차수 감소
        Node* neighbor = graph->adjList[currentCourse];
        while (neighbor != NULL) {
            graph->inDegree[neighbor->course]--;
            if (graph->inDegree[neighbor->course] == 0) {
                enqueue(&q, neighbor->course);
            }
            neighbor = neighbor->next;
        }
    }

    // 모든 과목의 진입 차수가 0인지 확인
    for (int i = 0; i < numCourses; i++) {
        if (graph->inDegree[i] > 0) {
            return false; // 순환이 발견되면 false 반환
        }
    }

    return true; // 순환이 없으면 true 반환
}

bool canFinish(int numCourses, int** prerequisites, int prerequisitesSize, int* prerequisitesColSize) {
    Graph graph;
    initGraph(&graph, numCourses);

    for (int i = 0; i < prerequisitesSize; i++) {
        int currentCourse = prerequisites[i][0]; // 현재 과목
        for (int j = 1; j < prerequisitesColSize[i]; j++) {
            int prerequisiteCourse = prerequisites[i][j]; // 선수과목
            addEdge(&graph, currentCourse, prerequisiteCourse);
        }
    }

    return topologicalSort(&graph, numCourses);
}