package cleaning_service;

import java.io.Serializable;

public class Customer implements Serializable{
  private  int customer_id;  
  private  String customer_no;
  private  String customer_name;
  private  String customer_surname;

    public Customer(int customer_id, String customer_no, String customer_name, String customer_surname) {
        this.customer_id = customer_id;
        this.customer_no = customer_no;
        this.customer_name = customer_name;
        this.customer_surname = customer_surname;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
 
    public String getCustomer_no() {
        return customer_no;
    }

    public void setCustomer_no(String customer_no) {
        this.customer_no = customer_no;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_surname() {
        return customer_surname;
    }

    public void setCustomer_surname(String customer_surname) {
        this.customer_surname = customer_surname;
    }

}
