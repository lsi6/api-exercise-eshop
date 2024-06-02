package com.example.eshop.service;

import com.example.eshop.db.entities.Product;
import com.example.eshop.db.repos.ProductRepo;
import com.example.eshop.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService
{
    /**
     * Class defining methods to interact with the product table
     */
    private final ProductRepo productRepo;

    /**
     * Constructor
     *
     * @param productRepo - The product repository to autowire
     */
    @Autowired
    public ProductServiceImpl(final ProductRepo productRepo)
    {
        this.productRepo = productRepo;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Product> getAllProducts()
    {
        return this.productRepo.findAll();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Product getProduct(final int id) throws NotFoundException
    {
        Optional<Product> productEntity = this.productRepo.findById(id);
        if(productEntity.isEmpty())
        {
            throw new NotFoundException("product", String.valueOf(id));
        }
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Product persistProduct(final Product product)
    {
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void deleteProduct(final int id)
    {

    }
}
