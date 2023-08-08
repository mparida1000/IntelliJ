package contest;

public class ExcelColumnTitle {
    public String convertToTitle(int A){
        if(A <= 26){
            char c = (char)(64+A);
            return String.valueOf(c);
        }
        StringBuilder sb = new StringBuilder();
        int n = A;
        while(n > 0){
            if(n <= 26){
                sb.append((char)(64+n));
                break;
            }
            int r = n%26;
            if(r==0){
                sb.append("Z");
                n-=26;
            }
            else{
                sb.append((char)(64+r));
            }
            n/=26;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        int n = 943566;
        System.out.println(new ExcelColumnTitle().convertToTitle(n));
    }
}
