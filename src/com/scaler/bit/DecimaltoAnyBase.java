package com.scaler.bit;

public class DecimaltoAnyBase {
	
	public static int DecimalToAnyBase(int A, int B) {
		if(A==0) return 0;
        StringBuffer sb = new StringBuffer();
        int num = A;
        while(num > 0){
            int rem = num % B;
            num/=B;
            sb.append(rem);
        }
        return Integer.parseInt(sb.reverse().toString());
        
    }
	
	public static void main(String[] args) {
		System.out.println(DecimalToAnyBase(0, 6));
		
	}

}
