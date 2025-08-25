package br.com.brisabr.helpdesk.repository;

import br.com.brisabr.helpdesk.model.user.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
