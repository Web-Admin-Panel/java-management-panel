package cleaning_service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList; // Assuming you have an ArrayList for customers

public class MainForm extends JFrame {
    private JPanel mainPanel;

    // Navigation buttons
    private JButton systemPageButton; // System
    private JButton customersPageButton; // Employees
    private JButton employeesPageButton; // Customers (active panel)
    private JButton appointmentsPageButton; // Appointments
    private JPanel navigationPanel;

    private JPanel customerPanel;
    private JPanel contentPanel;
    private JButton addNewCustomerButton;
    private JTextField customerNameTextField; // Renamed for clarity
    private JTextField customerSurnameTextField;
    private JTextField customerNoTextField; // Assuming customer has a no field
    private JPanel systemPanel;
    private JLabel systemLable;

    // Assuming you have an ArrayList to store customers
    private static ArrayList<Customer> customers = new ArrayList<>();

    public MainForm() {
        this.systemLable = systemLable;
        setTitle("Management App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        BorderLayout main_layout = new BorderLayout();
        mainPanel.setLayout(main_layout); // Change layout manager

        mainPanel.add(navigationPanel, BorderLayout.NORTH);
//        mainPanel.add(customerPanel, BorderLayout.CENTER);

        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);
        cardPanel.add(customerPanel, "customers");
        cardPanel.add(systemPanel, "system");

        mainPanel.add(cardPanel, BorderLayout.CENTER); // Add customerPanel to center


        setContentPane(mainPanel);
        systemPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove the center component (assuming only one panel there)
//                mainPanel.remove(mainPanel.getComponentAt(0, 1));
                // Add and show systemPanel
                cardLayout.show(cardPanel, "system");
                systemPageButton.setEnabled(false);
                customersPageButton.setEnabled(true);
                appointmentsPageButton.setEnabled(true);
                employeesPageButton.setEnabled(true);
//                mainPanel.add(systemPanel, BorderLayout.CENTER); // Add systemPanel to center
//                mainPanel.revalidate(); // Inform Swing about changes
            }
        });

        customersPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to handle Employees button click (switch to employee panel)
                cardLayout.show(cardPanel, "customers");
                customersPageButton.setEnabled(false);
                systemPageButton.setEnabled(true);
                appointmentsPageButton.setEnabled(true);
                employeesPageButton.setEnabled(true);
//                mainPanel.add(customerPanel, BorderLayout.CENTER); // Add systemPanel to center
//                mainPanel.revalidate(); // Inform Swing about changes
            }
        });

        appointmentsPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to handle Appointments button click (switch to appointment panel)
            }
        });



        // Add new customer button action
        addNewCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String customerName = customerNameTextField.getText();
                String customerSurname = customerSurnameTextField.getText();
                String customerNo = customerNoTextField.getText(); // Assuming customer has a no field

                // Add validation if needed (e.g., check if any field is empty)
                int customer_id = 0;
                Customer newCustomer = new Customer(customer_id, customerNo, customerName, customerSurname); // Assuming appropriate Customer constructor
                add_customer(newCustomer); // Call your add_customer function

                // Clear text fields after adding customer (optional)
                JOptionPane.showMessageDialog(null, "New customer was added:\n- id: " + customer_id + "\n- Number: " + customerNo + "\n- Name: " + customerName + "\n- Surname: " + customerSurname);
                customerNameTextField.setText("");
                customerSurnameTextField.setText("");
                customerNoTextField.setText("");

            }
        });
    }

    public static void add_customer(Customer customer) {
        customers.add(customer);
    }

    public static void main(String[] args) {
        new MainForm();
    }
}
