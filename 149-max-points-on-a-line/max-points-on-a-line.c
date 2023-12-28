struct Point {
    int x;
    int y;
};

double calculateSlope(struct Point p1, struct Point p2) {
    if (p1.x == p2.x)
        return INFINITY;
    return (double)(p2.y - p1.y) / (p2.x - p1.x);
}

int maxPoints(int** points, int pointsSize, int* pointsColSize) {
    if (pointsSize <= 1) {
        return pointsSize;
    }

    int maxPoints = 1;

    for (int i = 0; i < pointsSize; i++) {
        int currentMax = 1;

        for (int j = i + 1; j < pointsSize; j++) {
            struct Point p1 = {points[i][0], points[i][1]};
            struct Point p2 = {points[j][0], points[j][1]};

            double slope = calculateSlope(p1, p2);

            if (slope == INFINITY && p1.x == p2.x && p1.y == p2.y) {
                currentMax++;
            } else {
                int slopeCount = 0;
                for (int k = j + 1; k < pointsSize; k++) {
                    struct Point p3 = {points[j][0], points[j][1]};
                    struct Point p4 = {points[k][0], points[k][1]};

                    if (calculateSlope(p3, p4) == slope) {
                        slopeCount++;
                    }
                }

                currentMax = slopeCount + 2 > currentMax ? slopeCount + 2 : currentMax;
            }
        }

        maxPoints = currentMax > maxPoints ? currentMax : maxPoints;
    }

    return maxPoints;
}