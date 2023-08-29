class Solution {
    public int maxProfit(int[] prices) {
        int[] slopes = new int [prices.length-1];
        int i=0;
        int profit=0;
        for(i=0;i<prices.length-1;i++){
            slopes[i]= prices[i+1]-prices[i];
        }
        
        for (int slope : slopes) {
            if(slope>0){
                profit+=slope;
                }
        }
        return profit;
    }
}