class Solution(object):
    def combinationSum(self, candidates, target):
        """
        :type candidates: List[int]
        :type target: int
        :rtype: List[List[int]]
        """
        result = []

        def backtrack(current, remaining, start):
            if remaining < 0:
                return
            elif remaining == 0:
                result.append(current[:]) 
            else:
                for i in range(start, len(candidates)):
                    current.append(candidates[i])
                    backtrack(current, remaining - candidates[i], i)
                    current.pop() 

        candidates.sort()
        backtrack([], target, 0)

        return result