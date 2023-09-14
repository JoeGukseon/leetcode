class Solution {
    public int snakesAndLadders(int[][] board) {
        int cnt = 0; //move cnt
        int mapSize = board.length;

        boolean[] visited = new boolean[(mapSize * mapSize)+1]; //최대 크기 6 * 6 37 +1 인덱스

        Queue<Integer> queue = new LinkedList<>();

        queue.offer(1); //start 1

        while (!queue.isEmpty()) { //bfs 시작
            int qSize = queue.size();

            for (int i = 0; i < qSize; i++) {
                Integer curPos = queue.poll();
//                //골 위치면 카운트 반환
//                if (curPos == mapSize * mapSize) {
//                    cnt++;
//                    return cnt;
//                }
                //visited[curPos] = true;
                //주사위 경우의 수
                for (int j = 1; j <= 6; j++) {
                    int next = curPos + j;

                    if(next > mapSize * mapSize) break;
                    //현재 위치가 뱀,사다리 인지 확인
                    int[] position = getPosition(mapSize, next);
                    //int[] position = getPosition(mapSize, 4);
//                    int[] position = getPosition(6, 1);
                    //System.out.println("position = " + Arrays.toString(position));

                    //-1이면 다음 칸을 아니면 지정된 위치로
                    int nextPos  = board[position[0]][position[1]] == -1? next:board[position[0]][position[1]];

                    if (nextPos == mapSize * mapSize) {
                        cnt++;
                        return cnt;
                    }

                    //방문 여부 확인 후 방문 했으면 큐에 안넣음
                    if (!visited[nextPos]) {
                        visited[nextPos] = true;
                        queue.offer(nextPos);
                    }
                }
            }
            cnt++; //q 반복문 당 cnt +1

        }

        return -1;
    }

    //위치를 어떻게 잡을까? 지그재그
    // 1 -> [5][0], 7 -> [4][5], 36 -> [0][0]
    // 행 계산 : 역순이니 (length - 1) - ((n-1)/length) (행크기-1) - 값-1/행크기 인덱스때문에 -1
    // 열 계산 :
    // 1. 계산 한 행을 % 2 해서 홀수 짝수 나누기
    // 짝수면 정방향 0 ~ 5 (1) (값-1) % 행크기
    // 홀수면 역방향 5 ~ 0 (7,36) (행크기-1) - (값-1) % 행크기

    private int[] getPosition(int mapSize, int n) {
        int row = (mapSize - 1) - ((n - 1) / mapSize);
//        if (row % 2 != mapSize % 2) {
//            System.out.println("짝수");
//        }
        int col = row % 2 != mapSize % 2 ? (n - 1) % mapSize : (mapSize - 1) - ((n - 1) % mapSize);

        return new int[]{row, col};
    }
}