package cleaning_service.manager;

import cleaning_service.Customer;
import cleaning_service.Employee;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EmployeesManager {
    private static List<Employee> employees;

    static {
        employees = new ArrayList<>(); // Initialize the list for all instances
    }

    public static List<Employee> getEmployees(){
        return employees;
    }

    public static Employee getEmployee(int index){
        return employees.get(index);
    }

    public static void retrieve_employees()
    {
        try {
            File infile  = new File("employees.dat");
            FileInputStream infilestream = new FileInputStream(infile);
            ObjectInputStream inObjectStream = new ObjectInputStream(infilestream);
            employees = (ArrayList)inObjectStream.readObject();
            inObjectStream.close();
        }
        catch (IOException | ClassNotFoundException ignored){
        }
    }
    public static boolean backup_employees()
    {
        try {
            File outfile = new File("employees.dat");
            FileOutputStream outfilestream = new FileOutputStream(outfile);
            ObjectOutputStream outObjectStream = new ObjectOutputStream(outfilestream);

            outObjectStream.writeObject(employees);
            outObjectStream.close();
            return true;
        }
        catch (IOException e){
            return false;
        }

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
