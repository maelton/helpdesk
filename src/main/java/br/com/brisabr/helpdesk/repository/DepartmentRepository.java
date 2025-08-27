package br.com.brisabr.helpdesk.repository;

import br.com.brisabr.helpdesk.model.department.Department;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {}