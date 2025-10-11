package br.com.brisabr.helpdesk.model.sla;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_sla")
public class Sla {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "calendar_id")
    private SlaCalendar calendar;
    
    @ManyToOne
    @JoinColumn(name = "priority_id")
    private SlaPriority priority;
    
    private String name;
    private String description;
    private Long responseTime;
    private Long resolutionTime; //seconds
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}