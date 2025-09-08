package br.com.brisabr.helpdesk.service;

import br.com.brisabr.helpdesk.model.product.Product;
import br.com.brisabr.helpdesk.model.product.category.ProductCategory;
import br.com.brisabr.helpdesk.model.product.dto.ProductCreateDTO;
import br.com.brisabr.helpdesk.repository.ProductCategoryRespository;
import br.com.brisabr.helpdesk.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryRespository productCategoryRepository;

    @Transactional
    public Product create(ProductCreateDTO dto) {
        Product product = toEntity(dto);
        return productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public Product getById(Long id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    private Product toEntity(ProductCreateDTO dto) {
        Product product = new Product();
        product.setName(dto.name());
        product.setIsActive(dto.isActive() != null && dto.isActive());
        product.setDescription(dto.description());
        product.setIsPhysical(dto.isPhysical());

        ProductCategory category = productCategoryRepository.findById(dto.categoryId())
            .orElseThrow(() -> new RuntimeException("Product category ID not found"));
        
        product.setCategory(category);
        // internalCode, createdAt, and updatedAt can be handled by the persistence layer or listeners
        return product;
    }
}