package com.mm.ecommerce.services;

import com.mm.ecommerce.payloads.BookDto;

public interface BookService {

    ///create book
    BookDto createBook(BookDto bookDto, int userId);
    
}
