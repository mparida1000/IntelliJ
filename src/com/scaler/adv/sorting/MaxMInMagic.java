package com.scaler.adv.sorting;

public class MaxMInMagic {
	public static int[] B;
    public static int[] solve(int[] A) {
        B = new int[A.length];
        sort(A, 0, A.length-1);
        int [] op = new int [2];
        long maxMagic = 0;
        int i = 0, j = A.length-1;
        int m = 1000000007;
        while(i<j){
            maxMagic = maxMagic%m + Math.abs(A[j]-A[i])%m;
            i++;j--;
        }
        long minMagic = 0;
        for(int k = A.length - 1 ; k >= 0 ; k-=2){
            minMagic = minMagic%m + Math.abs(A[k] - A[k-1])%m;
        }
        op[0] = (int)maxMagic%m;
        op[1] = (int)minMagic%m;
        return op;
    }

    public static void sort(int [] A, int l, int h){
        if(l == h) return;
        int mid = (l+h)/2;
        sort(A, l, mid);
        sort(A, mid+1, h);
        merge(A, l, mid, h);
    }

    public static void merge(int [] A, int l, int mid, int h){
        int i = l, j = mid+1, k = l;
        while(i <= mid && j <= h){
            if(A[i] <= A[j]){
                B[k] = A[i];
                i++;k++;
            }else{
                B[k] = A[j];
                j++;k++;
            }
        }
        while(i <= mid){
           B[k] = A[i];
           i++;k++; 
        }
        while(j <= h){
            B[k] = A[j];
            j++;k++;
        }
        for(k = l ; k <= h ; k++){
            A[k] = B[k];
        }
    }
    
    public static void main(String[] args) {
		int [] A = {5,4,3,2,1,10,8,9,6,7};
		int [] op = solve(A);
		for(int i : op) {
			System.out.print(i + " ");
		}
	}

}
