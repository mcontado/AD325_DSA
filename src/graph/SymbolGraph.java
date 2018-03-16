package graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class SymbolGraph {
	private HashMap<String, Integer> airportCodeIndexMap; // string -> index
	private String[] keys; // index -> string
	private static final String filePath = "src/graph/";
	private Graph graph; // the underlying graph
	private File fileLoading;
	private BufferedReader bufferedReader;

	/**
	 * Accepts the filename as input param, build a map of airport codes, then builds the graph.
	 * @param fileName
	 * @throws IOException
	 */
	public SymbolGraph(final String fileName) throws IOException {
		airportCodeIndexMap = new HashMap<>();
		fileLoading = new File(filePath + fileName);

		// First pass builds the index by reading strings to associate
		// distinct strings with an index
		buildMapOfAirportCodeToIndex();

		// inverted index to get string keys in an aray
		keys = new String[airportCodeIndexMap.size()];
		for (String name : airportCodeIndexMap.keySet()) {
			keys[airportCodeIndexMap.get(name)] = name;
		}

		// second pass builds the graph by connecting first vertex on each
		// line to all others
		buildGraph();
	}

	/**
	 * Builds a map of airport code to index code.
	 * 
	 */
	public void buildMapOfAirportCodeToIndex() {
		try {
			bufferedReader = new BufferedReader(new FileReader(fileLoading));
			String inputLine;
			while ((inputLine = bufferedReader.readLine()) != null) {
				String firstAirportCode = inputLine.split(",")[1];
				String secondAirportCode = inputLine.split(",")[2];

				// check the first airport code if exists in the map
				if (!airportCodeIndexMap.containsKey(firstAirportCode)) {
					airportCodeIndexMap.put(firstAirportCode, airportCodeIndexMap.size());
				}
				if (!airportCodeIndexMap.containsKey(secondAirportCode)) {
					airportCodeIndexMap.put(secondAirportCode, airportCodeIndexMap.size());
				}
			}

			bufferedReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Builds the graph.
	 */
	public void buildGraph() {
		try {
			bufferedReader = new BufferedReader(new FileReader(fileLoading));

			graph = new Graph(airportCodeIndexMap.size());
			String inputLine2;
			while ((inputLine2 = bufferedReader.readLine()) != null) {
				String[] airportCodes = inputLine2.split(",");
				int v = airportCodeIndexMap.get(airportCodes[1]);
				int w = airportCodeIndexMap.get(airportCodes[2]);

				graph.addEdge(v, w);
			}

			// graph.print();
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Calls the graph isReachable method to check if possible to reach.
	 * @param strSrc
	 * @param strDest
	 * @return
	 */
	public boolean possibleToReach(final String strSrc, final String strDest) {
		final int intSrc = airportCodeIndexMap.get(strSrc);
		final int intDest = airportCodeIndexMap.get(strDest);

		return graph.isReachable(intSrc, intDest);
	}

	/**
	 * Prints the shortest path from source to destination.
	 * @param src
	 * @param dest
	 * @return
	 */
	public String shortestPathList(final String src, final String dest) {
		final int intSrc = airportCodeIndexMap.get(src);
		final int intDest = airportCodeIndexMap.get(dest);
		String[] paths = graph.findPath(intSrc, intDest).split(" ");
		StringBuffer shortestPath = new StringBuffer();
		
		for (String stringValue : paths) {
			shortestPath.append(keys[Integer.parseInt(stringValue)]);
			shortestPath.append("=>");
			//System.out.print(keys[Integer.parseInt(stringValue)].concat("=>"));
		}
		
		// need to do substring in order to remove the arrow at the end.
		return shortestPath.substring(0, shortestPath.length()-2).toString();
	}

	/**
	 * Checks if the airport code is valid
	 * 
	 * @param airportCode
	 * @return true if valid, otherwise false
	 */
	public boolean isValidAirportCode(final String airportCode) {
		if (!airportCodeIndexMap.containsKey(airportCode)) {
			System.out.println("*** Please enter valid airport code. ");
			return false;
		} else {
			return true;
		}

	}

	public void print() {
		for (int i = 0; i < graph.adj.length; i++) {
			System.out.print(keys[i] + " => ");
			for (int intValue : graph.adj[i]) {
				System.out.print(keys[intValue].concat(" "));
			}
			System.out.println();

		}
	}

}
