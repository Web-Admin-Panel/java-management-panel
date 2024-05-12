package cleaning_service.manager;

import cleaning_service.Customer;
import cleaning_service.Employee;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class CustomerManager {
//    public static List customers;
    private static List<Customer> customers;

    static {
        customers = new ArrayList<>(); // Initialize the list for all instances
    }

    public static List<Customer> getCustomers(){
        return customers;
    }

    public static Customer getCustomerByNum(int customer_no){
//        Customer customer=null;
//        int listCustomerNo;
//        Iterator<Customer> itr = customers.iterator();
//        System.out.print("Checking customers:\n");
//        while (itr.hasNext()) {
//            customer = itr.next();
//            listCustomerNo = Integer.parseInt(customer.getCustomer_no());
//            System.out.print("Comparing: " + customer_no + " end " + listCustomerNo + "\n");
//            if(customer_no == listCustomerNo) {
//                System.out.print("Found!\n");
//                break;
//            }
//        }
//        return customer;  // return either found item or Null
        int listCustomerNo;
        String StrCustomerNo;
        System.out.print("Checking customers: \n");
        for (Customer customer: customers)
        {
            StrCustomerNo = customer.getCustomer_no();
            if (StrCustomerNo.isEmpty())
                break;
            listCustomerNo = Integer.parseInt(StrCustomerNo);
            System.out.print("Comparing: " + customer_no + " end " + listCustomerNo + "\n");
            if(customer_no == listCustomerNo) {
                System.out.print("Found!\n");
                return customer;
            }
        }
        return null;
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

    public static void edit_customer(int customer_id, String customer_no, String customer_name,
                                     String customer_surname) {
        Customer st=null;
        Boolean found=false;
        Iterator<Customer> itr = customers.iterator();
        while (itr.hasNext()) {
            st = itr.next();
            if(customer_id==st.getCustomer_id()) {
                found=true;
                break;
            }
        }
        if (found) {
            st.setCustomer_no(customer_no);
            st.setCustomer_name(customer_name);
            st.setCustomer_surname(customer_surname);
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
