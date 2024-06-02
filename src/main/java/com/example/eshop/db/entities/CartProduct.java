package com.example.eshop.db.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_product", schema = "eshop")
public class CartProduct
{
    @EmbeddedId
    private CartProductKey id;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @MapsId("cartId")
    @JoinColumn(name = "cart_id")
    private Cart cart;

    /**
     * Method to get the cart product ID
     *
     * @return - The cart product ID
     */
    public CartProductKey getId()
    {
        return this.id;
    }

    /**
     * Method to set the cart product ID
     *
     * @param id - The CartProductKey composite key to set
     */
    public void setId(CartProductKey id)
    {
        this.id = id;
    }

    /**
     * Method to get the quantity of this product in the shopping cart
     *
     * @return - The quantity of this product in the shopping cart
     */
    public int getQuantity()
    {
        return this.quantity;
    }

    /**
     * Method to set the quantity value
     *
     * @param quantity - The quantity to set
     */
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    /**
     * Method to get the cart this cart product belongs to
     *
     * @return - The cart this cart product belongs to
     */
    public Cart getCart()
    {
        return this.cart;
    }

    /**
     * Method to set the cart
     *
     * @param cart - The cart to set
     */
    public void setCart(Cart cart)
    {
        this.cart = cart;
    }

    @Override
    public boolean equals(Object other)
    {
        if(other == this) return true;
        if(!(other instanceof CartProduct)) return false;
        return ((CartProduct) other).getId().equals(this.id);
    }
}
