package cleaning_service.manager;

import cleaning_service.Employee;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EmployeesManager {
    public static List employees;
//  public static List employees, grades, departments, courses, attendance;

    public static void main(String[] args) {
        employees = new ArrayList();

        test_employees();

        System.out.print("\n");
    }

    public static void test_employees() {
        try {
            System.out.print("\n Running Tests for Class Employee...\n\n");
            try {
                System.out.print("\n Try to load the employees from the file...\n\n");
                retrieve_employees();
                System.out.print("\n List_employee() after loading from the file\n\n");
            }
            catch (FileNotFoundException | InvalidClassException e) {
                System.out.print("\n Unable to read the file, adding new employees...\n\n");

                add_employee(1, "116229", "Anatolii", "Dubinka", "Male", "The boss","Turkey", "18/03/2022");
                add_employee(2, "186731", "Ayse", "Kemaller", "Female", "Manager","Cyprus", "28/09/1998");
                add_employee(3, "168337", "Muhammad", "Fahrad", "Male", "Cleaner","Iran", "30/05/1996");
                add_employee(4, "189222", "Fatima", "Reshad", "Female", "Cleaner","Syria", "22/07/1998");
                add_employee(5, "199221", "Bahar", "Tunc", "Female", "Cashier","Cyprus", "27/08/2019");
                System.out.print("\n Add_employee()\n\n");
            }



            System.out.print("\n List_employee()\n\n");
            list_employees();
            System.out.print("\n Edit_employee()\n\n");
            edit_employee(2, "186731", "Ayse", "Kemaller", "Female", "Top manager","Turkey", "28/09/1998");
            System.out.print("\n List_employee()\n\n");
            list_employees();
            backup_employees();

            System.out.print("\n Delete_employee(4)\n\n");
            delete_employee(4);

            System.out.print("\n List_employee()\n\n");
            list_employees();

            retrieve_employees();
            System.out.print("\n List_employee()\n\n");
            list_employees();
        }
        catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString() + e.getMessage());
        }
    }

    public static void retrieve_employees() throws IOException, ClassNotFoundException
    {
        File infile  = new File("employees.dat");
        FileInputStream infilestream = new FileInputStream(infile);
        ObjectInputStream inObjectStream = new ObjectInputStream(infilestream);
        employees = (ArrayList)inObjectStream.readObject();

        inObjectStream.close();

    }
    public static void backup_employees() throws IOException
    {
        File outfile  = new File("employees.dat");
        FileOutputStream outfilestream = new FileOutputStream(outfile);
        ObjectOutputStream outObjectStream = new ObjectOutputStream(outfilestream);

        outObjectStream.writeObject(employees);
        outObjectStream.close();

    }

    public static void add_employee(int emp_id, String emp_no, String emp_name, String emp_surname,
                                    String gender, String job_title, String nationality, String birthday) {
        Employee st =new Employee(emp_id, emp_no, emp_name, emp_surname,
                gender, job_title, nationality, birthday);
        employees.add(st);
    }

    public static void edit_employee(int emp_id, String emp_no, String emp_name,
                                     String emp_surname, String gender, String job_title,
                                     String nationality, String birthday) {
        Employee st=null;
        Boolean found=false;
        Iterator<Employee> itr = employees.iterator();
        while (itr.hasNext()) {
            st = itr.next();
            if(emp_id==st.getEmp_id()) {
                found=true;
                break;
            }
        }
        if (found) {
            st.setEmp_no(emp_no);
            st.setEmp_name(emp_name);
            st.setEmp_surname(emp_surname);
            st.setNationality(nationality);
            st.setGender(gender);
            st.setJobTitle(job_title);
            st.setBirthday(birthday);
        }
    }

    public static void delete_employee(int emp_id) {
        Employee st=null;
        Boolean found=false;
        Iterator <Employee> itr = employees.iterator();
        while (itr.hasNext()) {
            st = itr.next();
            if(emp_id==(st.getEmp_id())) {
                found=true;
                break;
            }
        }
        if (found) employees.remove(st);
    }

    public static void draw_line(int num) {
        String ln="";
        for (int i=0; i<num; i++) ln+="=";
        System.out.print("\n"+ln);
    }

    public static void list_employees() {
        Employee st;
        Iterator <Employee> itr = employees.iterator();
        System.out.printf("\n%2s %10s %15s %15s %10s %12s %12s",
                "Id", "Employee No","Emp. Name", "Emp. Surname",
                "Job Title",
                "Gender","Nationality", "Birthday");
        draw_line(79);

        while (itr.hasNext()) {
            st = itr.next();


            System.out.printf("\n%2d %10s %15s %15s %10s %12s %12s",
                    st.getEmp_id(), st.getEmp_no(),
                    st.getEmp_name(), st.getEmp_surname(), st.getJobTitle(),
                    st.getGender(), st.getNationality(), st.getBirthday());
        }
        draw_line(79);

    }
}
