class Solution {
    public int maxPoints(int[][] points) {
        if (points.length <= 1) {
            return points.length;
        }

        int maxPoints = 1;

        for (int i = 0; i < points.length; i++) {
            HashMap<Double, Integer> slopes = new HashMap<>();

            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }

                double slope;
                if (points[i][0] == points[j][0]) {
                    slope = Double.POSITIVE_INFINITY;  // 수직인 경우 무한대로 표현
                } else {
                    slope = (double) (points[j][1] - points[i][1]) / (points[j][0] - points[i][0]);
                }

                slopes.put(slope, slopes.getOrDefault(slope, 0) + 1);
            }

            maxPoints = Math.max(maxPoints, slopes.values().stream().max(Integer::compareTo).orElse(0) + 1);
        }

        return maxPoints;
    }
}