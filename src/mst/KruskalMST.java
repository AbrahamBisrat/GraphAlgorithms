package mst;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Heavily inspired by The lecture from Professor Robert Sedgewick
 * 
 * 
 * @author Er. Abraham Bisrat https://github.com/abrahammehari
 *
 */
public class KruskalMST {
	private Queue<Edge> mst = new Queue<>();	// To consider the edges in an ascending order
	
	public KruskalMST(EdgeWeightedGraph g) {
		PriorityQueue<Edge> pq = new PriorityQueue<>(g.edges()); // build the min-Heap
		
		/* For a quick look up if two of the considered edges are from connected components
		   Thus preventing the occurrence of cyclic paths */
		UF uf = new UF(g.V()); 					
		while(!pq.isEmpty() && mst.size() < g.V() - 1) {
			Edge e = pq.remove();
			int v = e.either();					// greedily add edges to MST
			int w = e.other(v);
			if(!uf.connected(v, w)) {			// edge v-> w does not exist ? Create it : move on;
				uf.union(v, w);					// merge sets
				mst.enqueue(e);					// add edge to MST
			}
		}
	}
	public Iterable<Edge> edges() { return mst; }
}
