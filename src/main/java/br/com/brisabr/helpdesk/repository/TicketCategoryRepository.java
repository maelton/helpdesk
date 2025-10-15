package br.com.brisabr.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brisabr.helpdesk.model.ticket_category.TicketCategory;

public interface TicketCategoryRepository extends JpaRepository<TicketCategory, Long> {}
