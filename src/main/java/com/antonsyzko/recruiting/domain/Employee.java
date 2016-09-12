package com.antonsyzko.recruiting.domain;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * @author ihor zadyra
 */

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String first_name;
    private String last_name;
    @ManyToOne
    private Department department;
    private double salary;
    private Date recruited_date;
    private Date birth_date;
    private Boolean is_active;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getRecruited_date() throws ParseException {

        return new SimpleDateFormat("yyyy-MM-dd").format(recruited_date);
    }

    public String getBirth_date() {

        return new SimpleDateFormat("yyyy-MM-dd").format(birth_date);
    }

    public void setRecruited_date(Date recruited_date) {
        this.recruited_date = recruited_date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }


    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

}

