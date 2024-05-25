package cleaning_service.tests;

import cleaning_service.Customer;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomerTest {
    public static List customers;
//  public static List customers, grades, departments, courses, attendance;

    public static void main(String[] args) {
        customers = new ArrayList();

        test_customers();

        System.out.print("\n");
    }

    public static void test_customers() {
        try {
            System.out.print("\n Running Tests for Class Customer...\n\n");
            try {
                System.out.print("\n Try to load the customers from the file...\n\n");
                retrieve_customers();
                System.out.print("\n List_customer() after loading from the file\n\n");
            }
            catch (FileNotFoundException | InvalidClassException e) {
                System.out.print("\n Unable to read the file, adding new customers...\n\n");

                add_customer(1, "116229", "Anatolii", "Faisal");
                add_customer(2, "186731", "Anastasia", "Kemaller");
                add_customer(3, "168337", "Kate", "Fahrad");
                add_customer(4, "189222", "Jacob", "Reshad");
                add_customer(5, "199221", "Kirill", "Tunc");
                System.out.print("\n Add_customer()\n\n");
            }



            System.out.print("\n List_customer()\n\n");
            list_customers();
            System.out.print("\n Edit_customer()\n\n");
            edit_customer(2, "186731", "Ayse", "Kemaller", "Female", "Turkey", "28/09/1998");
            System.out.print("\n List_customer()\n\n");
            list_customers();
            backup_customers();

            System.out.print("\n Delete_customer(4)\n\n");
            delete_customer(4);

            System.out.print("\n List_customer()\n\n");
            list_customers();

            retrieve_customers();
            System.out.print("\n List_customer()\n\n");
            list_customers();
        }
        catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    public static void retrieve_customers() throws IOException, ClassNotFoundException
    {
        File infile  = new File("customers.dat");
        FileInputStream infilestream = new FileInputStream(infile);
        ObjectInputStream inObjectStream = new ObjectInputStream(infilestream);
        customers = (ArrayList)inObjectStream.readObject();

        inObjectStream.close();

    }
    public static void backup_customers() throws IOException
    {
        File outfile  = new File("customers.dat");
        FileOutputStream outfilestream = new FileOutputStream(outfile);
        ObjectOutputStream outObjectStream = new ObjectOutputStream(outfilestream);

        outObjectStream.writeObject(customers);
        outObjectStream.close();

    }

    public static void add_customer(int emp_id, String emp_no, String emp_name, String emp_surname) {
        Customer st =new Customer(emp_id, emp_no, emp_name, emp_surname);
        customers.add(st);
    }

    public static void edit_customer(int emp_id, String emp_no, String emp_name,
                                     String emp_surname, String gender,
                                     String nationality, String birthday) {
        Customer st=null;
        Boolean found=false;
        Iterator<Customer> itr = customers.iterator();
        while (itr.hasNext()) {
            st = itr.next();
            if(emp_id==st.getCustomer_id()) {
                found=true;
                break;
            }
        }
        if (found) {
            st.setCustomer_no(emp_no);
            st.setCustomer_name(emp_name);
            st.setCustomer_surname(emp_surname);
        }
    }

    public static void delete_customer(int emp_id) {
        Customer st=null;
        Boolean found=false;
        Iterator <Customer> itr = customers.iterator();
        while (itr.hasNext()) {
            st = itr.next();
            if(emp_id==(st.getCustomer_id())) {
                found=true;
                break;
            }
        }
        if (found) customers.remove(st);
    }

    public static void draw_line(int num) {
        String ln="";
        for (int i=0; i<num; i++) ln+="=";
        System.out.print("\n"+ln);
    }

    public static void list_customers() {
        Customer st;
        Iterator <Customer> itr = customers.iterator();
        System.out.printf("\n%2s %10s %15s %15s",
                "Id", "Customer No","Customer. Name", "Customer. Surname"
        );
        draw_line(49);

        while (itr.hasNext()) {
            st = itr.next();


            System.out.printf("\n%2d %10s %15s %15s",
                    st.getCustomer_id(), st.getCustomer_no(),
                    st.getCustomer_name(), st.getCustomer_surname()
            );
        }
        draw_line(49);

    }
}
