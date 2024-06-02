package com.example.eshop.service;

import com.example.eshop.db.entities.Product;
import com.example.eshop.db.entities.ProductLabel;
import com.example.eshop.db.repos.LabelRepo;
import com.example.eshop.db.repos.ProductRepo;
import com.example.eshop.exception.BadRequestException;
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
     * Class defining methods to interact with the product table
     */
    private final LabelRepo labelRepo;

    /**
     * Constructor
     *
     * @param productRepo - The product repository to autowire
     */
    @Autowired
    public ProductServiceImpl(final ProductRepo productRepo, final LabelRepo labelRepo)
    {
        this.productRepo = productRepo;
        this.labelRepo = labelRepo;
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
        return productEntity.get();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Product persistProduct(final Product product)
    {
        return this.productRepo.save(product);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void deleteProduct(final int id)
    {
        this.productRepo.deleteById(id);
    }

    /**
     * @inheritDoc
     */
    @Override
    public ProductLabel findLabelByText(final String labelText)
    {
        return this.labelRepo.findLabelByText(labelText);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Product findByName(final String name)
    {
        return this.productRepo.findProductByName(name);
    }
}
