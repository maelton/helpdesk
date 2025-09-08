package br.com.brisabr.helpdesk.model.product;

import br.com.brisabr.helpdesk.model.product.category.ProductCategory;

import jakarta.persistence.Entity;
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}