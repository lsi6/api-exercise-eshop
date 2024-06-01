package com.example.eshop.controller;

import com.example.eshop.exception.BadRequestException;
import com.example.eshop.exception.NotFoundException;
import com.example.eshop.manager.CartManager;
import com.example.eshop.manager.ProductManager;
import com.example.eshop.model.dto.ProductDto;
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

}
