package com.example.eshop.manager;

import com.example.eshop.db.entities.Product;
import com.example.eshop.db.entities.ProductLabel;
import com.example.eshop.exception.BadRequestException;
import com.example.eshop.exception.NotFoundException;
import com.example.eshop.model.dto.ProductDto;
import com.example.eshop.model.mapper.ProductMapper;
import com.example.eshop.service.ProductService;
import com.example.eshop.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ProductManagerImpl implements ProductManager
{
    /**
     * The date format to be used
     */
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * The maximum length for a product name
     */
    private static final int PRODUCT_NAME_MAX_LENGTH = 200;

    /**
     * Product Service object
     */
    private final ProductService productService;

    @Autowired
    public ProductManagerImpl(ProductService productService)
    {
        this.productService = productService;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<ProductDto> getAllProducts()
    {
        List<Product> products = this.productService.getAllProducts();
        return ProductMapper.mapProductEntityListToDtoList(products);
    }

    /**
     * @inheritDoc
     */
    @Override
    public ProductDto getProduct(final String id) throws NotFoundException, BadRequestException
    {
        Product productEntity = this.productService.getProduct(Util.validateId(id));
        return ProductMapper.mapProductEntityToDto(productEntity);
    }

    /**
     * @inheritDoc
     */
    @Override
    public ProductDto postProduct(final ProductDto productDto) throws BadRequestException, ParseException
    {
        // Validate the posted product
        Set<ProductLabel> productLabels = this.validateProductDto(productDto);
        // Map the product DTO into an entity object, so it can be saved to the database
        Product productEntity = ProductMapper.mapProductDtoToEntity(productDto, productLabels);
        // Set today's date for the product added_at date
        productEntity.setAddedAt(dateFormat.parse(LocalDate.now().toString()));
        // Save the entity
        Product savedProductEntity = this.productService.persistProduct(productEntity);
        // Map the saved entity into a DTO, so it can be returned
        return ProductMapper.mapProductEntityToDto(savedProductEntity);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void deleteProduct(final String id) throws BadRequestException
    {
        this.productService.deleteProduct(Util.validateId(id));
    }

    /**
     * Method to validate the product DTO before it's converted to an entity and saved to the database.
     * Also fetches the product label entities.
     *
     * @param productDto - The product DTO to be validated
     * @return - A set of product labels
     */
    private Set<ProductLabel> validateProductDto(final ProductDto productDto) throws BadRequestException
    {
        // Validate the product labels
        Set<ProductLabel> productLabels = new HashSet<>();
        for(String label: productDto.getLabels())
        {
            ProductLabel productLabel = this.productService.findLabelByText(label);
            // If the product label does not exist, throw a BadRequestException
            if(productLabel == null)
            {
                throw new BadRequestException(String.format("Invalid product label: product label was not one of the allowed values. " +
                        "Label was '%s'.", label));
            }
            productLabels.add(productLabel);
        }

        if(productDto.getName().isEmpty())
        {
            throw new BadRequestException("Invalid product name: product name cannot be empty.");
        }

        if(productDto.getName().length() > PRODUCT_NAME_MAX_LENGTH)
        {
            throw new BadRequestException(String.format("Invalid product name: product name '%s' is too long. " +
                    "Must be less than %s characters.", productDto.getName(), PRODUCT_NAME_MAX_LENGTH));
        }

        if(this.productService.findByName(productDto.getName()) != null)
        {
            throw new BadRequestException(String.format(
                    "Invalid product name: a product with name '%s' already exists.", productDto.getName()));
        }

        return productLabels;
    }
}
