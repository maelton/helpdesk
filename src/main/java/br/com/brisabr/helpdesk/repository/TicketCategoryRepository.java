package br.com.brisabr.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brisabr.helpdesk.model.ticket.category.TicketCategory;

public interface TicketCategoryRepository extends JpaRepository<TicketCategory, Long> {}
