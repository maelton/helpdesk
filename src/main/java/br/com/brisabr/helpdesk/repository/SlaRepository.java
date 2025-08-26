package br.com.brisabr.helpdesk.repository;

import br.com.brisabr.helpdesk.model.sla.Sla;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SlaRepository extends JpaRepository<Sla, Long> {}