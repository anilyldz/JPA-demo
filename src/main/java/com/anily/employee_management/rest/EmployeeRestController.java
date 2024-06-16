package com.anily.employee_management.rest;

import com.anily.employee_management.entity.Employee;
import com.anily.employee_management.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee findById(@PathVariable int employeeId) {
        Employee employee = employeeService.findById(employeeId);
        if (employee==null){
            throw  new RuntimeException("Employee id not found" + employeeId);
        }
        return employee;
    }

    @PostMapping("/employees")
    public Employee save(@RequestBody Employee employee) {
        employee.setId(0);
        Employee savedEmployee = employeeService.save(employee);
        if (savedEmployee == null) {
            throw new RuntimeException("Employee not saved");
        }
        return savedEmployee;
    }

    @PutMapping("/employees")
    public Employee update(@RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.save(employee);
        if (updatedEmployee == null) {
            throw new RuntimeException("Employee not updated");
        }
        return updatedEmployee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public void delete(@PathVariable int employeeId) {
        employeeService.deleteById(employeeId);
    }

}
