import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class BidderRegistrationForm extends JFrame {

    private final JTextField nameField;
    private final JTextField emailField;
    private final JPasswordField passwordField;
    private final JButton registerButton;
    private final JButton loginButton; // New login button
    private final Controller controller;

    public BidderRegistrationForm() {
        setTitle("Bidder Registration");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create UI elements
        JLabel titleLabel = new JLabel("Bidder Registration", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        nameField = new JTextField(20);
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);

        registerButton = new JButton("Register");
        loginButton = new JButton("Login"); // Initialize login button

        // Add action listeners
        registerButton.addActionListener((ActionEvent e) -> {
            register();
        });

        loginButton.addActionListener((ActionEvent e) -> {
            login();
        });

        // Create panels for layout
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(registerButton);
        buttonPanel.add(loginButton); // Add login button to the panel

        // Add components to the frame
        setLayout(new BorderLayout());
        add(titleLabel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Initialize the controller
        controller = new Controller();
    }

    private void register() {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        // Perform registration validation
        if (validateRegistration(name, email, password)) {
            // Call the controller method to handle registration
            controller.registerBidder(name, email, password);
            JOptionPane.showMessageDialog(this, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            // Print all registered bidders
            controller.printAllUsers();
        } else {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateRegistration(String name, String email, String password) {
        // Validate that none of the fields are empty
        return !name.isEmpty() && !email.isEmpty() && !password.isEmpty();
    }

    private void login() {
        // Logic to navigate to bidder login form
        loginBidder loginBidder = new loginBidder();
        loginBidder.setVisible(true);
        dispose(); // Dispose the current frame
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BidderRegistrationForm().setVisible(true);
        });
    }
}
