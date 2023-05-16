package com.mm.ecommerce.payloads;

import com.mm.ecommerce.entity.Product;
import com.mm.ecommerce.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {
 
    private int cartId;

    private User user;

    private Product product;

    private int quantity;

}
