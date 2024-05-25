package cleaning_service.manager;

import cleaning_service.Customer;
import cleaning_service.Employee;
import cleaning_service.util.DBConnection;

import javax.swing.*;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import cleaning_service.util.DBConnection;
import cleaning_service.Employee;

import java.sql.*;

public class EmployeesManager {

    // Retrieve all employees from the database
    public static List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM Employee";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt("emp_id"),
                        rs.getString("emp_no"),
                        rs.getString("emp_name"),
                        rs.getString("emp_surname"),
                        rs.getString("gender"),
                        rs.getString("job_title"),
                        rs.getString("nationality"),
                        rs.getString("birthday")
                );
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public static Employee getEmployee(int index) {
        List<Employee> employees = getEmployees();
        return employees.get(index);
    }

    public static Employee getEmployeeByNum(int employee_no) {
        Employee employee = null;
        String query = "SELECT * FROM Employee WHERE emp_no = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, employee_no);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    employee = new Employee(
                            rs.getInt("emp_id"),
                            rs.getString("emp_no"),
                            rs.getString("emp_name"),
                            rs.getString("emp_surname"),
                            rs.getString("gender"),
                            rs.getString("job_title"),
                            rs.getString("nationality"),
                            rs.getString("birthday")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public static void add_employee(int emp_id, String emp_no, String emp_name, String emp_surname, String gender, String job_title, String nationality, String birthday) {
        String query = "INSERT INTO Employee (emp_id, emp_no, emp_name, emp_surname, gender, job_title, nationality, birthday) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, emp_id);
            pstmt.setString(2, emp_no);
            pstmt.setString(3, emp_name);
            pstmt.setString(4, emp_surname);
            pstmt.setString(5, gender);
            pstmt.setString(6, job_title);
            pstmt.setString(7, nationality);
            pstmt.setString(8, birthday);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void edit_employee(int emp_id, String emp_no, String emp_name, String emp_surname, String gender, String job_title, String nationality, String birthday) {
        String query = "UPDATE Employee SET emp_no = ?, emp_name = ?, emp_surname = ?, gender = ?, job_title = ?, nationality = ?, birthday = ? WHERE emp_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, emp_no);
            pstmt.setString(2, emp_name);
            pstmt.setString(3, emp_surname);
            pstmt.setString(4, gender);
            pstmt.setString(5, job_title);
            pstmt.setString(6, nationality);
            pstmt.setString(7, birthday);
            pstmt.setInt(8, emp_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete_employee(int emp_id) {
        String query = "DELETE FROM Employee WHERE emp_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, emp_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void draw_line(int num) {
        StringBuilder ln = new StringBuilder();
        for (int i = 0; i < num; i++) ln.append("=");
        System.out.print("\n" + ln);
    }

    public static void list_employees() {
        List<Employee> employees = getEmployees();
        System.out.printf("\n%2s %10s %15s %15s %10s %12s %12s %12s",
                "Id", "Employee No", "Emp. Name", "Emp. Surname",
                "Job Title", "Gender", "Nationality", "Birthday");
        draw_line(95);

        for (Employee st : employees) {
            System.out.printf("\n%2d %10s %15s %15s %10s %12s %12s %12s",
                    st.getEmp_id(), st.getEmp_no(),
                    st.getEmp_name(), st.getEmp_surname(), st.getJobTitle(),
                    st.getGender(), st.getNationality(), st.getBirthday());
        }
        draw_line(95);
    }
}

