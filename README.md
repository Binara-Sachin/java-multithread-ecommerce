# Concurrent and distributed systems Assignment 01

- An Online retail shop using Java
- Focusing on concurrent systems
  - Multithreading and thread safety

## Design
- The program consists of following components
    - Shop
    - Product
      - Item
    - People
      - Customer
      - Cashier
      - Manager
    - Shipment

### Shop
  - Reference to the online shop and will have only single instance.
  - Has the following properties
    - List of products: Contains all the products in the shop
    - List of people
      - List of Cashiers: Contains a list of threads that belong to the cashier class
      - List of Managers: Contains a list of threads that belong to the manager class

### Product
  - Reference to actual products that is stored in the shop
  - Has a property named `stock` which is the quantity of the product available in the shop
    - This property is thread safe

### Item
  - Has 2 properties
    - Product: Reference to a product object
    - Quantity: The quantity of the product
  - This class is used to store the items in the cart, and the items in the shipment

### People
- Each run on its own thread
  - **Customers**: Buy products
    - New Customers will enter shop at random intervals
    - Customers will have a list of desired products in form of `Item` objects
    - Customers will add all the items in the list to the cart
      - If a product had less than the desired amount in stock, the customer will buy all the available stock
      - If a product is out of stock, customer will not buy the product
    - Finally, customers will check out the cart by going to the checkout queue in the shop
  - **Cashiers**: Check out customers
  - **Managers**: Restock products

### Shipment
- Each shipment run on a separate thread
- Shipments will arrive after a fixed interval when a shipment request is made by a manager

### Thread Priorities
| Thread Type | Priority |
|-------------|----------|
| Customer    | 5        |
| Cashier     | 7        |
| Manager     | 10       |
| Shipment    | 10       |


## Assumptions

### Products
- Product prices are final. They will not change after the product is created.
- For convenience, products have an initial stock of 100.
- Products are requested to be restocked by cashiers when the stock is less than 10.

### Customers
- Customers have a list of shopping items (products and desired amounts) when they enter the shop.
  - If a product had less than the desired amount in stock, the customer will buy all the available stock
  - If a product is out of stock, customer will not buy the product
- When checking out if the product is out of stock, the customer will not buy the product

### Shipments
- Shipments will arrive after a fixed interval when a shipment request is made by a manager
- All products will be restocked by 50 when a shipment arrives

## How to run

- Run `Main.java` file
---
Project By: `Binara Sachin`
