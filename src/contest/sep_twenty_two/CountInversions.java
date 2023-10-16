package contest.sep_twenty_two;

public class CountInversions {

    private static final int MOD = 1000000007;

    public static int countInversions(int[] arr) {
        int[] temp = new int[arr.length];
        return mergeSortAndCount(arr, temp, 0, arr.length - 1);
    }

    private static int mergeSortAndCount(int[] arr, int[] temp, int left, int right) {
        if (left >= right) {
            return 0;
        }

        int mid = left + (right - left) / 2;
        int count = 0;

        count += mergeSortAndCount(arr, temp, left, mid); // Count inversions in the left subarray
        count += mergeSortAndCount(arr, temp, mid + 1, right); // Count inversions in the right subarray
        count += merge(arr, temp, left, mid, right); // Merge and count inversions

        return count % MOD;
    }

    private static int merge(int[] arr, int[] temp, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;
        int count = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                count = (count + (mid - i + 1)) % MOD; // Count inversions
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        while (j <= right) {
            temp[k++] = arr[j++];
        }

        for (i = left; i <= right; i++) {
            arr[i] = temp[i];
        }

        return count;
    }

    public static void main(String[] args) {
        //int[] arr = {45, 10, 15, 25, 50};
        int[] arr = {6,12,10,17,10,22,9,19,21,31,26,8};
        int totalInversions = countInversions(arr);
        System.out.println("Total inversions: " + totalInversions);
    }
}
