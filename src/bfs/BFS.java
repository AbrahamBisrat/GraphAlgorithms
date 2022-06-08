package bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import bag.Bag;
import graph.Graph;

/**
 * 
 * Implementation and traversal of Breadth first search algorithm
 * 
 * @author Er. Abraham Bisrat
 *
 */

public class BFS {
	private Queue<Integer> queue = new LinkedList<>();
	private int count=0;
	
	void traverse(Graph graph, HashSet<Integer> visited, int vertex) {
		queue.add(vertex);
		if(!visited.contains(vertex)) count++;
		while(!queue.isEmpty()) {
			int current = queue.poll();
			for(int edge : graph.adj(current)) {
				if(!visited.contains(edge)) {
					queue.add(edge);
					visited.add(edge);
					System.out.println(edge);
				}
			}
			visited.add(current);
		}
	}
	public boolean hasLoop() {
		
		return false;
	}
	public boolean hasPath(int src, int dst) {
		
		
		return false;
	}
	public int components(Graph graph) {
		init();
		HashSet<Integer> visited = new HashSet<>();
		for(int v=0; v<graph.V(); v++)
			traverse(graph, visited, v);
		return count;
	}
	void init() {
		queue.clear();
		count=0;
	}
}
