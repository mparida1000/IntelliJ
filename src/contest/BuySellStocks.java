package contest;

public class BuySellStocks {
    public int maxprofit(int [] A){
        if(A.length == 0 || A.length == 1) return 0;
        int min = A[0];
        int maxProfit = Integer.MIN_VALUE;
        for(int i = 1; i < A.length ; i++){
            maxProfit = Math.max(maxProfit, A[i] - min);
            min = Math.min(min, A[i]);
        }
        return maxProfit < 0 ? 0 : maxProfit;
    }

    public static void main(String[] args) {
        int [] A = {2,9,10,6,8,11,4,13,3,1};
        System.out.println(new BuySellStocks().maxprofit(A));
    }
}
