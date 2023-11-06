class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 그래프 생성
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList<>());
        }

        // 그래프 초기화
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int prerequisiteCourse = prerequisite[1];
            graph.get(prerequisiteCourse).add(course);
        }

        // 방문 여부를 나타내는 집합
        HashSet<Integer> visited = new HashSet<>();

        // 각 노드에 대해 DFS 수행
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(i, graph, visited, new HashSet<>())) {
                return false; // 순환이 발견되면 false 반환
            }
        }

        return true; // 순환이 없으면 true 반환
    }
    private boolean dfs(int node, HashMap<Integer, ArrayList<Integer>> graph, HashSet<Integer> visited, HashSet<Integer> recursionStack) {
        // 현재 노드를 방문 중인 경우 순환이 발견된 것이므로 false 반환
        if (recursionStack.contains(node)) {
            return false;
        }

        // 이미 방문한 노드인 경우 중복 방문을 방지하기 위해 true 반환
        if (visited.contains(node)) {
            return true;
        }

        // 현재 노드를 방문 중임을 표시
        visited.add(node);
        recursionStack.add(node);

        // 현재 노드와 연결된 노드에 대해 DFS 수행
        for (int neighbor : graph.get(node)) {
            if (!dfs(neighbor, graph, visited, recursionStack)) {
                return false;
            }
        }

        // 현재 노드의 탐색이 완료되면 recursionStack에서 제거
        recursionStack.remove(node);

        return true;
    }
}