package com.example.eshop.db.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
public class CartProductKey implements Serializable
{
    @Serial
    private static final long serialVersionUID = 16829458569L;

    @Column(name = "cart_id")
    private int cartId;

    @Column(name = "product_id")
    private int productId;

    public CartProductKey()
    {

    }

    /**
     * Constructor
     *
     * @param cartId - The cart ID to set
     * @param productId - The product ID to set
     */
    public CartProductKey(final int cartId, final int productId)
    {
        this.cartId = cartId;
        this.productId = productId;
    }

    /**
     * Method to get the cart ID
     *
     * @return - The cart ID
     */
    public int getCartId()
    {
        return this.cartId;
    }

    /**
     * Method to set the cart ID
     *
     * @param cartId - The cart ID to set
     */
    public void setCartId(int cartId)
    {
        this.cartId = cartId;
    }

    /**
     * Method to get the product ID
     *
     * @return - The product ID to set
     */
    public int getProductId()
    {
        return this.productId;
    }

    /**
     * Method to set the product ID
     *
     * @param productId - The product ID to set
     */
    public void setProductId(int productId)
    {
        this.productId = productId;
    }

    @Override
    public int hashCode()
    {
        return this.cartId + this.productId;
    }

    @Override
    public boolean equals(Object other)
    {
        if(other == this) return true;
        if(!(other instanceof CartProductKey comparisonObject)) return false;

        return comparisonObject.getCartId() == this.cartId &&
                comparisonObject.getProductId() == this.productId;
    }
}
