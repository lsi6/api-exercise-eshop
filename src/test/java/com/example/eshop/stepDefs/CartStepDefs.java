package com.example.eshop.stepDefs;

import com.example.eshop.db.entities.Cart;
import com.example.eshop.db.entities.CartProduct;
import com.example.eshop.db.entities.Product;
import com.example.eshop.db.repos.CartProductRepo;
import com.example.eshop.db.repos.CartRepo;
import com.example.eshop.db.repos.LabelRepo;
import com.example.eshop.db.repos.ProductRepo;
import com.example.eshop.model.dto.CartDto;
import com.example.eshop.model.dto.CartProductDto;
import com.example.eshop.model.dto.CheckedOutCartDto;
import com.example.eshop.util.DataLoader;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartStepDefs
{
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private CartProductRepo cartProductRepo;

    @Autowired
    private LabelRepo labelRepo;

    /**
     * Util class to load test data
     */
    private DataLoader dataLoader;

    /**
     * Variable to hold the RestAssured response
     */
    private Response response;

    @Before("@Component")
    public void before()
    {
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.port = 8080;
        this.dataLoader = new DataLoader(productRepo, cartRepo, cartProductRepo, labelRepo);
        this.dataLoader.clearDatabase();
    }

    @Given("I make a POST request to create a new cart")
    public void postNewCart()
    {
        RestAssured.with().contentType(MediaType.APPLICATION_JSON_VALUE).post("/carts");
    }

    @Then("A new empty cart is created")
    public void emptyCartIsCreated()
    {
        List<Cart> carts = this.cartRepo.findAll();
        Assertions.assertEquals(1, carts.size());
        Assertions.assertTrue(carts.get(0).getCartProducts().isEmpty());
    }

    @Given("multiple shopping carts exist in the database")
    public void confirmMultipleCartsExist()
    {
        this.postNewCart();
        this.postNewCart();
        this.postNewCart();
    }

    @When("I make a GET request to get all of the shopping carts")
    public void getAllCarts()
    {
        this.response = RestAssured.get("/carts");
    }

    @Then("All shopping carts are returned")
    public void allCartsReturned()
    {
        Assertions.assertEquals(200, this.response.getStatusCode());
        CartDto[] carts = this.response.getBody().as(CartDto[].class);
        Assertions.assertEquals(3, carts.length);
    }

    @Given("a shopping cart exists in the database")
    public void cartExists()
    {
        this.postNewCart();
        this.response = RestAssured.get("/carts");
        Assertions.assertEquals(1, this.response.getBody().as(CartDto[].class).length);
    }

    @When("I make a PUT request to add products the shopping cart")
    public void modifyCart() throws ParseException
    {
        // Create a product to add to the cart
        Product product = this.dataLoader.createProduct("cheese", 1.99, new String[]{"food"});

        CartDto cart = this.response.getBody().as(CartDto[].class)[0];
        CartProductDto cartProductDto = new CartProductDto();
        cartProductDto.setQuantity(2);
        cartProductDto.setProduct_id(product.getId());
        CartProductDto[] cartProductDtos = new CartProductDto[1];
        cartProductDtos[0] = cartProductDto;

        this.response = RestAssured.with().contentType(MediaType.APPLICATION_JSON_VALUE).body(cartProductDtos).put(
                "/carts/" + cart.getCart_id());
        Assertions.assertEquals(200, this.response.getStatusCode());
    }

    @Then("the contents of that cart are modified")
    public void confirmContentsModified()
    {
        this.response = RestAssured.get("/carts");
        CartDto cart = this.response.getBody().as(CartDto[].class)[0];
        // The cart should now have a product
        Assertions.assertEquals(1, cart.getProducts().length);
    }

    @Given("a cart exists containing multiple products")
    public void createCartMultipleProducts() throws ParseException
    {
        this.postNewCart();
        this.response = RestAssured.get("/carts");

        // Create products to add to the cart
        Product product1 = this.dataLoader.createProduct("bread", 1.20, new String[]{"food"});
        Product product2 = this.dataLoader.createProduct("bacon", 2.50, new String[]{"food"});

        CartDto cart = this.response.getBody().as(CartDto[].class)[0];

        // Add the first product
        CartProductDto cartProductDto1 = new CartProductDto();
        cartProductDto1.setQuantity(2);
        cartProductDto1.setProduct_id(product1.getId());

        // Add the second product
        CartProductDto cartProductDto2 = new CartProductDto();
        cartProductDto2.setQuantity(3);
        cartProductDto2.setProduct_id(product2.getId());

        CartProductDto[] cartProductDtos = new CartProductDto[2];
        cartProductDtos[0] = cartProductDto1;
        cartProductDtos[1] = cartProductDto2;

        this.response = RestAssured.with().contentType(MediaType.APPLICATION_JSON_VALUE).body(cartProductDtos).put(
                "/carts/" + cart.getCart_id());
    }

    @When("I make a POST request to checkout that cart")
    public void checkoutCart()
    {
        CartDto cart = this.response.getBody().as(CartDto.class);
        this.response = RestAssured.post("/carts/" + cart.getCart_id() + "/checkout");
    }

    @Then("That cart is now in a checked out state and the total cost is correct")
    public void confirmCheckedOutCart()
    {
        CheckedOutCartDto checkedOutCartDto = this.response.getBody().as(CheckedOutCartDto.class);
        Assertions.assertTrue(checkedOutCartDto.getCart().getChecked_out());
        Assertions.assertEquals(2*1.20 + 3*2.50, checkedOutCartDto.getTotal_cost());
    }

    @Given("a cart exists in a checked out state")
    public void createCheckedOutCart() throws ParseException
    {
        this.createCartMultipleProducts();
        this.checkoutCart();
    }

    @When("I make a PUT request to modify the shopping cart")
    public void modifyShoppingCart()
    {
        this.response = RestAssured.get("/carts");
        CartDto cart = this.response.getBody().as(CartDto[].class)[0];

        CartProductDto cartProductDto = new CartProductDto();
        CartProductDto[] cartProductDtos = new CartProductDto[1];
        cartProductDtos[0] = cartProductDto;

        this.response = RestAssured.with().contentType(MediaType.APPLICATION_JSON_VALUE).body(cartProductDtos).put(
                "/carts/" + cart.getCart_id());
    }

    @Then("I receive a 400 BadRequest response")
    public void verifyBadRequestResponse()
    {
        Assertions.assertEquals(400, this.response.getStatusCode());
    }
}
