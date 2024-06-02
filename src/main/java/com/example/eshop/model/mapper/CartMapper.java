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

public class CartMapper
{
    public static CartDto mapCartEntityToDto(final Cart cart)
    {
        CartDto cartDto = new CartDto();
        cartDto.setCart_id(cart.getId());
        cartDto.setChecked_out(cart.getCheckedOut());
        cartDto.setProducts(CartMapper.mapCartProductEntityListToDtoList(cart.getCartProducts()));
        return cartDto;
    }

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

    public static List<CartDto> mapCartEntityListToDtoList(final List<Cart> cartList)
    {
        List<CartDto> cartDtos = new ArrayList<>();

        for(Cart cart: cartList)
        {
            cartDtos.add(CartMapper.mapCartEntityToDto(cart));
        }

        return cartDtos;
    }

    public static CartProduct mapCartProductDtoToEntity(final Cart cartEntity, final CartProductDto cartProductDto)
    {
        CartProduct cartProduct = new CartProduct();
        cartProduct.setId(new CartProductKey(cartEntity.getId(), cartProductDto.getProduct_id()));
        cartProduct.setQuantity(cartProductDto.getQuantity());
        cartProduct.setCart(cartEntity);
        return cartProduct;
    }

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

    public static CheckedOutCartDto mapCartEntityToCheckedOutCartDto(final Cart cart)
    {
        CheckedOutCartDto checkedOutCartDto = new CheckedOutCartDto();
        checkedOutCartDto.setCart(CartMapper.mapCartEntityToDto(cart));
        checkedOutCartDto.setTotal_cost(cart.getTotalCost());
        return checkedOutCartDto;
    }
}
