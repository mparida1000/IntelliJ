package contest;

public class SearchInRowAndColWiseSortedMatrix {
    public int solve(int [][] A, int B){
        int n = A.length;
        int m = A[0].length;
        int row = 0;
        int col = 0;
        boolean isPresent = false;
        for( int i = 0 ; i < n ;i ++){
            col = callBinarySearch(i,m,A,B) ;
            if(col != -1){
                row = i;
                isPresent = true;
                break;
            }
        }
        if(!isPresent) return -1;
        return 1009*(row+1)+(col+1);

    }

    public int callBinarySearch(int i, int m, int [][] A, int B){
        int l = 0, h = m-1;
        while(l<=h){
            int mid = (l+h)/2;
            if(mid > 0 && A[i][mid] == B && A[i][mid-1] < A[i][mid]) {
                return mid;
            }else if(mid > 0 && A[i][mid-1] == A[i][mid]){
                h = mid-1;
            }else if(mid == 0 && A[i][mid] == B){
                return mid;
            }else if(A[i][mid] > B){
                h = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int [][] A = {
                {1,1,1},
                {2,2,3},
                {3,3,3}
        };
        int B = 2;
        System.out.println(new SearchInRowAndColWiseSortedMatrix().solve(A, B));

    }
}
