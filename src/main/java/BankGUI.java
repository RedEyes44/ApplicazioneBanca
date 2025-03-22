import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe principale che gestisce l'interfaccia grafica dell'app bancaria.
 * Permette agli utenti di registrarsi, accedere, depositare, prelevare,
 * investire e visualizzare il proprio portafoglio.
 */

public class BankGUI extends JFrame {
    private JTextField userField, amountField;
    private JPasswordField passwordField;
    private JTextArea displayArea;
    private Map<String, UserAccount> accounts;
    private String loggedInUser = null;
    private static final String FILE_NAME = "accounts.dat";
    
    /**
     * Costruttore della classe BankGUI.
     * Inizializza l'interfaccia grafica e carica i dati degli utenti.
     */
    
    public BankGUI() {
        setTitle("Banking App");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);
        
        accounts = loadAccounts();
        
     // Creazione dei pannelli per input e bottoni
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("User:"));
        userField = new JTextField();
        inputPanel.add(userField);
        
        inputPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        inputPanel.add(passwordField);
        
        inputPanel.add(new JLabel("Amount:"));
        amountField = new JTextField();
        inputPanel.add(amountField);
        
        add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 3, 5, 5));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        JButton registerButton = new JButton("Register");
        JButton loginButton = new JButton("Login");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton investButton = new JButton("Invest");
        JButton viewBalanceButton = new JButton("View Balance");
        JButton viewPortfolioButton = new JButton("View Portfolio");
        JButton logoutButton = new JButton("Logout");
        
        registerButton.addActionListener(e -> registerUser());
        loginButton.addActionListener(e -> loginUser());
        depositButton.addActionListener(e -> deposit());
        withdrawButton.addActionListener(e -> withdraw());
        investButton.addActionListener(e -> invest());
        viewBalanceButton.addActionListener(e -> viewBalance());
        viewPortfolioButton.addActionListener(e -> viewPortfolio());
        logoutButton.addActionListener(e -> logoutUser());
        
        buttonPanel.add(registerButton);
        buttonPanel.add(loginButton);
        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(investButton);
        buttonPanel.add(viewBalanceButton);
        buttonPanel.add(viewPortfolioButton);
        buttonPanel.add(logoutButton);
        
        add(buttonPanel, BorderLayout.CENTER);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(new JScrollPane(displayArea), BorderLayout.SOUTH);
    }
    
    /**
     * Metodo per investire una somma di denaro.
     * Il guadagno o la perdita è casuale fino al 10%.
     */
    
    private void invest() {
        if (loggedInUser == null) {
            displayArea.append("Please log in first.\n");
            return;
        }
        try {
            double amount = Double.parseDouble(amountField.getText().trim());
            if (amount <= 0 || amount > accounts.get(loggedInUser).getBalance()) {
                displayArea.append("Invalid or insufficient funds for investment.\n");
                return;
            }
            double profit = amount * (Math.random() * 0.2 - 0.1); // Profit or loss up to 10%
            accounts.get(loggedInUser).withdraw(amount);
            accounts.get(loggedInUser).deposit(amount + profit);
            saveAccounts();
            displayArea.append("Invested " + amount + " and " + (profit >= 0 ? "gained" : "lost") + " " + Math.abs(profit) + "\n");
        } catch (NumberFormatException e) {
            displayArea.append("Invalid amount.\n");
        }
    }
    
    /**
     * Registra un nuovo utente con nome utente e password.
     */

    private void registerUser() {
        String user = userField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        if (!user.isEmpty() && !password.isEmpty() && !accounts.containsKey(user)) {
            accounts.put(user, new UserAccount(user, password, 1000, 0));
            saveAccounts();
            displayArea.append("User registered: " + user + "\n");
        } else {
            displayArea.append("User already exists or invalid input.\n");
        }
    }
    
    /**
     * Permette a un utente di accedere con le proprie credenziali.
     */

    private void loginUser() {
        String user = userField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        if (accounts.containsKey(user) && accounts.get(user).checkPassword(password)) {
            loggedInUser = user;
            displayArea.append("Logged in as: " + user + "\n");
        } else {
            displayArea.append("Invalid username or password.\n");
        }
    }
    
    
    /**
     * Disconnette l'utente attualmente loggato.
     */

    private void logoutUser() {
        loggedInUser = null;
        displayArea.append("Logged out.\n");
    }
    
    /**
     * Mostra il portafoglio dell'utente loggato.
     */
    
    private void viewPortfolio() {
        if (loggedInUser == null) {
            displayArea.append("Please log in first.\n");
            return;
        }
        displayArea.append("Portfolio of " + loggedInUser + "\nBalance: " + accounts.get(loggedInUser).getBalance() + "\n");
    }

    /**
     * Permette il prelievo di denaro dal conto dell'utente.
     */
    
    private void withdraw() {
        if (loggedInUser == null) {
            displayArea.append("Please log in first.\n");
            return;
        }
        try {
            double amount = Double.parseDouble(amountField.getText().trim());
            accounts.get(loggedInUser).withdraw(amount);
            saveAccounts();
            displayArea.append("Withdrawed " + amount + " to " + loggedInUser + "\n");
        } catch (NumberFormatException e) {
            displayArea.append("Invalid amount.\n");
        }
    }
    
    /**
     * Permette di depositare una somma di denaro nel conto.
     */

    private void deposit() {
        if (loggedInUser == null) {
            displayArea.append("Please log in first.\n");
            return;
        }
        try {
            double amount = Double.parseDouble(amountField.getText().trim());
            accounts.get(loggedInUser).deposit(amount);
            saveAccounts();
            displayArea.append("Deposited " + amount + " to " + loggedInUser + "\n");
        } catch (NumberFormatException e) {
            displayArea.append("Invalid amount.\n");
        }
    }
    
    /**
     * Visualizza il saldo dell'utente loggato.
     */

    private void viewBalance() {
        if (loggedInUser == null) {
            displayArea.append("Please log in first.\n");
            return;
        }
        displayArea.append("Balance for " + loggedInUser + ": " + accounts.get(loggedInUser).getBalance() + "\n");
    }
    
    /**
     * Salva i dati degli utenti su file.
     */

    private void saveAccounts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            displayArea.append("Error saving accounts.\n");
        }
    }
    
    /**
     * Carica i dati degli utenti dal file.
     * @return Una mappa contenente gli account utente salvati.
     */

    private Map<String, UserAccount> loadAccounts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (Map<String, UserAccount>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new HashMap<>();
        }
    }

    /**
     * Metodo principale per avviare l'applicazione.
     * @param args Argomenti da linea di comando.
     */
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BankGUI().setVisible(true));
    }
}

