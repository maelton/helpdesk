package br.com.brisabr.helpdesk.repository;

import br.com.brisabr.helpdesk.model.sla.SlaException;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SlaExceptionRepository extends JpaRepository<SlaException, Long> {
    
    List<SlaException> findByIsActiveTrueOrderByExceptionDate();
    
    List<SlaException> findByExceptionDateAndIsActiveTrue(LocalDate date);
    
    List<SlaException> findByIsRecurringTrueAndIsActiveTrue();
    
    List<SlaException> findByExceptionDateBetweenAndIsActiveTrue(LocalDate startDate, LocalDate endDate);
}