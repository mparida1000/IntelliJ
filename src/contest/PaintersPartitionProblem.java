package contest;

public class PaintersPartitionProblem {
    final static int m = 10000003;
    public int paint(int A, int B, int [] C){
        int min = Integer.MIN_VALUE, max = 0;
        for(int i = 0 ; i < C.length ; i++){
            min = Math.max(min, C[i]);
            max+=C[i];
        }
        int l = min;
        int h = max;
        long ans = 0;
        while(l<=h){
            int mid = l+ (h-l)/2;
            if(canAccomodatePainters(A, B, C, mid)){
                ans = (mid*(long)B)%m;
                h = mid - 1;
            }else{
                l = mid+1;
            }
        }
        return (int)ans;
    }
    public boolean canAccomodatePainters(int A, int B, int [] C, int mid){
        int count = 1;
        long sum = 0;
        for(int i = 0 ; i < C.length ; i++){
            sum+=C[i];
            if(sum > mid){
                sum = C[i];
                count++;
            }
        }
        if(count > A)return false;
        return true;
    }

    public static void main(String[] args) {
        //int C[] = {4,2,6,9,17,9,10};
        //int C[] = {762, 798, 592, 899, 329};
        int C[] = {185, 186, 938,558,655,461,441,234,902,681};
        //System.out.println(new PaintersPartitionProblem().paint(6,3,C));
        System.out.println(new PaintersPartitionProblem().paint(3,10,C));
    }
}
