/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
#define MAX_ELEMENTS 40

// 문자열을 매핑하여 인덱스를 찾는 함수
int findIndex(char* strings[MAX_ELEMENTS], char *target, int numStrings) {
    // 문자열 배열(strings)을 처음부터 끝까지 반복하며 탐색
    for (int i = 0; i < numStrings; i++) {
        // 현재 문자열이 찾고자 하는 대상 문자열과 같은지 비교
        if (!strcmp(strings[i], target))
            // 같다면 해당 인덱스를 반환 (1부터 시작)
            return i + 1;
    }
    // 찾고자 하는 대상 문자열이 배열에 없으면 0 반환
    return 0;
}

// 루트 노드를 찾는 함수 (Union-Find 알고리즘)
int findRoot(int *parent, double weights[][MAX_ELEMENTS], int x) {
    // 현재 노드의 부모 노드를 가져옴
    int rootX = parent[x];
    
    // 현재 노드가 루트 노드인 경우 현재 노드의 인덱스를 반환
    if (rootX == x) {
        return x;
    }
    
    // 재귀적으로 루트 노드를 찾음
    parent[x] = findRoot(parent, weights, rootX);
    
    // 부모 노드가 루트 노드가 아닌 경우 가중치 계산
    if (parent[x] != rootX) {
        weights[x][parent[x]] = weights[x][rootX] * weights[rootX][parent[x]];
        weights[parent[x]][x] = 1 / (weights[x][parent[x]]);
    }
    
    // 최종적으로 현재 노드의 루트 노드 인덱스 반환
    return (parent[x]);
}

// 두 집합을 합치는 함수 (Union-Find 알고리즘)
void unionSets(int *parent, int *rank, double weights[][MAX_ELEMENTS], double weight, int x, int y) {
    // 각 노드의 루트 노드를 찾음
    int rootX = findRoot(parent, weights, x);
    int rootY = findRoot(parent, weights, y);

    // 가중치 초기화
    weights[x][y] = weight;
    weights[y][x] = 1 / weight;

    // 이미 같은 집합에 속해 있다면 합치지 않음
    if (rootX == rootY)
        return;

    // 두 집합을 합침과 동시에 가중치 업데이트
    weights[rootX][rootY] = weights[rootX][x] * weights[y][rootY] * weights[x][y];
    weights[rootY][rootX] = 1 / (weights[rootX][rootY]);

    // 랭크를 고려하여 부모 설정
    if (rank[rootX] > rank[rootY]) {
        parent[rootY] = rootX;
    } else if (rank[rootY] > rank[rootX]) {
        parent[rootX] = rootY;
    } else {
        parent[rootY] = rootX;
        rank[rootX] += 1;
    }
}

double* calcEquation(char ***equations, int numEquations, int* equationsColSize, double* values, int numValues, char ***queries, int numQueries, int* queriesColSize, int* returnSize) {
    // 필요한 변수 및 배열 초기화
    int numStrings = 0;
    char *strings[MAX_ELEMENTS];
    int parent[MAX_ELEMENTS];
    char *currentString;
    int index1, index2;
    double graphWeights[MAX_ELEMENTS][MAX_ELEMENTS];

    // 초기화: 각 노드의 부모, 랭크, 그래프 가중치 초기화
    int rank[MAX_ELEMENTS];
    for (int i = 0; i < MAX_ELEMENTS; i++) {
        for (int j = 0; j < MAX_ELEMENTS; j++)
            graphWeights[i][j] = 1.0;
        parent[i] = i;
        rank[i] = 1;
    }

    // 등식들을 통해 그래프를 생성하고 가중치 계산
    for (int i = 0; i < numEquations; i++) {
        // 등식에서 첫 번째 문자열 처리
        currentString = equations[i][0];
        if (!(index1 = findIndex(strings, currentString, numStrings))) {
            // 새로운 문자열이면 배열에 추가하고 인덱스 할당
            strings[numStrings] = currentString;
            index1 = numStrings;
            numStrings++;
        } else
            index1--; // 이미 등장한 문자열이면 인덱스 갱신

        // 등식에서 두 번째 문자열 처리
        currentString = equations[i][1];
        if (!(index2 = findIndex(strings, currentString, numStrings))) {
            // 새로운 문자열이면 배열에 추가하고 인덱스 할당
            strings[numStrings] = currentString;
            index2 = numStrings;
            numStrings++;
        } else
            index2--; // 이미 등장한 문자열이면 인덱스 갱신

        // 등식을 통해 두 노드를 연결하고 가중치 계산
        unionSets(parent, rank, graphWeights, values[i], index1, index2);
    }

    // 결과를 저장할 배열 동적 할당
    double *results = (double *) malloc(sizeof(double) * numQueries);

    // 쿼리에 대한 결과 계산
    for (int i = 0; i < numQueries; i++) {
        // 쿼리에서 첫 번째 문자열 처리
        currentString = queries[i][0];
        if (!(index1 = findIndex(strings, currentString, numStrings))) {
            // 쿼리에서 등장한 문자열이 배열에 없다면 결과를 -1로 설정하고 다음 쿼리로 진행
            results[i] = -1;
            continue;
        } else
            index1--; // 이미 등장한 문자열이라면 인덱스 갱신

        // 쿼리에서 두 번째 문자열 처리
        currentString = queries[i][1];
        if (!(index2 = findIndex(strings, currentString, numStrings))) {
            // 쿼리에서 등장한 문자열이 배열에 없다면 결과를 -1로 설정하고 다음 쿼리로 진행
            results[i] = -1;
            continue;
        } else
            index2--; // 이미 등장한 문자열이라면 인덱스 갱신

        // 두 노드에 대한 결과 계산
        if (index1 == index2) {
            // 두 노드가 동일한 경우 결과를 1로 설정
            results[i] = 1;
        } else if (graphWeights[index1][index2] != 1.0) {
            // 두 노드 간의 가중치가 1이 아닌 경우 해당 가중치를 결과로 설정
            results[i] = graphWeights[index1][index2];
        } else {
            // 두 노드가 서로 다른 집합에 속해 있을 경우
            int rootX = findRoot(parent, graphWeights, index1);
            int rootY = findRoot(parent, graphWeights, index2);
            if (rootX == rootY) {
                // 두 노드가 동일한 부모를 가지고 있으면 해당 가중치 계산
                results[i] = graphWeights[index1][rootX] * graphWeights[rootX][index2];
            } else
                // 두 노드가 서로 다른 부모를 가지고 있어 계산할 수 없는 경우
                results[i] = -1.0;
        }
    }
    *returnSize = numQueries;
    return results;
}