package com.mm.ecommerce.services;

import com.mm.ecommerce.payloads.ReviewDto;

public interface ReviewService {
    
    ///Create review
    ReviewDto createReview(ReviewDto reviewDto, int productId);

}
