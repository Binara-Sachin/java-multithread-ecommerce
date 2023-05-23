# Concurrent and distributed systems Assignment 01

- An Online retail shop using Java
- Focusing on concurrent systems
  - Multithreading and thread safety

## Design and Assumptions
- Shop
  - List of products
  - List of people
  - Warehouse
  - customerQueue
- Product
  - A singleton class
  - Reference to the actual product that is stored in the shop
  - Has a property named `stock` which is the quantity of the product
- **People**: Each run on its own thread
  - **Customers**: Buy products
  - **Cashiers**: Check out customers
  - **Managers**: Restock products
- Warehouse: Stores products
  - **Stock**: Stores the quantity of each product
    - Is synchronized
- Shipment

### Products
- Product prices are final. They will not change after the product is created.
- For convenience, products have an initial stock of 10.

### Customers
- Customers have a list of shopping items then they enter the shop.
- Customers will att all the items in the list to the cart (not buy).
- When chaking out 

## How to run

- Run `Main.java` file
---
Project By: `Binara Sachin`
