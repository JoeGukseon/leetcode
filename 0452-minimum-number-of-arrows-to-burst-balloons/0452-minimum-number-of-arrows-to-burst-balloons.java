class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }

        // 풍선을 xend를 기준으로 정렬합니다.
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

        int arrows = 1;  // 첫 번째 풍선을 터트릴 화살은 이미 있습니다.
        int end = points[0][1];

        // 다음 풍선부터 반복하면서 화살을 쏩니다.
        for (int i = 1; i < points.length; i++) {
            // 현재 풍선의 시작점이 이전 풍선의 끝보다 크다면 새로운 화살이 필요합니다.
            if (points[i][0] > end) {
                arrows++;
                end = points[i][1];
            }
        }

        return arrows;
    }
}