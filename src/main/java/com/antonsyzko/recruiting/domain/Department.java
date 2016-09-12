package com.antonsyzko.recruiting.domain;

import javax.persistence.*;
import java.util.List;
/**
 * @author ihor zadyra
 */

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    private String department_name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Employee> employees;

    @Transient
    private double cashExpenditures;

    public double getCashExpenditures() {
        return cashExpenditures;
    }

    public void setCashExpenditures(double cashExpenditures) {
        this.cashExpenditures = cashExpenditures;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }
}
