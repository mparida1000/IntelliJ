package com.scaler.bit;

public class Numberof1Bits {
	
	public static int numSetBits(int A) {
        int n = A;
        int cnt = 0;
        while(n > 0){
        	int res = n&1;
            if(res == 1) cnt++;
            n =  n>>1;
        }
        return cnt;
    }
	
	public static void main(String[] args) {
		System.out.println(numSetBits(929217377));
	}
}
