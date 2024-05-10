package cleaning_service;

import java.io.Serializable;

public class Appointment implements Serializable{

    private int appointment_id;
    private  int customer_id;
    private  int employee_id;
    private  String address;
    private  String date;
    private  String time;

    public Appointment(int appointment_id, int customer_id, int employee_id, String address, String date, String time) {
        this.appointment_id = appointment_id;
        this.customer_id = customer_id;
        this.employee_id = employee_id;
        this.address = address;
        this.date = date;
        this.time = time;
    }
    public int getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(int appointment_id) {
        this.appointment_id = appointment_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
