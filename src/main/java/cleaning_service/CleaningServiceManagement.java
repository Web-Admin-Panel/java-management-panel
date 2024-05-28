
package cleaning_service;
import cleaning_service.tests.*;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class CleaningServiceManagement {
//  public static List employees, customers, appointments;

  public static void test(String[] args){
    EmployeesTest.main(null);
    System.out.print("\n\n=======================================================================================\n=======================================================================================\n=======================================================================================");
    CustomerTest.main(null);
    System.out.print("\n\n=======================================================================================\n=======================================================================================\n=======================================================================================");
    AppointmentsTest.main(null);
    System.exit(200);
  }

  public static void main(String[] args){
//    test(null);
    MainForm.main(null);
  }

}
