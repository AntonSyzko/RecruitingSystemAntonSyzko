package com.antonsyzko.recruiting.services;

import com.antonsyzko.recruiting.dao.DepartmentRepository;
import com.antonsyzko.recruiting.dao.EmployeeRepository;
import com.antonsyzko.recruiting.domain.Department;
import com.antonsyzko.recruiting.domain.Employee;
import com.antonsyzko.recruiting.exception.SingleResultNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author ihor zadyra
 */

@Service
public class EmployeeService {
    static Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    public List<Employee> getAll() {
        return employeeRepository.getAll();
    }

    @Transactional
    public void add(String first_name, String last_name, Double salary, long departmentId, String birth_date, String recruited_date) throws SingleResultNotFoundException {
        SimpleDateFormat userTimeDf = new SimpleDateFormat("yyyy-MM-dd");
        Employee employee = new Employee();
        employee.setFirst_name(first_name);
        employee.setIs_active(true);
        employee.setLast_name(last_name);
        employee.setSalary(salary);
        Department department = departmentRepository.get(departmentId);
        department.getEmployees().add(employee);
        employee.setDepartment(department);
        try {
            employee.setBirth_date(userTimeDf.parse(birth_date));
            employee.setRecruited_date(userTimeDf.parse(recruited_date));
        } catch (ParseException e) {
            logger.error("ParseException '{}'", e);
        }
        departmentRepository.add(department);

    }

    public Employee get(long employeeId) throws SingleResultNotFoundException {
        return employeeRepository.get(employeeId);
    }

    @Transactional
    public void updateEmployee(String isActive, long employeeId, String first_name, String last_name, Double salary, long departmentId, String birth_date) throws SingleResultNotFoundException {
        SimpleDateFormat userTimeDf = new SimpleDateFormat("yyyy-MM-dd");

        Employee employee = employeeRepository.get(employeeId);
        employee.setFirst_name(first_name);
        employee.setLast_name(last_name);
        employee.setSalary(salary);
        if (isActive.equals("working")) employee.setIs_active(true);
        else employee.setIs_active(false);

        try {
            employee.setBirth_date(userTimeDf.parse(birth_date));
        } catch (ParseException e) {
            logger.error("ParseException '{}'", e);
        }

        Department departmentOld = employee.getDepartment();
        Department departmentNew = departmentRepository.get(departmentId);
        if (departmentNew.getId() != departmentOld.getId()) {
            departmentNew.getEmployees().add(employee);
            employee.setDepartment(departmentNew);
            departmentOld.getEmployees().remove(employee);
            departmentRepository.add(departmentOld);
            departmentRepository.add(departmentNew);
            employeeRepository.add(employee);
        } else {
            departmentRepository.add(departmentOld);
            employeeRepository.add(employee);
        }
    }

    public int countPages() {
        Long count = employeeRepository.count();
        return (int) new BigDecimal((double) count / 20).setScale(0, RoundingMode.UP).doubleValue();
    }

    public List<Employee> getEmployeesOnCurrentPage(String desc, String sortingCriteria, int countPages) {

        List<Employee> employeeList = null;
        if (countPages == 1) {
            employeeList = employeeRepository.getLimitEmployees(desc, sortingCriteria, 0);
        } else if (countPages == 2) {
            employeeList = employeeRepository.getLimitEmployees(desc, sortingCriteria, 20);
        } else {
            employeeList = employeeRepository.getLimitEmployees(desc, sortingCriteria, (countPages * 20) - 20);
        }
        return employeeList;
    }


    public List<Employee> getEmployeesOnCurrentPageByCategory(String categoryName, String categoryValue, String desc, String sortingCriteria, int countPages) {

        List<Employee> employeesList = null;
        if (countPages == 1) {
            employeesList = employeeRepository.getLimitEmployeesByCategory(categoryName, categoryValue, desc, sortingCriteria, 0);
        } else if (countPages == 2) {
            employeesList = employeeRepository.getLimitEmployeesByCategory(categoryName, categoryValue, desc, sortingCriteria, 20);
        } else {
            employeesList = employeeRepository.getLimitEmployeesByCategory(categoryName, categoryValue, desc, sortingCriteria, (countPages * 20) - 20);
        }
        return employeesList;
    }

    public List<Employee> getEmployeesOnCurrentPageBySearch(String searchInput, String desc, String sortingCriteria, int countPages) {


        List<Employee> employeesList = null;
        if (countPages == 1) {
            employeesList = employeeRepository.getLimitEmployeesBySearch(searchInput, desc, sortingCriteria, 0);
        } else if (countPages == 2) {
            employeesList = employeeRepository.getLimitEmployeesBySearch(searchInput, desc, sortingCriteria, 20);
        } else {
            employeesList = employeeRepository.getLimitEmployeesBySearch(searchInput, desc, sortingCriteria, (countPages * 20) - 20);
        }
        return employeesList;
    }

    public int countPagesByCategory(String categoryName, String categoryValue) {

        Long count = employeeRepository.countAllEmployeesByCategory(categoryName, categoryValue);
        return (int) new BigDecimal((double) count / 20).setScale(0, RoundingMode.UP).doubleValue();


    }

    public int countPagesBySearch(String searchInput) {

        Long count = employeeRepository.countAllEmployeesBySearch(searchInput);
        return (int) new BigDecimal((double) count / 20).setScale(0, RoundingMode.UP).doubleValue();

    }

}
