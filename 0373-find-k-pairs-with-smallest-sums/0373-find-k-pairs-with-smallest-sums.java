class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> answer = new ArrayList<>();

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
                Comparator.comparingInt(a -> (nums1[a[0]] + nums2[a[1]]))
        );

        //초기 상태 (0,0)을 힙에 추가
        minHeap.offer(new int[]{0, 0});

        //k가 1이상 이고 힙이 비워있지 않은 상태
        while (k > 0 && !minHeap.isEmpty()) {
            int[] pair = minHeap.poll();
            int first = pair[0];
            int second = pair[1];

            List<Integer> curPair = new ArrayList<>();
            curPair.add(nums1[first]);
            curPair.add(nums2[second]);

            answer.add(curPair);

            if (first < nums1.length - 1) {
                minHeap.offer(new int[]{first + 1, second});
            }
            if (first == 0 && second < nums2.length - 1) {
                minHeap.offer(new int[]{first, second + 1});
            }

            k--;

        }
        return answer;
    }
}