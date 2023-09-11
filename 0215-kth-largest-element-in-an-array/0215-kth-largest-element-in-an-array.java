class Solution {
    public int findKthLargest(int[] nums, int k) {
        Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int num : nums) {
            maxHeap.offer(num);
        }
        int answer = 0;
        for (int i = 0; i < k; i++) {
            answer = maxHeap.poll();
        }
        return answer;
    }
}