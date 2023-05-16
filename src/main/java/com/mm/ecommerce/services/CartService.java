package com.mm.ecommerce.services;

import java.util.List;

import com.mm.ecommerce.entity.CartItem;
import com.mm.ecommerce.entity.Product;
import com.mm.ecommerce.entity.User;

public interface CartService {
    
    //add item to cart
    void addItem(User user, Product product, int quantity);

    //update item
    void updateItem(int cartItemId, int quantity);
    
    //remove item
    void removeItem(int cartItemId);

    //get all cart items
    List<CartItem> getCartItem(User user);

}
