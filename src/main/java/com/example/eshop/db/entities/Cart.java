package com.example.eshop.db.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "cart", schema = "eshop")
public class Cart
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "checked_out")
    private boolean checkedOut;

    @Column(name = "total_cost")
    private double totalCost;

    @OneToMany(mappedBy = "cart")
    private Set<CartProduct> cartProducts;

    /**
     * Method to get the ID for the cart
     *
     * @return - The cart ID
     */
    public int getId()
    {
        return id;
    }

    /**
     * Method to set the cart ID
     *
     * @param id - The ID to set
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Method to get the checkedOut boolean value
     *
     * @return - Boolean representing if this cart is checked out or not
     */
    public boolean getCheckedOut()
    {
        return this.checkedOut;
    }

    /**
     * Method to set the checked out boolean value
     *
     * @param checkedOut - Checkout out value to set
     */
    public void setCheckedOut(boolean checkedOut)
    {
        this.checkedOut = checkedOut;
    }

    /**
     * Method to get the total cost of the shopping cart
     *
     * @return - The total cost of the shopping cart
     */
    public double getTotalCost()
    {
        return this.totalCost;
    }

    /**
     * Method to set the total cost of the shopping cart
     *
     * @param totalCost - The value to set as the total cost
     */
    public void setTotalCost(double totalCost)
    {
        this.totalCost = totalCost;
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
