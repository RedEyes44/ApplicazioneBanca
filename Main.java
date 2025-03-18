

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner tastiera = new Scanner(System.in);

		Utente u = new Utente(1000, 200, 0, 0);

		Menu m = new Menu();

		do {
			m.menuMain();

			switch (m.getSceltaMain()) {

			case 1: {

				System.out.print("Inserisci quanto vuoi depositare nel conto Bancario: ");
				double saldo = Azioni.deposita(u.getPortafoglio());

				u.setPortafoglio(u.getPortafoglio() - saldo);

				u.setContoBancario(u.getContoBancario() + saldo);

				System.out.println("Portafoglio: " + u.getPortafoglio());
				System.out.println("Conto in banca: " + u.getContoBancario());
				System.out.println();
				break;
			}

			case 2: {

				System.out.print("Inserisci quanto vuoi prelevare dal conto Bancario: ");
				double saldo = Azioni.preleva(u.getContoBancario());

				u.setPortafoglio(u.getPortafoglio() + saldo);

				u.setContoBancario(u.getContoBancario() - saldo);

				System.out.println("Portafoglio: " + u.getPortafoglio());
				System.out.println("Conto in banca: " + u.getContoBancario());
				System.out.println();

			}

			case 3: {

				if (u.getContoBancario() <= 0) {

					System.out.println("Non puoi investire, saldo insufficiente!");

				} else {

					System.out.println("Benvenuto nella sezione investimenti \n");
					System.out.println(
							"Ricorda che nell'arco de tempo d'investimento i soldi mensili vanno SOLO sul conto in banca \n");

					System.out.print(
							"Inserire quanti soldi vuoi investire. Ricorda che puoi solo investire con il conto bancario! ");
					double soldiDaInvestire = Conversione.conversioneDouble(tastiera.nextLine());

					u.setContoBancario(u.getContoBancario() - soldiDaInvestire);

					m.menuPerDurata();

					switch (m.getSceltaDurata()) {

					case 1: {

						u.setMese(u.getMese() + 1);

						u.setContoBancario(u.getContoBancario() + 100);
						break;
					}

					case 2: {

						u.setMese(u.getMese() + 3);

						u.setContoBancario(u.getContoBancario() + (100 * 3));

						break;

					}

					case 3: {

						u.setMese(u.getMese() + 6);
						u.setContoBancario(u.getContoBancario() + (100 * 6));

						break;
					}

					}

					m.menuPerRischio();
					double guadagno = 0;

					switch (m.getSceltaRischio()) {

					case 1: {
						System.out.println("\nBasso rischio");
						guadagno = Azioni.investimento(100, soldiDaInvestire);

						break;
					}

					case 2: {
						System.out.println("\nMedio rischio");
						guadagno = Azioni.investimento(250, soldiDaInvestire);

						break;
					}

					case 3: {
						System.out.println("\nAlto rischio");
						guadagno = Azioni.investimento(500, soldiDaInvestire);
						break;
					}
					

					}
					

					u.setGuadagno(guadagno);
					u.setContoBancario(u.getContoBancario() + u.getGuadagno());

					System.out.println();
					if (u.getGuadagno() <= soldiDaInvestire) {
						System.out.println("Mi dispiace. L'investimento non e' andato a buon fine!!!");
						System.out.println("Hai perso " + u.getGuadagno() + " euro");
					} else {
						System.out.println("CONGRATULAZIONI. L'investimento e' andato a buon fine!!!");
						System.out.println("Hai guadagnato " + u.getGuadagno() + " euro!!!");
					}

					System.out.println("Il tuo conto in banca e' " + u.getContoBancario());

				}

				break;

			}

			case 4: {

				System.out.print("Di quanti mesi vuoi andare avanti: ");
				int nMesi = Conversione.conversioneInt(tastiera.nextLine());
				System.out.println("Sei andato avanti di " + nMesi + " mesi! \n");
				System.out.println("Saldo del conto in Banca: " + u.getContoBancario());
				System.out.println("Saldo del portafoglio personale: " + u.getPortafoglio());
				System.out.println();

				u.setMese(u.getMese() + nMesi);
				System.out.println("Ora hai a disposizione altri " + (nMesi * 100) + "€, dove vuoi depositarli? \n");

				m.menuDeposito();
				switch (m.getSceltaDeposito()) {

				case 1: {
					
					System.out.println("Sono stati depositati 100 euro nel conto in Banca \n");
					u.setContoBancario(u.getContoBancario()+(nMesi*100));
                    System.out.println("Saldo del conto in Banca: " + u.getContoBancario());
                    System.out.println("Saldo del portafoglio personale: " + u.getPortafoglio());
					
					break;
				}
				
				case 2:{
					
					System.out.println("Sono stati depositati 100 euro nel portafoglio personale \n");
                    
                    u.setPortafoglio(u.getPortafoglio() + (nMesi*100));
					
					break;
				}
				
				default:{
				
					System.out.println("Saldo del conto in Banca: " + u.getContoBancario());
					System.out.println("Saldo del portafoglio personale: " + u.getPortafoglio());
				}
				System.out.println();
				
				}

				break;
			}

			case 5: {
				
				System.out.println("Stato del conto in banca: " + u.getContoBancario());
                System.out.println();
				
				break;
			}

			case 6: {
				System.out.println("Stato del portafoglio: " + u.getPortafoglio());
                System.out.println();
				break;
			}

			}

		} while (m.getSceltaMain() != 7);
		System.out.println("Grazie per aver usato questo programma!");

	}

}
