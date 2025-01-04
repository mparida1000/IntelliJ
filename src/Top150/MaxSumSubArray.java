package Top150;

public class MaxSumSubArray {
    public int maxSubArray(int[] nums) {
        int currMax = Integer.MIN_VALUE;
        int sum = 0;
        for(int i = 0 ; i < nums.length ; i++){
            sum+=nums[i];
            currMax = Math.max(sum, currMax);
            if(sum <0 ) sum = 0;
        }
        return currMax;
    }

    public static void main(String[] args) {
        int [] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(new MaxSumSubArray().maxSubArray(nums));
    }
}
