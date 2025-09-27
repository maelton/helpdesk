package br.com.brisabr.helpdesk.repository;

import br.com.brisabr.helpdesk.model.sla.Sla;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SlaRepository extends JpaRepository<Sla, Long> {
    
    List<Sla> findByIsActiveTrueOrderByName();
    
    Optional<Sla> findByNameAndIsActiveTrue(String name);
    
    List<Sla> findByPriorityIdAndIsActiveTrue(Long priorityId);
    
    List<Sla> findByCalendarIdAndIsActiveTrue(Long calendarId);
    
    @Query("SELECT s FROM Sla s LEFT JOIN FETCH s.priority LEFT JOIN FETCH s.calendar WHERE s.id = :id")
    Optional<Sla> findByIdWithDetails(Long id);
}