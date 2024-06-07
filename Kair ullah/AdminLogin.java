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

public class AdminLogin extends JFrame {
    private final JTextField emailField;
    private final JPasswordField passwordField;
    private final JButton loginButton;

    public AdminLogin() {
        setTitle("Auction System - Admin Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Create UI elements
        JLabel titleLabel = new JLabel("Admin Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        
        // Add action listener to the login button
        loginButton.addActionListener((ActionEvent e) -> {
            login();
        });
        
        // Create panels for layout
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel(""));
        panel.add(loginButton);

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
            // Proceed to the Admin Dashboard
            new AdminDashboard().setVisible(true);
            this.dispose(); // Close the login window
        } else {
            JOptionPane.showMessageDialog(this, "Invalid email or password.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateLogin(String email, String password) {
        // In a real application, you would query the database or another data source to validate the credentials
        // Here, we'll just check if the email is "admin@example.com" and the password is "password"
        return "admin@example.com".equals(email) && "password".equals(password);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AdminLogin().setVisible(true);
        });
    }

    public JTextField getEmailField() {
        return emailField;
    }
}
