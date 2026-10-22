package Top150;

public class MedianOfSortedArrays {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure nums1 is the smaller array, so swap array
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int left = 0, right = m; // Binary search bounds
        int totalLeft = (m + n + 1) / 2; // Elements in the left part

        while (left <= right) {
            int partition1 = (left + right) / 2; // Partition index for nums1
            int partition2 = totalLeft - partition1; // Partition index for nums2

            // Edge cases where partition is at the boundaries
            int maxLeft1 = (partition1 == 0) ? Integer.MIN_VALUE : nums1[partition1 - 1]; //x1
            int minRight1 = (partition1 == m) ? Integer.MAX_VALUE : nums1[partition1];//x2

            int maxLeft2 = (partition2 == 0) ? Integer.MIN_VALUE : nums2[partition2 - 1]; //y1
            int minRight2 = (partition2 == n) ? Integer.MAX_VALUE : nums2[partition2];//y2

            // Check if partition is correct
            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                // Odd total length
                if ((m + n) % 2 == 1) {
                    return Math.max(maxLeft1, maxLeft2);
                }
                // Even total length
                return (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2.0;
            } else if (maxLeft1 > minRight2) {
                // Move partition1 left
                right = partition1 - 1;
            } else {
                // Move partition1 right
                left = partition1 + 1;
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted.");
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,3,4,5};
        int[] nums2 = {1,2,3,4,5,6,7,8};
        System.out.println("Median: " + findMedianSortedArrays(nums1, nums2)); // Output: 2.0

        int[] nums3 = {1, 2};
        int[] nums4 = {3, 4};
        System.out.println("Median: " + findMedianSortedArrays(nums3, nums4)); // Output: 2.5
    }
}

