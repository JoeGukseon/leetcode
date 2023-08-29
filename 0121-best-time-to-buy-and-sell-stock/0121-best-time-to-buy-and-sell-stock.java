class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }
        int buy = prices[0];
        int sell = Integer.MIN_VALUE;
        int profit = 0;

        for (int i = 1; i < prices.length; i++) {
            if(buy > prices[i]) sell = prices[i];
            buy = Math.min(buy, prices[i]);
            sell = Math.max(sell, prices[i]);
            profit = Math.max(profit,sell-buy);
        }

        return profit;
    }
}