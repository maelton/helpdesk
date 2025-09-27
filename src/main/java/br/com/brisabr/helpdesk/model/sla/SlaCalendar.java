package br.com.brisabr.helpdesk.model.sla;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_sla_calendar")
public class SlaCalendar {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    private String description;
    
    @Column(nullable = false)
    private String timezone;
    
    @Column(nullable = false)
    private Boolean considerWeekends;
    
    @Column(nullable = false)
    private Boolean considerHolidays;
    
    @Column(nullable = false)
    private Boolean isActive;
    
    @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<SlaDay> slaDays = new LinkedHashSet<>();
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}