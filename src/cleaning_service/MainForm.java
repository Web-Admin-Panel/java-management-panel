package cleaning_service;
import cleaning_service.manager.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

//import static cleaning_service.manager.CustomerManager.customers;

public class MainForm extends JFrame {
    private JPanel mainPanel;

    // Navigation buttons
    private JButton systemPageButton; // System
    private JButton customersPageButton; // Employees
    private JButton employeesPageButton; // Customers (active panel)
    private JButton appointmentsPageButton; // Appointments
    private JPanel navigationPanel;


    // Content Pages
    private JPanel contentPanel;

    // Customers operations
    private JPanel customerPanel;
    private JButton addNewCustomerButton;
    private JTextField customerNameTextField;
    private JTextField customerSurnameTextField;
    private JTextField customerNoTextField;
    private JScrollPane CustomersTableScroll;

    // System operations
    private JPanel systemPanel;
    private JLabel systemLabel;
    private JTable customersTable;
    private JButton loadDataFromFileButton;
    private JButton dumpDataToFileButton;
    private JLabel localStorageField;
    private JPanel employeePanel;
    private JScrollPane EmployeesTableScroll;
    private JTextField employeeNameTextField;
    private JTextField employeeSurnameTextField;
    private JTextField employeeNumberTextField;
    private JButton addNewEmployeeButton;
    private JTextField employeeGenderField;
    private JTextField employeeJobTextField;
    private JTextField employeeNationalityTextField;
    private JTextField employeeBirthdayTextField;
    private JTable employeesTable;
    private JComboBox employeeGenderComboBox;


    private DefaultTableModel customerTableModel;
    private DefaultTableModel employeeTableModel;
//    private DefaultListModel<String> customerListModel;


    // Assuming you have an ArrayList to store customers
//    private static ArrayList<Customer> customers = new ArrayList<>();
//    public static List employees, customers, appointments;
//    public static List employees = customers;
//    private CustomerManager customerManager = new CustomerManager();


    public MainForm() {
        setTitle("Management App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        BorderLayout main_layout = new BorderLayout();
        mainPanel.setLayout(main_layout); // Change layout manager
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        mainPanel.add(navigationPanel, BorderLayout.NORTH);

        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);
        cardPanel.add(customerPanel, "customers");
        cardPanel.add(employeePanel, "employees");
        cardPanel.add(systemPanel, "system");

        mainPanel.add(cardPanel, BorderLayout.CENTER); // Add customerPanel to center


        setContentPane(mainPanel);
        cardLayout.show(cardPanel, "system");


        String[] customersTableColumnNames = {"id", "Number", "Name", "Surname"};
        customerTableModel = new DefaultTableModel(customersTableColumnNames, 0);
        customersTable.setModel(customerTableModel);

        String[] employeesTableColumnNames = {"id", "Number", "Name", "Surname", "Gender", "Job Title", "Nationality", "Birthday"};
        employeeTableModel = new DefaultTableModel(employeesTableColumnNames, 0);
        employeesTable.setModel(employeeTableModel);


//        String[] genderOptions = {"Male", "Female"};
//        employeeGenderComboBox.op;
//        JComboBox<String> employeeGenderComboBox = new JComboBox<>(genderOptions);



        // Navigation Listeners
        systemPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "system");
                systemPageButton.setEnabled(false);
                customersPageButton.setEnabled(true);
                appointmentsPageButton.setEnabled(true);
                employeesPageButton.setEnabled(true);
            }
        });

        customersPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "customers");
                customersPageButton.setEnabled(false);
                systemPageButton.setEnabled(true);
                appointmentsPageButton.setEnabled(true);
                employeesPageButton.setEnabled(true);
            }
        });

        employeesPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "employees");
                customersPageButton.setEnabled(true);
                systemPageButton.setEnabled(true);
                appointmentsPageButton.setEnabled(true);
                employeesPageButton.setEnabled(false);
            }
        });
        appointmentsPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

                int customer_id = CustomerManager.getCustomers().size() + 1;
                CustomerManager.add_customer(customer_id, customerNo, customerName, customerSurname); // Call your add_customer function
                Object[] data = {
                        customer_id, customerNo, customerName, customerSurname
                };

                customerTableModel.addRow(data);
                // Clear text fields after adding customer (optional)
                JOptionPane.showMessageDialog(contentPanel, "New customer was added:\n- id: " + customer_id + "\n- Number: " + customerNo + "\n- Name: " + customerName + "\n- Surname: " + customerSurname);
                customerNameTextField.setText("");
                customerSurnameTextField.setText("");
                customerNoTextField.setText("");

            }
        });


        // Add new employee button action
        addNewEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String employeeName = employeeNameTextField.getText();
                String employeeSurname = employeeSurnameTextField.getText();
                String employeeNo = employeeNumberTextField.getText();
//                String employeeGender = employeeGenderField.getText();
                String employeeGender = Objects.requireNonNull(employeeGenderComboBox.getSelectedItem()).toString();
                String employeeJob = employeeJobTextField.getText();
                String employeeBirthday = employeeBirthdayTextField.getText();
                String employeeNationality = employeeNationalityTextField.getText();


                // Add validation if needed (e.g., check if any field is empty)

                int employee_id = EmployeesManager.getEmployees().size() + 1;
                EmployeesManager.add_employee(
                        employee_id, employeeNo, employeeName, employeeSurname,
                        employeeGender, employeeJob, employeeNationality, employeeBirthday);
                Object[] data = {
                        employee_id, employeeNo, employeeName, employeeSurname,
                        employeeGender, employeeJob, employeeBirthday, employeeNationality
                };

                employeeTableModel.addRow(data);
                JOptionPane.showMessageDialog(contentPanel, "New employee was added!");
                employeeNameTextField.setText("");
                employeeSurnameTextField.setText("");
                employeeNumberTextField.setText("");
//                employeeGenderField.setText("");
                employeeJobTextField.setText("");
                employeeBirthdayTextField.setText("");
                employeeNationalityTextField.setText("");

            }
        });


        // System page actions
        dumpDataToFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerManager.backup_customers();
                EmployeesManager.backup_employees();
                JOptionPane.showMessageDialog(null, "Data was saved successfully!");

            }
        });
        loadDataFromFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerTableModel.setRowCount(0); // Clears existing rows
                employeeTableModel.setRowCount(0);

                CustomerManager.retrieve_customers();
                for (Customer customer : CustomerManager.getCustomers()) {
                    Object[] data = {
                            customer.getCustomer_id(),
                            customer.getCustomer_no(),
                            customer.getCustomer_name(),
                            customer.getCustomer_surname()
                    };
                    customerTableModel.addRow(data);
                }
                // Repeat the same for employees
                EmployeesManager.retrieve_employees();
                for (Employee employee : EmployeesManager.getEmployees()) {
                    Object[] data = {
                            employee.getEmp_id(),
                            employee.getEmp_no(),
                            employee.getEmp_name(),
                            employee.getEmp_surname(),
                            employee.getGender(),
                            employee.getJobTitle(),
                            employee.getBirthday(),
                            employee.getNationality()
                    };
                    employeeTableModel.addRow(data);
                }
                JOptionPane.showMessageDialog(contentPanel, "Data was loaded successfully!");

            }
        });
    }


    public static void main(String[] args) {
        new MainForm();
    }
}
