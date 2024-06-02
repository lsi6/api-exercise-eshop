package com.example.eshop.model.dto;

public class CartDto
{
    /**
     * The ID for the cart
     */
    private int cart_id;

    /**
     * A list of products in this cart
     */
    private CartProductDto[] products;

    /**
     * Boolean representing if this cart is checked out
     */
    private boolean checked_out;

    /**
     * Method to get the cart ID.
     *
     * @return - The cart ID
     */
    public int getCart_id()
    {
        return this.cart_id;
    }

    /**
     * Method to set the cart ID
     *
     * @param id - The ID to set
     */
    public void setCart_id(final int id)
    {
        this.cart_id = id;
    }

    /**
     * Method to set the checked out status of this cart
     *
     * @param checked_out - The checked out status to set
     */
    public void setChecked_out(final boolean checked_out)
    {
        this.checked_out = checked_out;
    }

    /**
     * Method to set the products belonging to this cart
     *
     * @param products - The products to set
     */
    public void setProducts(CartProductDto[] products)
    {
        this.products = products;
    }

    /**
     * Method to get the checked out value
     *
     * @return - The checked out value
     */
    public boolean getChecked_out()
    {
        return this.checked_out;
    }

    /**
     * Method to get the products in this cart.
     *
     * @return - The products in this cart
     */
    public CartProductDto[] getProducts()
    {
        return this.products;
    }
}
