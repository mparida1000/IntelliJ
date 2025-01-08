package Top150;

import java.util.HashMap;
import java.util.Map;

public class MaxPointsOnLine {
    public int maxPoints(int[][] points) {
        if (points.length <= 2) {
            return points.length;
        }

        int maxPoints = 0;

        // Iterate through each point as a base point
        for (int i = 0; i < points.length; i++) {
            Map<String, Integer> slopeMap = new HashMap<>();
            int duplicate = 1; // Count the base point itself
            int localMax = 0;

            for (int j = i + 1; j < points.length; j++) {
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];

                if (dx == 0 && dy == 0) {
                    duplicate++; // Same point
                    continue;
                }

                // Reduce the slope to its simplest form
                int gcd = gcd(dx, dy);
                dx /= gcd;
                dy /= gcd;

                // Normalize slope to handle signs consistently
                if (dx < 0) {
                    dx = -dx;
                    dy = -dy;
                }

                String slopeKey = dy + "/" + dx;
                slopeMap.put(slopeKey, slopeMap.getOrDefault(slopeKey, 0) + 1);
                localMax = Math.max(localMax, slopeMap.get(slopeKey));
            }

            maxPoints = Math.max(maxPoints, localMax + duplicate);
        }

        return maxPoints;
    }

    // Helper function to calculate GCD
    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        MaxPointsOnLine solution = new MaxPointsOnLine();

        // Example 1
        int[][] points1 = {{1, 3}, {2, 4}, {2, 6},{4,8},{5,10}};
        System.out.println("Output: " + solution.maxPoints(points1)); // Output: 3

/*        // Example 2
        int[][] points2 = {{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}};
        System.out.println("Output: " + solution.maxPoints(points2)); // Output: 4*/
    }
}
