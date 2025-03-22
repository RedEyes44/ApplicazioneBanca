import java.io.*;
import java.util.Scanner;

/**
 * Classe Conto che gestisce le informazioni di un conto bancario e le operazioni finanziarie associate.
 */

class Conto {
    private String nome;
    private String cognome;
    private int eta;
    private String username;
    private String password;
    private double contoBancario;
    private double portafoglio;
    private double banca;
    private int mese; 
    private int anno;

    static Scanner tastiera = new Scanner(System.in);
    
    /**
     * Costruttore per inizializzare un nuovo conto.
     * @param nome Nome dell'utente.
     * @param cognome Cognome dell'utente.
     * @param eta Età dell'utente.
     * @param username Nome utente per l'accesso.
     * @param password Password dell'utente.
     */

    public Conto(String nome, String cognome, int eta, String username, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
        this.username = username;
        this.password = password;
        this.contoBancario = 0.0;
        this.portafoglio = 0.0;
    }

    /**
     * Registra un nuovo utente salvandolo su file.
     * @return true se la registrazione ha successo, false se l'utente esiste già o si verifica un errore.
     */
    public boolean registra() {
        File userFile = new File(username + ".txt");
        if (userFile.exists()) {
            System.out.println("Utente già registrato.");
            return false;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userFile))) {
            writer.write(nome + "\n");
            writer.write(cognome + "\n");
            writer.write(eta + "\n");
            writer.write(username + "\n");
            writer.write(password + "\n");
            return true;
        } catch (IOException e) {
            System.out.println("Errore nella creazione del file utente.");
        }
        return false;
    }

    /**
     * Effettua il login controllando i dati salvati.
     * @param username Nome utente inserito.
     * @param password Password inserita.
     * @return true se il login ha successo, false altrimenti.
     */
    public static boolean login(String username, String password) {
        File userFile = new File(username + ".txt");
        if (!userFile.exists()) {
            System.out.println("\nUtente non trovato.");
            return false;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
            reader.readLine(); // Nome
            reader.readLine(); // Cognome
            reader.readLine(); // Età
            reader.readLine(); // Username
            String storedPassword = reader.readLine();
            return storedPassword != null && storedPassword.equals(password);
        } catch (IOException e) {
            System.out.println("Errore nella lettura del file utente.");
        }
        return false;
    }

    /**
     * Mostra il menu per la gestione delle operazioni finanziarie dell'utente.
     */
    public void menuGestioneFinanziaria() {
        int scelta;
        do {
            System.out.println("---------------------------MENU'---------------------------");
            System.out.println("1) Depositare soldi");
            System.out.println("2) Prelevare soldi");
            System.out.println("3) Possibilità di investire soldi");
            System.out.println("4) Andare avanti di n mesi");
            System.out.println("5) Visualizzare lo stato del proprio conto");
            System.out.println("6) Visualizzare stato proprio portafoglio");
            System.out.println("7) USCIRE\n");
            System.out.print("Inserisci l'opzione desiderata: ");
            scelta = conversioneInt(tastiera.nextLine());

            switch (scelta) {
                case 1:
                    System.out.print("Inserisci quanto vuoi depositare nel conto Bancario: ");
                    double deposito = conversioneDouble(tastiera.nextLine());
                    deposita(deposito);
                    break;
                case 2:
                    System.out.print("Inserisci quanto vuoi prelevare dal conto Bancario: ");
                    double prelievo = conversioneDouble(tastiera.nextLine());
                    preleva(prelievo);
                    break;
                case 3:
                    System.out.print("Inserisci l'importo da investire: ");
                    double soldiDaInvestire = conversioneDouble(tastiera.nextLine());
                    System.out.print("Inserisci il livello di rischio (1-100): ");
                    int rischio = conversioneInt(tastiera.nextLine());
                    investi(rischio, soldiDaInvestire);
                    break;
                case 4:
                    System.out.print("Inserisci quanti mesi vuoi avanzare: ");
                    int mesi = conversioneInt(tastiera.nextLine());
                    aggiornaSaldo(mesi);
                    break;
                case 5:
                    System.out.println("Saldo conto bancario: " + contoBancario);
                    break;
                case 6:
                    System.out.println("Saldo portafoglio: " + portafoglio);
                    break;
            }
        } while (scelta != 7);
    }
    /**
     * Deposita un importo nel conto bancario.
     * @param saldo Importo da depositare.
     */
    public void deposita(double saldo) {
        if (saldo <= portafoglio) {
            portafoglio -= saldo;
            contoBancario += saldo;
            System.out.println("Deposito effettuato.");
        } else {
            System.out.println("Saldo del portafoglio non sufficiente.");
        }
    }
    
    /**
     * Preleva un importo dal conto bancario.
     * @param saldo Importo da prelevare.
     */
    public void preleva(double saldo) {
        if (saldo <= contoBancario) {
            contoBancario -= saldo;
            portafoglio += saldo;
            System.out.println("Prelievo effettuato.");
        } else {
            System.out.println("Saldo del conto bancario non sufficiente.");
        }
    }

    /**
     * Aggiorna il saldo del conto avanzando di un certo numero di mesi.
     * @param mesi Numero di mesi per cui aggiornare il saldo.
     */    public void aggiornaSaldo(int mesi) {
        contoBancario += mesi * 100;
        System.out.println("Saldo aggiornato.");
    }

     /**
      * Effettua un investimento con un determinato livello di rischio.
      * @param grandezzaRischio Livello di rischio (1-100).
      * @param soldiDaInvestire Importo da investire.
      */
    public void investi(int grandezzaRischio, double soldiDaInvestire) {
        int rischio = (int) (Math.random() * grandezzaRischio);
        double guadagno;
        if (rischio <= 50) {
            guadagno = soldiDaInvestire * (grandezzaRischio / 20.0);
        } else {
            guadagno = (20.0 / grandezzaRischio) * soldiDaInvestire;
        }
        contoBancario += guadagno;
        System.out.println("Investimento effettuato. Guadagno: " + guadagno);
    }

    /**
     * Converte una stringa in un numero intero, garantendo l'input corretto.
     * @param input Stringa da convertire.
     * @return Numero intero corrispondente.
     */
    public static int conversioneInt(String input) {
        int toInt = -1;
        boolean ok;
        do {
            ok = true;
            try {
                toInt = Integer.parseInt(input);
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

   /**
    Converte una stringa in un numero double, garantendo l'input corretto.
    @param input Stringa da convertire.
     @return Numero double corrispondente.
   **/ 
    public static double conversioneDouble(String input) {
        double toDouble = -1;
        boolean ok;
        do {
            ok = true;
            try {
                toDouble = Double.parseDouble(input);
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

    /**
     * Imposta il saldo della banca.
     * @param banca Nuovo saldo della banca.
     */
    public void setBanca(double banca) {
        this.banca = banca;
    }

    /**
     * Imposta il saldo del portafoglio.
     * @param portafoglio Nuovo saldo del portafoglio.
     */
    public void setPortafoglio(double portafoglio) {
        this.portafoglio = portafoglio;
    }

    /**
     * Imposta il mese corrente.
     * @param mese Nuovo mese.
     */
    public void setMese(int mese) {
        this.mese = mese;
    }

    /**
     * Imposta l'anno corrente.
     * @param anno Nuovo anno.
     */
    public void setAnno(int anno) {
        this.anno = anno;
    }

    /**
     * Restituisce il saldo della banca.
     * @return Saldo della banca.
     */
    public double getBanca() {
        return banca;
    }

    /**
     * Restituisce il saldo del portafoglio.
     * @return Saldo del portafoglio.
     */
    public double getPortafoglio() {
        return portafoglio;
    }

    /**
     * Restituisce il mese corrente.
     * @return Mese corrente.
     */
    public int getMese() {
        return mese;
    }

    /**
     * Restituisce l'anno corrente.
     * @return Anno corrente.
     */
    public int getAnno() {
        return anno;
    }

}
