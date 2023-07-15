package com.scaler.greedy;
/**
 * Problem Description
You are given a string A consisting of 1's and 0's. Now the task is to make the string consisting of only 1's. 
But you are allowed to perform only the following operation:

Take exactly B consecutive string elements and change 1 to 0 and 0 to 1.
Each operation takes 1 unit time, so you have to determine the minimum time required to only make the string of 1's. 
If not possible, return -1.

The first argument is a string A consisting of 1's and 0's.
The second argument is an integer B which represents the number of consecutive elements which can be changed.
 * @author mp1863
 *
 */

public class BinaryStrings {

	static final String ZERO = "0";
	static final String ONE = "1";
	public static int solve(String A, int B) {
        int count = 0;
        int n = A.length();
        StringBuilder sb = new StringBuilder(A);
        for(int i = 0 ; i < n ; i++){
            if(sb.charAt(i) == '0' && i+B <= n){
                for(int j = i ; j < i+B ; j++){
                	int k = j;
                    if(sb.charAt(j) == '1'){
                        sb.replace(j, k+1, ZERO);
                    }else{
                    	sb.replace(j, k+1, ONE);
                    }
                }
                count++;
            }
        }
        for(int i = 0 ; i < n ; i++){
            if(sb.charAt(i) == '0'){
                count = -1;
            }
        }
        return count;
    }
	/**static void minOperation(String S, int K, int N){
		int min = 0;
		int i;
		for (i = 0; i < N; i++) {
			if (S.charAt(i) == '0' && i + K <= N) {
				for (int j = i; j < i + K; j++) {
					if (S.charAt(j) == '1')
						S = S.substring(0, j) + '0' + S.substring(j + 1);
					else
					S = S.substring(0, j) + '1' + S.substring(j + 1);
				}
				min++;
			}
		}
		for (i = 0; i < N; i++) {
			if (S.charAt(i) == '0')
				break;
		}

		if (i == N)
		System.out.println(min);
		else
		System.out.println(-1);
	}**/

	public static void main(String []args){

		//String S = "0010000011111110101001001001101010111011011011101001111110010000000001010001101100000010010110001111100010101100011110001011101000100011111111111010000010010101010111001000010100101100001101011101101011011001000110111111010000000110110000010101100100010000111000100111100110001110111101010011001011010011011010011110111101111001001001010111110001101";//"00010110";
		//String S = "00010110";
		//String S = "1010110101101010110101010";//3
		String S = "10101010101010101010101010101010101010101010101010";//5
		int K = 5;
		int N = S.length();
		int l = solve(S, K);
		System.out.println(l);
		//System.out.println(minOperation(S, K, 10));
	}
}
