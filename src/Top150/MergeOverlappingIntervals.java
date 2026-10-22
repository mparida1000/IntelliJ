package Top150;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeOverlappingIntervals {
    public int[][] merge(int[][] intervals) {
        List<int[]> result = new ArrayList<>();
        if(intervals == null)return new int[0][];
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));
        int s = intervals[0][0];
        int e = intervals[0][1];
        for(int i = 1; i < intervals.length ; i++){
            if(intervals[i][0] <= e){
                e = Math.max(intervals[i][1], e);
            }else{
                result.add(new int[]{s, e});
                s = intervals[i][0];
                e = intervals[i][1];
            }
        }
        result.add(new int[]{s, e});
        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        int [][] intervals = {
                {1,3},
                {2,6},
                {8,10},
                {15,18}
        };
        int[][] result = new MergeOverlappingIntervals().merge(intervals);
        for(int [] interval : result){
            System.out.println(interval[0] + ", "+interval[1]);
        }

    }
}
