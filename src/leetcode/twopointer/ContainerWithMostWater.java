package leetcode.twopointer;
/*
    Leet #11
 */
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int maxArea = 0;
        int l = 0, r = height.length-1;
        while(l < r){
            maxArea = Math.max(maxArea, Math.min(height[l], height[r]) * (r-l));
            if(height[l] <= height[r]){
                l++;
            }else{
                r--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int [] A = {1,8,6,2,5,4,8,3,7};
        int [] B = {1,4,3,9,3,6};
        System.out.println(new ContainerWithMostWater().maxArea(A));
        System.out.println(new ContainerWithMostWater().maxArea(B));

    }
}
