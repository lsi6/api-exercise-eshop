package com.example.eshop.stepDefs;

import com.example.eshop.db.entities.Product;
import com.example.eshop.db.entities.ProductLabel;
import com.example.eshop.db.repos.CartProductRepo;
import com.example.eshop.db.repos.CartRepo;
import com.example.eshop.db.repos.LabelRepo;
import com.example.eshop.db.repos.ProductRepo;
import com.example.eshop.model.dto.ProductDto;
import com.example.eshop.model.mapper.ProductMapper;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductStepDefs
{
    /**
     * The date format to be used
     */
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private CartProductRepo cartProductRepo;

    @Autowired
    private LabelRepo labelRepo;

    /**
     * Variable to hold the RestAssured response
     */
    private Response response;

    /**
     * Util class to load test data
     */
    private DataLoader dataLoader;

    @Before("@Component")
    public void before()
    {
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.port = 8080;
        this.dataLoader = new DataLoader(productRepo, cartRepo, cartProductRepo, labelRepo);
        this.dataLoader.clearDatabase();
    }

    @Given("products exist in the catalogue")
    public void addProducts() throws ParseException
    {
        this.dataLoader.createProduct("bread", 1.20d, new String[]{"food"});
        this.dataLoader.createProduct("milk", 1.10d, new String[]{"drink"});
        this.dataLoader.createProduct("chocolate", 2.00d, new String[]{"food"});
    }

    @When("I make a get request to get all products in the catalogue")
    public void getAllProducts()
    {
        this.response = RestAssured.get("/products");
    }

    @Then("a list of products are returned")
    public void verifyReturnedProducts()
    {
        Assertions.assertEquals(200, this.response.statusCode());
        ProductDto[] products = this.response.getBody().as(ProductDto[].class);
        Assertions.assertEquals(products.length, 3);
    }

    @When("I post a new product")
    public void postNewProduct()
    {
        ProductDto productDto = this.dataLoader.createProductDto("cheese", 1.50, new String[]{"food"});
        this.response = RestAssured.with().body(productDto).contentType(MediaType.APPLICATION_JSON_VALUE).post("/products");
    }

    @Then("That product is successfully added to the database")
    public void checkPostResponse()
    {
        Assertions.assertEquals(201, this.response.getStatusCode());
    }

    @When("I post a product with an empty product name")
    public void postProductWithEmptyName()
    {
        ProductDto productDto = this.dataLoader.createProductDto("", 1.50, new String[]{"food"});
        this.response = RestAssured.with().body(productDto).contentType(MediaType.APPLICATION_JSON_VALUE).post("/products");
    }

    @Then("I receive a BadRequest response")
    public void checkBadRequestResponse()
    {
        Assertions.assertEquals(400, this.response.getStatusCode());
    }

    @When("I post a product with a name with over 200 characters")
    public void postProductTooManyCharacters()
    {
        ProductDto productDto = this.dataLoader.createProductDto(
                "201Characters-fdsafasfewfeasfasefeasfdsafasfewfeasfasefeasfdsafasfewfeasfas" +
                        "efeasfdsafasfewfeasfasefeasfdsafasfewfeasfasefeasfdsafasfewfeasfasefeasfdsafasfew" +
                        "feasfasefeasfdsafasfewfeasfasefeasfdsafasfewd", 1.50, new String[]{"food"});
        this.response = RestAssured.with().body(productDto).contentType(MediaType.APPLICATION_JSON_VALUE).post("/products");
    }

    @When("I attempt to create a product with a name that already exists")
    public void duplicateName()
    {
        ProductDto productDto = this.dataLoader.createProductDto("duplicateName", 1.50, new String[]{"food"});
        this.response = RestAssured.with().body(productDto).contentType(MediaType.APPLICATION_JSON_VALUE).post("/products");
        this.response = RestAssured.with().body(productDto).contentType(MediaType.APPLICATION_JSON_VALUE).post("/products");
    }

    @When("I attempt to create a product with an invalid label")
    public void invalidLabel()
    {
        ProductDto productDto = this.dataLoader.createProductDto("cheese", 1.50, new String[]{"invalidLabel"});
        this.response = RestAssured.with().body(productDto).contentType(MediaType.APPLICATION_JSON_VALUE).post("/products");
    }

}
