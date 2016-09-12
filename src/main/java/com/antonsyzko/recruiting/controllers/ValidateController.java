package com.antonsyzko.recruiting.controllers;

import org.apache.commons.lang.WordUtils;
import com.antonsyzko.recruiting.exception.SingleResultNotFoundException;
import com.antonsyzko.recruiting.services.DepartmentService;
import com.antonsyzko.recruiting.services.EditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * @author ihor zadyra
 */

@Controller
public class ValidateController {

    @Autowired
    private EditorService editorService;

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/ajax/validate/editor/name/", method = RequestMethod.GET)
    public
    @ResponseBody
    String validateEditor(@RequestParam(value = "editorName", required = false) String editorName) {
        try {
            return editorService.isNameFree(WordUtils.capitalize(editorName.trim().toLowerCase())).toString();
        } catch (SingleResultNotFoundException e) {
            return Boolean.TRUE.toString();
        }
    }

    @RequestMapping(value = "/ajax/validate/department/name/", method = RequestMethod.GET)
    public
    @ResponseBody
    String validateDepartment(@RequestParam(value = "departmentName", required = false) String departmentName) {
        try {
            return departmentService.isDepartmentFree(WordUtils.capitalize(departmentName.trim().toLowerCase())).toString();
        } catch (SingleResultNotFoundException e) {
            return Boolean.TRUE.toString();
        }
    }

    @RequestMapping(value = "/ajax/validate/editor/login/", method = RequestMethod.GET)
    public
    @ResponseBody
    String validateLogin(@RequestParam(value = "login", required = false) String login) throws IOException {
        try {
            return editorService.containsLogin(login).toString();
        } catch (SingleResultNotFoundException e) {
            return Boolean.FALSE.toString();
        }
    }

    @RequestMapping(value = "/ajax/validate/editor/password/", method = RequestMethod.GET)
    public
    @ResponseBody
    String validatePassword(@RequestParam(value = "login", required = false) String login, @RequestParam(value = "password", required = false) String password) throws IOException {
        try {
            return editorService.containsEditorByLoginAndPassword(login, password).toString();
        } catch (SingleResultNotFoundException e) {
            return Boolean.FALSE.toString();
        }
    }
}
