package com.example.employee;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Employee> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getOne(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Employee employee) {
        if (employee.getName() == null || employee.getName().isBlank()
                || employee.getEmail() == null || employee.getEmail().isBlank()
                || employee.getDepartment() == null || employee.getDepartment().isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Name, email and department are required"));
        }
        if (repository.existsByEmail(employee.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", "Email already exists"));
        }
        employee.setId(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Employee input) {
        return repository.findById(id).map(existing -> {
            if (input.getName() == null || input.getName().isBlank()
                    || input.getEmail() == null || input.getEmail().isBlank()
                    || input.getDepartment() == null || input.getDepartment().isBlank()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Name, email and department are required"));
            }
            if (repository.existsByEmail(input.getEmail())
                    && !existing.getEmail().equalsIgnoreCase(input.getEmail())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", "Email already exists"));
            }
            existing.setName(input.getName());
            existing.setEmail(input.getEmail());
            existing.setDepartment(input.getDepartment());
            return ResponseEntity.ok(repository.save(existing));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.ok(Map.of("message", "Employee deleted"));
    }
}
