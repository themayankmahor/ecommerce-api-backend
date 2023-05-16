package com.mm.ecommerce.payloads;

import com.mm.ecommerce.entity.CartItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponse {
    
    private int cartItemId;
    private int productId;
    private String productName;
    private long productPrice;
    private int quantity;
 
    public CartItemResponse(CartItem cartItem) {
        this.cartItemId = cartItem.getCartId();
        this.productId = cartItem.getProduct().getProductId();
        this.productName = cartItem.getProduct().getProductName();
        this.productPrice = cartItem.getProduct().getPrice();
        this.quantity = cartItem.getQuantity();
    }

}
