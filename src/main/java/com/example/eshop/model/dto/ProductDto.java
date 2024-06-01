package com.example.eshop.model.dto;

import java.util.Date;

/**
 * Product DTO class
 */
public class ProductDto
{
    /**
     * The ID of the product
     */
    private Integer product_id;

    /**
     * The human-readable name for the product
     */
    private String name;

    /**
     * The price of the product
     */
    private double price;

    /**
     * The date the product was added to the catalogue
     */
    private Date added_at;

    /**
     * The category labels for the product
     */
    private String[] labels;

    /**
     * Default constructor
     */
    public ProductDto()
    {

    }

    /**
     * Constructor
     *
     * @param product_id - The product ID to set
     * @param name       - The product name to set
     * @param price      - The product price to set
     * @param added_at   - The added_at date to set
     * @param labels     - The category labels to set
     */
    public ProductDto(Integer product_id, String name, double price, Date added_at, String[] labels)
    {
        this.product_id = product_id;
        this.name = name;
        this.price = price;
        this.added_at = added_at;
        this.labels = labels;
    }

    /**
     * Method to set the product ID
     *
     * @param product_id - The product ID to set
     */
    public void setProduct_id(Integer product_id)
    {
        this.product_id = product_id;
    }

    /**
     * Method to set the product name
     *
     * @param name - The product name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Method to set the product price
     *
     * @param price - The product price to set
     */
    public void setPrice(double price)
    {
        this.price = price;
    }

    /**
     * Method to set the date the product was added to the catalogue
     *
     * @param added_at - The date to set
     */
    public void setAdded_at(Date added_at)
    {
        this.added_at = added_at;
    }

    /**
     * Method to set the product category labels
     *
     * @param labels - The labels to set
     */
    public void setLabels(String[] labels)
    {
        this.labels = labels;
    }

    /**
     * Method to get the product ID
     *
     * @return - The ID of the product
     */
    public Integer getProduct_id()
    {
        return this.product_id;
    }

    /**
     * Method to get the product name
     *
     * @return - The name of the product
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Method to get the product price
     *
     * @return - The price of the product
     */
    public double getPrice()
    {
        return this.price;
    }

    /**
     * Method to get the date the product was added to the catalogue
     *
     * @return - The date the product was added to the catalogue
     */
    public Date getAdded_at()
    {
        return this.added_at;
    }

    /**
     * Method to get the product category labels
     *
     * @return - The product category labels
     */
    public String[] getLabels()
    {
        return this.labels;
    }


}
