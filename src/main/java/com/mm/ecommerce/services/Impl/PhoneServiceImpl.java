package com.mm.ecommerce.services.Impl;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mm.ecommerce.entity.Category;
import com.mm.ecommerce.entity.Phone;
import com.mm.ecommerce.entity.Product;
import com.mm.ecommerce.entity.User;
import com.mm.ecommerce.exceptions.ResourceNotFoundException;
import com.mm.ecommerce.payloads.PhoneDto;
import com.mm.ecommerce.repository.CategoryRepository;
import com.mm.ecommerce.repository.PhoneRepository;
import com.mm.ecommerce.repository.ProductRepository;
import com.mm.ecommerce.repository.UserRepository;
import com.mm.ecommerce.services.PhoneService;

@Service
public class PhoneServiceImpl implements PhoneService{

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PhoneDto createPhone(PhoneDto phoneDto, int sellerId) {

        //convert obj
        Phone phone = modelMapper.map(phoneDto, Phone.class);

        phone.setDate(new Date());

        //save book
        Phone savedPhone = phoneRepository.save(phone);

        ///get seller
        User seller = userRepository.findById(sellerId).orElseThrow(() -> new ResourceNotFoundException("Seller", "ID", sellerId));

        ///get category
        Category category = categoryRepository.findById(2).orElseThrow(() -> new ResourceNotFoundException("Category", "ID", 2));

        ///
        Product product = new Product();
        product.setSeller(seller);
        product.setCategory(category);
        product.setImageName("default.png");
        //product.setDate(bookDto.getDate());
        product.setDate(phone.getDate());
        product.setPrice(phoneDto.getPrice());
        product.setProductName(phoneDto.getPhoneName());

        
        productRepository.save(product);

        return modelMapper.map(savedPhone, PhoneDto.class);

    }

}
