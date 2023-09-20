class Solution {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int idx = 0;

        for (int i = citations.length; i > 0; i--) {
            if (citations[idx] >= i) {
                return i;
            }
            idx++;
        }
        
        return 0;
    }
}