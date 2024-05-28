package cleaning_service.manager;
import cleaning_service.Customer;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import cleaning_service.util.DBConnection;


public class CustomerManager {

    // Method to retrieve all customers from the database
    public static List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM Customer";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("customer_no"),
                        rs.getString("customer_name"),
                        rs.getString("customer_surname")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public static Customer getCustomer(int customer_id) {
        Customer customer = null;
        String query = "SELECT * FROM Customer WHERE customer_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, customer_id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                customer = new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("customer_no"),
                        rs.getString("customer_name"),
                        rs.getString("customer_surname")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    // Method to retrieve a specific customer by number
    public static Customer getCustomerByNum(String customer_no) {
        Customer customer = null;
        String query = "SELECT * FROM Customer WHERE customer_no = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, customer_no);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                customer = new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("customer_no"),
                        rs.getString("customer_name"),
                        rs.getString("customer_surname")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    // Method to add a new customer to the database
    public static void add_customer(String customer_no, String customer_name, String customer_surname) {
        String query = "INSERT INTO Customer (customer_no, customer_name, customer_surname) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, customer_no);
            pstmt.setString(2, customer_name);
            pstmt.setString(3, customer_surname);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update an existing customer
    public static void edit_customer(int customer_id, String customer_no, String customer_name,

                                    String customer_surname) {
        String query = "UPDATE Customer SET customer_no = ?, customer_name = ?, customer_surname = ? WHERE customer_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, customer_no);
            pstmt.setString(2, customer_name);
            pstmt.setString(3, customer_surname);
            pstmt.setInt(4, customer_id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a customer from the database
    public static void delete_customer(int customer_id) throws SQLException {
        String query = "DELETE FROM Customer WHERE customer_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, customer_id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    // Method to print all customers to the console
    public static void list_customers() {
        List<Customer> customers = getCustomers();
        System.out.printf("\n%2s %10s %15s %15s",
                "Id", "Customer No", "Customer Name", "Customer Surname"
        );
        drawLine(49);
        for (Customer customer : customers) {
            System.out.printf("\n%2d %10s %15s %15s",
                    customer.getCustomer_id(), customer.getCustomer_no(),
                    customer.getCustomer_name(), customer.getCustomer_surname()
            );
        }
        drawLine(49);
    }

    public static void drawLine(int num) {
        StringBuilder ln = new StringBuilder();
        ln.append("=".repeat(Math.max(0, num)));
        System.out.print("\n" + ln);
    }
}
