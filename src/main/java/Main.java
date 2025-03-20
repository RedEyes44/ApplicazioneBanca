package mine;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		int DIM = 4;
		
		Banca banca = new Banca(DIM,"storico.txt");
		
		Scanner tastiera = new Scanner(System.in);

		Utente u = new Utente("!","!",0,0,0,0);

		banca.assignment();
		
		int pos = banca.login();

		u = new Utente(banca.getNome(pos),banca.getPassword(pos),banca.getContoBancario(pos),banca.getPortafoglio(pos),banca.getGain(pos),banca.getMese(pos));

		Menu m = new Menu();
		
		
		String message="";

		do {
			m.menuMain();

			switch (m.getSceltaMain()) {

			case 1: {
				
				if(u.getPortafoglio()>1) {
					System.out.print("Inserisci quanto vuoi depositare nel conto Bancario: ");
					double saldo = Azioni.deposita(u.getPortafoglio());
	
					u.setPortafoglio(u.getPortafoglio() - saldo);
					
					message = LocalDate.now() + " : sono stati rimossi " + saldo + " euro dal portafoglio" ;
					banca.transazione(message);
					
					u.setContoBancario(u.getContoBancario() + saldo);
					
					message = LocalDate.now() + " : sono stati aggiunti " + saldo + " euro al conto";
					banca.transazione(message);
				}else {
					
					System.out.println("Non e' possibile depositare denaro");
					
				}
				
				u.setContoBancario(Tools.arrotondaSecondaCifra(u.getContoBancario()));
				u.setPortafoglio(Tools.arrotondaSecondaCifra(u.getPortafoglio()));
				System.out.println("Portafoglio: " + u.getPortafoglio());
				System.out.println("Conto in banca: " + u.getContoBancario());
				System.out.println();
				break;
			}

			case 2: {
				if(u.getContoBancario()>1) {
					System.out.print("Inserisci quanto vuoi prelevare dal conto Bancario: ");
					double saldo = Azioni.preleva(u.getContoBancario());
	
					u.setPortafoglio(u.getPortafoglio() + saldo);
					
					message = LocalDate.now() + " : sono stati aggiunti " + saldo + " euro al portafoglio";
					banca.transazione(message);
	
					u.setContoBancario(u.getContoBancario() - saldo);
					
					message = LocalDate.now() + " : sono stati tolti " + saldo ;
					banca.transazione(message);
				}else {
					System.out.println("Non e' possibile prelevare denaro");
				}
				
				
				u.setContoBancario(Tools.arrotondaSecondaCifra(u.getContoBancario()));
				u.setPortafoglio(Tools.arrotondaSecondaCifra(u.getPortafoglio()));
				
				System.out.println("Portafoglio: " + u.getPortafoglio());
				System.out.println("Conto in banca: " + u.getContoBancario());
				System.out.println();
				break;
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
					
					message = LocalDate.now() + " : sono stati tolti " + soldiDaInvestire + " euro dal conto";
					banca.transazione(message);

					m.menuPerDurata();

					switch (m.getSceltaDurata()) {

					case 1: {

						u.setMese(u.getMese() + 1);

						u.setContoBancario(u.getContoBancario() + 100);
						
						message = LocalDate.now() + " : sono stati aggiunti " + 100 + " euro al conto";
						banca.transazione(message);
						break;
					}

					case 2: {

						u.setMese(u.getMese() + 3);

						u.setContoBancario(u.getContoBancario() + (100 * 3));
						
						message = LocalDate.now() + " : sono stati aggiunti " + (100*3) + " euro al conto";
						banca.transazione(message);

						break;

					}

					case 3: {

						u.setMese(u.getMese() + 6);
						u.setContoBancario(u.getContoBancario() + (100 * 6));
						
						message = LocalDate.now() + " : sono stati aggiunti " + (100*6) + " euro al conto";
						banca.transazione(message);

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
						u.setGuadagno(Tools.arrotondaSecondaCifra(u.getGuadagno()));
						System.out.println("Hai perso " + u.getGuadagno() + " euro");
						
						message = LocalDate.now() + " : sono stati tolti " + u.getGuadagno() + " euro dal conto";
						banca.transazione(message);
						
	
					} else {
						System.out.println("CONGRATULAZIONI. L'investimento e' andato a buon fine!!!");
						u.setGuadagno(Tools.arrotondaSecondaCifra(u.getGuadagno()));
						System.out.println("Hai guadagnato " + u.getGuadagno() + " euro!!!");
						
						message = LocalDate.now() + " : sono stati aggiunti " + u.getGuadagno() + " euro dal conto";
						banca.transazione(message);
					}
					
					u.setContoBancario(Tools.arrotondaSecondaCifra(u.getContoBancario()));
					
					System.out.println("Il tuo conto in banca e' " + u.getContoBancario());

				}

				break;

			}

			case 4: {

				System.out.print("Di quanti mesi vuoi andare avanti: ");
				int nMesi = Conversione.conversioneInt(tastiera.nextLine());
				System.out.println("Sei andato avanti di " + nMesi + " mesi! \n");
				
				u.setContoBancario(Tools.arrotondaSecondaCifra(u.getContoBancario()));
				u.setPortafoglio(Tools.arrotondaSecondaCifra(u.getPortafoglio()));
				
				System.out.println("Saldo del conto in Banca: " + u.getContoBancario());
				System.out.println("Saldo del portafoglio personale: " + u.getPortafoglio());
				System.out.println();

				u.setMese(u.getMese() + nMesi);
				System.out.println("Ora hai a disposizione altri " + (nMesi * 100) + "€, dove vuoi depositarli? \n");

				m.menuDeposito();
				switch (m.getSceltaDeposito()) {

				case 1: {

					System.out.println("Sono stati depositati 100 euro nel conto in Banca \n");
					u.setContoBancario(u.getContoBancario() + (nMesi * 100));
					
					message = LocalDate.now() + " : sono stati aggiunti " + (100*nMesi) + " euro al conto";
					banca.transazione(message);
					
					u.setContoBancario(Tools.arrotondaSecondaCifra(u.getContoBancario()));
					u.setPortafoglio(Tools.arrotondaSecondaCifra(u.getPortafoglio()));
					
					System.out.println("Saldo del conto in Banca: " + u.getContoBancario());
					System.out.println("Saldo del portafoglio personale: " + u.getPortafoglio());

					break;
				}

				case 2: {

					System.out.println("Sono stati depositati 100 euro nel portafoglio personale \n");

					u.setPortafoglio(u.getPortafoglio() + (nMesi * 100));
					
					message = LocalDate.now() + " : sono stati aggiunti " + (100*nMesi) + " euro al portafoglio";
					banca.transazione(message);

					break;
				}

				default: {
					
					u.setContoBancario(Tools.arrotondaSecondaCifra(u.getContoBancario()));
					u.setPortafoglio(Tools.arrotondaSecondaCifra(u.getPortafoglio()));
					
					System.out.println("Saldo del conto in Banca: " + u.getContoBancario());
					System.out.println("Saldo del portafoglio personale: " + u.getPortafoglio());
				}
					System.out.println();

				}

				break;
			}

			case 5: {
				u.setContoBancario(Tools.arrotondaSecondaCifra(u.getContoBancario()));
				System.out.println("Stato del conto in banca: " + u.getContoBancario());
				System.out.println();

				break;
			}

			case 6: {
				
				u.setPortafoglio(Tools.arrotondaSecondaCifra(u.getPortafoglio()));
				System.out.println("Stato del portafoglio: " + u.getPortafoglio());
				System.out.println();
				break;
			}

			}

		} while (m.getSceltaMain() != 7);
		System.out.println("Grazie per aver usato questo programma!");
		
		
		banca.setDati(u.getNome(), u.getPassword(), u.getContoBancario(), u.getPortafoglio(), u.getGuadagno(), u.getMese(), pos);
		
		banca.salvaInfo();
		

	}

}
