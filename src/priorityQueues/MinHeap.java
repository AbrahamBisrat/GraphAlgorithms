package priorityQueues;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

/**
 * Playing around with heaps
 * 
 * @author Er. Abraham Bisrat https://github.com/abrahammehari
 *
 */
public class MinHeap {
	public static void p(Object line) { System.out.println(line); }
	public static void pl(Object line) { System.out.print(line); }
	public static void main(String[] args) {
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		for(int i = 0; i < 10; i++)
			minHeap.add((int) Math.floor(Math.random() * 10) + 2);
		p("Min Heap : " + minHeap);
		while(!minHeap.isEmpty())
			p(minHeap.remove());
		
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		for(int i = 0; i < 10; i++)
			maxHeap.add((int) Math.floor(Math.random() * 10) + 2);
		p("\nMax Heap : " + maxHeap);
		while(!maxHeap.isEmpty())
			p(maxHeap.remove());
		
	}
}
