package Top150;
/*
You are given an m x n integer matrix matrix with the following two properties:

Each row is sorted in non-decreasing order.
The first integer of each row is greater than the last integer of the previous row.
Given an integer target, return true if target is in matrix or false otherwise.

You must write a solution in O(log(m * n)) time complexity.

*/

public class Search2D {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int left = 0, right = m * n - 1;
        while(left <= right){
            int mid = left + (right - left)/2;
            int midVal = matrix[mid/n][mid%n];
            if(midVal == target)
                return true;
            else if(midVal < target)
                left = mid+1;
            else
                right = mid - 1;
        }
        return false;
    }

    public static void main(String[] args) {
        int [][] arr = {
                {1,3,5,7},
                {10,11,16,20},
                {23,30,34,60}
        };
        System.out.println(new Search2D().searchMatrix(arr, 3));
    }
}
