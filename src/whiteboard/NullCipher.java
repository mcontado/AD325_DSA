package whiteboard;

public class NullCipher {
	
	/**
	 * Cipher - getting all first character for each word.
	 * @param inputString
	 * @return the final string.
	 */
	public static String nullCipher1(String inputString) {
		StringBuffer finalString = new StringBuffer();
		
		String[] strArray = inputString.toLowerCase().split("\\s+");
		
		for (String s: strArray) {
			String currentString = s.replaceAll("\\W", "");
			finalString.append(currentString.charAt(0));
		}
		return finalString.toString();
	}

	/**
	 * Cipher - getting all the last character for each word.
	 * @param inputString
	 * @return the final string
	 */
	public static String nullCipher2(String inputString) {
		StringBuffer finalString = new StringBuffer();
		
		String[] strArray = inputString.toLowerCase().split("\\s+");
		
		for (String s: strArray) {
			String currentString = s.replaceAll("\\W", "");
			finalString.append(currentString.charAt(currentString.length()-1));
		}
		return finalString.toString();
	}
	
	/**
	 * Cipher - 1, 2, 3 pattern. gets the first, for the first word, 2 for the next, 3 for the next, 
	 * Reset back to 1,2,3 and so on.
	 * If the current word's length is less than the counter, skip the word and proceed to the next word
	 * but still use the current counter.
	 * @param inputString
	 * @return the final string
	 */
	public static String nullCipher3(String inputString) {
		String[] strArray3 = inputString.toLowerCase().split(" ");
		StringBuffer finalString = new StringBuffer();
		
		int counter = 1;
		
		for (String s: strArray3) {
			
			String currentString = s.replaceAll("\\W", "");
			
			if (counter <= currentString.length()) {
				finalString.append(currentString.charAt(counter-1));
				counter++;
			}
			
			if (counter > 3) {
				counter = 1;
			}
		}
		
		return finalString.toString();
	}
	
	public static void main(String[] args) {

		String str = "You would. You got less to eat. Only knowing free humans sung songs draped around free donuts. Epochs           good in knowing attacks  suck. Effort good range attacks. John is assured.";
		System.out.println(nullCipher3(str));
		
	}
}
