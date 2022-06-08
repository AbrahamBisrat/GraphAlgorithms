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
	HashSet<Integer> visited = new HashSet<>();
	private Queue<Integer> queue = new LinkedList<>();
	private int count=0;
	
	void traverse(Graph graph, int vertex) {
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
		init();
		
		return false;
	}
	public boolean hasPath(Graph graph, int src, int dst) {
		init();
		queue.add(src);
		while(!queue.isEmpty()) {
			int current = queue.poll();
			if(current==dst) return true;
			for(int edge : graph.adj(current)) {
				if(!visited.contains(edge)) {
					queue.add(edge);
					visited.add(edge);
					System.out.println(edge);
				}
			}
			visited.add(current);
		}
		return false;
	}
	public int components(Graph graph) {
		init();
		for(int v=0; v<graph.V(); v++)
			traverse(graph, v);
		return count;
	}
	void init() {
		visited.clear();
		queue.clear();
		count=0;
	}
}
