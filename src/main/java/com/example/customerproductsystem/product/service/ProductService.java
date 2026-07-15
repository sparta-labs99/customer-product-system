package com.example.customerproductsystem.product.service;

import com.example.customerproductsystem.admin.entity.Admin;
import com.example.customerproductsystem.admin.repository.AdminRepository;
import com.example.customerproductsystem.auth.LoginAdmin;
import com.example.customerproductsystem.product.dto.*;
import com.example.customerproductsystem.product.entity.Categories;
import com.example.customerproductsystem.product.entity.Product;
import com.example.customerproductsystem.product.entity.ProductStatus;
import com.example.customerproductsystem.admin.error.AdminNotFoundException;
import com.example.customerproductsystem.product.error.ProductNotFoundException;
import com.example.customerproductsystem.product.repository.ProductRepository;
import com.example.customerproductsystem.review.dto.GetReviewResponse;
import com.example.customerproductsystem.review.dto.RatingCountDto;
import com.example.customerproductsystem.review.entity.Review;
import com.example.customerproductsystem.review.repository.ReviewRepository;
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
    private final AdminRepository adminRepository;
    private final ReviewRepository reviewRepository;

    // 상품 등록
    @Transactional
    public CreateProductResponse create (CreateProductRequest request, LoginAdmin sessionAdmin) {

        // enum
        Categories category =
                Categories.from(request.getCategory());

        ProductStatus status =
                ProductStatus.from(request.getStatus());

        Admin admin = adminRepository.findById(sessionAdmin.id()).orElseThrow(
                AdminNotFoundException::new);

        Product product = new Product(
                request.getName(),
                category,
                request.getPrice(),
                request.getStock(),
                status,
                admin
        );

        productRepository.save(product);

        return CreateProductResponse.from(product);
    }

    @Transactional(readOnly = true)
    public GetProductDetailResponse getOne (Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        // 최근 리뷰 3개 찾기
        List<Review> reviews = reviewRepository.findTop3ByProductIdOrderByCreatedAtDesc(id);
        List<GetReviewResponse> reviewDtos = new ArrayList<>();
        for (Review review : reviews) {
            reviewDtos.add(GetReviewResponse.from(review));
        }

        // 평점 별 리뷰 갯수 찾기
        List<RatingCountDto> counts = reviewRepository.countByRating(id);

        // 모든 리뷰 갯수
        long totalReviewCount = counts.stream()
                .mapToLong(RatingCountDto::count)
                .sum();

        // 모든 평점의 합
        long totalReviewRating = counts.stream()
                .mapToLong(dto -> dto.rating() * dto.count())
                .sum();

        // 평점 평균 (소수점 첫째 자리 자름)
        double averageReviewRating =
                Math.round(((double)totalReviewRating / totalReviewCount) * 10)/10.0;

        return GetProductDetailResponse.from(
                product, reviewDtos, counts, totalReviewCount, averageReviewRating);
    }

    @Transactional(readOnly = true)
    public Page<GetProductResponse> getAll(String keyword, String category, String status, Pageable pageable){

        Categories categories =
                (category == null || category.isEmpty())? null : Categories.from(category);

        ProductStatus productStatus =
                (status == null || status.isEmpty())? null : ProductStatus.from(status);

        Specification<Product> productSpecification = withCondition(keyword, categories, productStatus);

        Page<Product> products = productRepository.findAll(productSpecification, pageable);

        return products.map(GetProductResponse::from);
    }

    public Specification<Product> withCondition(
            String keyword,
            Categories categories,
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

            if (categories != null) {
                predicates.add(
                        cb.equal(root.get("category"), categories)
                );
            }

            if (status != null) {
                predicates.add(
                        cb.equal(root.get("status"), status)
                );
            }

            // status가 DELETED가 아닌 경우, DELETED 제외
            if (status != ProductStatus.DELETED) {
                predicates.add(
                        cb.notEqual(root.get("status"), ProductStatus.DELETED)
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
