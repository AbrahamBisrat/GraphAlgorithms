package graph;

import java.util.LinkedList;
import java.util.List;

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
	
	// add to both - undirected
	void addEdge(int x, int y) { adjLists[x].add(y); adjLists[y].add(x); }
	
	
	public static void main(String[] args) {
		GraphX g = new GraphX(13);
		init(g);
		for(List<Integer> eachList : g.adjLists) {
			eachList.forEach(System.out::print);
			p("");
		}
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
