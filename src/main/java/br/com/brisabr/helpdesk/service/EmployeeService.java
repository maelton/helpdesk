package br.com.brisabr.helpdesk.service;

import br.com.brisabr.helpdesk.model.user.employee.Employee;
import br.com.brisabr.helpdesk.model.user.employee.EmployeeCreatedEvent;
import br.com.brisabr.helpdesk.model.user.employee.dto.EmployeeCreateDTO;
import br.com.brisabr.helpdesk.repository.EmployeeRepository;
import br.com.brisabr.helpdesk.utils.hash.PasswordGenerator;
import br.com.brisabr.helpdesk.utils.mail.EmailService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public Employee create(EmployeeCreateDTO dto) {
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
        return created;
    }

    @Transactional(readOnly = true)
    public Employee getById(Long id) {
        return employeeRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));
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

    private Employee toEntity(EmployeeCreateDTO dto) {
        Employee employee = new Employee();
            employee.setFirstName(dto.firstName());
            employee.setLastName(dto.lastName());
            employee.setCpf(dto.cpf());
            employee.setUsername(dto.email());
            employee.setIsActive(dto.isActive() != null && dto.isActive());
        return employee;
    }
}