package com.mm.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mm.ecommerce.config.AppConstants;
import com.mm.ecommerce.payloads.ProductResponse;
import com.mm.ecommerce.services.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all-products")
    public ResponseEntity<ProductResponse> getAllProducts(
        @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNumber,
        @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) int pageSize,
        @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
        @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir 
    )
    {
        ProductResponse productResponse = productService.getAllProduct(pageNumber, pageSize, sortBy, sortDir);

        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
    }

}
