package com.scaler.twopointer;

public class Subarraywithgivensum {
	
	public static int[] solve(int[] A, int B) {
        int n = A.length;
        if(n == 1 && A[0] == B)return new int[] {A[0]};
        if(n ==1 && A[0] != B) return new int[] {-1};
        int p1 = 0, p2 = 0;
        int sum = A[p1];
        boolean isFound = false;
        while(p1 < n && p2 < n){
            if(sum == B){
                isFound = true;
                break;
            }else if(sum > B){
                sum-=A[p1];
                p1++;
            }else if(sum < B){
                p2++;
                if(p2 < n) {
                	sum+=A[p2];	
                }else
                	break;
            }
        }
        if(!isFound) return new int[] {-1};
        int l = p2-p1+1;
        int [] op = new int[l];
        int i = 0;
        while(i < l) {
        	op[i] = A[p1];
        	i++;
        	p1++;
        }
        return op;
    }
	
	public static void main(String[] args) {
		//int [] A = {4,1,1,6,2,4,3};
		int [] A = {35,3,1,48,34,13,6,13,45,15,12,36,15,14,38};
		int [] op = solve(A, 29);
		for(Integer i : op) {
			System.out.print(i + " ");
		}
	}

}
