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

public class RateSeller extends JFrame implements ActionListener {

    private final JComboBox<String> sellerComboBox;
    private final JButton rateButton;
    private final List<JToggleButton> starButtons;
    private final JButton backButton;

    private final List<Seller> sellerList;

    public RateSeller() {
        setTitle("Rate Seller");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Dummy data for demonstration
        sellerList = new ArrayList<>();
        sellerList.add(new Seller("Seller 1"));
        sellerList.add(new Seller("Seller 2"));
        sellerList.add(new Seller("Seller 3"));

        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        // Panel for selecting seller
        JPanel sellerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        sellerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel sellerLabel = new JLabel("Select Seller:");
        sellerLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        sellerPanel.add(sellerLabel);

        sellerComboBox = new JComboBox<>();
        sellerComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        for (Seller seller : sellerList) {
            sellerComboBox.addItem(seller.getName());
        }
        sellerPanel.add(sellerComboBox);

        container.add(sellerPanel, BorderLayout.NORTH);

        // Panel for star rating
        JPanel ratingPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ratingPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

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
        rateButton = new JButton("Rate Seller");
        rateButton.setFont(new Font("Arial", Font.PLAIN, 16));
        rateButton.addActionListener(this);
        buttonPanel.add(rateButton);

        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.addActionListener(this);
        buttonPanel.add(backButton);

        container.add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to full screen
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rateButton) {
            String selectedSellerName = (String) sellerComboBox.getSelectedItem();
            int rating = getSelectedRating();
            if (selectedSellerName != null && rating != -1) {
                // Perform rating logic here
                JOptionPane.showMessageDialog(this, "Seller rated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                // Clear fields after rating
                sellerComboBox.setSelectedIndex(0);
                clearStarSelection();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a seller and rate the seller", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == backButton) {
            // Go back to BidderDashboard
            new BidderDashboard().setVisible(true);
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
        SwingUtilities.invokeLater(() -> new RateSeller());
    }

    class Seller {
        private final String name;

        public Seller(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
