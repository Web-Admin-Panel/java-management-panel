package cleaning_service;
import cleaning_service.manager.*;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingUtilities;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import javax.swing.table.TableCellRenderer;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;


import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;


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
    private JPanel appointmentPanel;
    private JTable appointmentsTable;
    private JScrollPane appointmentsTableScroll;
    private DatePicker appointmentDatePicker;
    private TimePicker appointmentTimePicker;
    private JButton addNewAppointmentButton;
    private JTextField appointmentAddressTextField;
    private JFormattedTextField appointmentEmpNoTextField;
    private JFormattedTextField appointmentCustomerNoTextField;


    private DefaultTableModel customerTableModel;
    private DefaultTableModel employeeTableModel;
    private DefaultTableModel appointmentsTableModel;
    private EmployeeCellEditorListener employeeTableListener;
    private CustomerCellEditorListener customerTableListener;
    private AppointmentCellEditorListener appointmentTableListener;
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

        // Set up integer input validation
        employeeNumberTextField.setFormatterFactory(new DefaultFormatterFactory(formatter));
        customerNumberTextField.setFormatterFactory(new DefaultFormatterFactory(formatter));
        appointmentEmpNoTextField.setFormatterFactory(new DefaultFormatterFactory(formatter));
        appointmentCustomerNoTextField.setFormatterFactory(new DefaultFormatterFactory(formatter));
        employeeBDayDatePicker.setDateToToday();



        BorderLayout main_layout = new BorderLayout();
        mainPanel.setLayout(main_layout); // Change layout manager
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        mainPanel.add(navigationPanel, BorderLayout.NORTH);

        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);
        cardPanel.add(customerPanel, "customers");
        cardPanel.add(employeePanel, "employees");
        cardPanel.add(appointmentPanel, "appointments");
        cardPanel.add(systemPanel, "system");

        mainPanel.add(cardPanel, BorderLayout.CENTER); // Add customerPanel to center

        setContentPane(mainPanel);
        cardLayout.show(cardPanel, "system");


        String[] customersTableColumnNames = {"id", "Number", "Name", "Surname", "Operations"};
        customerTableModel = new DefaultTableModel(customersTableColumnNames, 0);
        customersTable.setModel(customerTableModel);

        // Event listeners
        customerTableListener = new CustomerCellEditorListener();
        customerTableModel.addTableModelListener(customerTableListener);  // To handle data changes
        customersTable.addMouseListener(customerTableListener);  // To delete row on button click
        customersTable.getColumnModel().getColumn(customerTableModel.getColumnCount() - 1).setCellRenderer(new DeleteButtonRenderer());


        String[] employeesTableColumnNames = {"id", "Number", "Name", "Surname", "Gender", "Job Title", "Birthday", "Nationality"};
        employeeTableModel = new DefaultTableModel(employeesTableColumnNames, 0);
        employeesTable.setModel(employeeTableModel);
        employeeTableListener = new EmployeeCellEditorListener();
        employeeTableModel.addTableModelListener(employeeTableListener);

        String[] appointmentTableColumnNames = {"Appointment Id", "Employee No", "Customer No", "Address", "Date", "Time"};
        appointmentsTableModel = new DefaultTableModel(appointmentTableColumnNames, 0);
        appointmentsTable.setModel(appointmentsTableModel);




// Add action listener to capture button clicks (assuming your listener class handles it)

