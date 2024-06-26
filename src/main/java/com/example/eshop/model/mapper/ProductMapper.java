package com.example.eshop.model.mapper;

import com.example.eshop.db.entities.Product;
import com.example.eshop.db.entities.ProductLabel;
import com.example.eshop.exception.BadRequestException;
import com.example.eshop.model.dto.ProductDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Utility class to map between product DTOs and entities.
 */
public class ProductMapper
{
    /**
     * The date format to be used for the added_at value
     */
    private static final SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy/MM/dd");

    /**
     * Method to map a product DTO into a product entity.
     *
     * @param productDto - The product DTO to convert
     * @param productLabels - The product labels belonging to this product
     * @return - The converted product entity
     * @throws BadRequestException - Thrown if the product added_at date was in an unexpected format
     */
    public static Product mapProductDtoToEntity(ProductDto productDto, Set<ProductLabel> productLabels)
            throws BadRequestException
    {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        if(productDto.getAdded_at() != null)
        {
            try
            {
                product.setAddedAt(outputDateFormat.parse(productDto.getAdded_at()));
            }
            catch(ParseException e)
            {
                throw new BadRequestException(String.format(
                        "Unexpected date format. Date must be in format yyyy/MM/dd. Date was '%s'", productDto.getAdded_at()));
            }
        }
        product.setLabels(productLabels);
        return product;
    }

    /**
     * Method to map a product entity into a product DTO.
     *
     * @param product - The product entity to convert
     * @return The converted product DTO
     */
    public static ProductDto mapProductEntityToDto(Product product)
    {
        ProductDto productDto = new ProductDto();
        productDto.setProduct_id(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setAdded_at(outputDateFormat.format(product.getAddedAt()));

        List<String> labels = new ArrayList<>();
        for(ProductLabel productLabel: product.getLabels())
        {
            labels.add(productLabel.getLabelText());
        }
        productDto.setLabels(labels.toArray(new String[0]));
        return productDto;
    }

    /**
     * Method to map a list of product entities into a list of product DTOs.
     *
     * @param productList - The list of product entities to convert
     * @return - The converted product DTOs
     */
    public static List<ProductDto> mapProductEntityListToDtoList(List<Product> productList)
    {
        List<ProductDto> productDtos = new ArrayList<>();

        for(Product product: productList)
        {
            productDtos.add(ProductMapper.mapProductEntityToDto(product));
        }

        return productDtos;
    }
}
