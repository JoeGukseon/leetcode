class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        int start = 0; // 가장 긴 팰린드롬의 시작 인덱스
        int maxLength = 1; // 가장 긴 팰린드롬의 길이

        // 모든 길이 1인 부분 문자열은 팰린드롬
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        // 길이 2인 부분 문자열 판별
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                start = i;
                maxLength = 2;
            }
        }

        // 길이 3 이상의 부분 문자열 판별
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1; // 부분 문자열의 끝 인덱스
                if (dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;
                    start = i;
                    maxLength = len;
                }
            }
        }

        // 가장 긴 팰린드롬 부분 문자열 반환
        return s.substring(start, start + maxLength);
    }
}