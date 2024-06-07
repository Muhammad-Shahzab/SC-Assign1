import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class BidItem extends JFrame implements ActionListener {

    private final JComboBox<String> itemsComboBox;
    private final JLabel itemNameLabel;
    private final JTextArea descriptionTextArea;
    private final JLabel currentPriceLabel;
    private final JTextField bidPriceField;
    private final JButton bidButton;
    private final JButton backButton;

    private final Map<String, Item> itemMap;

    public BidItem(BidderDashboard parent) {
        setTitle("Bid Item");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Dummy data for demonstration
        itemMap = new HashMap<>();
        itemMap.put("Item 1", new Item("Item 1", "Description for Item 1", 100));
        itemMap.put("Item 2", new Item("Item 2", "Description for Item 2", 200));
        itemMap.put("Item 3", new Item("Item 3", "Description for Item 3", 150));

        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        // Panel for selecting auction item
        JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        itemPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel itemLabel = new JLabel("Select Item:");
        itemLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        itemPanel.add(itemLabel);

        itemsComboBox = new JComboBox<>();
        itemsComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        for (String itemName : itemMap.keySet()) {
            itemsComboBox.addItem(itemName);
        }
        itemsComboBox.addActionListener(this);
        itemPanel.add(itemsComboBox);

        container.add(itemPanel, BorderLayout.NORTH);

        // Panel for displaying item details and bidding
        JPanel detailsPanel = new JPanel(new BorderLayout());
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        itemNameLabel = new JLabel("");
        itemNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        detailsPanel.add(itemNameLabel, BorderLayout.NORTH);

        descriptionTextArea = new JTextArea();
        descriptionTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
        descriptionTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(descriptionTextArea);
        detailsPanel.add(scrollPane, BorderLayout.CENTER);

        currentPriceLabel = new JLabel("");
        currentPriceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        detailsPanel.add(currentPriceLabel, BorderLayout.WEST);

        JPanel bidPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel bidLabel = new JLabel("Enter Bid Price:");
        bidLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        bidPanel.add(bidLabel);

        bidPriceField = new JTextField(10);
        bidPriceField.setFont(new Font("Arial", Font.PLAIN, 16));
        bidPanel.add(bidPriceField);

        bidButton = new JButton("Place Bid");
        bidButton.setFont(new Font("Arial", Font.PLAIN, 16));
        bidButton.addActionListener(this);
        bidPanel.add(bidButton);

        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.addActionListener(e -> {
            parent.setVisible(true);
            dispose();
        });
        bidPanel.add(backButton);

        detailsPanel.add(bidPanel, BorderLayout.SOUTH);

        container.add(detailsPanel, BorderLayout.CENTER);

        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to full screen
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == itemsComboBox) {
            String selectedItem = (String) itemsComboBox.getSelectedItem();
            if (selectedItem != null) {
                Item item = itemMap.get(selectedItem);
                if (item != null) {
                    itemNameLabel.setText(item.getName());
                    descriptionTextArea.setText(item.getDescription());
                    currentPriceLabel.setText("Current Price: $" + item.getPrice());
                }
            }
        } else if (e.getSource() == bidButton) {
            String selectedItem = (String) itemsComboBox.getSelectedItem();
            if (selectedItem != null) {
                Item item = itemMap.get(selectedItem);
                if (item != null) {
                    try {
                        int newBidPrice = Integer.parseInt(bidPriceField.getText());
                        if (newBidPrice > item.getPrice()) {
                            item.setPrice(newBidPrice);
                            currentPriceLabel.setText("Current Price: $" + item.getPrice());
                            JOptionPane.showMessageDialog(this, "Bid placed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(this, "Bid price must be higher than current price", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Please enter a valid bid price", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BidItem(new BidderDashboard()));
    }

    class Item {
        private final String name;
        private final String description;
        private int price;

        public Item(String name, String description, int price) {
            this.name = name;
            this.description = description;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
