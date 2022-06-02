package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @param <Y> the type of Object stored in the graph
 * @param <W> the type of weight
 * 
 *  
 * @author Er. Abraham Bisrat
 */

public class Graph<Y, W> {
	
	/*
	 * Node representation of each element
	 */
	static class Node<Y, W> {
		Y y;			// adjacency info, DATA!
		W weight;		// edge weight
		Node<Y, W> next;		// Java doesn't have pointer
	}
	
	/*
	 * Pointers to edge lists
	 * In an appeal to prevent memory gobbling, this should have been a pointer
	 * but again, Java!
	 * 
	 * Represented as a linked list due to the fact that the graph will most likely be sparse
	 * otherwise, it would be a grid - Not memory effective for sparse-graphs;
	 */
	private LinkedList<Node<Y, W>> edges = new LinkedList<>();
	
	private int nEdges; 								// number of edges in the graph

	/*
	 * Optional, additions :: The graph would work without it, strictly speaking
	 */
	private List<Integer> degree = new ArrayList<>();	// Out degree of each vertex
	
	private int nVertices; 								// Number of vertices in the graph
	
	/*
	 * directed T, undirected F
	 * 
	 * The point (x, y) in the adjacency list represented in both  x(y) and y(x)
	 * If the directed flag is set to true only one will be the case.
	 * In the event which it is set to false, it will be both x(y) and y(x)
	 * z
	 */
	private boolean directed;
	
	/**
	 * Initialize the graph with direct or not flag
	 * 
	 * @param directed
	 */
	private void initializeGraph(boolean directed) {
		this.directed = directed;
	}
	
	
	
}
