package Top150;

import java.util.*;

public class kSmallestPairsSum {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> nums1[a[0]] + nums2[a[1]]));

        // Add first k pairs (nums1[i], nums2[0]) to the heap
        for (int i = 0; i < Math.min(k, nums1.length); i++) {
            minHeap.offer(new int[]{i, 0});  // Store indices instead of values
        }

        // Extract k smallest pairs
        while (k-- > 0 && !minHeap.isEmpty()) {
            int[] indices = minHeap.poll();
            int i = indices[0], j = indices[1];
            result.add(Arrays.asList(nums1[i], nums2[j]));  // Add the pair to result

            // Add next pair (nums1[i], nums2[j+1]) if exists
            if (j + 1 < nums2.length) {
                minHeap.offer(new int[]{i, j + 1});
            }
        }

        return result;
    }

    public static void main(String[] args) {
        kSmallestPairsSum solution = new kSmallestPairsSum();

        // Example 1
        int[] nums1 = {1, 1, 2,3};
        int[] nums2 = {4,5,7,9};
        int k = 4;
        System.out.println("Output 1: " + solution.kSmallestPairs(nums1, nums2, k));

        // Example 2
        int[] nums3 = {1, 1, 2};
        int[] nums4 = {1, 2, 3};
        int k2 = 2;
        System.out.println("Output 2: " + solution.kSmallestPairs(nums3, nums4, k2));
    }
}
