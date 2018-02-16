package whiteboard;

import java.util.Map;

public abstract class AbstractMapSum{
	Map<String,Integer> map;
	Map<String,Integer> storageMap;
	
	public abstract void insert(String key, int value);
	
	public abstract int sum(String prefix);
	
	public int getMapValueFromKey(String key){
		return map.get(key);
	}
	
}
