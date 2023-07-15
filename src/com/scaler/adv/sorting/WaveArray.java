package com.scaler.adv.sorting;

public class WaveArray {
	private static int [] B;
    public int[] wave(int[] A) {
        B = new int [A.length];
        sort(A, 0, A.length-1);
        for(int i = 0 ; i < A.length-1 ; i+=2){
            int temp = A[i];
            A[i] = A[i+1];
            A[i+1] = temp;
        }
        return A;
    }
    public void sort(int [] A, int l, int h){
        if(l == h) return;
        int mid = (l+h)/2;
        sort(A, l, mid);
        sort(A, mid+1, h);
        merge(A, l, mid, h);
    }

    public void merge(int [] A, int l, int mid, int h){
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
        for(k = l ; k <=h ; k++){
            A[k] = B[k];
        }
    }


}
