package br.com.brisabr.helpdesk.service;

import br.com.brisabr.helpdesk.model.employee.Employee;
import br.com.brisabr.helpdesk.model.employee.EmployeeCreatedEvent;
import br.com.brisabr.helpdesk.model.employee.dto.EmployeeCreateDTO;
import br.com.brisabr.helpdesk.model.employee.dto.EmployeeResponseDTO;
import br.com.brisabr.helpdesk.repository.EmployeeRepository;
import br.com.brisabr.helpdesk.utils.hash.PasswordGenerator;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public EmployeeResponseDTO create(EmployeeCreateDTO dto) {
        Employee employee = toEntity(dto);
        employee.setPassword(
            passwordEncoder.encode(
                PasswordGenerator.generateRandomPassword()
            )
        );
        Employee created = employeeRepository.save(employee);
        eventPublisher.publishEvent(
            new EmployeeCreatedEvent(created)
        );
        return this.toResponse(created);
    }

    @Transactional(readOnly = true)
    public Employee getById(Long id) {
        return employeeRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public Employee getByUsername(String username) {
        return employeeRepository.findByUsername(username)
            .orElseThrow(() -> new EntityNotFoundException("Employee not found with username: " + username));
    }

    @Transactional(readOnly = true)
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EntityNotFoundException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }

    public boolean existsByUsername(String username) {
        return employeeRepository.existsByUsername(username);
    }

    private Employee toEntity(EmployeeCreateDTO dto) {
        Employee employee = new Employee();
            employee.setFirstName(dto.firstName());
            employee.setLastName(dto.lastName());
            employee.setCpf(dto.cpf());
            employee.setUsername(dto.email());
            employee.setIsActive(dto.isActive() != null && dto.isActive());
        return employee;
    }

    private EmployeeResponseDTO toResponse(Employee employee) {
        return new EmployeeResponseDTO(
            employee.getId(),
            employee.getFirstName(),
            employee.getLastName(),
            employee.getCpf(),
            employee.getInternalCode(),
            employee.getUsername(),
            employee.getIsActive(),
            employee.getCreatedAt(),
            employee.getUpdatedAt()
        );
    }
}