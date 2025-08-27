package br.com.brisabr.helpdesk.repository;

import br.com.brisabr.helpdesk.model.ticket.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {}