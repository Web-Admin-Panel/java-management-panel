package cleaning_service;

import java.io.Serializable;

public class Employee implements Serializable{
  private  int emp_id;  
  private  String emp_no;
  private  String emp_name;
  private  String emp_surname;
  private  String gender;
  private  String job_title;
  private  String nationality;
  private  String birthday;

    public Employee(int emp_id, String emp_no, String emp_name, String emp_surname, String gender, String job_title, String nationality, String birthday) {
        this.emp_id = emp_id;
        this.emp_no = emp_no;
        this.emp_name = emp_name;
        this.emp_surname = emp_surname;
        this.job_title = job_title;
        this.gender = gender;
        this.nationality = nationality;
        this.birthday = birthday;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }
 
    public String getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(String emp_no) {
        this.emp_no = emp_no;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmp_surname() {
        return emp_surname;
    }

    public void setEmp_surname(String emp_surname) {
        this.emp_surname = emp_surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getJobTitle() {
        return job_title;
    }

    public void setJobTitle(String job_title) {
        this.job_title = job_title;
    }

 
  
}
