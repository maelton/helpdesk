package br.com.brisabr.helpdesk.model.user.employee;

import br.com.brisabr.helpdesk.model.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "tb_employee")
public class Employee extends User {
    private UUID internalCode; //AUTO-GENERATED
    private String firstName;
    private String lastName;

    @Column(unique=true, nullable=false, length = 11)
    private String cpf;
}