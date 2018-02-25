package graph;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class SymbolGraphTest {

	SymbolGraph sg;
	
	@Before
	public void setup() throws IOException {
		sg = new SymbolGraph("sample.csv");
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
