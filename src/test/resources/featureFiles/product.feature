@Component
Feature: Test the product functionality

  Scenario: Get all products in the catalogue
    Given products exist in the catalogue
    When I make a get request to get all products in the catalogue
    Then a list of products are returned

  Scenario: Successfully post a product
    When I post a new product
    Then That product is successfully added to the database

  Scenario: Post a product with an empty product name
    When I post a product with an empty product name
    Then I receive a BadRequest response

  Scenario: Post a product with a name with over 200 characters
    When I post a product with a name with over 200 characters
    Then I receive a BadRequest response

  Scenario: Create product with a name that already exists
    When I attempt to create a product with a name that already exists
    Then I receive a BadRequest response

  Scenario: Create a product with an invalid label
    When I attempt to create a product with an invalid label
    Then I receive a BadRequest response