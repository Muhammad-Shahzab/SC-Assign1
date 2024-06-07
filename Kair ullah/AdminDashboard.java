import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class AdminDashboard extends JFrame {

    private final JButton approveItemsButton;
    private final JButton viewItemsButton;
    private final JButton manageAuctionsButton;
    private final JButton logoutButton;
    private final JLabel pictureLabel;

    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Create UI elements
        JLabel titleLabel = new JLabel("Admin Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        approveItemsButton = new JButton("Approve Items");
        viewItemsButton = new JButton("View Items");
        manageAuctionsButton = new JButton("Manage Auctions");
        logoutButton = new JButton("Logout");

        // Load image
        ImageIcon imageIcon = loadImageIcon("admin.jpg");
        pictureLabel = new JLabel(imageIcon);

        // Add action listeners
        approveItemsButton.addActionListener((ActionEvent e) -> {
            new ApproveItem().setVisible(true); // Changed to ApproveItem
        });

        viewItemsButton.addActionListener((ActionEvent e) -> {
            new RemoveItem().setVisible(true); // Changed to RemoveItem
        });

        manageAuctionsButton.addActionListener((ActionEvent e) -> {
            new ManageAuction().setVisible(true);
        });

        logoutButton.addActionListener((ActionEvent e) -> {
            dispose();
            JOptionPane.showMessageDialog(null, "Logged out successfully.", "Logout", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        });

        // Create panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(approveItemsButton);
        buttonPanel.add(viewItemsButton);
        buttonPanel.add(manageAuctionsButton);
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(logoutButton);

        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(buttonPanel, BorderLayout.WEST);
        mainPanel.add(pictureLabel, BorderLayout.CENTER);

        // Add components to the frame
        setLayout(new BorderLayout());
        add(titleLabel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    private ImageIcon loadImageIcon(String path) {
        // Try loading image from file system
        ImageIcon imageIcon = new ImageIcon(path);
        if (imageIcon.getIconWidth() == -1) {
            // If not found, try loading from resources
            java.net.URL imgURL = getClass().getResource("/" + path);
            if (imgURL != null) {
                imageIcon = new ImageIcon(imgURL);
            } else {
                System.err.println("Error: Image not found at " + path);
                imageIcon = new ImageIcon(); // return an empty icon if not found
            }
        }
        return imageIcon;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AdminDashboard().setVisible(true);
        });
    }
}
