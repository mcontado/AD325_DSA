package graph;

import java.util.Iterator;
import java.util.LinkedList;

public class Graph {
	private int V; // no. of vertices
	protected LinkedList<Integer> adj[];
	
	// Added to detect the shortest path
	public String[] pathTo;
	
	public Graph(int v) {
		V = v;
		adj = new LinkedList[V];
		
		pathTo = new String[V];
		
		for (int i = 0; i < v; i++) {
			adj[i] = new LinkedList<>();
		}
	}

	/**
	 * To add an edge into the graph
	 * @param v
	 * @param w
	 */
	public void addEdge(int v, int w) {
		if (!adj[v].contains(w)) {
			adj[v].add(w);
		}
	}

	public void print() {
		// print the array
		for (int i = 0; i < adj.length; i++) {
			System.out.println(i + ":" + adj[i]);
		}
	}

	
	/**
	 * Checks if the source is reachable or not
	 * @param src
	 * @param dest
	 * @return true if reachable, otherwise false
	 */
	public boolean isReachable(int src, int dest) {
		// mark all vertices as not visited
		boolean visited[] = new boolean[V];

		// Create queue for BFS
		LinkedList<Integer> queue = new LinkedList<>();
		
		// mark the current node as visited and enqueue it
		visited[src] = true;
		queue.add(src);		
		
		pathTo[src] = src+" ";
		
		// 'node' will be used to get all adjacent vertices of a vertex
		Iterator<Integer> node;

		while (queue.size() != 0) {
			// Dequeue a vertex from queue
			src = queue.poll();

			int n;
			node = adj[src].listIterator();

			// Get all adjacent vertices of the dequeued vertex s
			// If a adjacent has not been visited, then mark it
			// visited and enqueue it
			while (node.hasNext()) {
				n = node.next();

				// If this adjacent node is the destination node,
				// then return true
				if (n == dest) {
					pathTo[n] = pathTo[src] + n + " ";
					return true;
				}

				// Else, continue to do BFS
				if (!visited[n]) {
					visited[n] = true;
					queue.add(n);
					// keep track of the path
					pathTo[n] = pathTo[src] + n + " ";
				} 

			} // end inner while
		
		} // end outer while queue size
		
		return false;
	}
	
	/**
	 * Fin the path from source to destination.
	 * @param intSrc
	 * @param intDest
	 * @return
	 */
	public String findPath(int intSrc, int intDest) {
		return pathTo[intDest];
	}

}
