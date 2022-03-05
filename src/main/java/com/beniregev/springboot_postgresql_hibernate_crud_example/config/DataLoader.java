package com.beniregev.springboot_postgresql_hibernate_crud_example.config;

import com.beniregev.springboot_postgresql_hibernate_crud_example.model.Employee;
import com.beniregev.springboot_postgresql_hibernate_crud_example.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);

    @Autowired
    private EmployeeRepository repository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (repository.findAll().size() != 10)
            repository.deleteAll();
        Employee emp = repository.save(new Employee("david", "king", "david@king.com"));
        log.info("Populating Employee " + emp);
    }
}
