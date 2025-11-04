package br.com.brisabr.helpdesk.model.product;

import br.com.brisabr.helpdesk.model.product_category.ProductCategory;
import br.com.brisabr.helpdesk.model.sla.Sla;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_product")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID internalCode; //AUTO-GENERATED
    private String name;
    private Boolean isActive;
    private String description;
    private Boolean isPhysical;

    @ManyToOne @JoinColumn(name="category_id")
    private ProductCategory category;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name="sla_id") @JsonIgnore
    private Sla sla;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}