package com.example.customerproductsystem.product.service;

import com.example.customerproductsystem.product.dto.*;
import com.example.customerproductsystem.product.entity.Product;
import com.example.customerproductsystem.product.entity.ProductEnum;
import com.example.customerproductsystem.product.error.ProductNotFoundException;
import com.example.customerproductsystem.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public CreateProductResponse create (CreateProductRequest request) {

        ProductEnum.Category category =
                ProductEnum.Category.from(request.getCategory());

        ProductEnum.ProductStatus status =
                ProductEnum.ProductStatus.from(request.getStatus());

        Product product = new Product(
                request.getName(),
                category,
                request.getPrice(),
                request.getStock(),
                status
        );

        productRepository.save(product);

        return new CreateProductResponse(
                product.getId(),
                product.getName(),
                product.getCategory(),
                product.getPrice(),
                product.getStock(),
                product.getStatus(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }

    @Transactional(readOnly = true)
    public GetProductResponse getOne (Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        return new GetProductResponse(
                product.getId(),
                product.getName(),
                product.getCategory(),
                product.getPrice(),
                product.getStock(),
                product.getStatus(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }

    @Transactional
    public UpdateProductResponse update(Long id, UpdateProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        ProductEnum.Category category =
                ProductEnum.Category.from(request.getCategory());

        product.update(request.getName(), category, request.getPrice());

        // dirty checking에 의해 자동으로 업데이트 되지만 가독성을 위해서 작성
        productRepository.save(product);

        return new UpdateProductResponse(
                product.getId(),
                product.getName(),
                product.getCategory(),
                product.getPrice(),
                product.getStock(),
                product.getStatus(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }

    @Transactional
    public UpdateProductResponse updateStock(Long id, int newStock) {

        Product product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        product.updateStock(newStock);

        productRepository.save(product);

        return new UpdateProductResponse(
                product.getId(),
                product.getName(),
                product.getCategory(),
                product.getPrice(),
                product.getStock(),
                product.getStatus(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }

    @Transactional
    public UpdateProductResponse updateStatus(Long id, String newStatus) {

        Product product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        ProductEnum.ProductStatus status =
                ProductEnum.ProductStatus.from(newStatus);

        product.updateStatus(status);

        productRepository.save(product);

        return new UpdateProductResponse(
                product.getId(),
                product.getName(),
                product.getCategory(),
                product.getPrice(),
                product.getStock(),
                product.getStatus(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }


    @Transactional
    public void delete(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        product.updateStatus(ProductEnum.ProductStatus.DELETED);

        productRepository.save(product);

    }
}
