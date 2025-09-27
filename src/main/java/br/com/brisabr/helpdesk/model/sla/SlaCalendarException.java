package br.com.brisabr.helpdesk.model.sla;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_sla_calendar_exception")
public class SlaCalendarException {
    @Id
    @ManyToOne
    @JoinColumn(name = "calendar_id")
    private SlaCalendar calendar;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "exception_id")
    private SlaException exception;
}