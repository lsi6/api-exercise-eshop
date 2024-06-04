package com.example.eshop.util;

import com.example.eshop.db.entities.Product;
import com.example.eshop.db.entities.ProductLabel;
import com.example.eshop.db.repos.CartProductRepo;
import com.example.eshop.db.repos.CartRepo;
import com.example.eshop.db.repos.LabelRepo;
import com.example.eshop.db.repos.ProductRepo;
import com.example.eshop.model.dto.ProductDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Utility class to load test data.
 */
public class DataLoader
{
    /**
     * The date format to be used.
     */
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * The product repository
     */
    private final ProductRepo productRepo;

    /**
     * The cart repository
     */
    private final CartRepo cartRepo;

    /**
     * The cart product repository
     */
    private final CartProductRepo cartProductRepo;

    /**
     * The label repository
     */
    private final LabelRepo labelRepo;

    /**
     * Constructor
     *
     * @param productRepo - The product repository
     * @param cartRepo - The cart repository
     * @param cartProductRepo - The cart product repository
     * @param labelRepo - The label repository
     */
    public DataLoader(ProductRepo productRepo,
                      CartRepo cartRepo,
                      CartProductRepo cartProductRepo,
                      LabelRepo labelRepo)
    {
        this.productRepo = productRepo;
        this.cartRepo = cartRepo;
        this.cartProductRepo = cartProductRepo;
        this.labelRepo = labelRepo;
    }

    /**
     * Method to clear the database.
     */
    public void clearDatabase()
    {
        cartProductRepo.deleteAll();
        cartRepo.deleteAll();
        productRepo.deleteAll();
    }

    /**
     * Method to create a new product DTO with the input fields.
     *
     * @param name - The name to set
     * @param price - The price to set
     * @param labels - The product labels to set
     * @return - The product DTO
     */
    public ProductDto createProductDto(String name, double price, String[] labels)
    {
        ProductDto productDto = new ProductDto();
        productDto.setName(name);
        productDto.setPrice(price);
        productDto.setLabels(labels);
        return productDto;
    }

    /**
     * Method to create a product entity object with the input fields.
     *
     * @param name - The product name
     * @param price - The product price
     * @param labels - The product labels
     * @return - The product entity
     * @throws ParseException - Thrown if the date was in an unexpected format
     */
    public Product createProduct(String name, double price, String[] labels) throws ParseException
    {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setAddedAt(dateFormat.parse(LocalDate.now().toString()));
        Set<ProductLabel> productLabels = new HashSet<>();
        for(String label: labels)
        {
            ProductLabel productLabel = labelRepo.findLabelByText(label);

            productLabels.add(productLabel);
        }

        product.setLabels(productLabels);

        return productRepo.save(product);
    }
}
