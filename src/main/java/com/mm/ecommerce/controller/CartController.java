package com.mm.ecommerce.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mm.ecommerce.entity.CartItem;
import com.mm.ecommerce.entity.Product;
import com.mm.ecommerce.entity.User;
import com.mm.ecommerce.payloads.CartItemRequest;
import com.mm.ecommerce.payloads.CartItemResponse;
import com.mm.ecommerce.payloads.ProductDto;
import com.mm.ecommerce.services.CartService;
import com.mm.ecommerce.services.ProductService;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;
 
 
    @PostMapping("/add-to-cart")
    public ResponseEntity<Void> addItemToCart(@RequestBody CartItemRequest cartItemRequest, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        ProductDto productDto = productService.getProductById(cartItemRequest.getProductId());
        Product product = modelMapper.map(productDto, Product.class);
        cartService.addItem(user, product, cartItemRequest.getQuantity());
        return ResponseEntity.ok().build();
    }
 
    @PutMapping("update-cart/{cartItemId}")
    public ResponseEntity<Void> updateCartItem(@PathVariable int cartItemId, @RequestBody CartItemRequest cartItemRequest) {
        cartService.updateItem(cartItemId, cartItemRequest.getQuantity());
        return ResponseEntity.ok().build();
    }
 
    @DeleteMapping("remove-from-cart/{cartItemId}")
    public ResponseEntity<Void> removeCartItem(@PathVariable int cartItemId) {
        cartService.removeItem(cartItemId);
        return ResponseEntity.ok().build();
    }
 
    @GetMapping("/cart-items")
    public List<CartItemResponse> getCartItems(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        List<CartItem> cartItems = cartService.getCartItem(user);
        return cartItems.stream().map(CartItemResponse::new).collect(Collectors.toList());
    }

}