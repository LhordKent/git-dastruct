package DastructAct;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;

public class CustomerManagementApp {
    private Queue<String> generalQueue = new LinkedList<>();
    private Stack<String> vipStack = new Stack<>();
    private JTextArea queueDisplay, reportArea;

    public CustomerManagementApp() {
        JFrame frame = new JFrame("Customer Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        JTextField nameField = new JTextField(10);
        JButton addGeneral = new JButton("Add General"), addVIP = new JButton("Add VIP"), serve = new JButton("Serve");
        inputPanel.add(new JLabel("Customer Name:"));
        inputPanel.add(nameField);
        inputPanel.add(addGeneral);
        inputPanel.add(addVIP);
        inputPanel.add(serve);

        queueDisplay = new JTextArea(10, 20);
        reportArea = new JTextArea(10, 20);
        queueDisplay.setEditable(false);
        reportArea.setEditable(false);
        JPanel displayPanel = new JPanel(new GridLayout(1, 2));
        displayPanel.add(new JScrollPane(queueDisplay));
        displayPanel.add(new JScrollPane(reportArea));

        addGeneral.addActionListener(e -> addCustomer(nameField.getText(), false));
        addVIP.addActionListener(e -> addCustomer(nameField.getText(), true));
        serve.addActionListener(this::serveCustomer);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(displayPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void addCustomer(String name, boolean isVIP) {
        if (!name.trim().isEmpty()) {
            if (isVIP) vipStack.push(name);
            else generalQueue.add(name);
            updateDisplay();
        }
    }

    private void serveCustomer(ActionEvent e) {
        if (!vipStack.isEmpty()) reportArea.append("Served: " + vipStack.pop() + " (VIP)\n");
        else if (!generalQueue.isEmpty()) reportArea.append("Served: " + generalQueue.poll() + " (General)\n");
        else JOptionPane.showMessageDialog(null, "No customers in queue");
        updateDisplay();
    }

    private void updateDisplay() {
        queueDisplay.setText("Queue:\n" + String.join("\n", generalQueue) + "\n\nVIP:\n" + String.join("\n", vipStack));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CustomerManagementApp::new);
    }
}
