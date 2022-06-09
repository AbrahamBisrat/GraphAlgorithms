package graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.NoSuchElementException;

import bag.Bag;
import bfs.BFS;

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

	private static BufferedReader file;
	
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
//	@SuppressWarnings("unchecked")
	public Graph(BufferedReader file) {
		try {
			if(!file.ready()) throw new IllegalArgumentException("Argument is null");
			
			this.V = Integer.parseInt(file.readLine());
			
			adj = new Bag[V];
			for(int v = 0; v < V; v++)
				adj[v] = new Bag<Integer>();
			
			this.E = Integer.parseInt(file.readLine());
			if(E < 0) throw new IllegalArgumentException("Number of edges in a Graph must be non-negative");
			
			for(int i = 0; i < E; i++) {
				if(!file.ready()) return;
				int v = file.read();
				int w = file.read();
				addEdge(v, w);
			}
					
		} catch (NoSuchElementException e) { 
			throw new NoSuchElementException("Invalid input format in graph data");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Something went wrong");
		}
	}
	public int V() { return V; }
	public int E() { return E; }
	public Bag<Integer>[] adj() { return this.adj; }
	
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
	public Bag<Integer> adj(int v) {
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
//		System.out.println("Working Directory = " + System.getProperty("user.dir"));

//		file = new BufferedReader(new FileReader(args[0]));
		
		Graph G;
//		G = new Graph(file);
		G = manualEntry();
		
		p(G);
		BFS bfs = new BFS();
		
		util.Analyzer.benchmark(placeholder -> bfsTests(G, bfs));
		bfsTests(G, bfs);
	}

	private static void bfsTests(Graph G, BFS bfs) {
		p("Connected Components : " + bfs.components(G));
		p("hasPath ? 0 -> 2 " + bfs.hasPath(G, 0, 2));
		p("hasPath ? 2 -> 3 " + bfs.hasPath(G, 2, 3));
		p("hasPath ? 2 -> 7 " + bfs.hasPath(G, 2, 7));
		p("hasPath ? 10 -> 12 " + bfs.hasPath(G, 10, 12));
		p("\n");
		p("hasLoop ? " + bfs.hasLoop(G));
		Map<Integer, Bag<Integer>> adjList = bfs.matrixToList(G);
		adjListToString(adjList);
		p(bfs.hasLoop(G));
	}

	@SuppressWarnings("unused")
	private static void adjListToString(Map<Integer, Bag<Integer>> adjList) {
		for(int vertex : adjList.keySet()) {
			System.out.print(vertex + " : ");
			for(int index : adjList.get(vertex))
				System.out.print(index + " ");
			System.out.println();
		}
	}
	static void p(Object line) { System.out.println(line); }

	private static Graph manualEntry() {
		Graph G = new Graph(13);
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
		
		return G;
	}

}