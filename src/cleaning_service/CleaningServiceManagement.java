
package cleaning_service;
import cleaning_service.tests.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class CleaningServiceManagement {
  public static List employees;
//  public static List employees, grades, departments, courses, attendance;

    public static void main(String[] args){
        // TODO code application logic here

        EmployeesTest.main(null);
        System.out.print("\n\n=======================================================================================\n=======================================================================================\n=======================================================================================");
        CustomerTest.main(null);
        System.out.print("\n\n=======================================================================================\n=======================================================================================\n=======================================================================================");
        AppointmentsTest.main(null);
    }

}
