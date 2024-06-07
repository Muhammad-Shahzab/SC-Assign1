import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class ManageAuction extends JFrame {

    private final JComboBox<String> itemComboBox;
    private final JButton startAuctionButton;
    private final JButton endAuctionButton;
    private final JButton backButton;

    private final List<String> itemList; // Replace with your item data source

    public ManageAuction() {
        setTitle("Auction Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        // Dummy data for demonstration
        itemList = new ArrayList<>();
        itemList.add("Item 1");
        itemList.add("Item 2");
        itemList.add("Item 3");

        // Create UI elements
        JLabel titleLabel = new JLabel("Auction Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel itemLabel = new JLabel("Select Item:");
        itemComboBox = new JComboBox<>();
        for (String item : itemList) {
            itemComboBox.addItem(item);
        }

        startAuctionButton = new JButton("Start Auction");
        endAuctionButton = new JButton("End Auction");
        backButton = new JButton("Back ");

        // Add action listeners
        startAuctionButton.addActionListener(e -> startAuction());
        endAuctionButton.addActionListener(e -> endAuction());
        backButton.addActionListener(e -> backToAdminDashboard());

        // Create panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(startAuctionButton);
        buttonPanel.add(endAuctionButton);
        buttonPanel.add(backButton);

        // Add components to the frame
        setLayout(new BorderLayout());
        add(titleLabel, BorderLayout.NORTH);
        add(itemLabel, BorderLayout.WEST);
        add(itemComboBox, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void startAuction() {
        String selectedItem = (String) itemComboBox.getSelectedItem();
        if (selectedItem != null) {
            int duration = getAuctionDuration(); // Get the selected auction duration
            // Logic to start auction with the selected item and duration
            JOptionPane.showMessageDialog(this, "Auction started for " + selectedItem + " with " + duration + " days duration");
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item to start auction", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void endAuction() {
        String selectedItem = (String) itemComboBox.getSelectedItem();
        if (selectedItem != null) {
            // Logic to end auction for the selected item
            JOptionPane.showMessageDialog(this, "Auction ended for " + selectedItem);
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item to end auction", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void backToAdminDashboard() {
        // Logic to navigate back to AdminDashboard
        new AdminDashboard().setVisible(true);
        dispose(); // Close the current window
    }

    private int getAuctionDuration() {
        // Dummy implementation to get auction duration from user input or selection
        String[] options = {"1 Day", "5 Days", "14 Days"};
        int option = JOptionPane.showOptionDialog(this, "Select Auction Duration", "Auction Duration", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        return switch (option) {
            case 0 -> 1;
            case 1 -> 5;
            case 2 -> 14;
            default -> -1;
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ManageAuction().setVisible(true));
    }
}
