package graph;

import java.util.Iterator;
import java.util.LinkedList;

public class Graph {
	private int V; // no. of vertices
	protected LinkedList<Integer> adj[];

	public Graph(int v) {
		V = v;
		adj = new LinkedList[V];

		for (int i = 0; i < v; i++) {
			adj[i] = new LinkedList<>();
		}
	}

	// function to add an edge into the graph
	public void addEdge(int v, int w) {
		adj[v].add(w);
	}

	public void print() {
		// print the array
		for (int i = 0; i < adj.length; i++) {
			System.out.println(i + ":" + adj[i]);
		}
	}

	/**
	 * Prints BFS traversal from a given source s
	 * 
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

		// 'i' will be used to get all adjacent vertices of a vertex
		Iterator<Integer> i;

		while (queue.size() != 0) {
			// Dequeue a vertex from queue
			src = queue.poll();

			int n;
			i = adj[src].listIterator();

			// Get all adjacent vertices of the dequeued vertex s
			// If a adjacent has not been visited, then mark it
			// visited and enqueue it
			while (i.hasNext()) {
				n = i.next();

				// If this adjacent node is the destination node,
				// then return true
				if (n == dest) {
					return true;
				}

				// Else, continue to do BFS
				if (!visited[n]) {
					visited[n] = true;
					queue.add(n);
				}

			}
		}
		// If BFS is complete without visited d
		return false;
	}

}
