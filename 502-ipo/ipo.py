
class Project:
    def __init__(self, profit, capital):
        self.profit = profit
        self.capital = capital

class Solution(object):
    def findMaximizedCapital(self, k, w, profits, capital):
        """
        :type k: int
        :type w: int
        :type profits: List[int]
        :type capital: List[int]
        :rtype: int
        """
        n = len(profits)
        projects = [Project(profits[i], capital[i]) for i in range(n)]

        projects.sort(key=lambda x: x.capital)

        i = 0

        maximizeCapital = []
        while k > 0:
            while i < n and projects[i].capital <= w:
                heapq.heappush(maximizeCapital, (-projects[i].profit, projects[i].capital))
                i += 1
            if not maximizeCapital:
                break
            profit, project_capital = heapq.heappop(maximizeCapital)
            w += -profit
            k -= 1

        return w