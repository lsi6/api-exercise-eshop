package com.example.eshop.manager;

import com.example.eshop.db.entities.Product;
import com.example.eshop.exception.BadRequestException;
import com.example.eshop.exception.NotFoundException;
import com.example.eshop.model.dto.ProductDto;
import com.example.eshop.model.mapper.ProductMapper;
import com.example.eshop.model.enums.ProductLabelEnum;
import com.example.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ProductManagerImpl implements ProductManager
{
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

        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public ProductDto getProduct(final String id) throws NotFoundException, BadRequestException
    {
        Product productEntity = this.productService.getProduct(this.validateId(id));
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public ProductDto postProduct(final ProductDto productDto) throws BadRequestException
    {
        // Validate the posted product
        this.validateProductDto(productDto);
        // Map the product DTO into an entity object, so it can be saved to the database
        Product productEntity = ProductMapper.mapProductDtoToEntity(productDto);
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
        this.productService.deleteProduct(this.validateId(id));
    }

    /**
     * Method to validate the input string ID can be converted into an Integer.
     *
     * @param id - The ID string to check
     * @return - The converted Integer ID
     * @throws BadRequestException - Thrown if the input ID string cannot be converted into an Integer
     */
    private Integer validateId(String id) throws BadRequestException
    {
        try
        {
            return Integer.parseInt(id);
        }
        catch(NumberFormatException e)
        {
            throw new BadRequestException(String.format("Invalid ID: Could not convert ID '%s' into an Integer.", id));
        }
    }

    /**
     * Method to validate the product DTO before it's converted to an entity and saved to the database.
     *
     * @param productDto - The product DTO to be validated
     */
    private void validateProductDto(final ProductDto productDto) throws BadRequestException
    {
        // Validate the product labels
        for(String label: productDto.getLabels())
        {
            // If the response of ProductLabelEnum.getByLabel is null, no ProductLabelEnum could be found for that label.
            // In which case, that label is invalid so throw an exception.
            if(ProductLabelEnum.getByLabel(label) == null)
            {
                throw new BadRequestException(String.format("Unexpected product label '%s'. " +
                        "Product label must be one of %s", label, Arrays.toString(ProductLabelEnum.values())));
            }
        }

        if(productDto.getName().isEmpty())
        {
            throw new BadRequestException("Invalid product name: product name cannot be empty.");
        }

        if(productDto.getName().length() > PRODUCT_NAME_MAX_LENGTH)
        {
            throw new BadRequestException(String.format("Invalid product name: product name '%s' is too long. " +
                    "Must be less than %s characters", productDto.getName(), PRODUCT_NAME_MAX_LENGTH));
        }
    }
}
