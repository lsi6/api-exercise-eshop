package com.example.eshop.db.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "cart", schema = "eshop")
public class Cart
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "checked_out")
    private Boolean checkedOut;

    @OneToMany(mappedBy = "cart")
    private Set<CartProduct> cartProducts;

    /**
     * Method to get the ID for the cart
     *
     * @return - The cart ID
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * Method to set the cart ID
     *
     * @param id - The ID to set
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * Method to get the checkedOut boolean value
     *
     * @return - Boolean representing if this cart is checked out or not
     */
    public Boolean getCheckedOut()
    {
        return this.checkedOut;
    }

    /**
     * Method to set the check out boolean value
     *
     * @param checkedOut - Checkout out value to set
     */
    public void setCheckedOut(Boolean checkedOut)
    {
        this.checkedOut = checkedOut;
    }

    /**
     * Method to get the cart products list
     *
     * @return - The list of products in this cart
     */
    public Set<CartProduct> getCartProducts()
    {
        return this.cartProducts;
    }

    /**
     * Method to set the cart products list
     *
     * @param cartProducts - The cart products list to set
     */
    public void setCartProducts(Set<CartProduct> cartProducts)
    {
        this.cartProducts = cartProducts;
    }
}
