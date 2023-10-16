package contest.sep_twenty_two;

import java.util.Comparator;
import java.util.PriorityQueue;

public class FrequencySorting {

    public String solve(String A){
        int [] charArr = new int[26];
        PriorityQueue<CharFreq> pq = new PriorityQueue<>(new Comparator<CharFreq>() {
            @Override
            public int compare(CharFreq o1, CharFreq o2) {
                if(o1.freq < o2.freq){
                    return 1;
                }else if(o1.freq == o2.freq){
                    if(o1.ch > o2.ch){
                        return 1;
                    }
                }
                return -1;
            }
        });
        int len = A.length();
        for(int i = 0 ; i < len ; i++){
            int index = A.charAt(i) - 97;
            charArr[index]++;
        }
        for(int i = 0 ; i < 26 ; i++){
            int a = i+97;
            char c = (char)a;
            if(charArr[i] != 0){
                pq.offer(new CharFreq(c, charArr[i]));
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()){
            CharFreq cf = pq.poll();
            for(int i = 0 ; i < cf.freq ; i++){
                sb.append(cf.ch);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "scaleracademy";
        System.out.println(new FrequencySorting().solve(s));
    }
}

class CharFreq{
     char ch;
     int freq;

    public CharFreq(char c, int i) {
        this.ch = c;
        this.freq = i;
    }
}
