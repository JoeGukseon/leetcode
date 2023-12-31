class Solution(object):
    def findOrder(self, numCourses, prerequisites):
        """
        :type numCourses: int
        :type prerequisites: List[List[int]]
        :rtype: List[int]
        """
        indegree = defaultdict(int)

        for pair in prerequisites:
            indegree[pair[0]] += 1

        queue = deque(course for course in range(numCourses) if indegree[course] == 0)

        result_order = []
        while queue:
            current_course = queue.popleft()
            result_order.append(current_course)

            for pair in prerequisites:
                if pair[1] == current_course:
                    indegree[pair[0]] -= 1
                    if indegree[pair[0]] == 0:
                        queue.append(pair[0])

        if len(result_order) == numCourses:
            return result_order
        else:
            return []