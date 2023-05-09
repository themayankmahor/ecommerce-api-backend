package com.mm.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mm.ecommerce.payloads.ReviewDto;
import com.mm.ecommerce.services.ReviewService;

@RestController
@RequestMapping("/api/v1/")
@PreAuthorize("hasRole('NORMAL')")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    
    @PostMapping("/product/{productId}/reviews")
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto, @PathVariable("productId") int productId)
    {
        ReviewDto review = reviewService.createReview(reviewDto, productId);

        return new ResponseEntity<ReviewDto>(review, HttpStatus.CREATED);
    }

}
