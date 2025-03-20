package mine;

import java.util.Scanner;

public class Azioni {

	public static double deposita(double portafoglio) {
		Scanner tastiera = new Scanner(System.in);
        double saldo = Conversione.conversioneDouble(tastiera.nextLine());

        while (saldo > portafoglio) {
            System.out.println("Saldo del portafoglio non sufficiente");
            System.out.print("Inserisci quanto vuoi depositare nel conto Bancario: ");
            saldo = Conversione.conversioneDouble(tastiera.nextLine());
        }

        return saldo;
    }
	
	public static double preleva(double contoBancario) {
		Scanner tastiera = new Scanner(System.in);
        double saldo = Conversione.conversioneDouble(tastiera.nextLine());

        while (saldo > contoBancario) {
            System.out.println("Saldo del conto bancario non sufficiente");
            System.out.print("Inserisci quanto vuoi prelevare dal conto Bancario: ");
            saldo = Conversione.conversioneDouble(tastiera.nextLine());
        }

        return saldo;
    }
	
	public static double investimento(int grandezzaRischio, double soldiDaInvestire) {
        double guadagno;
        int rischio = (int) (Math.random() * grandezzaRischio);
        if (rischio <= 50) {
            guadagno = (soldiDaInvestire * (grandezzaRischio / 20.0));
        } else {
            guadagno = (double) ((20.0 / grandezzaRischio) * soldiDaInvestire);
        }
        
        
        return guadagno;
    }
	
	
	
	
}
