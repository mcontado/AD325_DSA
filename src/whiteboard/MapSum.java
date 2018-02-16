package whiteboard;

import java.util.HashMap;

public class MapSum extends AbstractMapSum {

	public MapSum() {
		map = new HashMap<>();
	}
	@Override
	public void insert(String key, int value) {
		map.put(key, value);
		
	}

	@Override
	public int sum(String prefix) {
		int sum = 0;
		for (String currentString: map.keySet()) {
			if (currentString.startsWith(prefix)) {
				sum += getMapValueFromKey(currentString);
			}
		}
		
		return sum;
	}
	
}
