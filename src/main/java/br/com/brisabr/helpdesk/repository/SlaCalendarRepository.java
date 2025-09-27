package br.com.brisabr.helpdesk.repository;

import br.com.brisabr.helpdesk.model.sla.SlaCalendar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SlaCalendarRepository extends JpaRepository<SlaCalendar, Long> {
    
    List<SlaCalendar> findByIsActiveTrueOrderByName();
    
    Optional<SlaCalendar> findByNameAndIsActiveTrue(String name);
    
    @Query("SELECT sc FROM SlaCalendar sc LEFT JOIN FETCH sc.slaDays WHERE sc.id = :id")
    Optional<SlaCalendar> findByIdWithDays(Long id);
}