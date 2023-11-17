class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int rows = triangle.size();

        int[][] dp = new int[rows][rows];

        //row 끝에 값 채우기
        for (int i = 0; i < rows; i++) {
            dp[rows - 1][i] = triangle.get(rows - 1).get(i);
        }

        //DP로 값 업데이트
        for (int i = rows - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = triangle.get(i).get(j) + Math.min(dp[i + 1][j], dp[i + 1][j + 1]);
            }
        }

        return dp[0][0];
    }
}