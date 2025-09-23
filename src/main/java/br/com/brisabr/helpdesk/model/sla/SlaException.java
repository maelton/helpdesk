package br.com.brisabr.helpdesk.model.sla;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_sla_exception")
public class SlaException {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    private String description;
    
    @Column(nullable = false)
    private LocalDate exceptionDate;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SlaExceptionType type;
    
    private LocalTime startTime;
    private LocalTime endTime;
    
    @Column(nullable = false)
    private Boolean isRecurring;
    
    @Column(nullable = false)
    private Boolean isActive;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public enum SlaExceptionType {
        HOLIDAY,
        SPECIAL_HOURS,
        NON_WORKING_DAY
    }
}