package com.example.springreactcrud.controllers;

import com.example.springreactcrud.exceptions.ResourceNotFoundException;
import com.example.springreactcrud.models.Employee;
import com.example.springreactcrud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // get all employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }


    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // get employe by id

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
        Employee emp = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Employee not exist with id :" + id
        ));
        return ResponseEntity.ok(emp);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Employee emp = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Employee not exist with id :" + id
        ));
        emp.setFirstName(employee.getFirstName());
        emp.setLastName(employee.getLastName());
        emp.setEmail(employee.getEmail());
        Employee updatedEmp = employeeRepository.save(emp);
        return ResponseEntity.ok(updatedEmp);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
        Employee emp = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Employee not exist with id :" + id
        ));
        employeeRepository.delete(emp);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
