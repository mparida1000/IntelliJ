package leetcode.twopointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0 ; i < nums.length - 2; i++){
            if(i > 0 && nums[i] ==  nums[i-1]){
                continue;
            }
            int l = i+1;
            int h = nums.length - 1;
            int target = -nums[i];
            while(l < h){
                if(nums[l]+nums[h] == target){
                    result.add(Arrays.asList(nums[i], nums[l], nums[h])) ;

                    while(l < h && nums[l] == nums[l+1]){
                        l++;
                    }
                    while(l < h && nums[h] == nums[h-1]){
                        h--;
                    }
                    l++;
                    h--;
                }
                else if(nums[l]+nums[h] < target){
                    l++;
                }else{
                    h--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        int[] nums = {3, -1, 0, 1, 2, -1, -4, 5};
        List<List<Integer>> result = threeSum.threeSum(nums);

        // Print the result
        for (List<Integer> triplet : result) {
            System.out.println(triplet);
        }
    }
}
