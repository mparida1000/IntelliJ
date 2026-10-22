package Top150;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFinder {
    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;
    public MedianFinder() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if(maxHeap.isEmpty() || maxHeap.size() == minHeap.size()){
            maxHeap.offer(num);
        }else if(maxHeap.size() > minHeap.size()+1){
            if(num >= maxHeap.peek()){
                minHeap.offer(num);
            }else{
                minHeap.offer(maxHeap.poll());
                maxHeap.offer(num);
            }
        }else{
            maxHeap.offer(num);
        }
        if(maxHeap.size() == minHeap.size()+2){
            minHeap.offer(maxHeap.poll());
        }
    }

    public double findMedian() {
        if(maxHeap.size() == minHeap.size())
            return (double)(maxHeap.peek() + minHeap.peek())/2;
        else return maxHeap.peek();
    }

    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
        mf.addNum(11);
        System.out.println("Median ----------"+mf.findMedian());
        mf.addNum(33);
        System.out.println("Median ----------"+mf.findMedian());
        mf.addNum(2);
        System.out.println("Median ----------"+mf.findMedian());
        mf.addNum(54);
        System.out.println("Median ----------"+mf.findMedian());
        mf.addNum(56);
        System.out.println("Median ----------"+mf.findMedian());
        mf.addNum(9);
        System.out.println("Median ----------"+mf.findMedian());
        mf.addNum(0);
        System.out.println("Median ----------"+mf.findMedian());
        mf.addNum(19);
        System.out.println("Median ----------"+mf.findMedian());
    }
}
