package com.example.customerproductsystem.product.service;

import com.example.customerproductsystem.product.dto.*;
import com.example.customerproductsystem.product.entity.Categories;
import com.example.customerproductsystem.product.entity.Product;
import com.example.customerproductsystem.product.entity.ProductStatus;
import com.example.customerproductsystem.product.error.ProductNotFoundException;
import com.example.customerproductsystem.product.repository.ProductRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public CreateProductResponse create (CreateProductRequest request) {

        Categories category =
                Categories.from(request.getCategory());

        ProductStatus status =
                ProductStatus.from(request.getStatus());

        Product product = new Product(
                request.getName(),
                category,
                request.getPrice(),
                request.getStock(),
                status
        );

        productRepository.save(product);

        return CreateProductResponse.from(product);
    }

    @Transactional(readOnly = true)
    public GetProductResponse getOne (Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        return GetProductResponse.from(product);
    }

    @Transactional(readOnly = true)
    public List<GetProductResponse> getAll(String keyword, String status, Pageable pageable){

        ProductStatus productStatus = ProductStatus.from(status);

        Specification<Product> productSpecification = withCondition(keyword, productStatus);

        Page<Product> products = productRepository.findAll(productSpecification, pageable);

        List<GetProductResponse> dtos = new ArrayList<>();

        for (Product product : products) {

            GetProductResponse dto = GetProductResponse.from(product);

            dtos.add(dto);
        }

        return dtos;
    }

    public static Specification<Product> withCondition(
            String keyword,
            ProductStatus status
    ) {

        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (keyword != null && !keyword.isBlank()) {
                predicates.add(
                        cb.like(root.get("name"),
                                "%" + keyword + "%")
                );
            }

            if (status != null) {
                predicates.add(
                        cb.equal(root.get("status"), status)
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Transactional
    public UpdateProductResponse update(Long id, UpdateProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        Categories category =
                Categories.from(request.getCategory());

        product.update(request.getName(), category, request.getPrice());

        // dirty checking에 의해 자동으로 업데이트 되지만 가독성을 위해서 작성
        productRepository.save(product);

        return UpdateProductResponse.from(product);
    }

    @Transactional
    public UpdateProductResponse updateStock(Long id, int newStock) {

        Product product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        product.updateStock(newStock);

        productRepository.save(product);

        return UpdateProductResponse.from(product);
    }

    @Transactional
    public UpdateProductResponse updateStatus(Long id, String newStatus) {

        Product product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        ProductStatus status =
                ProductStatus.from(newStatus);

        product.updateStatus(status);

        productRepository.save(product);

        return UpdateProductResponse.from(product);
    }

    @Transactional
    public void delete(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        product.updateStatus(ProductStatus.DELETED);

        productRepository.save(product);
    }
}
