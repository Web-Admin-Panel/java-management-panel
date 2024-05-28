package cleaning_service.manager;

import cleaning_service.Appointment;

import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import cleaning_service.util.DBConnection;

import cleaning_service.util.DBConnection;
import cleaning_service.Appointment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentsManager {

    // Retrieve all appointments from the database
    public static List<Appointment> getAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT * FROM Appointment";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Appointment appointment = new Appointment(
                        rs.getInt("appointment_id"),
                        rs.getInt("customer_id"),
                        rs.getInt("employee_id"),
                        rs.getString("address"),
                        rs.getString("date"),
                        rs.getString("time")
                );
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    // Retrieve a specific appointment by index
    public static Appointment getAppointment(int index) {
        List<Appointment> appointments = getAppointments();
        return appointments.get(index);
    }

    // Add a new appointment to the database
    public static int add_appointment(int customer_id, int employee_id, String address, String date, String time) {
        String query = "INSERT INTO Appointment (customer_id, employee_id, address, date, time) VALUES (?, ?, ?, ?, ?)";
        int newId = -1; // Default value in case insertion fails

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, customer_id);
            pstmt.setInt(2, employee_id);
            pstmt.setString(3, address);
            pstmt.setString(4, date);
            pstmt.setString(5, time);
            pstmt.executeUpdate();

            // Get the generated keys
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    newId = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newId;
    }


    // Edit an existing appointment in the database
    public static void edit_appointment(int appointment_id, int customer_id, int employee_id, String address, String date, String time) {
        String query = "UPDATE Appointment SET customer_id = ?, employee_id = ?, address = ?, date = ?, time = ? WHERE appointment_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, customer_id);
            pstmt.setInt(2, employee_id);
            pstmt.setString(3, address);
            pstmt.setString(4, date);
            pstmt.setString(5, time);
            pstmt.setInt(6, appointment_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete an appointment from the database
    public static void delete_appointment(int appointment_id) {
        String query = "DELETE FROM Appointment WHERE appointment_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, appointment_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Draw a line for table formatting
    public static void draw_line(int num) {
        StringBuilder ln = new StringBuilder();
        for (int i = 0; i < num; i++) ln.append("=");
        System.out.print("\n" + ln);
    }

    // List all appointments
    public static void list_appointments() {
        List<Appointment> appointments = getAppointments();
        System.out.printf("\n%15s %15s %15s %20s %12s %12s",
                "Appointment Id", "Customer Id", "Employee Id",
                "Address", "Date", "Time");
        draw_line(100);

        for (Appointment apptmnt : appointments) {
            System.out.printf("\n%15d %15d %15d %20s %12s %12s",
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
}
