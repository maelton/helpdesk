package br.com.brisabr.helpdesk.repository;

import br.com.brisabr.helpdesk.model.sla.SlaPriority;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SlaPriorityRepository extends JpaRepository<SlaPriority, Long> {
    
    List<SlaPriority> findByIsActiveTrueOrderByLevel();
    
    Optional<SlaPriority> findByNameAndIsActiveTrue(String name);
    
    Optional<SlaPriority> findByLevelAndIsActiveTrue(Integer level);
}