package CustomerManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.MatteBorder;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
    private Manager manager;
    private JTextArea queueDisplay;
    private JTextArea reportArea;  // Declare the report area

    public Main() {
        manager = new Manager();
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Theme Park Customers Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 500);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.WHITE);  // White background

        // Input Panel (Top)
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15),
                new MatteBorder(2, 2, 2, 2, Color.GRAY)
        ));
        inputPanel.setBackground(Color.WHITE);  // White background

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel customerNameLabel = new JLabel("Customer Name:");
        customerNameLabel.setForeground(new Color(50, 50, 50)); // Grayish text
        customerNameLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JTextField customerNameField = new JTextField(15);
        customerNameField.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton addGeneralCustomerButton = createStyledButton("Add General", new Color(200, 200, 200));  // Light gray button
        JButton addVipCustomerButton = createStyledButton("Add VIP", new Color(150, 150, 150));  // Slightly darker gray button

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(customerNameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        inputPanel.add(customerNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(addGeneralCustomerButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(addVipCustomerButton, gbc);

        // Action Panel (Centered Horizontally)
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        actionPanel.setBackground(Color.WHITE);  // White background

        JButton serveCustomerButton = createStyledButton("Serve Customer", new Color(180, 180, 180));  // Light gray button

        actionPanel.add(serveCustomerButton);

        // Queue Display & Report Area
        JPanel displayPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        displayPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));
        displayPanel.setBackground(Color.WHITE);  // White background

        queueDisplay = new JTextArea(6, 20);
        queueDisplay.setEditable(false);
        queueDisplay.setFont(new Font("Arial", Font.BOLD, 14));
        queueDisplay.setBackground(new Color(240, 240, 240)); // Light gray background for queue
        queueDisplay.setForeground(new Color(50, 50, 50)); // Grayish black text
        queueDisplay.setBorder(BorderFactory.createTitledBorder("Queue Status"));
        JScrollPane queueScrollPane = new JScrollPane(queueDisplay);

        reportArea = new JTextArea();
        reportArea.setEditable(false);
        reportArea.setFont(new Font("Arial", Font.PLAIN, 14));
        reportArea.setBackground(new Color(240, 240, 240)); // Light gray background for report
        reportArea.setForeground(new Color(50, 50, 50)); // Grayish black text
        reportArea.setBorder(BorderFactory.createTitledBorder("Customer Report"));
        JScrollPane reportScrollPane = new JScrollPane(reportArea);

        displayPanel.add(queueScrollPane);
        displayPanel.add(reportScrollPane);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(actionPanel, BorderLayout.CENTER);
        frame.add(displayPanel, BorderLayout.SOUTH);

        // Button Actions
        addGeneralCustomerButton.addActionListener(e -> {
            String name = customerNameField.getText().trim();
            if (!name.isEmpty()) {
                manager.addGeneralCustomer(name);
                customerNameField.setText("");
                updateQueueDisplay();
            }
        });

        addVipCustomerButton.addActionListener(e -> {
            String name = customerNameField.getText().trim();
            if (!name.isEmpty()) {
                manager.addVipCustomer(name);
                customerNameField.setText("");
                updateQueueDisplay();
            }
        });

        serveCustomerButton.addActionListener(e -> {
            try {
                String servedCustomer = manager.serveCustomer();
                reportArea.setText("Served Customer: " + servedCustomer);  // Only show the served customer
                updateQueueDisplay();
            } catch (IllegalStateException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
            }
        });

        frame.setVisible(true);
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.BLACK);  // Black text for buttons
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        return button;
    }

    private void updateQueueDisplay() {
        queueDisplay.setText(manager.getQueueStatus());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
