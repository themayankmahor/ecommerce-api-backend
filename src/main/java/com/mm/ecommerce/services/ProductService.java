package com.mm.ecommerce.services;



import com.mm.ecommerce.payloads.ProductDto;
import com.mm.ecommerce.payloads.ProductResponse;

public interface ProductService {
    
    ///Create product
    ProductDto createProduct(ProductDto productDto);

    ///Update Product
    ProductDto updateProduct(ProductDto productDto, int productId);

    ///get product by Id
    ProductDto getProductById(int productId);

    ///Get single product
    ProductResponse getAllProduct(int pageNumber, int pageSize, String sortBy, String sortDir, int categoryId, int sellerId);

    ProductDto getProductByName(String productName);

}
