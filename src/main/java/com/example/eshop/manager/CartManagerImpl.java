package com.example.eshop.manager;

import com.example.eshop.db.entities.Cart;
import com.example.eshop.db.entities.CartProduct;
import com.example.eshop.db.entities.Product;
import com.example.eshop.exception.BadRequestException;
import com.example.eshop.exception.NotFoundException;
import com.example.eshop.model.dto.CartDto;
import com.example.eshop.model.dto.CartProductDto;
import com.example.eshop.model.dto.CheckedOutCartDto;
import com.example.eshop.model.mapper.CartMapper;
import com.example.eshop.service.CartService;
import com.example.eshop.service.ProductService;
import com.example.eshop.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CartManagerImpl implements CartManager
{

    /**
     * Cart service object
     */
    private final CartService cartService;

    /**
     * Product Service object
     */
    private final ProductService productService;

    @Autowired
    public CartManagerImpl(CartService cartService, ProductService productService)
    {
        this.cartService = cartService;
        this.productService = productService;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<CartDto> getAllCarts()
    {
        List<Cart> carts = this.cartService.getAllCarts();
        return CartMapper.mapCartEntityListToDtoList(carts);
    }

    /**
     * @inheritDoc
     */
    @Override
    public CartDto createEmptyCart()
    {
        // Save a new cart entity
        Cart savedCartEntity = this.cartService.persistCart(new Cart());
        // Map the saved entity into a DTO, so it can be returned
        return CartMapper.mapCartEntityToDto(savedCartEntity);
    }

    /**
     * @inheritDoc
     */
    @Override
    public CartDto putCart(final String id, final CartProductDto[] cartProducts)
            throws BadRequestException, NotFoundException
    {
        // Check a cart with the given ID exists
        Cart cartEntity = this.cartService.getCart(Util.validateId(id));

        if(cartEntity.getCheckedOut())
        {
            throw new BadRequestException("Cannot modify a cart that is in a checked out state.");
        }

        // Calculate the total cost of the new cart
        cartEntity.setTotalCost(this.calculateCostOfProduct(cartProducts));

        // Convert the new cart products DTO list into a set of entities
        Set<CartProduct> newCartProducts = new HashSet<>(
                CartMapper.mapCartProductDtoListToDtoList(cartEntity, Arrays.asList(cartProducts)));
        cartEntity.setCartProducts(newCartProducts);

        cartEntity = this.cartService.persistCart(cartEntity);
        return CartMapper.mapCartEntityToDto(cartEntity);
    }

    /**
     * @inheritDoc
     */
    @Override
    public CheckedOutCartDto checkoutCart(final String id) throws BadRequestException, NotFoundException
    {
        // Check a cart with the given ID exists
        Cart cartEntity = this.cartService.getCart(Util.validateId(id));

        cartEntity.setCheckedOut(true);

        cartEntity = this.cartService.persistCart(cartEntity);

        return CartMapper.mapCartEntityToCheckedOutCartDto(cartEntity);
    }

    /**
     * Method to calculate the total cost of a given product taking into account the quantity.
     *
     * @param cartProductDtos - The cart product DTO object
     * @return - The cost of the given product
     * @throws NotFoundException - Thrown if the product could not be found
     */
    private double calculateCostOfProduct(CartProductDto[] cartProductDtos) throws NotFoundException
    {
        double totalCost = 0;
        for(CartProductDto cartProductDto: cartProductDtos)
        {
            // Fetch the product in order to get the price
            Product product = this.productService.getProduct(cartProductDto.getProduct_id());
            // Multiply the product price by the quantity
            totalCost += (product.getPrice() * cartProductDto.getQuantity());
        }

        return totalCost;
    }
}
