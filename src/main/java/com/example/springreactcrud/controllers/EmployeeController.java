package com.example.springreactcrud.controllers;

import com.example.springreactcrud.exceptions.ResourceNotFoundException;
import com.example.springreactcrud.models.Employee;
import com.example.springreactcrud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}
