package com.beniregev.springboot_postgresql_hibernate_crud_example.config;

import com.beniregev.springboot_postgresql_hibernate_crud_example.model.Employee;
import com.beniregev.springboot_postgresql_hibernate_crud_example.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {
        return args -> {
            List<Employee> kingDavidEmployees = repository.findByFirstName("David").orElse(null);
            if ((repository.findAll().size() != 1) || kingDavidEmployees.size() > 1)
                repository.deleteAll();
            for (int i=1; i<=10; i++) {
                Employee emp = new Employee("dummy" + i, "dam-dam" + i, "dummy" + i + "@domain.com");
                repository.save(emp);
                log.info("Preloading " + emp);
            }
        };
    }
}
