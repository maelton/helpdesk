package br.com.brisabr.helpdesk.repository;

import br.com.brisabr.helpdesk.model.employee.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value =
        """
        SELECT e.id FROM tb_employee e
        JOIN tb_user u ON e.id = u.id
        WHERE u.is_active = true
        ORDER BY RANDOM() LIMIT 1
        """, nativeQuery = true)
    Optional<Long> findRandomEmployeeId();
    boolean existsByUsername(String username);
    Optional<Employee> findByUsername(String username);
}
