package scaler.subsets;

public class SubarrayOR {
	
	public static int solve(int[] A) {
		long ans = 0;
		long n = A.length;
		for(int i = 0 ; i < 32 ; i++) {
			long index = n;
			for(long j = n-1 ; j >=0 ; j--) {
				long a  = ((A[(int)j]>>i))&1;
				if(a==1)index=j;
				ans+=(n-index)*(1<<i);
				ans = ans%1000000007;
			}
		}
		return (int)ans;
    }
	
	public static void main(String[] args) {
		int [] A = {4,2,1};
		System.out.println(solve(A));
	}

}
