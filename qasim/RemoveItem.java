import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class RemoveItem extends JFrame {

    private final List<Item> itemList;
    private final JComboBox<String> itemComboBox;
    private final JTextArea itemTextArea;

    public RemoveItem() {
        setTitle("Remove Listed Items");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Dummy data for demonstration
        itemList = new ArrayList<>();
        itemList.add(new Item("Item 1", "Description for item 1", 100));
        itemList.add(new Item("Item 2", "Description for item 2", 150));
        itemList.add(new Item("Item 3", "Description for item 3", 200));

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
        itemComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaySelectedItem();
            }
        });
        selectionPanel.add(itemComboBox);

        container.add(selectionPanel, BorderLayout.NORTH);

        // Panel for displaying item details
        JPanel itemPanel = new JPanel(new BorderLayout());
        itemPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        itemTextArea = new JTextArea(10, 30);
        itemTextArea.setEditable(false);
        itemTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
        itemPanel.add(new JScrollPane(itemTextArea), BorderLayout.CENTER);

        container.add(itemPanel, BorderLayout.CENTER);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton removeButton = new JButton("Remove");
        removeButton.setFont(new Font("Arial", Font.PLAIN, 16));
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeSelectedItem();
            }
        });
        buttonPanel.add(removeButton);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backToAdminDashboard();
            }
        });
        buttonPanel.add(backButton);

        container.add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }

    private void displaySelectedItem() {
        String selectedItemName = (String) itemComboBox.getSelectedItem();
        if (selectedItemName != null) {
            for (Item item : itemList) {
                if (item.getName().equals(selectedItemName)) {
                    itemTextArea.setText("Name: " + item.getName() + "\n"
                            + "Description: " + item.getDescription() + "\n"
                            + "Price: $" + item.getPrice());
                    break;
                }
            }
        }
    }

    private void removeSelectedItem() {
        String selectedItemName = (String) itemComboBox.getSelectedItem();
        if (selectedItemName != null) {
            for (Item item : itemList) {
                if (item.getName().equals(selectedItemName)) {
                    itemList.remove(item);
                    itemComboBox.removeItem(selectedItemName);
                    itemTextArea.setText("");
                    JOptionPane.showMessageDialog(this, "Item removed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }
        }
    }

    private void backToAdminDashboard() {
        // Go back to AdminDashboard
        new AdminDashboard().setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RemoveItem::new);
    }

    class Item {
        private final String name;
        private final String description;
        private final double price;

        public Item(String name, String description, double price) {
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

        public double getPrice() {
            return price;
        }
    }
}
