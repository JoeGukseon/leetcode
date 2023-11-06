class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 그래프 생성
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[numCourses]; //진입차수로 선수과목의 수

        // 그래프 초기화
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // 각 과목의 선수과목과 in-degree 설정
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int preCourse = prerequisite[1];
            graph.get(preCourse).add(course);
            inDegree[course]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        // in-degree가 0인 과목을 큐에 추가
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // 위상 정렬 수행
        while (!queue.isEmpty()) {
            int currentCourse = queue.poll();
            // 현재 과목의 선수과목들의 in-degree 감소
            for (int nextCourse : graph.get(currentCourse)) {
                inDegree[nextCourse]--;
                // in-degree가 0이 되면 큐에 추가
                if (inDegree[nextCourse] == 0) {
                    queue.add(nextCourse);
                }
            }
        }

        // 모든 과목의 in-degree가 0인지 확인
        for (int degree : inDegree) {
            if (degree > 0) {
                return false;
            }
        }

        return true; // 모든 과목을 수강할 수 있는 경우
    }
}