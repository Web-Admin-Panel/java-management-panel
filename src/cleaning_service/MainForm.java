package cleaning_service;
import cleaning_service.manager.*;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.Objects;

import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;


import com.github.lgooddatepicker.components.DatePicker;


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
    private JFormattedTextField customerNumberTextField;
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
    private JButton addNewEmployeeButton;
    private JTextField employeeJobTextField;
    private JTextField employeeNationalityTextField;
    private JTextField employeeBirthdayTextField;
    private JTable employeesTable;
    private JComboBox employeeGenderComboBox;
    private JFormattedTextField employeeNumberTextField;
    private DatePicker employeeBDayDatePicker;


    private DefaultTableModel customerTableModel;
    private DefaultTableModel employeeTableModel;
    private EmployeeCellEditorListener employeeTableListener;
    private CustomerCellEditorListener customerTableListener;
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

        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);

        formatter.setValueClass(Integer.class);
//        formatter.setMinimum(0); // Optional: sets the minimum value to 0
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        // formatter.setCommitsOnValidEdit(true); // Optional: if true, makes ValueEvent be fired with each valid edit

//        JFormattedTextField employeeNumberTextField = new JFormattedTextField(formatter);
        employeeNumberTextField.setFormatterFactory(new DefaultFormatterFactory(formatter));
        customerNumberTextField.setFormatterFactory(new DefaultFormatterFactory(formatter));
        employeeBDayDatePicker.setDateToToday();



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

        String[] employeesTableColumnNames = {"id", "Number", "Name", "Surname", "Gender", "Job Title", "Birthday", "Nationality"};
        employeeTableModel = new DefaultTableModel(employeesTableColumnNames, 0);
        employeesTable.setModel(employeeTableModel);
        employeeTableListener = new EmployeeCellEditorListener();
        employeeTableModel.addTableModelListener(employeeTableListener);

        customerTableListener = new CustomerCellEditorListener();
        customerTableModel.addTableModelListener(customerTableListener);



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
                String customerNo = customerNumberTextField.getText(); // Assuming customer has a no field

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
                customerNumberTextField.setText("");

            }
        });


        // Add new employee button action
        addNewEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String employeeName = employeeNameTextField.getText();
                String employeeSurname = employeeSurnameTextField.getText();
                String employeeNo = employeeNumberTextField.getText();
                String employeeGender = Objects.requireNonNull(employeeGenderComboBox.getSelectedItem()).toString();
                String employeeJob = employeeJobTextField.getText();
                String employeeBirthday;
                LocalDate selectedDate = employeeBDayDatePicker.getDate();
                employeeBirthday = selectedDate.toString();
                String employeeNationality = employeeNationalityTextField.getText();


                boolean allValid = true;

                for (String field : new String[]{employeeName, employeeSurname, employeeNo, employeeJob, employeeNationality}) {
                    if (field == null || field.isEmpty()) {
                        allValid = false;
                        JOptionPane.showMessageDialog(contentPanel, "Some fields are empty! Transaction is terminated.");
                        break;
                    }
                }

                if (!allValid) {
                    return;
                }
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
//                employeeBirthdayTextField.setText("");
                employeeBDayDatePicker.setDateToToday();
                employeeNationalityTextField.setText("");

            }
        });

        // Edit employee table data



        // System page actions
        dumpDataToFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerManager.backup_customers();
                EmployeesManager.backup_employees();
                JOptionPane.showMessageDialog(contentPanel, "Data was saved successfully!");

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
//        employeesTable.addInputMethodListener(new InputMethodListener() {
//            @Override
//            public void inputMethodTextChanged(InputMethodEvent event) {
//                System.out.println("AAAAAAAAAAaaaaaa");
//            }
//
//            @Override
//            public void caretPositionChanged(InputMethodEvent event) {
//
//            }
//        });
    }


    public static void main(String[] args) {
        new MainForm();
    }




    public class EmployeeCellEditorListener implements CellEditorListener, TableModelListener {
        @Override
        public void editingStopped(ChangeEvent e) {
        }
        @Override
        public void editingCanceled(ChangeEvent e) {
        }
        @Override
        public void tableChanged(TableModelEvent e) {
            if (e.getType() == TableModelEvent.UPDATE) { // Data was changed in the table
                int row = e.getFirstRow();
                JTable table = employeesTable;

                int empId = (int) table.getModel().getValueAt(row, 0);
                String empNo = (String) table.getModel().getValueAt(row, 1);
                String empName = (String) table.getModel().getValueAt(row, 2);
                String empSurname = (String) table.getModel().getValueAt(row, 3);
                String empGender = (String) table.getModel().getValueAt(row, 4);
                String empJobTitle = (String) table.getModel().getValueAt(row, 5);
                String empNationality = (String) table.getModel().getValueAt(row, 6);
                String empBirthday = (String) table.getModel().getValueAt(row, 7);


                System.out.print("\n" + empId + " " + empNo + " " + empName + " " + empSurname + " " + empGender + " " + empJobTitle + " " + empBirthday + " " + empNationality + "\n");
                EmployeesManager.edit_employee(empId, empNo, empName, empSurname, empGender, empJobTitle, empBirthday, empNationality);

            }


        }
    }

    public class CustomerCellEditorListener implements CellEditorListener, TableModelListener {
        @Override
        public void editingStopped(ChangeEvent e) {
        }
        @Override
        public void editingCanceled(ChangeEvent e) {
        }
        @Override
        public void tableChanged(TableModelEvent e) {
            if (e.getType() == TableModelEvent.UPDATE) { // Data was changed in the table
                int row = e.getFirstRow();
                JTable table = customersTable;

                int customerId = (int) table.getModel().getValueAt(row, 0);
                String customerNo = (String) table.getModel().getValueAt(row, 1);
                String customerName = (String) table.getModel().getValueAt(row, 2);
                String customerSurname = (String) table.getModel().getValueAt(row, 3);


                System.out.print("\n" + customerId + " " + customerNo + " " + customerName + " " + customerSurname + "\n");
                CustomerManager.edit_customer(customerId, customerNo, customerName, customerSurname);

            }


        }
    }
}



