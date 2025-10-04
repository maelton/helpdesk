package br.com.brisabr.helpdesk.model.client;

import br.com.brisabr.helpdesk.model.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_client")
public class Client extends User {
    private String name;
    
    @Column(unique=true, nullable=false, length = 11)
    private String taxId;
    private String address;
    private String phone;
    private Boolean status;
}