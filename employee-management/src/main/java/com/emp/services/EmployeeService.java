package com.emp.services;

import com.emp.model.Employee;
import com.emp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repo;

    public List<Employee> getAll() {
        return repo.findAll();
    }

    public Employee getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    public Employee save(Employee e) {
        return repo.save(e);
    }

    public Employee update(Long id, Employee updated) {
        Employee e = getById(id);

        e.setName(updated.getName());
        e.setDepartment(updated.getDepartment());
        e.setEmail(updated.getEmail());
        e.setSalary(updated.getSalary());
        e.setUsername(updated.getUsername());
        e.setPassword(updated.getPassword());
        e.setCreditDay(updated.getCreditDay());

        return repo.save(e);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Optional<Employee> login(String username, String password) {
        return repo.findByUsernameAndPassword(username, password);
    }
}