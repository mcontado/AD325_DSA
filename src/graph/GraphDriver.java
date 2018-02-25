package graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GraphDriver {
	
	public static void main(String[] args) throws FileNotFoundException {
		File fileLoading = new File("src/graph/Routes.csv");
		BufferedReader bufferedReader = new BufferedReader(new FileReader(fileLoading));

		String st;
		
		try {
			while ((st = bufferedReader.readLine()) != null) {
				System.out.println(st);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
