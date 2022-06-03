package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bag.Bag;

/**
 * Compilations:	javac Graph.java
 * Execution: 		java Graph input.txt
 * Data Files: 		./graphData/tinyG.txt
 * 					./graphData/medium.txt
 * 					./graphData/large.txt
 * 
 * A graph, implemented using an array of sets
 * Parallel edges and self-loops allowed.
 * 
 * Implementations : Adding edges to the graph.
 * 					 iterating over all the vertices adjacent to a vertex.
 * 					 methods for returning the degree of a vertex, the number of edges.
 * 					 
 * 
 * @param <Y> the type of Object stored in the graph
 * @param <W> the type of weight
 * 
 *  
 * @author Er. Abraham Bisrat
 * 
 */

public class Graph {
	private static final String NEWLINE = System.getProperty("line.separator");
	
	private int V; // number of vertices
	
	private int E; // number of edges
	
	private Bag<Integer>[] adj; // adjacency matrix - Array of Bags
	
	/*
	 * Initializes an empty graph with required vertices and zero degrees
	 */
	public Graph(int V) {
		if(V < 0) throw new IllegalArgumentException("Number of vertices must be non negative");
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];
		for(int v = 0; v < V; v++)
			adj[v] = new Bag<Integer>();		// Initialize a list for for the vertices
	}
	
	/*
	 * Initialize Graph from file
	 * File format -> No of vertices - No of Edges - pairs of vertices
	 */
	public Graph(BufferedReader file) {
		try {
//			Pattern p = Pattern.compile("^\\s*(\\d+)\\s+(\\d+)\\s+(\\d+)$");
			Pattern p = Pattern.compile("^\\s*(\\d+)\\s+(\\d+)$");
			Matcher m;
			if(!file.ready()) throw new IllegalArgumentException("Argument is null");
			
			this.V = Integer.parseInt(file.readLine());
			
			adj = (Bag<Integer>[]) new Bag[V];
			for(int v = 0; v < V; v++)
				adj[v] = new Bag<Integer>();
			
			this.E = Integer.parseInt(file.readLine());
			if(E < 0) throw new IllegalArgumentException("Number of edges in a Graph must be non-negative");
			
			for(int i = 0; i < E; i++) {
				if(!file.ready()) return;
				m = p.matcher(file.readLine());
				int v = Integer.parseInt(m.group(1));
				int w = Integer.parseInt(m.group(2));
				addEdge(v, w);
			}
					
		} catch (NoSuchElementException e) { 
			throw new NoSuchElementException("Invalid input format in graph data");
		} catch (IOException e) {
			throw new RuntimeException("Something went wrong");
		}
	}
	public int V() { return V; }
	public int E() { return E; }
	
	// Helper method to check validity
	public void validateVertex(int v) {
		if(v < 0 || v >= V)
			throw new IllegalArgumentException("vertex : " + v + " is not between 0 and " + (V - 1));
	}
	
	// Adds the undirected edge v-w to this graph
	public void addEdge(int v, int w) {
		validateVertex(v);
		validateVertex(w);
		E++;
		adj[v].add(w);
		adj[w].add(v);
	}
	
	// returns a bag of vertices adjacent to vertex
	public Iterable<Integer> adj(int v) {
		validateVertex(v);
		return adj[v];
	}
	
	// returns the degree of vertex
	public int degree(int v) {
		validateVertex(v);
		return adj[v].size();
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " vertices, " + E + " edges " + NEWLINE);
		for(int v = 0; v < V; v++) {
			s.append(v + " : ");
			for(int w : adj[v])
				s.append(w + " ");
			s.append(NEWLINE);
		}
		return s.toString();
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader file = new BufferedReader(new FileReader(args[0]));
			
		Graph G = new Graph(file);
		System.out.println(G);
	}
}