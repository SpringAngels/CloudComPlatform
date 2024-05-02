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

    private final EmployeeDetailsService employeeDetailsService;

    public EmployeeController(EmployeeDetailsService employeeDetailsService) {
        this.employeeDetailsService = employeeDetailsService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeDetailsService.getAllEmployees();
    }

    @PostMapping
    public void saveEmployee(@RequestBody Employee employee) {
        employeeDetailsService.createEmployee(employee);
    }

    @DeleteMapping
    public void deleteAllEmployees() {
        employeeDetailsService.deleteEmployees();
    }
}
