class Solution(object):
    def longestPalindrome(self, s):
        """
        :type s: str
        :rtype: str
        """
        n = len(s)
        dp = [[False] * n for _ in range(n)]

        start = 0
        maxLength = 1

        # 모든 길이 1인 부분
        for i in range(n):
            dp[i][i] = True

        # 길이 2인 부분
        for i in range(n - 1):
            if s[i] == s[i + 1]:
                dp[i][i + 1] = True
                start = i
                maxLength = 2

        # 길이 3 이상
        for length in range(3, n + 1):
            for i in range(n - length + 1):
                j = i + length - 1  # 부분 문자열의 끝 인덱스
                if dp[i + 1][j - 1] and s[i] == s[j]: # 기존 문자열이 같고, 현재 문자열의 i=j 가 같을경우 (처음과 끝)
                    dp[i][j] = True
                    start = i
                    maxLength = length

        # 가장 긴 팰린드롬 부분 문자열 반환
        return s[start:start + maxLength]