package leetcode.twopointer;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        PriorityQueue<Integer> pq  = new PriorityQueue<>();
        pq.add(Integer.MAX_VALUE);
        for(int i = 0 ; i < nums.length - 2; i++){
            int l = i+1;
            int r = nums.length - 1;
            while(l < r){
                int sum = nums[i]+nums[l]+nums[r];
                int absDiff = Math.abs(target-sum);
                if(absDiff !=0 && absDiff < pq.peek()){
                    pq.add(absDiff);
                    while(l < r && absDiff == pq.peek()){
                        l++;
                    }
                    while(l < r && absDiff > pq.peek()){
                        r--;
                    }
                    l++;
                    r--;
                }
            }
        }
        return pq.peek();
    }

    public static void main(String[] args) {
        ThreeSumClosest threeSum = new ThreeSumClosest();
        int[] nums = {3, -1, 0, 1, 2, -1, -4, 5};
        int result = threeSum.threeSumClosest(nums, 8);

        // Print the result
            System.out.println(result);
    }
}
