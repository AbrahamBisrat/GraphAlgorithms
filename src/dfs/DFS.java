package dfs;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import bag.Bag;
import graph.Graph;

/**
 * Depth first search implementation using both recursion and iterative approach using stack
 *  
 *  1. Start by putting any one of the graph's vertices on top of a stack
 *  2. take the top item of the stack and add it to the visited list
 *  3. Create a list of that vertex's adjacent nodes. Add the ones which aren't in the visited list.
 *  4. Keep repeating steps 2 and 3 until the stack is empty - Every node is visited.
 * 
 * @author Er. Abraham Bisrat https://github.com/abrahammehari
 *
 */

public class DFS {
	private static void p(Object line) { System.out.println(line); }
	private static Set<Integer> visited = new HashSet<>();
	private static Stack<Integer> stack = new Stack<>();
	
	public static void traverse(Graph graph, int vertex) {
		p(vertex);
		stack.push(vertex);
		visited.add(vertex);
		
		while(!stack.isEmpty()) {
			int current = stack.pop();
			for(int edge : graph.adj(current)) {
				if(!visited.contains(edge)) {
					stack.push(edge);
					visited.add(edge);
					traverse(graph, edge);
				} 
			}
		}
	}
	
}
