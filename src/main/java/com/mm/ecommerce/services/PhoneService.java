package com.mm.ecommerce.services;

import com.mm.ecommerce.payloads.PhoneDto;

public interface PhoneService {
    
    ///Create Phone
    PhoneDto createPhone(PhoneDto phoneDto, int sellerId);

}
