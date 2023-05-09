package com.mm.ecommerce.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mm.ecommerce.entity.Product;
import com.mm.ecommerce.payloads.ProductDto;
import com.mm.ecommerce.payloads.ProductResponse;
import com.mm.ecommerce.repository.ProductRepository;
import com.mm.ecommerce.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        
        //convert obj
        Product product = modelMapper.map(productDto, Product.class);

        //save product
        Product savedProduct = productRepository.save(product);

        return modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public ProductResponse getAllProduct(int pageNumber, int pageSize, String sortBy, String sortDir) {
        
        //sort dir
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        //
		// if (sortDir.equalsIgnoreCase("asc")) {
		// 	sort = Sort.by(sortBy).ascending();
		// } else {
		// 	sort = Sort.by(sortBy).descending();
		// }

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);

        Page<Product> pageProduct = productRepository.findAll(p);
        List<Product> allProduct = pageProduct.getContent();

        //
        List<ProductDto> productDtos = allProduct.stream().map((product) -> modelMapper.map(product, ProductDto.class))
        .collect(Collectors.toList());

        ProductResponse productResponse = new ProductResponse();

        productResponse.setProducts(productDtos);
        productResponse.setPageNumber(pageProduct.getNumber());
        productResponse.setPageSize(pageProduct.getSize());
        productResponse.setTotalElements(pageProduct.getTotalElements());
        productResponse.setTotalPage(pageProduct.getTotalPages());
        productResponse.setLastPage(pageProduct.isLast());

        return productResponse;
    }
    
}
