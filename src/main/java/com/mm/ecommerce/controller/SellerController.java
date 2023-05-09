package com.mm.ecommerce.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mm.ecommerce.payloads.BookDto;
import com.mm.ecommerce.payloads.PhoneDto;
import com.mm.ecommerce.services.BookService;
import com.mm.ecommerce.services.PhoneService;

@RestController
@RequestMapping("/api/v1/seller")
@PreAuthorize("hasRole('SELLER')")
public class SellerController {
    
    @Autowired
    private BookService bookService;

    @Autowired
    private PhoneService phoneService;

    ///Add Book
    @PostMapping("/{sellerId}/add-book")
    public ResponseEntity<BookDto> addBook(@Valid @RequestBody BookDto bookDto, @PathVariable("sellerId") int sellerId)
    {
        BookDto book = bookService.createBook(bookDto, sellerId);

        return new ResponseEntity<BookDto>(book, HttpStatus.CREATED);
    }

    ///Add Phone
    @PostMapping("/{sellerId}/add-phone")
    public ResponseEntity<PhoneDto> addPhone(@Valid @RequestBody PhoneDto phoneDto, @PathVariable("sellerId") int sellerId)
    {
        PhoneDto phone = phoneService.createPhone(phoneDto, sellerId);

        return new ResponseEntity<PhoneDto>(phone, HttpStatus.CREATED);
    }

}
