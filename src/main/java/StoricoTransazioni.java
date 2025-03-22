import java.util.ArrayList;
import java.util.List;

/**
 * Classe StoricoTransazioni che tiene traccia delle transazioni effettuate.
 */

public class StoricoTransazioni {

    // Lista che tiene traccia delle transazioni
    private List<String> transazioni;

    /**
     * Costruttore della classe StoricoTransazioni.
     * Inizializza la lista delle transazioni.
     */
    public StoricoTransazioni() {
        this.transazioni = new ArrayList<>();
    }

    /**
     * Aggiunge una nuova transazione allo storico.
     * @param tipoTransazione Il tipo di transazione effettuata (es. "Deposito", "Prelievo").
     * @param importo L'importo della transazione.
     */
    public void aggiungiTransazione(String tipoTransazione, double importo) {
        String transazione = tipoTransazione + ": " + importo + "€";
        transazioni.add(transazione);
    }

    /**
     * Visualizza l'elenco delle transazioni effettuate.
     */
    public void visualizzaStorico() {
        if (transazioni.isEmpty()) {
            System.out.println("Nessuna transazione effettuata.");
        } else {
            System.out.println("Storico delle transazioni:");
            for (String transazione : transazioni) {
                System.out.println(transazione);
            }
        }
    }

    /**
     * Restituisce l'elenco delle transazioni effettuate.
     * @return Una lista contenente tutte le transazioni registrate.
     */
    public List<String> getTransazioni() {
        return transazioni;
    }
}