import java.awt.BorderLayout;
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

public class LoginSellerForm extends JFrame {

    private final JTextField emailField;
    private final JPasswordField passwordField;
    private final JButton loginButton;
    private final JButton registerButton; // New register button

    public LoginSellerForm() {
        setTitle("Seller Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create UI elements
        JLabel titleLabel = new JLabel("Seller Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);

        loginButton = new JButton("Login");
        registerButton = new JButton("Register"); // Initialize register button

        // Add action listeners
        loginButton.addActionListener((ActionEvent e) -> {
            login();
        });

        registerButton.addActionListener((ActionEvent e) -> {
            register();
        });

        // Create panels for layout
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton); // Add register button to the panel

        // Add components to the frame
        setLayout(new BorderLayout());
        add(titleLabel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }

    private void login() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        // Perform login validation
        if (validateLogin(email, password)) {
            JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            // Proceed to seller dashboard or functionality
        } else {
            JOptionPane.showMessageDialog(this, "Invalid email or password.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateLogin(String email, String password) {
        // In a real application, you would query the database or another data source to validate the credentials
        // Here, we'll just check if the email is "seller@example.com" and the password is "sellerpassword"
        return "seller@example.com".equals(email) && "sellerpassword".equals(password);
    }

    private void register() {
        // Redirect to the SellerRegistrationForm
        SwingUtilities.invokeLater(() -> {
            new SellerRegistrationForm().setVisible(true);
            dispose(); // Close the current login form
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginSellerForm().setVisible(true);
        });
    }
}
