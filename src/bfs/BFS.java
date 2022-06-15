package bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

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
	static void p(Object line) { System.out.println(line); }
	HashSet<Integer> visited = new HashSet<>();
	private Queue<Integer> queue = new LinkedList<>();
	private int count=0; // TODO: move it out
	
	void traverse(Graph graph, int vertex) {
		queue.add(vertex);
		if(!visited.contains(vertex)) count++;
		while(!queue.isEmpty()) {
			int current = queue.poll();
			for(int edge : graph.adj(current)) {
				if(!visited.contains(edge)) {
					queue.add(edge);
					visited.add(edge);
					p(edge);
				}
			}
			visited.add(current);
		}
	}
	public boolean hasLoop(Graph graph) {
		init();
		boolean hasLoop = false;
		/*
		 * Keeps track of who discovered who and (-1) for root node
		 */
		Map<Integer, Integer> parents = new HashMap<>();
		// mark the root element with -1
		parents.put(matrixToList(graph).keySet().stream().toList().get(0), -1);
		
		for(int vertex : matrixToList(graph).keySet()) {
			queue.add(vertex);
			while(!queue.isEmpty()) {
				int current = queue.poll();
				for(int edge : graph.adj(current)) {
					if(!visited.contains(edge)) {
						queue.add(edge);
						visited.add(edge);
						parents.put(edge, current);
					} else if(parents.containsKey(edge)) {
						/*
						 * The node has been discovered by other parent before
						 * and is not the root element
						 */
						if(parents.get(edge) != current && parents.get(edge) != -1)
							return true;
					}
				}
				visited.add(current);
			}
		}
		p(parents); // print
		return hasLoop;
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
					p(edge);
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
	// decision made based on lookup and access time both O(1)
	public Map<Integer, Bag<Integer>> matrixToList(Graph graph) {
		Map<Integer, Bag<Integer>> adjList = new HashMap<>();
		for(int v=0; v<graph.V(); v++)
			adjList.put(v, graph.adj(v));
		return adjList;
	}
	/**
	 * Implementation of bfs with depth of traversal limitation
	 * 
	 * @param g
	 * @param src
	 * @param d
	 * @param desired -> do you want just the values on that depth or the whole 
	 * 					 thing upto that part? - true = all;
	 * @return List<Integer> items contained within the treaversal depth reach of d
	 */
	public List<Integer> bfs(final Graph g, final int src, int depth, boolean desired) {
		if(g==null || depth < 1) throw new IllegalArgumentException("Invalid input");
		Queue<Integer> queue = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();
		List<Integer> resultSet = new ArrayList<>();
		int level = 0;
		queue.add(src);
		int current;
		int levelSize = 0;
		while(!queue.isEmpty() && level < depth) {
			levelSize = queue.size();
			while(levelSize-- != 0) {
				current = queue.poll();
				visited.add(current);
				for(int vertice : g.adj(current)) {
					if(!visited.contains(vertice)) {
						visited.add(vertice);
						queue.add(vertice);
						resultSet.add(vertice);
					}
				}
			}
			level++;
		}
		return desired ? resultSet : new ArrayList<>(queue);
	}
}

// refactor by using Consumer functional interface
