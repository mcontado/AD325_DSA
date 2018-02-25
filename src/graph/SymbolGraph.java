package graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class SymbolGraph {
	private HashMap<String, Integer> airportCodeIndexMap; // string -> index
	private String[] keys; // index -> string
	private static final String filePath = "src/graph/";
	private Graph graph; // the underlying graph
	private File fileLoading;
	private BufferedReader bufferedReader;

	public SymbolGraph(final String fileName) throws IOException {
		airportCodeIndexMap = new HashMap<>();
		fileLoading = new File(filePath + fileName);

		// First pass builds the index by reading strings to associate
		// distinct strings with an index
		buildMapOfAirportCodeToIndex();

		// inverted index to get string keys in an array
		invertIndex();

		// second pass builds the graph by connecting first vertex on each
		// line to all others
		buildGraph();
	}

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

	public void invertIndex() {
		// inverted index to get string keys in an array
		keys = new String[airportCodeIndexMap.size()];
		for (String name : airportCodeIndexMap.keySet()) {
			keys[airportCodeIndexMap.get(name)] = name;
		}
	}

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
			
			System.out.println(graph.toString());
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public boolean possibleToReach(final String strSrc, final String strDest) {
		final int intSrc = airportCodeIndexMap.get(strSrc);
		final int intDest = airportCodeIndexMap.get(strDest);
		
		return graph.isReachable(intSrc, intDest);
	}
	
	/**
	 * Checks if the airport code is valid
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
	
}
