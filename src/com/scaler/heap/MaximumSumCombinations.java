package com.scaler.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MaximumSumCombinations {
	public static int[] findMaxNElements(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        int N = A.length;
        int i = A.length - 1;
        int j = B.length - 1;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return b[0] - a[0];
            }
        });
        
        pq.offer(new int[]{A[i] + B[j], i, j});
        
        int[] result = new int[N];
        int k = 0;
        
        while(k < N && !pq.isEmpty()) {
            int[] max = pq.poll();
            result[k++] = max[0];
            
            if(max[1] > 0) {
                int[] next = {A[max[1] - 1] + B[max[2]], max[1] - 1, max[2]};
                pq.offer(next);
            }
            
            if(max[2] > 0) {
                int[] next = {A[max[1]] + B[max[2] - 1], max[1], max[2] - 1};
                pq.offer(next);
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        int[] A = {1, 4, 2, 3};
        int[] B = {2, 5, 1, 6};
        int N = 4;
        
        int[] result = findMaxNElements(A, B);
        
        System.out.println("Maximum " + N + " elements from sum combinations:");
        for(int i : result) {
            System.out.print(i + " ");
        }
    }
}
