package com.antonsyzko.recruiting.controllers;

import com.antonsyzko.recruiting.exception.SingleResultNotFoundException;
import com.antonsyzko.recruiting.services.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

/**
 * @author ihor zadyra
 */

@Controller
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @RequestMapping(value = "/department/search.html", method = RequestMethod.GET)
    public String search(@RequestParam(value = "countPages", defaultValue = "1", required = false) int countPages, @RequestParam(value = "sortingCriteria", defaultValue = "department", required = false) String sortingCriteria, @RequestParam(value = "desc", defaultValue = "asc", required = false) String desc, @RequestParam(value = "searchInput", required = false) String searchInput, Model model) throws IOException {

        int page = 0;
        if (countPages != 0) page = countPages;
        int countPage = departmentService.countPageBySearch(searchInput);

        model.addAttribute("desc", desc);
        model.addAttribute("sortingCriteria", sortingCriteria);
        model.addAttribute("currentPage", page);
        model.addAttribute("count", countPage);
        model.addAttribute("searchInput", searchInput);
        model.addAttribute("department_list", departmentService.getDepartmentOnCurrentPageBySearch(searchInput, desc, sortingCriteria, page));
        return "department_result";
    }


    @RequestMapping(value = "/department/list.html", method = RequestMethod.GET)
    public String index(@RequestParam(value = "countPages", defaultValue = "1", required = false) int countPages, @RequestParam(value = "sortingCriteria", defaultValue = "id", required = false) String sortingCriteria, @RequestParam(value = "desc", defaultValue = "asc", required = false) String desc, Model model) {


        int page = 0;
        if (countPages != 0) page = countPages;
        int countPage = departmentService.countPage();
        model.addAttribute("desc", desc);
        model.addAttribute("sortingCriteria", sortingCriteria);
        model.addAttribute("currentPage", page);
        model.addAttribute("count", countPage);

        model.addAttribute("department_list", departmentService.getDepartmentOnCurrentPage(desc, sortingCriteria, page));

        return "department_list";
    }


    @RequestMapping(value = "/department/save.html", method = RequestMethod.GET)
    public String saveDepartment(@RequestParam("department") String department, @RequestParam(value = "countPages", defaultValue = "1", required = false) int countPages, @RequestParam(value = "sortingCriteria", defaultValue = "id", required = false) String sortingCriteria, @RequestParam(value = "desc", defaultValue = "asc", required = false) String desc, Model model) {
        departmentService.addDepartment(department);
        int page = 0;
        if (countPages != 0) page = countPages;
        int countPage = departmentService.countPage();
        model.addAttribute("desc", desc);
        model.addAttribute("sortingCriteria", sortingCriteria);
        model.addAttribute("currentPage", page);
        model.addAttribute("count", countPage);
        model.addAttribute("department_list", departmentService.getDepartmentOnCurrentPage(desc, sortingCriteria, page));
        return "department_list";
    }


    @RequestMapping(value = "/department/add.html", method = RequestMethod.GET)
    public String forwardToAddDepartment(Model model) {
        return "depart_add";
    }

    @RequestMapping(value = "/department/edit.html", method = RequestMethod.GET)
    public String forwardToEditEmployee(@RequestParam("departmentId") long departmentId, Model model) {
        try {
            model.addAttribute("department", departmentService.getDepartmentById(departmentId));
        } catch (SingleResultNotFoundException e) {
            logger.error("{}" + e);
            return "error_page";
        }
        return "department_edit";
    }

    @RequestMapping(value = "/department/update.html", method = RequestMethod.GET)
    public String updateEmployee(@RequestParam("departmentId") long departmentId, @RequestParam("department") String departmentName, @RequestParam(value = "countPages", defaultValue = "1", required = false) int countPages, @RequestParam(value = "sortingCriteria", defaultValue = "id", required = false) String sortingCriteria, @RequestParam(value = "desc", defaultValue = "asc", required = false) String desc, Model model) {
        try {
            departmentService.updateDepartment(departmentId, departmentName);
        } catch (SingleResultNotFoundException e) {
            logger.error("{}" + e);
            return "error_page";
        }

        int page = 0;
        if (countPages != 0) page = countPages;
        int countPage = departmentService.countPage();
        model.addAttribute("desc", desc);
        model.addAttribute("sortingCriteria", sortingCriteria);
        model.addAttribute("currentPage", page);
        model.addAttribute("count", countPage);
        model.addAttribute("department_list", departmentService.getDepartmentOnCurrentPage(desc, sortingCriteria, page));
        return "department_list";
    }
}
