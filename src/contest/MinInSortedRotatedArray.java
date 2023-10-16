package contest;

public class MinInSortedRotatedArray {

    public int findMin(int [] A){
        int l = 0, h = A.length - 1;
        int min = Integer.MAX_VALUE;
        while(l <= h){
            int mid = (l+h)/2;
            if(A[mid] >= A[l]){
                min = Math.min(min, A[l]);
                l = mid + 1;
            }else if(A[mid] < A[l]){
                min = Math.min(min, A[mid]);
                h = mid - 1;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        int [] A = {0,1,2,4,5,6,7};
        System.out.println(new MinInSortedRotatedArray().findMin(A));
    }
}
