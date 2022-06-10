package graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Simpler light weight graph
 * 
 * @author Er. Abraham Bisrat https://github.com/abrahammehari
 *
 */

public class GraphX {
	public static void p(Object line) { System.out.println(line); }
	private List<Integer> adjLists[];
	private boolean visited[];
	
	// Creation
	GraphX(int vertices) {
		adjLists = new LinkedList[vertices];
		visited = new boolean[vertices];
		
		// Initialize adjacency list
		for(int i = 0; i < adjLists.length; i++)
			adjLists[i] = new LinkedList<Integer>();
	}
	
	void addEdge(int src, int dst) { adjLists[src].add(dst); adjLists[dst].add(src); }
	void addEdgeDirected(int src, int dst) { adjLists[src].add(dst); }
	
	// Depth first search
	void dfs(int vertex) {
		Stack<Integer> stack = new Stack<>();
		stack.push(vertex);
		
		while(!stack.isEmpty()) {
			for(int edge : adjLists[vertex]) {
//				if()
			}
		}
	}

	void dfsX(int vertex) {
		visited[vertex] = true;
		p(vertex);
		
		Iterator<Integer> iter = adjLists[vertex].listIterator();
		while(iter.hasNext()) {
			int adj = iter.next();
			if(!visited[adj])
				dfsX(adj);
		}
	}
	
	public static void main(String[] args) {
		GraphX g = new GraphX(13);
		init(g);
		g.dfsX(1);
	}
	private static void init(GraphX G) {
		G.addEdge(0, 5);
		G.addEdge(4, 3);
		G.addEdge(0, 1);
		G.addEdge(9, 12);
		G.addEdge(6, 4);
		G.addEdge(5, 4);
		G.addEdge(0, 2);
		G.addEdge(11, 12);
		G.addEdge(9, 10);
		G.addEdge(0, 6);
		G.addEdge(7, 8);
		G.addEdge(9, 11);
		G.addEdge(5, 3);
	}
}
