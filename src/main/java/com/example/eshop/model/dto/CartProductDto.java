package com.example.eshop.model.dto;

public class CartProductDto
{
    /**
     * The product ID
     */
    private int product_id;

    /**
     * The quantity of this product in the cart
     */
    private int quantity;

    /**
     * Method to get the product ID.
     *
     * @return - The product ID
     */
    public int getProduct_id()
    {
        return this.product_id;
    }

    /**
     * Method to set the product ID.
     *
     * @param product_id - The product ID to set
     */
    public void setProduct_id(final int product_id)
    {
        this.product_id = product_id;
    }

    /**
     * Method to get the quantity of this product.
     *
     * @return - The quantity of this product
     */
    public int getQuantity()
    {
        return this.quantity;
    }

    /**
     * Method to set the quantity.
     *
     * @param quantity - The quantity to set
     */
    public void setQuantity(final int quantity)
    {
        this.quantity = quantity;
    }
}
