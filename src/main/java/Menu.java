

import java.util.Scanner;

public class Menu {

	private int sceltaMain, sceltaDurata, sceltaRischio, sceltaDeposito;

	private void setSceltaMain(int s) {
		this.sceltaMain=s;
	}

	private void setSceltaDurata(int s) {
		this.sceltaDurata=s;
	}

	private void setSceltaRischio(int s) {
		this.sceltaRischio=s;
	}

	private void setSceltaDeposito(int s) {
		this.sceltaDeposito=s;
	}
	
	public int getSceltaMain() {
		return this.sceltaMain;
	}
	
	public int getSceltaDurata() {
		return this.sceltaDurata;
	}

	public int getSceltaRischio() {
		
		return this.sceltaRischio;
		
	}
	
	public int getSceltaDeposito() {
		return this.sceltaDeposito;
	}
	
	public void menuMain() {
		Scanner tastiera = new Scanner(System.in);
		int scelta;
		System.out.println("---------------------------MENU'---------------------------");
		System.out.println("1)Depositare soldi");
		System.out.println("2)Prelevare soldi");
		System.out.println("3)Possibilità di investire soldi");
		System.out.println("4)Andare avanti di n mesi");
		System.out.println("5)Visualizzare lo stato del proprio conto");
		System.out.println("6)Visualizzare stato proprio portafoglio");
		System.out.println();
		System.out.println("7)USCIRE \n");
		System.out.print("Inserisci l'opzione desiderata: ");
		scelta = Conversione.conversioneInt(tastiera.nextLine());
		while (scelta < 0 || scelta > 7) {
			System.out.println("Opzione non disponibile");
			System.out.print("Reinserisci: ");
			scelta = Conversione.conversioneInt(tastiera.nextLine());
		}
		
		setSceltaMain(scelta);
	}

	public void menuPerDurata() {
		Scanner tastiera = new Scanner(System.in);
		int scelta;
		System.out.println("1)Investimenti di breve durata");
		System.out.println("2)Investimenti di media durata");
		System.out.println("3)Investimenti di lunga durata");
		System.out.print("Inserisci l'opzione desiderata: ");
		scelta = Conversione.conversioneInt(tastiera.nextLine());
		while (scelta > 3) {
			System.out.println("Opzione non disponibile");
			System.out.print("Reinserisci: ");
			scelta = Conversione.conversioneInt(tastiera.nextLine());
		}
		
		setSceltaDurata(scelta);

	}

	public void menuPerRischio() {
		Scanner tastiera = new Scanner(System.in);
		int scelta;
		System.out.println("1)Investimenti di basso rischio");
		System.out.println("2)Investimenti di medio rischio");
		System.out.println("3)Investimenti di alto rischio");
		System.out.print("Inserisci l'opzione desiderata: ");
		scelta = Conversione.conversioneInt(tastiera.nextLine());
		while (scelta > 3) {
			System.out.println("Opzione non disponibile");
			System.out.print("Reinserisci: ");
			scelta = Conversione.conversioneInt(tastiera.nextLine());
		}
		
		setSceltaRischio(scelta);

	}

	public void menuDeposito() {
		Scanner tastiera = new Scanner(System.in);
		int scelta;
		System.out.println("1)Depositarli sul conto in Banca");
		System.out.println("2)Depositarli sul portafoglio personale");
		System.out.print("Inserisci la scelta: ");
		scelta = Conversione.conversioneInt(tastiera.nextLine());
		while (scelta < 1 || scelta > 2) {
			System.out.println("Opzione non disponibile");
			System.out.print("Reinserisci: ");
			scelta = Conversione.conversioneInt(tastiera.nextLine());
		}
		
		setSceltaDeposito(scelta);
	}

}
