package com.mm.ecommerce.services.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mm.ecommerce.entity.Product;
import com.mm.ecommerce.entity.Review;
import com.mm.ecommerce.exceptions.ResourceNotFoundException;
import com.mm.ecommerce.payloads.ReviewDto;
import com.mm.ecommerce.repository.ProductRepository;
import com.mm.ecommerce.repository.ReviewRepository;
import com.mm.ecommerce.services.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ReviewDto createReview(ReviewDto reviewDto, int productId) {

        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "ID", productId));

        Review review = modelMapper.map(reviewDto, Review.class);
        review.setProduct(product);

        Review savedReview = reviewRepository.save(review);

        return modelMapper.map(savedReview, ReviewDto.class);
    }
    
}