//        customersTable.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (SwingUtilities.isRightMouseButton(e)) { // Check for right-click on button
//                    int row = customersTable.rowAtPoint(e.getPoint());
//                    if (row != -1) { // Ensure a valid row is clicked
//                        Point clickPoint = e.getPoint();
//                        int clickColumn = customersTable.columnAtPoint(clickPoint);
//                        // Check if the click is within the "delete" button column
//                        if (clickColumn == customerTableModel.getColumnCount() - 1) { // Assuming "delete" is the last column
//                            CustomerCellEditorListener listener = new CustomerCellEditorListener();
//                            listener.handleDeleteButtonClick(row);
//                        }
//                    }
//                }
//            }
//        });

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
                cardLayout.show(cardPanel, "appointments");
                customersPageButton.setEnabled(true);
                systemPageButton.setEnabled(true);
                appointmentsPageButton.setEnabled(false);
                employeesPageButton.setEnabled(true);
            }
        });



        // Add new customer button action
        addNewCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String customerName = customerNameTextField.getText();
                String customerSurname = customerSurnameTextField.getText();
                String customerNo = customerNumberTextField.getValue().toString(); // Assuming customer has a no field

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
                String employeeNo = employeeNumberTextField.getValue().toString();
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



        // Add new appointment button action
        addNewAppointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int employeeNo = (int) appointmentEmpNoTextField.getValue();
                int customerNo = (int) appointmentCustomerNoTextField.getValue();

                LocalDate selectedDate = appointmentDatePicker.getDate();
                String appointmentDate;
                if (selectedDate != null)
                    appointmentDate = selectedDate.toString();
                else
                    appointmentDate = "";

                LocalTime selectedTime = appointmentTimePicker.getTime();
                String appointmentTime;
                if (selectedTime != null)
                    appointmentTime = selectedTime.toString();
                else
                    appointmentTime = "";


                String address = appointmentAddressTextField.getText();



                boolean allValid = true;

                for (String field : new String[]{appointmentDate, appointmentTime}) {
                    if (field == null || field.isEmpty()) {
                        allValid = false;
                        JOptionPane.showMessageDialog(contentPanel, "Some fields are empty! Transaction is terminated.");
                        break;
                    }
                }
                if (!allValid) {
                    return;
                }
                Customer customer = CustomerManager.getCustomerByNum(customerNo);
                Employee employee = EmployeesManager.getEmployeeByNum(employeeNo);
                if (customer == null){
                    JOptionPane.showMessageDialog(contentPanel, "Invalid data! The Customer with such a Number wasn't found! Check you input.");
                    return;
                }
                else if (employee == null){
                    JOptionPane.showMessageDialog(contentPanel, "Invalid data! The Employee with such a Number wasn't found! Check you input.");
                    return;
                }
                int cusId = customer.getCustomer_id();
                int empId = employee.getEmp_id();


                int appointment_id = AppointmentsManager.getAppointments().size() + 1;

                AppointmentsManager.add_appointment(
                        appointment_id, empId, cusId, address, appointmentDate, appointmentTime
                );
                Object[] data = {
                        appointment_id, employeeNo, customerNo, address, appointmentDate, appointmentTime
                };
                appointmentsTableModel.addRow(data);
                JOptionPane.showMessageDialog(contentPanel, "New appointment was added!");
                appointmentEmpNoTextField.setText("");
                appointmentCustomerNoTextField.setText("");
                appointmentAddressTextField.setText("");

            }
        });


        // System page actions
        dumpDataToFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CustomerManager.backup_customers() && EmployeesManager.backup_employees() && AppointmentsManager.backup_appointments())
                    JOptionPane.showMessageDialog(contentPanel, "Data was saved successfully!");
                else
                    JOptionPane.showMessageDialog(contentPanel, "There was an error while dumping the data!");
            }
        });
        loadDataFromFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerTableModel.setRowCount(0); // Clears existing rows
                employeeTableModel.setRowCount(0);
                appointmentsTableModel.setRowCount(0);

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
                // And for appointments
                AppointmentsManager.retrieve_appointments();
                for (Appointment appointment : AppointmentsManager.getAppointments()) {
                    Object[] data = {
                            appointment.getAppointment_id(),
                            appointment.getEmployee_id(),
                            appointment.getCustomer_id(),
                            appointment.getAddress(),
                            appointment.getDate(),
                            appointment.getTime()
                    };
                    appointmentsTableModel.addRow(data);
                }
                JOptionPane.showMessageDialog(contentPanel, "Data was loaded successfully!");

            }
        });
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

    public class CustomerCellEditorListener implements CellEditorListener, TableModelListener, MouseListener {
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
        @Override
        public void mouseClicked(MouseEvent e) {
            if (!SwingUtilities.isLeftMouseButton(e))
                return;
            Point clickPoint = e.getPoint();
            int column = customersTable.columnAtPoint(clickPoint);
            if (column != customersTable.getColumnCount() - 1)
                return;
            int confirmation = JOptionPane.showConfirmDialog(contentPanel, "Are you sure you want to delete this row?", "Warning", JOptionPane.YES_NO_OPTION);
            if (confirmation == 0) {  // Means yes
                int row = customersTable.rowAtPoint(clickPoint);
                int customerId = (int) customerTableModel.getValueAt(row, 0);
                String customerNo = (String) customerTableModel.getValueAt(row, 1);
                CustomerManager.delete_customer(customerId);
                customerTableModel.removeRow(row);
                JOptionPane.showMessageDialog(contentPanel, "Customer with Number " + customerNo + " was deleted successfully!");
            }

        }

        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
    }

    public class AppointmentCellEditorListener implements CellEditorListener, TableModelListener {
        @Override
        public void editingStopped(ChangeEvent e) {
        }
        @Override
        public void editingCanceled(ChangeEvent e) {
        }
        @Override
        public void tableChanged(TableModelEvent e) {
            return;
        }

    }

    public class DeleteButtonRenderer extends JButton implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {

            setText("Delete"); // Set button text
//            setBackground(Color.RED); // Set background color
            setForeground(Color.BLACK); // Set text color
            setFont(getFont().deriveFont(Font.BOLD)); // Set bold font
            setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Set padding
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            return this;
        }
    }


}



