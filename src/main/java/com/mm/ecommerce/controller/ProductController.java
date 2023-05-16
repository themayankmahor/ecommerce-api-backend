package com.mm.ecommerce.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mm.ecommerce.config.AppConstants;
import com.mm.ecommerce.payloads.ProductDto;
import com.mm.ecommerce.payloads.ProductResponse;
import com.mm.ecommerce.services.FileService;
import com.mm.ecommerce.services.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    ///Get all products
    @GetMapping("/all-products")
    public ResponseEntity<ProductResponse> getAllProducts(
        @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNumber,
        @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) int pageSize,
        @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
        @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir,
        @RequestParam(value = "categoryId", defaultValue = AppConstants.SORT_BY_CATEGORYID, required = false) int categoryId,
        @RequestParam(value = "sellerId", defaultValue = AppConstants.SORT_BY_SELLERID, required = false) int sellerId
    )
    {
        ProductResponse productResponse = productService.getAllProduct(pageNumber, pageSize, sortBy, sortDir, categoryId, sellerId);

        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
    }

    ///get products by name
    @GetMapping("/getByName/{productName}")
    public ResponseEntity<ProductDto> getProductByName(@PathVariable("productName") String productName)
    {
        ProductDto productDto = productService.getProductByName(productName);

        return new ResponseEntity<ProductDto>(productDto, HttpStatus.OK);
    }


    ///upload product image
    @PostMapping("/image/upload/{productId}")
    public ResponseEntity<ProductDto> uploadImage(
        @RequestParam("image") MultipartFile image,
        @PathVariable("productId") int productId
    ) throws IOException
    {
        ProductDto productDto = productService.getProductById(productId);
        String fileName = fileService.uploadImage(path, image);

        productDto.setImageName(fileName);
        ProductDto updatedProduct =  productService.updateProduct(productDto, productId);
        
        return new ResponseEntity<ProductDto>(updatedProduct, HttpStatus.OK);
    }


    ///serve image
    @GetMapping(value = "/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
        @PathVariable("imageName") String imageName,
        HttpServletResponse response
    ) throws IOException
    {
        InputStream resource = fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
    }


}
