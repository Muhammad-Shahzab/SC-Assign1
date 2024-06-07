import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ApproveItem extends JFrame implements ActionListener {

    private final List<Item> itemList;
    private final JComboBox<String> itemComboBox;
    private final JButton approveButton;
    private final JButton rejectButton;
    private final JButton backButton;

    public ApproveItem() {
        setTitle("Approve Items");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to full screen
        setResizable(false);

        // Dummy data for demonstration
        itemList = new ArrayList<>();
        itemList.add(new Item("Item 1", "Description for Item 1", new ImageIcon("item1.jpg")));
        itemList.add(new Item("Item 2", "Description for Item 2", new ImageIcon("item2.jpg")));
        itemList.add(new Item("Item 3", "Description for Item 3", new ImageIcon("item3.jpg")));

        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        // Panel for item selection
        JPanel selectionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        selectionPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel selectLabel = new JLabel("Select Item:");
        selectLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        selectionPanel.add(selectLabel);

        itemComboBox = new JComboBox<>();
        itemComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        for (Item item : itemList) {
            itemComboBox.addItem(item.getName());
        }
        itemComboBox.addActionListener(this);
        selectionPanel.add(itemComboBox);

        container.add(selectionPanel, BorderLayout.NORTH);

        // Panel for approve and reject buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        approveButton = new JButton("Approve");
        approveButton.setFont(new Font("Arial", Font.PLAIN, 16));
        approveButton.addActionListener(this);
        buttonPanel.add(approveButton);

        rejectButton = new JButton("Reject");
        rejectButton.setFont(new Font("Arial", Font.PLAIN, 16));
        rejectButton.addActionListener(this);
        buttonPanel.add(rejectButton);

        backButton = new JButton("Back to Admin Dashboard");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.addActionListener(this);
        buttonPanel.add(backButton);

        container.add(buttonPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == approveButton) {
            int selectedIndex = itemComboBox.getSelectedIndex();
            if (selectedIndex != -1) {
                itemList.get(selectedIndex).setApproved(true);
                JOptionPane.showMessageDialog(this, "Item Approved!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (e.getSource() == rejectButton) {
            int selectedIndex = itemComboBox.getSelectedIndex();
            if (selectedIndex != -1) {
                itemList.get(selectedIndex).setApproved(false);
                JOptionPane.showMessageDialog(this, "Item Rejected!", "Rejected", JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getSource() == itemComboBox) {
            int selectedIndex = itemComboBox.getSelectedIndex();
            if (selectedIndex != -1) {
                displayItemDetails(selectedIndex);
            }
        } else if (e.getSource() == backButton) {
            // Go back to AdminDashboard
            new AdminDashboard().setVisible(true);
            dispose(); // Close the current window
        }
    }

    private void displayItemDetails(int index) {
        Item selectedItem = itemList.get(index);
        JOptionPane.showMessageDialog(this, "Name: " + selectedItem.getName() +
                "\nDescription: " + selectedItem.getDescription() +
                "\nPrice: " + selectedItem.getPrice(), "Item Details", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ApproveItem());
    }

    class Item {
        private final String name;
        private final String description;
        private final ImageIcon picture;
        private boolean approved;

        // Add price field
        private final double price;

        public Item(String name, String description, ImageIcon picture) {
            this.name = name;
            this.description = description;
            this.picture = picture;
            this.approved = false;

            // Dummy price for demonstration
            this.price = 100.0;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public ImageIcon getPicture() {
            return picture;
        }

        public boolean isApproved() {
            return approved;
        }

        public void setApproved(boolean approved) {
            this.approved = approved;
        }

        // Getter for price
        public double getPrice() {
            return price;
        }
    }
}
