import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class BidderDashboard extends JFrame {

    private final JButton bidButton;
    private final JButton rateSellerButton;
    private final JButton logoutButton;

    public BidderDashboard() {
        setTitle("Bidder Dashboard");
        setSize(800, 300); // Increased width to accommodate the image
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create UI elements
        JLabel titleLabel = new JLabel("Bidder Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        bidButton = new JButton("Bid item");
        rateSellerButton = new JButton("Rate Seller");
        logoutButton = new JButton("Logout");

        // Add action listeners
        bidButton.addActionListener((e) -> bid());
        rateSellerButton.addActionListener((e) -> rateSeller());
        logoutButton.addActionListener((e) -> logout());

        // Create panel for buttons on the left side
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.add(bidButton);
        buttonPanel.add(rateSellerButton);
        buttonPanel.add(logoutButton);

        // Create panel for the image on the right side
        JPanel imagePanel = new JPanel();
        ImageIcon icon = new ImageIcon("Bid.jpg");
        JLabel imageLabel = new JLabel(icon);
        imagePanel.add(imageLabel);

        // Add components to the frame
        setLayout(new BorderLayout());
        add(titleLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.WEST);
        add(imagePanel, BorderLayout.CENTER);
    }

    private void bid() {
        new BidItem(this).setVisible(true);
        this.setVisible(false);
    }

    private void rateSeller() {
        // Create and display the RateSeller window
        SwingUtilities.invokeLater(() -> {
            new RateSeller().setVisible(true);
        });
        // Hide the current BidderDashboard window
        this.setVisible(false);
    }

    private void logout() {
        // Logic to handle logout
        int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Logging out...");
            dispose(); // Close the dashboard window
            // Add code to navigate back to the login screen or perform logout action
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BidderDashboard().setVisible(true);
        });
    }
}
