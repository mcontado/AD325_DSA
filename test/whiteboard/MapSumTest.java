package whiteboard;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MapSumTest {
	final static String[] TESTKEYS = { "alpha", "aries", "arrid" };
	final static int[] TESTVALUES = {11, 21, 31 };
	
	AbstractMapSum testMapSum;
	
	@Before
	public void setUp(){
		//build/reset MapSum collection.
		testMapSum  = new MapSum();
	}

	@Test
	public void testSum(){
		testMapSum.insert(TESTKEYS[0], TESTVALUES[0]);
		testMapSum.insert(TESTKEYS[1], TESTVALUES[1]);
		testMapSum.insert(TESTKEYS[2], TESTVALUES[2]);
		assertEquals(63, testMapSum.sum("a"));
		assertEquals(52, testMapSum.sum("ar"));
		assertEquals(31, testMapSum.sum("arr"));
		assertEquals(0, testMapSum.sum("b"));
	}
	
	@Test
	public void testInsert(){
		testMapSum.insert(TESTKEYS[0], TESTVALUES[0]);
		
		assertEquals((int)testMapSum.getMapValueFromKey(TESTKEYS[0]),(int)TESTVALUES[0]); 
	}
}
