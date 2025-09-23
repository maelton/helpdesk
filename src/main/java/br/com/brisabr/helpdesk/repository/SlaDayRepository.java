package br.com.brisabr.helpdesk.repository;

import br.com.brisabr.helpdesk.model.sla.SlaDay;
import br.com.brisabr.helpdesk.model.sla.SlaCalendar;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

public interface SlaDayRepository extends JpaRepository<SlaDay, Long> {
    
    List<SlaDay> findByCalendarOrderByDayOfWeek(SlaCalendar calendar);
    
    Optional<SlaDay> findByCalendarAndDayOfWeek(SlaCalendar calendar, DayOfWeek dayOfWeek);
    
    List<SlaDay> findByCalendarAndIsWorkingDayTrue(SlaCalendar calendar);
}