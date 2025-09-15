package br.com.brisabr.helpdesk.repository;

import br.com.brisabr.helpdesk.model.user.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByUsername(String username);
}