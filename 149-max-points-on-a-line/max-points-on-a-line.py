from fractions import Fraction

class Solution(object):
    def maxPoints(self, points):
        """
        :type points: List[List[int]]
        :rtype: int
        """
        if len(points) <= 1:
            return len(points)

        max_points = 1

        for i in range(len(points)):
            slopes = {}

            for j in range(len(points)):
                if i == j:
                    continue

                dx = points[j][0] - points[i][0]
                dy = points[j][1] - points[i][1]

                if dx == 0:
                    slope = float('inf')
                else:
                    slope = Fraction(dy, dx)

                slopes[slope] = slopes.get(slope, 0) + 1

            print(slopes)  # 추가된 부분

            current_max = max(slopes.values() or [0]) + 1
            max_points = max(max_points, current_max)

        return max_points