package br.com.brisabr.helpdesk.model.user.employee;

import br.com.brisabr.helpdesk.model.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "tb_employee")
public class Employee extends User {
    private String internalCode; //AUTO-GENERATED
    private String firstName;
    private String lastName;
    private String cpf;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}