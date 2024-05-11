package cleaning_service.manager;

import cleaning_service.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomerManager {
//    public static List customers;
    private static List<Customer> customers;

    static {
        customers = new ArrayList<>(); // Initialize the list for all instances
    }

    public static List<Customer> getCustomers(){
        return customers;
    }

    public static void retrieve_customers()
    {
        try {
            File infile  = new File("customers.dat");
            FileInputStream infilestream = new FileInputStream(infile);
            ObjectInputStream inObjectStream = new ObjectInputStream(infilestream);
            customers = (ArrayList)inObjectStream.readObject();
            inObjectStream.close();
        }
        catch (IOException | ClassNotFoundException ignored){
        }


    }
    public static boolean backup_customers()
    {
        try {
            File outfile = new File("customers.dat");
            FileOutputStream outfilestream = new FileOutputStream(outfile);
            ObjectOutputStream outObjectStream = new ObjectOutputStream(outfilestream);

            outObjectStream.writeObject(customers);
            outObjectStream.close();
            return true;
        }
        catch (IOException e){
            return false;
        }

    }

    public static void add_customer(int customer_id, String customer_no, String customer_name, String customer_surname) {
        Customer st =new Customer(customer_id, customer_no, customer_name, customer_surname);
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
