package com.scaler.string;

import java.util.ArrayList;

public class Toupper {
	
	public char[] to_upper(char[] A) {
        for(int i = 0 ; i < A.length ; i++){
            if(A[i] >= 97 || A[i] <= 122){
                A[i] = (char) (A[i] - 32);
            }
        }
        return A;
    }

}
