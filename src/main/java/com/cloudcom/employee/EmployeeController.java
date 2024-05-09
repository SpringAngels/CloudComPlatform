package com.cloudcom.employee;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public void register(@RequestBody Employee employee) {
        employeeService.signUpEmployee(employee);
    }

    @GetMapping
    public List<Employee> findAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @DeleteMapping
    public void deleteAllEmployees() {
        employeeService.deleteAllEmployees();
    }
}