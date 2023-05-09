package com.mm.ecommerce.services;


import com.mm.ecommerce.payloads.ProductDto;
import com.mm.ecommerce.payloads.ProductResponse;

public interface ProductService {
    
    ///Create product
    ProductDto createProduct(ProductDto productDto);

    ///Get single product
    ProductResponse getAllProduct(int pageNumber, int pageSize, String sortBy, String sortDir);

}
