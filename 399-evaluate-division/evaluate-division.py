class Solution(object):
    def calcEquation(self, equations, values, queries):
        """
        :type equations: List[List[str]]
        :type values: List[float]
        :type queries: List[List[str]]
        :rtype: List[float]
        """
        graph = {}

        for (num, den), val in zip(equations, values):
            graph.setdefault(num, {})[den] = val
            graph.setdefault(den, {})[num] = 1 / val

        results = [self.dfs(start, end, set(), graph) for start, end in queries]

        return results

    def dfs(self, start, end, visited, graph):
        if start not in graph or end not in graph:
            return -1.0

        if start == end:
            return 1.0

        visited.add(start)

        for neighbor, weight in graph[start].items():
            if neighbor not in visited:
                result = self.dfs(neighbor, end, visited, graph)
                if result != -1.0:
                    return weight * result

        visited.remove(start)

        return -1.0