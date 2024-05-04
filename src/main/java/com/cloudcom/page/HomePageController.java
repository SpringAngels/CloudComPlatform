package com.cloudcom.page;

import com.cloudcom.employee.Employee;
import com.cloudcom.employee.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("home")
public class HomePageController {

    private final EmployeeService employeeService;

    public HomePageController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String home(Model model) {
        List<Employee> sortedEmployees = employeeService.getSortedEmployees();
        model.addAttribute("employees", sortedEmployees);
        return "home";
    }
}
