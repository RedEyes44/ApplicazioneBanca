package mine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Banca {

	private File utenti = new File("utenti.txt");
	private FileWriter storico;

	private String nome[], password[];
	private double contoBancario[], portafoglio[], gain[];
	private int mese[];

	private int nUtenti;

	public Banca(int n, String nomeFile) throws IOException {

		storico = new FileWriter(nomeFile, true);
		nome = new String[n];
		password = new String[n];
		contoBancario = new double[n];
		portafoglio = new double[n];
		gain = new double[n];
		mese = new int[n];
		this.nUtenti = n;

	}

	public String getNome(int i) {
		if (i >= 0 && i < nome.length) {
			return nome[i];
		} else {
			return null;
		}
	}

	public String getPassword(int i) {
		if (i >= 0 && i < password.length) {
			return password[i];
		} else {
			return null;
		}
	}

	public Double getContoBancario(int i) {
		if (i >= 0 && i < 4) {
			return contoBancario[i];
		} else {
			return null;
		}
	}

	public Double getPortafoglio(int i) {
		if (i >= 0 && i < portafoglio.length) {
			return portafoglio[i];
		} else {
			return null;
		}
	}

	public Double getGain(int i) {
		if (i >= 0 && i < gain.length) {
			return gain[i];
		} else {
			return null;
		}
	}

	public Integer getMese(int i) {
		if (i >= 0 && i < mese.length) {
			return mese[i];
		} else {
			return null;
		}
	}
	
	public void transazione(String message) {
		
		PrintWriter pw = new PrintWriter(storico, true);
		
		pw.println(message);
	}

	private int controllaDati(String password, String nome) {

		for (int i = 0; i < this.nome.length; i++) {

			if (this.nome[i].equalsIgnoreCase(nome)) {
				if (this.password[i].equalsIgnoreCase(password)) {
					return i;
				} else {
					return -1;
				}
			}

		}

		return -1;

	}

	public int login() {
		Scanner tastiera = new Scanner(System.in);
		String nickname, pass;
		int pos = 0;

		do {
			System.out.print("Inserire il nome utente --> ");
			nickname = tastiera.nextLine().trim();
			System.out.println();
			System.out.println("Inserire la password -->");
			pass = tastiera.nextLine().trim();
			System.out.println();
			pos = controllaDati(pass, nickname);

		} while (pos == -1);
		
		System.out.println(pos);
		return pos;

	}
	
	public void setDati(String n, String p, double cB, double pt, double g, int m, int i) {
		this.nome[i]=n;
		this.password[i]=p;
		this.contoBancario[i]=cB;
		this.portafoglio[i]=pt;
		this.gain[i]=g;
		this.mese[i]=m;
	}

	public void salvaInfo() throws FileNotFoundException {
		
		PrintWriter pw = new PrintWriter(utenti);
		int c = 0;
		String riga = "";
		while (c < nUtenti) {

			riga = nome[c] + " " + password[c] + " " + contoBancario[c] + " " + portafoglio[c] + " " + gain[c] + " "
					+ mese[c];

			pw.println(Tools.virgolaPunto(riga));

			c++;
		}

		pw.close();
	}

	public void assignment() throws FileNotFoundException {
		Scanner input = new Scanner(utenti);

		String riga;

		int c = 0;

		while (input.hasNextLine()) {
			riga = input.nextLine();

			Scanner t = new Scanner(riga);
			while (t.hasNext()) {
				nome[c] = t.next();
				password[c] = t.next();
				contoBancario[c] = t.nextDouble();
				portafoglio[c] = t.nextDouble();
				gain[c] = t.nextDouble();
				mese[c] = t.nextInt();

				// System.out.println("Nome: "+nome[c]+"\t Password: "+password[c]+"\t Conto
				// bancario: "+ contoBancario[c] +"\t Portafoglio: "+ portafoglio[c] +"\t
				// Guadagno: " + gain[c] +"\t Mese: " + mese[c]);*/
			}
			c++;
		}

		input.close();
	}

}
