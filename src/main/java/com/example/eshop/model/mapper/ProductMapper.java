package com.example.eshop.model.mapper;

import com.example.eshop.db.entities.Product;
import com.example.eshop.model.dto.ProductDto;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper
{
    public static Product mapProductDtoToEntity(ProductDto productDto)
    {
        return null;
    }

    public static ProductDto mapProductEntityToDto(Product product)
    {
        return null;
    }

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
