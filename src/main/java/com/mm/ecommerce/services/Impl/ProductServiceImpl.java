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

import com.mm.ecommerce.entity.Category;
import com.mm.ecommerce.entity.Product;
import com.mm.ecommerce.entity.User;
import com.mm.ecommerce.exceptions.ResourceNotFoundException;
import com.mm.ecommerce.payloads.ProductDto;
import com.mm.ecommerce.payloads.ProductResponse;
import com.mm.ecommerce.repository.CategoryRepository;
import com.mm.ecommerce.repository.ProductRepository;
import com.mm.ecommerce.repository.UserRepository;
import com.mm.ecommerce.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

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

    ///Update product
    @Override
    public ProductDto updateProduct(ProductDto productDto, int productId) {
        
        //
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "ID", productId));
        //
        Category category = categoryRepository.findById(productDto.getCategory().getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category", "ID", productId));

        //
        product.setCategory(category);
        product.setImageName(productDto.getImageName());
        product.setPrice(productDto.getPrice());
        product.setProductName(productDto.getProductName());

        Product updatedProduct = productRepository.save(product);

        return modelMapper.map(updatedProduct, ProductDto.class);
    }

    ///Get product By Id
    @Override
    public ProductDto getProductById(int productId) {

        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "ID", productId));

        return modelMapper.map(product, ProductDto.class);

    }

    @Override
    public ProductResponse getAllProduct(int pageNumber, int pageSize, String sortBy, String sortDir, int categoryId, int sellerId) {
        
        //sort dir
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        //
        Page<Product> pageProduct;

        //
        if (categoryId != 0 && sellerId != 0)
        {
            System.out.println("----------------------");
            System.out.println("CATEGORY AND SELLER");
            System.out.println("----------------------");
            Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "ID", categoryId));
            User seller =  userRepository.findById(sellerId).orElseThrow(() -> new ResourceNotFoundException("Seller", "ID", sellerId));
            pageProduct = productRepository.findBySellerAndCategory(seller, category, pageable);
            
        }
        else if (sellerId != 0)
        {
            System.out.println("----------------------");
            System.out.println("SELLER");
            System.out.println("----------------------");
            User seller =  userRepository.findById(sellerId).orElseThrow(() -> new ResourceNotFoundException("Seller", "ID", sellerId));
            pageProduct = productRepository.findBySeller(seller, pageable);
        }
        else if (categoryId != 0)
        {
            System.out.println("----------------------");
            System.out.println("CATEGORY");
            System.out.println("----------------------");
            Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "ID", categoryId));
            pageProduct = productRepository.findByCategory(category, pageable);
        }
        else
        {
            System.out.println("----------------------");
            System.out.println("ALL");
            System.out.println("----------------------");
            pageProduct = productRepository.findAll(pageable);
        }

        
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


    ///Get product by Name
    @Override
    public ProductDto getProductByName(String productName) {
        
        Product product = productRepository.findByProductName(productName);

        return modelMapper.map(product, ProductDto.class);

    }



}
