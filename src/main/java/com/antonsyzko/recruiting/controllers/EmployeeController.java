package com.antonsyzko.recruiting.controllers;

import com.antonsyzko.recruiting.domain.Department;
import com.antonsyzko.recruiting.exception.SingleResultNotFoundException;
import com.antonsyzko.recruiting.services.DepartmentService;
import com.antonsyzko.recruiting.services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

/**
 * @author ihor zadyra
 */

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;
    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(@RequestParam(value = "countPages", defaultValue = "1", required = false) int countPages, @RequestParam(value = "sortingCriteria", defaultValue = "id", required = false) String sortingCriteria, @RequestParam(value = "desc", defaultValue = "asc", required = false) String desc, Model model) {
        int page = 0;
        if (countPages != 0) page = countPages;
        int countPage = employeeService.countPages();
        model.addAttribute("desc", desc);
        model.addAttribute("sortingCriteria", sortingCriteria);
        model.addAttribute("currentPage", page);
        model.addAttribute("count", countPage);
        model.addAttribute("employee_list", employeeService.getEmployeesOnCurrentPage(desc, sortingCriteria, page));
        return "employee_list";
    }

    @RequestMapping(value = "/employee/category.html", method = RequestMethod.GET)
    public String viewEmployeeByCategory(@RequestParam(value = "categoryName", required = false) String categoryName, @RequestParam(value = "categoryValue", required = false) String categoryValue, @RequestParam(value = "countPages", defaultValue = "1", required = false) int countPages, @RequestParam(value = "sortingCriteria", defaultValue = "id", required = false) String sortingCriteria, @RequestParam(value = "desc", defaultValue = "asc", required = false) String desc, Model model) {
        int page = 0;
        if (countPages != 0) page = countPages;
        int countPage = employeeService.countPagesByCategory(categoryName, categoryValue);
        model.addAttribute("categoryName", categoryName);
        model.addAttribute("categoryValue", categoryValue);
        model.addAttribute("desc", desc);
        model.addAttribute("sortingCriteria", sortingCriteria);
        model.addAttribute("currentPage", page);
        model.addAttribute("count", countPage);
        model.addAttribute("employee_list", employeeService.getEmployeesOnCurrentPageByCategory(categoryName, categoryValue, desc, sortingCriteria, page));
        return "employee_category";
    }

    @RequestMapping(value = "/employee/add.html", method = RequestMethod.GET)
    public String forwardToAddEmployee(Model model) {
        List<Department> departmentList = departmentService.getAll();
        if (departmentList.size() == 0) {
            model.addAttribute("department_found", true);
            return "depart_add";
        }
        model.addAttribute("allDepartments", departmentList);
        return "employee_add";
    }

    @RequestMapping(value = "/employee/save.html", method = RequestMethod.GET)
    public String saveEmployee(@RequestParam(value = "countPages", defaultValue = "1", required = false) int countPages, @RequestParam(value = "sortingCriteria", defaultValue = "id", required = false) String sortingCriteria, @RequestParam(value = "desc", defaultValue = "asc", required = false) String desc, @RequestParam("departmentId") long departmentId, @RequestParam("first_name") String first_name, @RequestParam("last_name") String last_name, @RequestParam("salary") Double salary, @RequestParam("birth_date") String birth_date, @RequestParam("recruited_date") String recruited_date, Model model) {

        try {
            employeeService.add(first_name, last_name, salary, departmentId, birth_date, recruited_date);
        } catch (SingleResultNotFoundException e) {
            logger.error("{}" + e);
            return "error_page";
        }
        int page = 0;
        if (countPages != 0) page = countPages;
        int countPage = employeeService.countPages();
        model.addAttribute("desc", desc);
        model.addAttribute("sortingCriteria", sortingCriteria);
        model.addAttribute("currentPage", page);
        model.addAttribute("count", countPage);
        model.addAttribute("employee_list", employeeService.getEmployeesOnCurrentPage(desc, sortingCriteria, page));
        return "employee_list";
    }

    @RequestMapping(value = "/employee/edit.html", method = RequestMethod.GET)
    public String forwardToEditEmployee(@RequestParam("employeeId") long employeeId, @RequestParam(value = "countPages", defaultValue = "1", required = false) int countPages, @RequestParam(value = "sortingCriteria", defaultValue = "id", required = false) String sortingCriteria, @RequestParam(value = "desc", defaultValue = "asc", required = false) String desc, Model model) {
        model.addAttribute("allDepartments", departmentService.getAll());
        try {
            model.addAttribute("employee", employeeService.get(employeeId));
        } catch (SingleResultNotFoundException e) {
            logger.error("{}" + e);
            return "error_page";
        }
        return "employee_edit";
    }

    @RequestMapping(value = "/employee/update.html", method = RequestMethod.GET)
    public String updateEmployee(@RequestParam(value = "countPages", defaultValue = "1", required = false) int countPages, @RequestParam(value = "sortingCriteria", defaultValue = "id", required = false) String sortingCriteria, @RequestParam(value = "desc", defaultValue = "asc", required = false) String desc, Model model, @RequestParam("is_active") String isActive, @RequestParam("employeeId") long employeeId, @RequestParam("department") long department, @RequestParam("first_name") String first_name, @RequestParam("last_name") String last_name, @RequestParam("salary") Double salary, @RequestParam("birth_date") String birth_date) {
        try {
            employeeService.updateEmployee(isActive, employeeId, first_name, last_name, salary, department, birth_date);
        } catch (SingleResultNotFoundException e) {
            logger.error("{}" + e);
            return "error_page";
        }
        int page = 0;
        if (countPages != 0) page = countPages;
        int countPage = employeeService.countPages();
        model.addAttribute("desc", desc);
        model.addAttribute("sortingCriteria", sortingCriteria);
        model.addAttribute("currentPage", page);
        model.addAttribute("count", countPage);

        model.addAttribute("employee_list", employeeService.getEmployeesOnCurrentPage(desc, sortingCriteria, page));

        return "employee_list";

    }

    @RequestMapping(value = "/employee/search.html", method = RequestMethod.GET)
    public String search(@RequestParam(value = "countPages", defaultValue = "1", required = false) int countPages, @RequestParam(value = "sortingCriteria", defaultValue = "last_name", required = false) String sortingCriteria, @RequestParam(value = "desc", defaultValue = "asc", required = false) String desc, @RequestParam(value = "searchInput", required = false) String searchInput, Model model) throws IOException {
        int page = 0;
        if (countPages != 0) page = countPages;
        int countPage = employeeService.countPagesBySearch(searchInput);
        model.addAttribute("desc", desc);
        model.addAttribute("sortingCriteria", sortingCriteria);
        model.addAttribute("currentPage", page);
        model.addAttribute("count", countPage);
        model.addAttribute("searchInput", searchInput);
        model.addAttribute("employee_list", employeeService.getEmployeesOnCurrentPageBySearch(searchInput, desc, sortingCriteria, page));
        return "employee_result";
    }
}
