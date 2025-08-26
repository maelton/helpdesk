package br.com.brisabr.helpdesk.model.sla;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
    //private SlaCalendar calendar;
    private String name;
    private String description;
    private Long responseTime; //Study the possibility of using Duration
    private Long resolutionTime; //Study the possibility of using Duration
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}