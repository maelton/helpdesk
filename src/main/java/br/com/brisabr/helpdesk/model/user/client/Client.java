package br.com.brisabr.helpdesk.model.user.client;

import br.com.brisabr.helpdesk.model.user.User;

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
    private String address;
    private String phone;
    private Boolean status;
}