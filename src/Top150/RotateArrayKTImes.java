package Top150;

/*
Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
*/
public class RotateArrayKTImes {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int b = k % n;
        reverse(nums, 0, n-b-1);
        reverse(nums, n-b, n-1);
        reverse(nums, 0, n-1);
    }
    public void reverse(int [] arr, int i, int j){
        while(i < j){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;j--;
        }
    }
    public static void main(String[] args) {
        //int [] nums = {-1,-100,3,99};
        int [] nums = {1,2,3,4,5,6,7};
        new RotateArrayKTImes().rotate(nums, 3);
        for(int  i : nums){
            System.out.print(i+", ");
        }
    }
}
