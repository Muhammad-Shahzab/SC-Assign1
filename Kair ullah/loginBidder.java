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

public class loginBidder extends JFrame {

    private final JTextField emailField;
    private final JPasswordField passwordField;
    private final JButton loginButton;
    private final JButton registerButton;

    public loginBidder() {
        setTitle("Bidder Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create UI elements
        JLabel titleLabel = new JLabel("Bidder Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        // Add action listeners
        loginButton.addActionListener((ActionEvent e) -> {
            login();
        });

        registerButton.addActionListener((ActionEvent e) -> {
            register();
        });

        // Create panel for layout
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);

        // Add components to the frame
        setLayout(new BorderLayout());
        add(titleLabel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }

    private void login() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        // Perform login validation (replace with actual validation logic)
        if ("bidder@example.com".equals(email) && "password".equals(password)) {
            JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            // Proceed to bidder dashboard or functionality
        } else {
            JOptionPane.showMessageDialog(this, "Invalid email or password.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void register() {
        // Create an instance of BidderRegistrationForm
        BidderRegistrationForm registrationForm = new BidderRegistrationForm();
        // Make the BidderRegistrationForm visible
        registrationForm.setVisible(true);
        // Close the current login form (optional)
        dispose(); // Dispose the current frame
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new loginBidder().setVisible(true);
        });
    }
}
