
public class Utente {

	private double contoBancario, portafoglio, guadagno;
	private int mese;
	private String nome, password;
	
	public Utente(String n, String pass,double cB, double p, double g, int m) {
		
		this.nome=n;
		this.password=pass;
		this.contoBancario=cB;
		this.portafoglio=p;
		this.guadagno=g;
		this.mese=m;
		
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public double getContoBancario() {
		return this.contoBancario;
	}
	
	public double getPortafoglio() {
		return this.portafoglio;
	}
	
	public double getGuadagno() {
		return this.guadagno;
	}
	
	public int getMese() {
		return this.mese;
	}
	
	public void setContoBancario(double cB) {
		this.contoBancario=cB;
	}
	
	public void setPortafoglio(double p) {
		this.portafoglio=p;
	}
	
	public void setGuadagno(double g) {
		this.guadagno=g;
	}
	
	public void setMese(int m) {
		this.mese=m;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
