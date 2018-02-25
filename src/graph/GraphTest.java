package graph;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class GraphTest {
	File fileLoading;
	BufferedReader bufferedReader;
	
	@Before
	public void setup() throws FileNotFoundException {
		fileLoading = new File("src/graph/Routes.csv");
		bufferedReader = new BufferedReader(new FileReader(fileLoading));

		String st;
		
		try {
			while ((st = bufferedReader.readLine()) != null) {
				System.out.println(st);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
