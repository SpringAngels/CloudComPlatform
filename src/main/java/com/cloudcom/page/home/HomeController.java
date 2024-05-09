package com.cloudcom.page.home;

import com.cloudcom.employee.Employee;
import com.cloudcom.employee.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("home")
public class HomeController {

    private final EmployeeService employeeService;

    public HomeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String home(Model model) {
        List<Employee> sortedEmployees = employeeService.getSortedEmployees();
        model.addAttribute("employees", sortedEmployees);
        return "home";
    }
}
