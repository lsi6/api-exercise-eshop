package com.example.eshop.service;

import com.example.eshop.db.entities.Product;
import com.example.eshop.db.entities.ProductLabel;
import com.example.eshop.exception.BadRequestException;
import com.example.eshop.exception.NotFoundException;

import java.util.List;

public interface ProductService
{
    /**
     * Method to get all the products in the database.
     *
     * @return - A list of all the products in the database
     */
    List<Product> getAllProducts();

    /**
     * Method to get a product by ID.
     *
     * @param id - The ID of the product to get
     * @return - The found product
     * @throws NotFoundException - Thrown when a product could not be found with the given ID
     */
    Product getProduct(final int id) throws NotFoundException;

    /**
     * Method to save a new product to the database.
     *
     * @param product - The product entity to post.
     * @return - The newly created product.
     */
    Product persistProduct(final Product product);

    /**
     * Method to delete a product by ID.
     *
     * @param id - The ID of the product to delete
     */
    void deleteProduct(final int id);

    /**
     * Method to find a product label by its label text. Returns null if no label is found
     *
     * @param labelText - The label text to search for
     */
    ProductLabel findLabelByText(final String labelText);

    /**
     * Method to find a product by its name.
     *
     * @param name - The product name to search by
     * @return - The product with the matching name or null if none is found
     */
    Product findByName(final String name);
}
