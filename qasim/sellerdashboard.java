import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class sellerdashboard extends JFrame {

    private final JButton viewItemsButton;
    private final JButton addItemButton;
    private final JButton rateBidderButton;
    private final JButton logoutButton;

    public sellerdashboard() {
        setTitle("Seller Dashboard");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create UI elements
        JLabel titleLabel = new JLabel("Seller Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        viewItemsButton = new JButton("View Items");
        addItemButton = new JButton("Add Item");
        rateBidderButton = new JButton("Rate Bidder");
        logoutButton = new JButton("Logout");

        // Add action listeners
        viewItemsButton.addActionListener((ActionEvent e) -> {
            viewItems();
        });

        addItemButton.addActionListener((ActionEvent e) -> {
            addItem();
        });

        rateBidderButton.addActionListener((ActionEvent e) -> {
            rateBidder();
        });

        logoutButton.addActionListener((ActionEvent e) -> {
            logout();
        });

        // Create panel for buttons on the left side
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        buttonPanel.add(viewItemsButton);
        buttonPanel.add(addItemButton);
        buttonPanel.add(rateBidderButton);
        buttonPanel.add(logoutButton);

        // Create panel for picture on the right side
        JPanel picturePanel = new JPanel(new BorderLayout());
        ImageIcon sellerImage = createImageIcon("seller.jpg", "Seller Image");
        if (sellerImage != null) {
            JLabel imageLabel = new JLabel(sellerImage);
            picturePanel.add(imageLabel, BorderLayout.CENTER);
        } else {
            JLabel errorLabel = new JLabel("Image not found");
            picturePanel.add(errorLabel, BorderLayout.CENTER);
        }

        // Add components to the frame
        setLayout(new BorderLayout());
        add(titleLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.WEST);
        add(picturePanel, BorderLayout.CENTER);
    }

    private void viewItems() {
        // Display items when the "View Items" button is clicked
        SwingUtilities.invokeLater(ViewItem::new);
    }

    private void addItem() {
        // Logic to navigate to the ListItem class
        SwingUtilities.invokeLater(() -> {
            ListItem listItem = new ListItem();
            listItem.setVisible(true);
        });
        // Close the seller dashboard window
        dispose();
    }

    private void rateBidder() {
        // Display the RateBidder window when the "Rate Bidder" button is clicked
        SwingUtilities.invokeLater(RateBidder::new);
    }

    private void logout() {
        // Logic to logout
        JOptionPane.showMessageDialog(this, "Logging out...");
        // Code to perform logout action
        // For example: navigate back to login screen
        dispose(); // Close the dashboard window
        // Code to navigate to login screen goes here
    }

    private ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new sellerdashboard().setVisible(true);
        });
    }
}
