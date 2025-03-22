

import java.util.Scanner;

public class Conversione {

	public static int conversioneInt(String input) {
		Scanner tastiera = new Scanner(System.in);
        int toInt = -1;
        boolean ok;
        do {
            ok = true;
            try {
                toInt = (int) Integer.parseInt(input);
                if (toInt <= 0) {
                    System.out.println("Il numero deve essere maggiore di 0");
                    ok = false;
                    System.out.print("Reinserisci: ");
                    input = tastiera.nextLine();
                }
            } catch (NumberFormatException e) {
                System.out.println("Formato non valido");
                System.out.print("Reinserisci: ");
                ok = false;
                input = tastiera.nextLine();
            }
        } while (!ok);

        return toInt;
    }
	
	 public static double conversioneDouble(String input) {
		 Scanner tastiera = new Scanner(System.in);
	        double toDouble = -1;
	        boolean ok;
	        do {
	            ok = true;
	            try {
	                toDouble = (double) Double.parseDouble(input);
	                if (toDouble <= 0) {
	                    System.out.println("Il numero deve essere maggiore di 0");
	                    ok = false;
	                    System.out.print("Reinserisci: ");
	                    input = tastiera.nextLine();
	                }
	            } catch (NumberFormatException e) {
	                System.out.println("Formato non valido");
	                System.out.print("Reinserisci: ");
	                ok = false;
	                input = tastiera.nextLine();
	            }
	        } while (!ok);

	        return toDouble;
	    }
	
}
