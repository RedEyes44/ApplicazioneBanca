/**
 * Classe Utente che rappresenta un utente del sistema con i relativi dati personali e un conto associato.
 */

public class Utente {
    private String nome;
    private String cognome;
    private String eta;
    private String username;
    private String password;
    private Conto conto;

    /**
     * Costruttore della classe Utente.
     * @param nome Nome dell'utente.
     * @param cognome Cognome dell'utente.
     * @param eta Età dell'utente.
     * @param username Nome utente per l'accesso.
     * @param password Password dell'utente.
     */
    public Utente(String nome, String cognome, String eta, String username, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
        this.username = username;
        this.password = password;
        this.conto = new Conto(nome, cognome, Integer.parseInt(eta), username, password); // Passa i parametri
    }

    /**
     * Restituisce il nome dell'utente.
     * @return Nome dell'utente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Restituisce il cognome dell'utente.
     * @return Cognome dell'utente.
     */
    
    public String getCognome() {
        return cognome;
    }
    
    /**
     * Restituisce l'età dell'utente.
     * @return Età dell'utente.
     */

    public String getEta() {
        return eta;
    }

    /**
     * Restituisce il nome utente dell'utente.
     * @return Nome utente.
     */
    
    public String getUsername() {
        return username;
    }

    /**
     * Restituisce la password dell'utente.
     * @return Password dell'utente.
     */
    
    public String getPassword() {
        return password;
    }
    
    /**
     * Restituisce il conto bancario associato all'utente.
     * @return Oggetto Conto dell'utente.
     */

    public Conto getConto() {
        return conto;
    }


    /**
     * Converte l'oggetto Utente in una stringa CSV.
     * @return Stringa in formato CSV con i dati dell'utente.
     */
    public String toCSV() {
        return nome + "," + cognome + "," + eta + "," + username + "," + password + ","
                + conto.getBanca() + "," + conto.getPortafoglio() + "," + conto.getMese() + "," + conto.getAnno();
    }

    /**
     * Crea un oggetto Utente da una stringa CSV.
     * @param line Stringa CSV contenente i dati dell'utente.
     * @return Oggetto Utente se la stringa è valida, null altrimenti.
     */
    public static Utente fromCSV(String line) {
        String[] parts = line.split(","); // Carattere separatore
        if (parts.length < 9)
            return null; // Restituisce null se i dati sono incompleti

        String nome = parts[0];
        String cognome = parts[1];
        String eta = parts[2];
        String username = parts[3];
        String password = parts[4];
        double banca = Double.parseDouble(parts[5]);
        double portafoglio = Double.parseDouble(parts[6]);
        int mese = Integer.parseInt(parts[7]);
        int anno = Integer.parseInt(parts[8]);

        Utente utente = new Utente(nome, cognome, eta, username, password);
        utente.getConto().setBanca(banca);
        utente.getConto().setPortafoglio(portafoglio);
        utente.getConto().setMese(mese);
        utente.getConto().setAnno(anno);

        return utente; // Restituisce l'oggetto utente
    }
}