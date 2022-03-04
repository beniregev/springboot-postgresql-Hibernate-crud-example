package com.beniregev.springboot_postgresql_hibernate_crud_example.service;

import com.beniregev.springboot_postgresql_hibernate_crud_example.exception.ResourceNotFoundException;
import com.beniregev.springboot_postgresql_hibernate_crud_example.model.Employee;
import com.beniregev.springboot_postgresql_hibernate_crud_example.model.EmployeeDto;
import com.beniregev.springboot_postgresql_hibernate_crud_example.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getAll() {
        return repository.findAll();
    }
    public Employee getById(String id) throws ResourceNotFoundException {
        return repository.findById(Long.parseLong(id)).orElseThrow(() -> new ResourceNotFoundException("Retrieve Failed! Employee with id=" + id + " not found."));
    }

    public Employee create(EmployeeDto employee) {
        return repository.save(
                new Employee(
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getEmail()
                )
        );
    }

    public Employee updateById(String id, EmployeeDto with) throws ResourceNotFoundException {
        Employee employee = this.getById(id);
        employee.setEmail(with.getEmail());
        employee.setLastName(with.getLastName());
        employee.setFirstName(with.getFirstName());
        Employee updated = repository.save(employee);
        return (updated);
    }

    public Employee deleteById(String id) throws ResourceNotFoundException {
        Employee employee = this.getById(id);
        repository.delete(employee);
        return employee;
    }
}
