package br.com.brisabr.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brisabr.helpdesk.model.product_category.ProductCategory;

public interface ProductCategoryRespository extends JpaRepository<ProductCategory, Long> {}
