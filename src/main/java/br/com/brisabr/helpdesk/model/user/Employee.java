package br.com.brisabr.helpdesk.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_employee")
public class Employee extends User {
    private String internalCode;
    private String firstName;
    private String lastName;
    private String cpf;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}