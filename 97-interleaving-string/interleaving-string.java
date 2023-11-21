class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();

        if (m + n != s3.length()) {
            return false;
        }

        boolean[][] dp = new boolean[m + 1][n + 1];

        //s1
        for (int i = 0; i <= m; i++) {
            //s2
            for (int j = 0; j <= n; j++) {
                // 빈 문자열은 항상 s1과 s2의 교차로 만들어질 수 있음
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    // s1이 비어있을 때, s2와 s3가 일치하는지 확인
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if (j == 0) {
                    // s2가 비어있을 때, s1과 s3가 일치하는지 확인
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {
                    // s1과 s2를 번갈아가면서 s3를 만들 수 있는지 확인
                    dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
                            || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }

        return dp[m][n];
    }
}