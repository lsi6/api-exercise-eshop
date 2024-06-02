package com.example.eshop.controller;

import com.example.eshop.exception.BadRequestException;
import com.example.eshop.exception.NotFoundException;
import com.example.eshop.manager.ProductManager;
import com.example.eshop.model.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class ProductController
{
    /**
     * Product manager class
     */
    private final ProductManager productManager;

    private final HttpHeaders httpHeaders = new HttpHeaders();

    /**
     * Constructor
     *
     * @param productManager - The product manager class to be autowired
     */
    @Autowired
    public ProductController(final ProductManager productManager)
    {
        this.productManager = productManager;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> listProducts()
    {
        List<ProductDto> products = this.productManager.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).headers(this.httpHeaders).body(products);
    }

    @GetMapping(value = "/products/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> getProduct(@PathVariable String id) throws NotFoundException, BadRequestException
    {
        ProductDto productDto = this.productManager.getProduct(id);
        return ResponseEntity.status(HttpStatus.OK).headers(this.httpHeaders).body(productDto);
    }

    @PostMapping(value = "/products", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> postProduct(@RequestBody ProductDto inputProductDto)
            throws BadRequestException, ParseException
    {
        ProductDto newProduct = this.productManager.postProduct(inputProductDto);
        return ResponseEntity.status(HttpStatus.CREATED).headers(this.httpHeaders).body(newProduct);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) throws BadRequestException
    {
        this.productManager.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).headers(this.httpHeaders).build();
    }

}
