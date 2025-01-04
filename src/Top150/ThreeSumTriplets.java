package Top150;
/*
Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
*/
import java.util.*;

public class ThreeSumTriplets {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        Set<List<Integer>> result = new HashSet<>();
        for(int i = 0 ; i < n - 2 ; i++){
            int l = i+1, r = n-1;
            while(l < r){
                int sum = nums[i]+nums[l]+nums[r];
                if(sum == 0){
                    result.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    l++;r--;
                }else if(sum < 0){
                    l++;
                }else{
                    r--;
                }
            }
        }
        return new ArrayList<>(result);
    }

    public static void main(String[] args) {
        int [] arr = {-1,0,1,2,-1,-4};
        for(List<Integer> l : new ThreeSumTriplets().threeSum(arr)){
            for(Integer i : l){
                System.out.print(i+" ");
            }
            System.out.println();
        }
    }
}
