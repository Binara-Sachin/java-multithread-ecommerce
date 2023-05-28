# Concurrent and distributed systems Assignment 01

- An Online retail shop using Java
- Focusing on concurrent systems
  - Multithreading and thread safety

## TODO
- [ ] Shop needs to close once all the customers are done
- [x] Fix customers buying products with 0 stock
- [ ] Add test cases
- [ ] Add Assumptions in Comments
- [ ] Finalize the Readme file

## Design
- Shop
  - List of products
  - List of people
    - List of Cashiers
    - List of Managers
  - customerQueue
- Product
  - Reference to the actual product that is stored in the shop
  - Has a property named `stock` which is the quantity of the product available in the shop
    - This property is thread safe
- **People**: Each run on its own thread
  - **Customers**: Buy products
  - **Cashiers**: Check out customers
  - **Managers**: Restock products
- Shipment

### Products
- Product prices are final. They will not change after the product is created.
- For convenience, products have an initial stock of 10.

### Customers
- Customers have a list of shopping items then they enter the shop.
- Customers will att all the items in the list to the cart (not buy).
- When checking out

| Thread Type | Priority |
|-------------|----------|
| Customer    | 5        |
| Cashier     | 7        |
| Manager     | 10       |
| Shipment    | 10       |

## Assumptions


## How to run

- Run `Main.java` file
---
Project By: `Binara Sachin`
