package com.example.eshop.service;

import com.example.eshop.db.entities.Cart;
import com.example.eshop.db.entities.CartProduct;
import com.example.eshop.db.repos.CartProductRepo;
import com.example.eshop.db.repos.CartRepo;
import com.example.eshop.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService
{

    /**
     * Class defining methods to interact with the cart table
     */
    private final CartRepo cartRepo;

    /**
     * Class defining methods to interact with the cart_product table
     */
    private final CartProductRepo cartProductRepo;

    /**
     * Constructor
     *
     * @param cartRepo - The product repository to autowire
     * @param cartProductRepo - The cartProduct repository to autowire
     */
    @Autowired
    public CartServiceImpl(final CartRepo cartRepo, final CartProductRepo cartProductRepo)
    {
        this.cartRepo = cartRepo;
        this.cartProductRepo = cartProductRepo;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Cart> getAllCarts()
    {
        return this.cartRepo.findAll();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Cart getCart(final int id) throws NotFoundException
    {
        Optional<Cart> cartEntity = this.cartRepo.findById(id);
        if(cartEntity.isEmpty())
        {
            throw new NotFoundException("cart", String.valueOf(id));
        }
        return cartEntity.get();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Cart persistCart(final Cart cart)
    {
        for(CartProduct cartProduct: cart.getCartProducts())
        {
            this.cartProductRepo.save(cartProduct);
        }
        return this.cartRepo.save(cart);
    }
}
