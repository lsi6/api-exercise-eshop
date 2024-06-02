package com.example.eshop.model.dto;

public class CheckedOutCartDto
{
    /**
     * Cart object
     */
    private CartDto cart;

    /**
     * The total cost for this cart
     */
    private double total_cost;

    /**
     * Method to get the cart DTO
     *
     * @return - The cart DTO
     */
    public CartDto getCart()
    {
        return this.cart;
    }

    /**
     * Method to set the cart DTO
     *
     * @param cart - The cart DTO to set
     */
    public void setCart(CartDto cart)
    {
        this.cart = cart;
    }

    /**
     * Method to get the total cost of this cart
     *
     * @return - The total cost of this cart
     */
    public double getTotal_cost()
    {
        return this.total_cost;
    }

    /**
     * Method to set the total cost of this cart
     *
     * @param total_cost - The total cost to set
     */
    public void setTotal_cost(double total_cost)
    {
        this.total_cost = total_cost;
    }
}
