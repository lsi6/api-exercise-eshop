package com.example.eshop.model.mapper;

import com.example.eshop.db.entities.Cart;
import com.example.eshop.db.entities.CartProduct;
import com.example.eshop.db.entities.CartProductKey;
import com.example.eshop.model.dto.CartDto;
import com.example.eshop.model.dto.CartProductDto;
import com.example.eshop.model.dto.CheckedOutCartDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Utility class to map between cart DTOs and entities.
 */
public class CartMapper
{
    /**
     * Method to map a cart entity object into a CartDto object.
     *
     * @param cart - The cart entity
     * @return - The Cart DTO
     */
    public static CartDto mapCartEntityToDto(final Cart cart)
    {
        CartDto cartDto = new CartDto();
        cartDto.setCart_id(cart.getId());
        cartDto.setChecked_out(cart.getCheckedOut());
        cartDto.setProducts(CartMapper.mapCartProductEntityListToDtoList(cart.getCartProducts()));
        return cartDto;
    }

    /**
     * Method to convert a Set of cart product entity objects into a CartProduct DTO.
     *
     * @param cartProductSet - The CartProduct entity set
     * @return - The converted cart product DTOs
     */
    private static CartProductDto[] mapCartProductEntityListToDtoList(final Set<CartProduct> cartProductSet)
    {
        if(cartProductSet == null) return new CartProductDto[0];

        List<CartProductDto> cartProductDtos = new ArrayList<>();
        for(CartProduct cartProduct: cartProductSet)
        {
            CartProductDto cartProductDto = new CartProductDto();
            cartProductDto.setProduct_id(cartProduct.getId().getProductId());
            cartProductDto.setQuantity(cartProduct.getQuantity());
            cartProductDtos.add(cartProductDto);
        }

        return cartProductDtos.toArray(new CartProductDto[0]);
    }

    /**
     * Method to map a cart entity object into the corresponding DTO.
     *
     * @param cartList - The list of cart entities to convert
     * @return - The converted cart DTO list
     */
    public static List<CartDto> mapCartEntityListToDtoList(final List<Cart> cartList)
    {
        List<CartDto> cartDtos = new ArrayList<>();

        for(Cart cart: cartList)
        {
            cartDtos.add(CartMapper.mapCartEntityToDto(cart));
        }

        return cartDtos;
    }

    /**
     * Method to map a cart product DTO into a cart product entity, so it can be saved to the database.
     *
     * @param cartEntity - The cart entity which this cart product belongs to
     * @param cartProductDto - The cart product DTO to convert
     * @return - The cart product entity
     */
    private static CartProduct mapCartProductDtoToEntity(final Cart cartEntity, final CartProductDto cartProductDto)
    {
        CartProduct cartProduct = new CartProduct();
        cartProduct.setId(new CartProductKey(cartEntity.getId(), cartProductDto.getProduct_id()));
        cartProduct.setQuantity(cartProductDto.getQuantity());
        cartProduct.setCart(cartEntity);
        return cartProduct;
    }

    /**
     * Method to map a list of cart product DTOs into a list of cart product entities.
     *
     * @param cart - The cart which these cart products belong to
     * @param cartProductDtoList - the list of cart products DTOs to convert
     * @return - The converted list of cart product entities
     */
    public static List<CartProduct> mapCartProductDtoListToDtoList(
            final Cart cart, final List<CartProductDto> cartProductDtoList)
    {
        List<CartProduct> cartProductDtos = new ArrayList<>();

        for(CartProductDto cartProductDto: cartProductDtoList)
        {
            cartProductDtos.add(CartMapper.mapCartProductDtoToEntity(cart, cartProductDto));
        }

        return cartProductDtos;
    }

    /**
     * Method to map a cart entity into a checked out cart DTO.
     *
     * @param cart - The cart entity to convert
     * @return - The converted checked out cart DTO
     */
    public static CheckedOutCartDto mapCartEntityToCheckedOutCartDto(final Cart cart)
    {
        CheckedOutCartDto checkedOutCartDto = new CheckedOutCartDto();
        checkedOutCartDto.setCart(CartMapper.mapCartEntityToDto(cart));
        checkedOutCartDto.setTotal_cost(cart.getTotalCost());
        return checkedOutCartDto;
    }
}
