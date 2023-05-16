package com.mm.ecommerce.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mm.ecommerce.entity.CartItem;
import com.mm.ecommerce.entity.Product;
import com.mm.ecommerce.entity.User;
import com.mm.ecommerce.exceptions.ResourceNotFoundException;
import com.mm.ecommerce.repository.CartRepository;
import com.mm.ecommerce.services.CartService;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;


    //Add item
    @Override
    public void addItem(User user, Product product, int quantity) {

        CartItem cartItem = cartRepository.findByUserAndProduct(user, product);
        
        //
        if (cartItem == null)
        {
            cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product);
        }

        cartItem.setQuantity(cartItem.getQuantity() + quantity);
        cartRepository.save(cartItem);

    }

    //Update Item
    @Override
    public void updateItem(int cartItemId, int quantity) {
        
        CartItem cartItem = cartRepository.findById(cartItemId).orElseThrow(() -> new ResourceNotFoundException("Cart item", "ID", cartItemId));
        
        cartItem.setQuantity(quantity);
        cartRepository.save(cartItem);
    }

    //Remove Item
    @Override
    public void removeItem(int cartItemId) {
        
        cartRepository.deleteById(cartItemId);

    }

    //Get all cart items
    @Override
    public List<CartItem> getCartItem(User user) {
        
        return cartRepository.findByUser(user);
    }


    
}
