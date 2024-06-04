package com.example.eshop.controller;

import com.example.eshop.exception.BadRequestException;
import com.example.eshop.exception.NotFoundException;
import com.example.eshop.manager.CartManager;
import com.example.eshop.model.dto.CartDto;
import com.example.eshop.model.dto.CartProductDto;
import com.example.eshop.model.dto.CheckedOutCartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class CartController
{
    /**
     * Product manager class
     */
    private final CartManager cartManager;

    private final HttpHeaders httpHeaders = new HttpHeaders();

    /**
     * Constructor
     *
     * @param cartManager - The cart manager class to be autowired
     */
    @Autowired
    public CartController(final CartManager cartManager)
    {
        this.cartManager = cartManager;
    }

    @GetMapping(value = "/carts", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CartDto>> listCarts()
    {
        List<CartDto> carts = this.cartManager.getAllCarts();
        return ResponseEntity.status(HttpStatus.OK).headers(this.httpHeaders).body(carts);
    }

    @PostMapping(value = "/carts", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CartDto> postCart()
    {
        CartDto newCartDto = this.cartManager.createEmptyCart();
        return ResponseEntity.status(HttpStatus.CREATED).headers(this.httpHeaders).body(newCartDto);
    }

    @PostMapping(value = "/carts/{id}/checkout", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CheckedOutCartDto> checkoutCart(@PathVariable String id)
            throws BadRequestException, NotFoundException
    {
        CheckedOutCartDto checkedOutCartDto = this.cartManager.checkoutCart(id);
        return ResponseEntity.status(HttpStatus.OK).headers(this.httpHeaders).body(checkedOutCartDto);
    }

    @PutMapping(value = "/carts/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CartDto> putCart(@PathVariable String id, @RequestBody CartProductDto[] cartProducts)
            throws BadRequestException, NotFoundException
    {
        CartDto newProduct = this.cartManager.putCart(id, cartProducts);
        return ResponseEntity.status(HttpStatus.OK).headers(this.httpHeaders).body(newProduct);
    }

}
