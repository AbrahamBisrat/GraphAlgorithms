package topologicalSort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import graph.GraphX;

public class TopologicalSort {
	public static void p(Object line) { System.out.println(line); }
	private static void printStack(Stack<Integer> reversePost) {
		ArrayList<Integer> resultSet = new ArrayList<>(reversePost);
		Collections.reverse(resultSet);
		p("\n" + resultSet);
	}
	private static Stack<Integer> reverseList = new Stack<>();
	private static Set<Integer> visited = new HashSet<>();
	private static void topSort(GraphX g) {
		for(int v = 0; v < g.V(); v++)
			if(!visited.contains(v))
				dfs(g, v);
		printStack(reverseList);
	}
	private static void dfs(GraphX g, int vertex) {
		visited.add(vertex);
		for(int w : g.adj(vertex))
			if(!visited.contains(w))
				dfs(g, w);
		reverseList.add(vertex);
	}
	public static void main(String[] args) {
		GraphX G = new GraphX(7);
		initTopologicalSort(G);
		p(G);
		topSort(G);
	}
	private static void initTopologicalSort(GraphX g) {
		g.addEdgeDirected(0, 1);
		g.addEdgeDirected(0, 2);
		g.addEdgeDirected(0, 5);
		g.addEdgeDirected(5, 2);
		g.addEdgeDirected(6, 0);
		g.addEdgeDirected(6, 4);
		g.addEdgeDirected(3, 6);
		g.addEdgeDirected(3, 2);
		g.addEdgeDirected(3, 5);
		g.addEdgeDirected(3, 4);
		g.addEdgeDirected(1, 4);
	}
}   
