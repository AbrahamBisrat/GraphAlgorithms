package priorityQueues;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Playing around with heaps
 * 
 * @author Er. Abraham Bisrat https://github.com/abrahammehari
 *
 */
public class Heap {
	public static void p(Object line) { System.out.println(line); }
	public static void pl(Object line) { System.out.print(line); }
	public static void main(String[] args) {
//		gettingWet();
		int[] arr = new int[20];
		for(int i = 0; i < arr.length; i++)
			arr[i] = (int) Math.floor(Math.random() * 100) + 5;
		p(Arrays.toString(arr));
		p(kthLargest(arr, 2));
		p(kthLargestImproved(arr, 2));
		p(kthMinimum(arr, 3));
	}

	private static void gettingWet() {
		PriorityQueue<Integer> maxH = new PriorityQueue<>();
		for(int i = 0; i < 10; i++)
			maxH.add((int) Math.floor(Math.random() * 10) + 2);
		p("Min Heap : " + maxH);
		while(!maxH.isEmpty())
			p(maxH.remove());
		
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		for(int i = 0; i < 10; i++)
			maxHeap.add((int) Math.floor(Math.random() * 10) + 2);
		p("\nMax Heap : " + maxHeap);
		while(!maxHeap.isEmpty())
			p(maxHeap.remove());
	}
	/**
	 * This exercise is to improve pattern recognition in some scenarios concerning
	 * 
	 * Order statistics, meaning nth largest, nth lowest, average access of constant time
	 * 
	 * @param arr
	 * @return
	 */
	public static int kthLargest(int[] arr, int k) {
		if(arr.length == 0 || k > arr.length - 1 || k < 1) throw new IllegalArgumentException("Invalid input");
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		for(int each : arr)
			maxHeap.add(each);
		for(int i = 0; i < k - 1; i++)
			maxHeap.remove();
		return maxHeap.remove();
	}
	public static int kthLargestImproved(int[] arr, int k) {
		if(arr.length == 0 || k > arr.length - 1 || k < 1) throw new IllegalArgumentException("Invalid input");
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		for(int each : arr) {
			minHeap.add(each);
			if(minHeap.size() > k)
				minHeap.remove();
		}
		return minHeap.remove();
	}
	public static int kthMinimum(int[] arr, int k) {
		if(arr.length == 0 || k > arr.length - 1 || k < 1) throw new IllegalArgumentException("Invalid input");
		PriorityQueue<Integer> minHeap = new PriorityQueue<>(Collections.reverseOrder());
		for(int each : arr) {
			minHeap.add(each);
			if(minHeap.size() > k)
				minHeap.remove();
		}
		return minHeap.remove();
	}
	public static int medianOfTwoSortedArrays(int[] x, int[] y) {
		
		return 0;
	}
}
