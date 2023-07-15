package com.scaler.dp;
/**
 * Explanation
 * https://drive.google.com/file/d/1Bk779HbirOv9NvGl6gwvgH_4Xn-oKtTX/view?usp=share_link
 */
import java.util.Arrays;

public class EggDrop {
	static int[][] dp;
	public int eggDrop(int n, int k) {
		if(dp[n][k] != -1) return dp[n][k];
		if(n == 1)return k;
		if(k == 1 || k == 0)return k;
		int min = Integer.MAX_VALUE;
		int res;
		for(int x = 1 ; x <= k ; x++) {
			res = Math.max(eggDrop(n-1, x-1), eggDrop(n, k-x));
			if(res < min) {
				min = res;
			}
		}
		dp[n][k] = min + 1;
		return min+1;
		
	}
	

	public static void main(String[] args) {
		int n = 2, k = 15;
		dp = new int [n+1][k+1];
		for(int i = 0 ; i <=n ; i++) {
			Arrays.fill(dp[i], -1);
		}
		System.out.println(new EggDrop().eggDrop(n, k));
	}
}
