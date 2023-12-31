class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];

        for (int[] pair : prerequisites) {
            indegree[pair[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int course = 0; course < numCourses; course++) {
            if (indegree[course] == 0) {
                queue.offer(course);
            }
        }

        List<Integer> resultOrder = new ArrayList<>();
        while (!queue.isEmpty()) {
            int currentCourse = queue.poll();
            resultOrder.add(currentCourse);

            for (int[] pair : prerequisites) {
                if (pair[1] == currentCourse) {
                    indegree[pair[0]]--;
                    if (indegree[pair[0]] == 0) {
                        queue.offer(pair[0]);
                    }
                }
            }
        }

        if (resultOrder.size() == numCourses) {
            int[] resultArray = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                resultArray[i] = resultOrder.get(i);
            }
            return resultArray;
        } else {
            return new int[]{};
        }
    }
}