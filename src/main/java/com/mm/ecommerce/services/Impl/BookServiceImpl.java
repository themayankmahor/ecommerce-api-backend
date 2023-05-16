package com.mm.ecommerce.services.Impl;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mm.ecommerce.entity.Book;
import com.mm.ecommerce.entity.Category;
import com.mm.ecommerce.entity.Product;
import com.mm.ecommerce.entity.User;
import com.mm.ecommerce.exceptions.ResourceNotFoundException;
import com.mm.ecommerce.payloads.BookDto;
import com.mm.ecommerce.repository.BookRepository;
import com.mm.ecommerce.repository.CategoryRepository;
import com.mm.ecommerce.repository.ProductRepository;
import com.mm.ecommerce.repository.UserRepository;
import com.mm.ecommerce.services.BookService;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BookDto createBook(BookDto bookDto, int userId) {
        
        //convert obj
        Book book = modelMapper.map(bookDto, Book.class);

        book.setDate(new Date());
        book.setImageName("default.jpg");

        //save book
        Book savedBook = bookRepository.save(book);

        ///get seller
        User seller = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Seller", "ID", userId));

        ///get category
        Category category = categoryRepository.findById(1).orElseThrow(() -> new ResourceNotFoundException("Category", "ID", 1));


        ///
        Product product = new Product();
        product.setSeller(seller);
        product.setCategory(category);
        product.setImageName("default.jpg");
        //product.setDate(bookDto.getDate());
        product.setDate(book.getDate());
        product.setPrice(bookDto.getPrice());
        product.setProductName(bookDto.getBookName());

        
        productRepository.save(product);

        return modelMapper.map(savedBook, BookDto.class);

    }
    
}
