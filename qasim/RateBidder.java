import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

public class RateBidder extends JFrame implements ActionListener {

    private final JComboBox<String> bidderComboBox;
    private final JButton rateButton;
    private final List<JToggleButton> starButtons;
    private final JButton backButton;

    private final List<Bidder> bidderList;

    public RateBidder() {
        setTitle("Rate Bidder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Dummy data for demonstration
        bidderList = new ArrayList<>();
        bidderList.add(new Bidder("Bidder 1"));
        bidderList.add(new Bidder("Bidder 2"));
        bidderList.add(new Bidder("Bidder 3"));

        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        // Panel for selecting bidder
        JPanel bidderPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bidderPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel bidderLabel = new JLabel("Select Bidder:");
        bidderLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        bidderPanel.add(bidderLabel);

        bidderComboBox = new JComboBox<>();
        bidderComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        for (Bidder bidder : bidderList) {
            bidderComboBox.addItem(bidder.getName());
        }
        bidderPanel.add(bidderComboBox);

        container.add(bidderPanel, BorderLayout.NORTH);

        // Panel for star rating
        JPanel ratingPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ratingPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        JLabel ratingLabel = new JLabel("Rating:");
        ratingLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        ratingPanel.add(ratingLabel);

        starButtons = new ArrayList<>();
        ButtonGroup starGroup = new ButtonGroup();
        for (int i = 1; i <= 5; i++) {
            JToggleButton starButton = new JToggleButton(String.valueOf(i));
            starButton.setFont(new Font("Arial", Font.PLAIN, 16));
            starButton.addActionListener(this);
            starButtons.add(starButton);
            starGroup.add(starButton);
            ratingPanel.add(starButton);
        }

        container.add(ratingPanel, BorderLayout.CENTER);

        // Panel for rating button and back button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        rateButton = new JButton("Rate Bidder");
        rateButton.setFont(new Font("Arial", Font.PLAIN, 16));
        rateButton.addActionListener(this);
        buttonPanel.add(rateButton);

        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.addActionListener(this);
        buttonPanel.add(backButton);

        container.add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rateButton) {
            String selectedBidderName = (String) bidderComboBox.getSelectedItem();
            int rating = getSelectedRating();
            if (selectedBidderName != null && rating != -1) {
                // Perform rating logic here
                JOptionPane.showMessageDialog(this, "Bidder rated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                // Clear fields after rating
                bidderComboBox.setSelectedIndex(0);
                clearStarSelection();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a bidder and rate the bidder", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == backButton) {
            // Go back to SellerDashboard
            new sellerdashboard().setVisible(true);
            dispose();
        }
    }

    private int getSelectedRating() {
        for (int i = 0; i < starButtons.size(); i++) {
            if (starButtons.get(i).isSelected()) {
                return i + 1; // Ratings start from 1
            }
        }
        return -1; // No rating selected
    }

    private void clearStarSelection() {
        for (JToggleButton starButton : starButtons) {
            starButton.setSelected(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RateBidder());
    }

    class Bidder {
        private final String name;

        public Bidder(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
