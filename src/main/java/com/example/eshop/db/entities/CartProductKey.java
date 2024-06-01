package com.example.eshop.db.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CartProductKey implements Serializable
{
    @Column(name = "cart_id")
    private Integer cartId;

    @Column(name = "product_id")
    private Integer productId;

    /**
     * Method to get the cart ID
     *
     * @return - The cart ID
     */
    public Integer getCartId()
    {
        return this.cartId;
    }

    /**
     * Method to set the cart ID
     *
     * @param cartId - The cart ID to set
     */
    public void setCartId(Integer cartId)
    {
        this.cartId = cartId;
    }

    /**
     * Method to get the product ID
     *
     * @return - The product ID to set
     */
    public Integer getProductId()
    {
        return this.productId;
    }

    /**
     * Method to set the product ID
     *
     * @param productId - The product ID to set
     */
    public void setProductId(Integer productId)
    {
        this.productId = productId;
    }
}
