package Top150;
/*
Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane, return the maximum number of points that lie on the same straight line.
*/

import java.util.HashMap;
import java.util.Map;

public class MaxPointsOnLine {
    public int maxPoints(int[][] points) {
        if(points.length < 3) return points.length;
        int maxPointsOnLine = 0;
        for(int i = 0 ; i < points.length ; i++){
            Map<String, Integer> slopeMap = new HashMap<>();
            int localMax = 0;
            for(int j = i+1 ; j < points.length ; j++){
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];
                int gcd = gcd(dx, dy);
                dx /= gcd;
                dy /= gcd;
                String slope = dy + "/" + dx;
                slopeMap.put(slope, slopeMap.getOrDefault(slope,0)+1);
                localMax = Math.max(localMax, slopeMap.get(slope));
            }
            maxPointsOnLine = Math.max(maxPointsOnLine, localMax);
        }
        return maxPointsOnLine+1;
    }

    // Helper function to calculate GCD
    private int gcd (int a, int b){
        if (b==0) return a;
        return gcd(b, a%b);
    }

    public static void main(String[] args) {
        MaxPointsOnLine solution = new MaxPointsOnLine();

        // Example 1
        int[][] points1 = {{1, 3}, {2, 4}, {2, 6},{4,8},{5,10}};
        System.out.println("Output: " + solution.maxPoints(points1)); // Output: 3

        // Example 2
        int[][] points2 = {{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}};
        System.out.println("Output: " + solution.maxPoints(points2)); // Output: 4
    }
}
