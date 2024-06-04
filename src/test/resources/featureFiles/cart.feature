@Component
Feature: Test the cart functionality

  Scenario: Successfully create a new cart
    When I make a POST request to create a new cart
    Then A new empty cart is created

  Scenario: Successfully list all shopping carts
    Given multiple shopping carts exist in the database
    When I make a GET request to get all of the shopping carts
    Then All shopping carts are returned

  Scenario: Successfully modify the contents of a shopping cart
    Given a shopping cart exists in the database
    When I make a PUT request to add products the shopping cart
    Then the contents of that cart are modified

  Scenario: Successfully checkout a cart and check the total cost is correct
    Given a cart exists containing multiple products
    When I make a POST request to checkout that cart
    Then That cart is now in a checked out state and the total cost is correct

  Scenario: Attempt to modify a checkout out cart
    Given a cart exists in a checked out state
    When I make a PUT request to modify the shopping cart
    Then I receive a 400 BadRequest response