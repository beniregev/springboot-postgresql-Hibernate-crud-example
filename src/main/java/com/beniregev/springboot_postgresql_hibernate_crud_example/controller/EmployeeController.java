package com.beniregev.springboot_postgresql_hibernate_crud_example.controller;

import com.beniregev.springboot_postgresql_hibernate_crud_example.exception.ResourceNotFoundException;
import com.beniregev.springboot_postgresql_hibernate_crud_example.model.Employee;
import com.beniregev.springboot_postgresql_hibernate_crud_example.model.EmployeeDto;
import com.beniregev.springboot_postgresql_hibernate_crud_example.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = {
        "https://localhost:4200/",
        "https://localhost:8080/",
        "https://java-spring-crud-with-angular-frontend-example.netlify.app/"})
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/employees")
    public ResponseEntity<List<Employee>> getAll() {
        List<Employee> employees = employeeService.getAll();
        //return new ResponseEntity<>(employees, HttpStatus.OK);
        return ResponseEntity.ok().body(employees);
    }

    @GetMapping(path = "/employees/{id}")
    public ResponseEntity<Employee> getById(@PathVariable String id) throws ResourceNotFoundException {
        try {
            Employee employee = employeeService.getById(id);
            //return new ResponseEntity<>(employee, HttpStatus.OK);
            return ResponseEntity.ok().body(employee);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping(path = "/employees")
    public ResponseEntity<Employee> create(@RequestBody EmployeeDto newEmployee) {
        Employee employee = employeeService.create(newEmployee);
        return Objects.nonNull(employee)
                ? new ResponseEntity<>(employee, HttpStatus.CREATED)
                : ResponseEntity.badRequest().body(new Employee(newEmployee.getFirstName(), newEmployee.getLastName(), newEmployee.getEmail()));
    }

    @PutMapping(path = "/employees/{id}")
    public ResponseEntity<Employee> updateById(@PathVariable String id, @RequestBody EmployeeDto with) throws ResourceNotFoundException {
        try {
            Employee employee = employeeService.updateById(id, with);
            //return new ResponseEntity<>(employee, HttpStatus.OK);
            //return ResponseEntity.ok().body(employee);
            return ResponseEntity.ok(employee);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
            throw e;
        }

    }

    @DeleteMapping(path = "/employees/{id}")
    public ResponseEntity<Employee> deleteById(@PathVariable String id) throws ResourceNotFoundException {
        try {
            Employee employee = employeeService.deleteById(id);
            //return new ResponseEntity<>(employee, HttpStatus.OK);
            return ResponseEntity.ok().body(employee);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
