package br.com.brisabr.helpdesk.repository;

import br.com.brisabr.helpdesk.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {}