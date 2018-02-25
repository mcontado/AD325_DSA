package graph;

import java.io.IOException;
import java.util.Scanner;

public class MainDriver {
	
	public static void main(String[] args) throws IOException  {
		SymbolGraph sg = new SymbolGraph("sample.csv");
		sg.print();
		Scanner in = new Scanner(System.in);
		
		do {
			String src = enterOriginAirportCode(sg);

			String dest = enterDestinationAirportCode(sg);
			
			if (sg.possibleToReach(src, dest)) {
				System.out.println("There is a path from " + src +" to " + dest);
			} else {
				System.out.println("There is no path from " + src +" to " + dest);
			}
			
			//Ask if the user wants to input again
			System.out.println("Input again? (Y/N): ");
			
		} while (in.next().equalsIgnoreCase("Y"));
		
	}
	
	public static String enterOriginAirportCode(SymbolGraph sg) {
		String src;
		do {
			System.out.println("Please enter origin airport code: ");
			Scanner in = new Scanner(System.in);
			src = in.nextLine().toUpperCase();
		} while (!sg.isValidAirportCode(src));
		
		return src;
	}
	
	public static String enterDestinationAirportCode(SymbolGraph sg) {
		String dest;
		do {
			System.out.println("Please enter destination airport code: ");
			Scanner in = new Scanner(System.in);
			dest = in.nextLine().toUpperCase();
		} while (!sg.isValidAirportCode(dest));
		
		return dest;
	}
}
