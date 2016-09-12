package com.antonsyzko.recruiting.services;

import org.apache.commons.lang.WordUtils;
import org.hibernate.Hibernate;
import com.antonsyzko.recruiting.dao.DepartmentRepository;
import com.antonsyzko.recruiting.domain.Department;
import com.antonsyzko.recruiting.domain.Employee;
import com.antonsyzko.recruiting.exception.SingleResultNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
/**
 * @author ihor zadyra
 */

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    public void addDepartment(String departmentName) {
        departmentName = WordUtils.capitalize(departmentName.trim().toLowerCase());
        Department department = new Department();
        department.setDepartment_name(departmentName);
        departmentRepository.add(department);
    }

    public Boolean isDepartmentFree(String departmentName) throws SingleResultNotFoundException {
        return departmentRepository.getDepartmentByName(departmentName) == null;
    }

    @Transactional
    public List<Department> getAll() {
        return departmentRepository.getAll();
    }



    public Department getDepartmentById(long departmentId) throws SingleResultNotFoundException {
            return departmentRepository.get(departmentId);
    }

    public void updateDepartment(long departmentId, String departmentName) throws SingleResultNotFoundException {
        Department department = departmentRepository.get(departmentId);
        department.setDepartment_name(departmentName);
        departmentRepository.add(department);
    }

    @Transactional
    public List<Department> getDepartmentOnCurrentPage(String desc, String sortingCriteria, int countPages) {

        List<Department> departmentList = null;
        if (countPages == 1) {
            departmentList = departmentRepository.getLimitDepartments(desc, sortingCriteria, 0);
        } else if (countPages == 2) {
            departmentList = departmentRepository.getLimitDepartments(desc, sortingCriteria, 20);
        } else {
            departmentList = departmentRepository.getLimitDepartments(desc, sortingCriteria, (countPages * 20) - 20);
        }
        for (Department department : departmentList) {
            Hibernate.initialize(department.getEmployees());

            double cashExpenditures = 0;
            for (Employee employee : department.getEmployees()) {
                cashExpenditures = cashExpenditures + employee.getSalary();
            }
            department.setCashExpenditures(cashExpenditures);
        }
        return departmentList;
    }


    public int countPageBySearch(String searchInput) {

        Long count = departmentRepository.countAllDepartmentsBySearch(searchInput);
        return (int) new BigDecimal((double) count / 20).setScale(0, RoundingMode.UP).doubleValue();
    }


    @Transactional
    public List<Department> getDepartmentOnCurrentPageBySearch(String searchInput, String desc, String sortingCriteria, int countPages) {

        List<Department> departmentList = null;
        if (countPages == 1) {
            departmentList = departmentRepository.getLimitDepartmentsBySearch(searchInput, desc, sortingCriteria, 0);
        } else if (countPages == 2) {
            departmentList = departmentRepository.getLimitDepartmentsBySearch(searchInput, desc, sortingCriteria, 20);
        } else {
            departmentList = departmentRepository.getLimitDepartmentsBySearch(searchInput, desc, sortingCriteria, (countPages * 20) - 20);
        }
        for (Department department : departmentList) {
            Hibernate.initialize(department.getEmployees());
            double cashExpenditures = 0;
            for (Employee employee : department.getEmployees()) {
                cashExpenditures = cashExpenditures + employee.getSalary();
            }
            department.setCashExpenditures(cashExpenditures);
        }


        return departmentList;
    }


    public int countPage() {
        Long count = departmentRepository.count();
        return (int) new BigDecimal((double) count / 20).setScale(0, RoundingMode.UP).doubleValue();
    }
}
