package contest;

public class MinimumMonotonicPartitions {

    public static int minPartitions(int[] A) {
        int n = A.length;
        if (n <= 1) {
            return n;
        }

        int increasing = 1; // Number of increasing partitions
        int decreasing = 1; // Number of decreasing partitions

        for (int i = 1; i < n; i++) {
            if (A[i] >= A[i - 1]) {
                decreasing = Math.min(increasing, decreasing) + 1;
            } else if (A[i] <= A[i - 1]) {
                increasing = Math.min(increasing, decreasing) + 1;
            }
        }

        return Math.min(increasing, decreasing);
    }

    public static void main(String[] args) {
        int[] A = {5, 2, 4, 3, 1, 6};
        System.out.println("Minimum number of partitions: " + minPartitions(A));
    }
}

