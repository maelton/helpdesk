package br.com.brisabr.helpdesk.model.sla;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_sla")
public class Sla {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "calendar_id") @JsonIgnore
    private SlaCalendar calendar;
    
    @ManyToOne @JoinColumn(name = "priority_id")
    private SlaPriority priority;
    
    private String name;
    private String description;
    private Long responseTime;
    private Long resolutionTime; //seconds
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}