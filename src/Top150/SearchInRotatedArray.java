package Top150;
/*There is an integer array nums sorted in ascending order (with distinct values).

Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].

Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.

You must write an algorithm with O(log n) runtime complexity.*/
public class SearchInRotatedArray {
    public int search(int[] nums, int target) {
        if(nums.length == 1)return 0;
        int n = nums.length;
        int l = 0, r = n-1;
        while(l<=r){
            int mid = l+(r-l)/2;
            if(nums[mid] == target)return mid;
            if(nums[mid] <= nums[r] && target > nums[mid]) {
                l = mid+1;
            }else{
                r = mid - 1;
            }

        }
        return -1;
    }

    public static void main(String[] args) {
        int [] nums = {14,15,17,1,3,7,9,11};
        //System.out.println(new SearchInRotatedArray().search(nums, 0));
        System.out.println(new SearchInRotatedArray().search(nums, 6));
        System.out.println(new SearchInRotatedArray().search(nums, 4));
    }
}
