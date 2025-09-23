package br.com.brisabr.helpdesk.model.sla;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_sla_priority")
public class SlaPriority {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    private String description;
    
    @Column(nullable = false)
    private Integer level;
    
    private String color;
    
    @Column(nullable = false)
    private Boolean isActive;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}