package com.example.eshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EshopController
{
    @GetMapping("/")
    public String index()
    {
        return "Hello!";
    }

    @GetMapping("/products")
    public ResponseEntity<String> listProducts()
    {
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<String> getProduct(@PathVariable String id)
    {
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<String> postProduct()
    {
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<> deleteProduct(@PathVariable String id)
    {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
