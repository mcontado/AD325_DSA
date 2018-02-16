package whiteboard;

import java.util.HashMap;

public class MapSum extends AbstractMapSum {

	public MapSum() {
		map = new HashMap<>();
		storageMap = new HashMap<>();
	}
	
	@Override
	public void insert(String key, int value) {
		map.put(key, value);
		
		for (int i=0; i<key.length(); i++) {
			String newKey = key.substring(0, i+1);
			if (!storageMap.containsKey(newKey)) {
				storageMap.put(newKey, value);
			} else {
				storageMap.put(newKey, value + storageMap.get(newKey));
			}
		}
	}

	@Override
	public int sum(String prefix) {
//		int sum = 0;
//		for (String currentString: map.keySet()) {
//			if (currentString.startsWith(prefix)) {
//				sum += getMapValueFromKey(currentString);
//			}
//		}
//		
		if (storageMap.get(prefix) != null) {
			return storageMap.get(prefix);
		} else {
			return 0;
		}
		
	}
	
}
