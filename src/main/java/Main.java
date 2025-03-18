
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
	

	public static void main(String[] args) throws FileNotFoundException {
		int nUtenti = 4;
		Scanner tastiera = new Scanner(System.in);
		
		String nome[] = new String[4], password [] = new String[4];
		double contoBancario[] = new double [4], portafoglio[] = new double [4], gain[] = new double [4];
		int mese[] = new int [4];
		
		File fin=new File("utenti.txt");
		Scanner input = new Scanner(fin);
			
		String riga;
		
		int c=0;
		
		// finchè vi è una riga da leggere
		while (input.hasNextLine()) {
			riga=input.nextLine();
			
			Scanner t = new Scanner(riga);
			  while (t.hasNext()) {
				  nome[c] = t.next();
				  password[c] = t.next();
				  contoBancario[c] = t.nextDouble();
				  portafoglio[c] = t.nextDouble();
				  gain[c] = t.nextDouble();
				  mese[c] = t.nextInt();
				  
				  }
			  //System.out.println("Nome: "+nome[c]+"\t Password: "+password[c]+"\t Conto bancario: "+ contoBancario[c] +"\t Portafoglio: "+ portafoglio[c] +"\t Guadagno: " + gain[c] +"\t Mese: " + mese[c]);
			  c++;
		}
		
		
		Utente u = new Utente("","",0, 0, 0, 0);
		
		String nickname, pass;
		int pos = 0;
		
		do {
			System.out.print("Inserire il nome utente --> ");
			nickname = tastiera.nextLine().trim();
			System.out.println();
			System.out.println("Inserire la password -->");
			pass = tastiera.nextLine().trim();
			System.out.println();
			pos = u.controllaDati(pass, nickname, password, nome);
			
		}while(pos==-1);
		
		
		u = new Utente(nome[pos],password[pos],contoBancario[pos], portafoglio[pos], gain[pos], mese[pos]);

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
		
		nome[pos]=u.getNome();
		password[pos]=u.getPassword();
		contoBancario[pos]=u.getContoBancario();
		portafoglio[pos]=u.getPortafoglio();
		gain[pos]=u.getGuadagno();
		mese[pos]=u.getMese();
		
		//File nuovo = new File("nuovo.txt");
		PrintWriter pw = new PrintWriter(new File("utenti.txt"));
		c=0;
		while(c<nUtenti) {
			
			pw.println(nome[c] + " " + password[c] + " " + contoBancario[c] + " " + portafoglio[c] + " " + gain[c] + " " + mese[c]);
			c++;
		}
		
		
		
		
		
		fin.delete();
		
		
		input.close();
		pw.close();

	}

}
