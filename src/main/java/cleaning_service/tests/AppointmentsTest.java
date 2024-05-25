package cleaning_service.tests;

import cleaning_service.Employee;
import cleaning_service.Customer;
import cleaning_service.Appointment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;
import java.util.Random;


public class AppointmentsTest {
    public static List employees, customers, appointments;

    public static void main(String[] args) {
        employees = new ArrayList();
        customers = new ArrayList();
        appointments = new ArrayList();


        test_appointments();
        System.out.print("\n");
    }

    public static void test_appointments() {
        try {
            retrieve_employees();
            retrieve_customers();
        } catch (IOException | ClassNotFoundException ignored) {
        }

        try {
            System.out.print("\n Running Tests for Class Appointment...\n\n");
            int numPairs = Math.min(employees.size(), customers.size());

            Random random = new Random();
            Collections.shuffle(employees, random);
            Collections.shuffle(customers, random);

            for (int i = 0; i < numPairs; i++) {
                Employee employee = (Employee) employees.get(i);
                Customer customer = (Customer) customers.get(i);

                String randomAddress = generateRandomAddress();
                String randomTime = generateRandomTime();
                String randomDate = generateRandomDate();
                add_appointment(i, customer.getCustomer_id(), employee.getEmp_id(), randomAddress, randomDate, randomTime);
            }

            System.out.print("\n Add_appointment()\n\n");


            System.out.print("\n List_appointment()\n\n");
            list_appointments();
            System.out.print("\n Edit_appointment()\n\n");
            edit_appointment(2, 1, 2, "Famagusta", "6.05.2024", "23:59");
            System.out.print("\n List_appointment()\n\n");
            list_appointments();
            backup_appointments();

            System.out.print("\n Delete_appointment(4)\n\n");
            delete_appointment(4);

            System.out.print("\n List_appointment()\n\n");
            list_appointments();

            retrieve_appointments();
            System.out.print("\n List_appointment()\n\n");
            list_appointments();
        }
        catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString() + e.getMessage());
        }
    }

    public static void retrieve_appointments() throws IOException, ClassNotFoundException
    {
        File infile  = new File("appointments.dat");
        FileInputStream infilestream = new FileInputStream(infile);
        ObjectInputStream inObjectStream = new ObjectInputStream(infilestream);
        appointments = (ArrayList)inObjectStream.readObject();

        inObjectStream.close();

    }
    public static void backup_appointments() throws IOException
    {
        File outfile  = new File("appointments.dat");
        FileOutputStream outfilestream = new FileOutputStream(outfile);
        ObjectOutputStream outObjectStream = new ObjectOutputStream(outfilestream);

        outObjectStream.writeObject(appointments);
        outObjectStream.close();

    }

    public static void add_appointment(int appointment_id, int customer_id, int employee_id, String address, String date, String time) {
        Appointment apptmnt = new Appointment(appointment_id, customer_id, employee_id, address, date, time);
        appointments.add(apptmnt);
    }

    public static void edit_appointment(int appointment_id, int customer_id, int employee_id, String address, String date, String time) {
        Appointment apptmnt=null;
        Boolean found=false;
        Iterator<Appointment> itr = appointments.iterator();
        while (itr.hasNext()) {
            apptmnt = itr.next();
            if(appointment_id==apptmnt.getAppointment_id()) {
                found=true;
                break;
            }
        }
        if (found) {
            apptmnt.setAppointment_id(appointment_id);
            apptmnt.setCustomer_id(customer_id);
            apptmnt.setEmployee_id(employee_id);
            apptmnt.setAddress(address);
            apptmnt.setDate(date);
            apptmnt.setTime(time);
        }
    }

    public static void delete_appointment(int emp_id) {
        Appointment apptmnt=null;
        Boolean found=false;
        Iterator <Appointment> itr = appointments.iterator();
        while (itr.hasNext()) {
            apptmnt = itr.next();
            if(emp_id==(apptmnt.getAppointment_id())) {
                found=true;
                break;
            }
        }
        if (found) appointments.remove(apptmnt);
    }

    public static void draw_line(int num) {
        String ln="";
        for (int i=0; i<num; i++) ln+="=";
        System.out.print("\n"+ln);
    }

    public static void list_appointments() {
        Appointment apptmnt;
        Iterator <Appointment> itr = appointments.iterator();
        System.out.printf("\n%15s %15s %15s %20s %12s %12s",
                "Appointment Id","Customer Id", "Employee Id",
                "Address", "Date", "Time"
                );
        draw_line(100);

        while (itr.hasNext()) {
            apptmnt = itr.next();


            System.out.printf("\n%15s %15s %15s %20s %12s %12s",
                    apptmnt.getAppointment_id(),
                    apptmnt.getCustomer_id(),
                    apptmnt.getEmployee_id(),
                    apptmnt.getAddress(),
                    apptmnt.getDate(),
                    apptmnt.getTime()
            );
        }
        draw_line(100);

    }

    private static String generateRandomAddress() {
        return "Famagusta";
    }

    private static String generateRandomTime() {
        Random random = new Random();
        int hour = random.nextInt(24); // Generate hour between 0-23
        int minute = random.nextInt(60); // Generate minute between 0-59

        // Format time string (adjust format as needed)
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        return timeFormat.format(Calendar.getInstance().getTime());
    }

    private static String generateRandomDate() {
        Random random = new Random();
        int day = random.nextInt(28) + 1;  // Generate day between 1-28 (adjust for leap years)
        int month = random.nextInt(12) + 1; // Generate month between 1-12

        // Format date string (adjust format as needed)
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month - 1); // Month is 0-indexed
        return dateFormat.format(calendar.getTime());
    }

    public static void retrieve_employees() throws IOException, ClassNotFoundException
    {
        File infile  = new File("employees.dat");
        FileInputStream infilestream = new FileInputStream(infile);
        ObjectInputStream inObjectStream = new ObjectInputStream(infilestream);
        employees = (ArrayList)inObjectStream.readObject();

        inObjectStream.close();

    }

    public static void retrieve_customers() throws IOException, ClassNotFoundException
    {
        File infile  = new File("customers.dat");
        FileInputStream infilestream = new FileInputStream(infile);
        ObjectInputStream inObjectStream = new ObjectInputStream(infilestream);
        customers = (ArrayList)inObjectStream.readObject();

        inObjectStream.close();

    }
}