/**
 * Classe che rappresenta un account utente con saldo e portafoglio.
 */

class UserAccount implements Serializable {
    private String username;
    private String password;
    private double portfolio;
    private double balance;

    /**
     * Costruttore della classe UserAccount.
     * @param username Nome utente.
     * @param password Password dell'utente.
     * @param balance Saldo iniziale.
     * @param portfolio Portafoglio iniziale.
     */
    
    public UserAccount(String username, String password, double balance, double portofolio) {
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    /**
     * Verifica se la password inserita è corretta.
     * @param inputPassword Password inserita.
     * @return True se la password è corretta, altrimenti false.
     */
    
    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }
    
    /**
     * Restituisce il saldo attuale.
     * @return Saldo dell'utente.
     */

    public double getBalance() {
        return balance;
    }

    /**
     * Aggiunge il denaro depositato al conto e lo toglie dal portafoglio
     * @param amount Denaro che si vuole depositare
     */
    
    public void deposit(double amount) {
        this.balance += amount;
        this.portfolio -= amount;
    }
    
    /**
     * Toglie il denaro prelevato dal conto e lo aggiunge al portafoglio
     * @param amount Denaro che si vuole prelevare
     */
    
    public void withdraw(double amount) {
    	this.balance -= amount;
    	this.portfolio += amount;
    }
}
