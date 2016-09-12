package com.antonsyzko.recruiting.controllers;

import com.antonsyzko.recruiting.services.EditorService;
import com.antonsyzko.recruiting.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ihor zadyra
 */

@Controller
public class EditorController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EditorService editorService;

    @RequestMapping(value = "/editor/login.html", method = RequestMethod.GET)
    public String editorLogin() {
        return "editor_login";
    }

    @RequestMapping(value = "/editor/registration.html", method = RequestMethod.GET)
    public String forwardToRegistration() {
        return "registration";
    }

    @RequestMapping(value = "/editor/register.html", method = RequestMethod.GET)
    public String addEditor(@RequestParam("password") String password, @RequestParam("name") String name, Model model) {
        editorService.add(name, password);
        int page = 1;
        int countPage = employeeService.countPages();
        model.addAttribute("desc", "asc");
        model.addAttribute("sortingCriteria", "id");
        model.addAttribute("currentPage", page);
        model.addAttribute("count", countPage);
        model.addAttribute("employee_list", employeeService.getEmployeesOnCurrentPage("asc", "id", page));
        return "employee_list";
    }

}
