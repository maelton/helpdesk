package br.com.brisabr.helpdesk.service;

import br.com.brisabr.helpdesk.model.department.Department;
import br.com.brisabr.helpdesk.model.department.dto.DepartmentCreateDTO;
import br.com.brisabr.helpdesk.repository.DepartmentRepository;

import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

    import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Transactional
    public Department create(DepartmentCreateDTO dto) {
        Department department = toEntity(dto);
        return departmentRepository.save(department);
    }

    @Transactional(readOnly = true)
    public Department getById(Long id) {
        return departmentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Department not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new EntityNotFoundException("Department not found with id: " + id);
        }
        departmentRepository.deleteById(id);
    }

    private Department toEntity(DepartmentCreateDTO dto) {
        Department department = new Department();
        department.setName(dto.name());
        // createdAt/updatedAt can be handled by database defaults or entity listeners
        return department;
    }
}