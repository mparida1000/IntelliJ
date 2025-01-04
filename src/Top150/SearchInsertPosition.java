package Top150;

public class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length-1;
        int mid = 0;
        while(l<=r){
            mid = l+(r-l)/2;
            if(nums[mid] == target) return mid;
            else if(nums[mid] < target){
                l = mid + 1;
            }else {
                r = mid - 1;
            }
        }
        //return mid+1;
        return nums[mid] > target ? mid : mid+1;
    }

    public static void main(String[] args) {
        int [] nums = {1,3,5,6};
        System.out.println(new SearchInsertPosition().searchInsert(nums, 5));
        System.out.println(new SearchInsertPosition().searchInsert(nums, 2));
        System.out.println(new SearchInsertPosition().searchInsert(nums, 7));
        System.out.println(new SearchInsertPosition().searchInsert(nums, 4));
    }
}
