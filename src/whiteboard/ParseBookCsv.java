package whiteboard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class ParseBookCsv {

	public static void main(String[] args) throws IOException {
		File file = new File("src/whiteboard/Book1.csv");
		BufferedReader br = new BufferedReader(new FileReader(file));
		// Map of String - word and set of integers as the line number
		// ex. hello - [1,2,3] 
		// 		- means hello appears in lines 1, 2 and 3
		// Using set in order to get rid of duplicates.
		HashMap<String, Set<Integer>> map = new HashMap<>();
		
		String inputLine;
		
		while ((inputLine = br.readLine()) != null) {
			
			String tempString = inputLine.replaceAll("[^A-Za-z0-9]", " ").toLowerCase();
			//System.out.println(tempString);
			
			String[] stringLines = tempString.split("\\s+");
			//System.out.println(Arrays.asList(stringLines));
			
			for (int i = 1; i < stringLines.length; i++) {
				// If the map does not contain the word yet, create a new set of listLines then add the line number
				// put into the the map the word as the key and the listLines as the set of line number values.
				if (!map.containsKey(stringLines[i])) {
					Set<Integer> listLines = new HashSet<>();
					listLines.add(Integer.parseInt(stringLines[0]));
					map.put(stringLines[i], listLines);
				} else {
					// This mean we have an existing key, therefore get that key, and add the line number to its set.
					map.get(stringLines[i]).add(Integer.parseInt(stringLines[0]));
				}
			}
			
		}
		
		for (Entry<String, Set<Integer>> entry : map.entrySet()) {
		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}
	}

}
