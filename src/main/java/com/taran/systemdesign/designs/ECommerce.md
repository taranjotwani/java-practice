6. Design an **e-commerce system**

Requisites: 

The e-comm system is pretty complicated with the number of products and details. Here we will only be keeping track of prices and the inventory. We will not be looking at other aspects of the system. Maybe the images.


Database
USER(id, name, username, user_type, last_ordered_at, address, created_at, updated_at)
ORDER(id, user_id, payment_id, cart_id, product_line_id, cart_id, created_at, updated_at)
PRODUCT(id, title, description, price, sale_price, created_at, updated_at )
INVENTORY(id, product_id, quantity, created_at, updated_at)
CART(id, total_price, discount_price, tax, coupon, created_at, updated_at)
PRODUCT_LINE(id, product_id, cart_id, discount, created_at, updated_at)


Services
    - Breakdown of services
        Product Services
            - Get Product/s - A get call to get all the products.
            - Save Product - This will be post call and a role based service to save product to the cart.
            - Get Product Details - This will be another get call to get the product details.

        Cart Service
            - Get Cart - GET request. Will get the cart details, if not create a new cart.
            - Add/Remove Cart - This will be a PUT request to update from/to the cart.
            - Add/Remove Quantity - This service will also be a PUT to add/update the quantity. 

        Inventory Services
            - Get Product Inventory - This is used to get the product invetory for the project.

        Login Services
            - User login/logout service to handle accounts. Not part of this design.

        Payment Service
            - Save Payment - This service will take the credit card details and save it to the database for processing.

        Order Service
            - This service would handle the order details and sending it over to the warehouse team.


Caching

    - The product details would be indexed on Solr for easier search and find functionality.
    - The product details could be cached on redis for easier reads.

Scaling
    - Any of the webservices can be scaled as per needed.



=== AI ====
# E-commerce System Design

This note is a clean, recall-friendly version of an e-commerce system design focused only on **product catalog, pricing, inventory, cart, and order creation**. It does not cover every e-commerce feature such as recommendations, reviews, returns, fraud detection, or full warehouse management.[1][2]

## Scope

The system supports the following user journey:

1. Browse products.
2. View product details.
3. Add products to cart.
4. Update quantity in cart.
5. Checkout.
6. Validate price and inventory.
7. Create order and send it for fulfillment.[3][1][4]

The main goal is to design a system that is simple to explain, scales for product reads, and prevents overselling during checkout.[1][5][6]

## Functional Requirements

- Users can browse and search products.[2][7]
- Users can view product details such as title, description, price, and sale price.[2]
- Users can add or remove products in a shopping cart.[3][4]
- Users can update quantity in the cart.[3]
- Users can place an order after payment authorization.[1]
- The system must check product inventory before confirming the order.[1][5]

## Non-Functional Requirements

- Product reads should be fast because catalog traffic is usually read-heavy.[2][7]
- Checkout must be correct and prevent overselling.[1][5]
- The system should scale horizontally for product and cart traffic.[2][7]
- Order creation should be durable and reliable.[1]

## High-Level Components

A simple high-level design can be split into these services:

- **Product Service**: manages product details and pricing.[2]
- **Search Service**: indexes products into Solr or Elasticsearch/OpenSearch for search and filtering.[2][7]
- **Cart Service**: stores the active cart and cart items.[3][4]
- **Inventory Service**: manages stock availability and reservations.[1][5]
- **Payment Service**: communicates with an external payment provider for authorization and capture.[1]
- **Order Service**: creates the final order and emits fulfillment events.[1]
- **Cache Layer**: Redis for fast product reads and cart/session access.[7][8]

## Database Design

A cleaner schema is shown below.

### Users

```sql
users(
  id,
  name,
  username,
  user_type,
  address,
  created_at,
  updated_at
)
```

### Products

```sql
products(
  id,
  title,
  description,
  base_price,
  sale_price,
  status,
  created_at,
  updated_at
)
```

### Inventory

```sql
inventory(
  product_id,
  available_qty,
  reserved_qty,
  updated_at
)
```

### Carts

```sql
carts(
  id,
  user_id,
  status,
  created_at,
  updated_at
)
```

### Cart Items

```sql
cart_items(
  id,
  cart_id,
  product_id,
  quantity,
  price_snapshot,
  created_at,
  updated_at
)
```

### Orders

```sql
orders(
  id,
  user_id,
  payment_ref,
  status,
  total_amount,
  discount_amount,
  tax_amount,
  created_at,
  updated_at
)
```

### Order Items

```sql
order_items(
  id,
  order_id,
  product_id,
  quantity,
  unit_price,
  discount,
  created_at
)
```

This separation is important because a **cart is mutable** while an **order is an immutable purchase snapshot** after checkout.[9][4]

## Why This Schema Is Better

The earlier version mixed `ORDER`, `CART`, and `PRODUCT_LINE` in a way that would make history, auditing, and checkout logic harder to manage.[9] A better design separates cart-time and purchase-time data so that future price changes do not alter past orders, and cart edits do not mutate already placed purchases.[9][1]

## Product Service

The Product Service is responsible for:

- getting product lists,
- getting product details,
- creating or updating product information for admin users,
- returning current pricing information.[2][7]

This service is read-heavy and can be horizontally scaled behind a load balancer.[2][7]

## Search Service

Search should be separated from the primary database for scale and better filtering support. Product data can be indexed into Solr or Elasticsearch/OpenSearch to support keyword search, category filters, sorting, and faceted queries.[2][7]

A common pattern is:

1. Product is created or updated.
2. Product event is published.
3. Search index is updated asynchronously.[2][7]

## Cart Service

The Cart Service handles:

- get cart,
- add item to cart,
- remove item from cart,
- update quantity,
- calculate subtotal before checkout.[3][4]

Cart data is mutable and can be stored in a primary database, with optional Redis support for fast session access or temporary cart caching.[3][8]

## Inventory Service

The Inventory Service is the most critical part for correctness. A simple `quantity` field is usually not enough for real-world checkout because multiple users may try to buy the same product at the same time.[1][5]

A stronger model tracks:

- `available_qty`: stock still available for purchase,
- `reserved_qty`: stock temporarily held during checkout.[1][5]

This enables an inventory reservation flow:

1. User clicks checkout.
2. Inventory Service checks stock.
3. Stock is reserved temporarily.
4. Payment is authorized.
5. If payment succeeds, reserved stock becomes committed stock reduction.
6. If payment fails or times out, reserved stock is released.[1][5][6]

This is one of the most important ideas to mention because it prevents overselling.[1][5]

## Payment Service

The Payment Service should not store raw credit card data in the main system database. Instead, it should call an external PCI-compliant payment provider and store only safe references such as:

- payment token,
- authorization ID,
- transaction reference,
- payment status.[1]

This keeps the system safer and more realistic from a design perspective.[1]

## Order Service

The Order Service creates the final order only after inventory validation and payment authorization succeed.[1]

Its responsibilities are:

- create order record,
- create order item snapshots,
- store order totals,
- trigger warehouse or fulfillment events asynchronously.[1]

This service should also be durable because orders are core business records.[1]

## Checkout Flow

A good checkout flow for interviews is:

1. User opens cart.
2. Cart Service returns cart items and totals.
3. Checkout starts.
4. Inventory Service validates and reserves inventory atomically.[1][5]
5. Payment Service authorizes payment via external provider.[1]
6. Order Service creates `orders` and `order_items`.[1]
7. Inventory Service deducts committed stock.[1][5]
8. Event is published for warehouse, email, analytics, and invoice generation.[10][11][1]

If payment fails, the reservation should expire or be released.[1][5]

## Caching Strategy

Redis can improve performance in the following areas:

- product detail cache,
- popular listing pages,
- category pages,
- cart/session data,
- short-lived inventory availability cache with caution.[7][8]

Catalog metadata is a better candidate for caching than inventory correctness data, because inventory changes more often and requires stronger consistency during checkout.[1][8]

## Image Handling

If product images are included, they should usually be stored in object storage and served through a CDN rather than from the application database.[2] This reduces load on backend services and improves global delivery performance.[2]

## Scaling Strategy

A stronger scaling discussion should mention different traffic patterns for different components.

### Product and Search

These are read-heavy systems, so they can scale horizontally with load balancers, caches, and search indexes.[2][7]

### Cart

Cart traffic is user-specific and update-heavy, but usually easier to shard by user ID or session ID.[3][4]

### Inventory and Checkout

These require stronger consistency. Scaling here is not just about adding instances; it also needs concurrency control, atomic updates, row locking, partitioning, or reservation logic.[1][5][6]

### Orders

Order creation must be durable, and downstream work such as fulfillment, email, and analytics should happen asynchronously using queues or event streams.[10][11][1]

## Common Mistakes

- Mixing cart and order data into the same mutable structure.[9][4]
- Storing raw payment details directly in the database.[1]
- Using only a single quantity field without reservation handling.[1][5]
- Forgetting that price at checkout should be snapshotted into order items.[9][1]
- Giving only generic scaling statements without discussing read-heavy catalog traffic versus consistency-heavy checkout traffic.[1][2]

## Interview Recall Version

A strong short version to remember is:

> For a simplified e-commerce system, split the design into Product, Search, Cart, Inventory, Payment, and Order services. The product catalog is read-heavy, so use Redis caching and a search index like Solr or Elasticsearch for fast browsing. The cart is mutable, but once checkout succeeds, the order becomes an immutable snapshot with separate order items. Inventory must support reservation or atomic checks during checkout to prevent overselling. Payment should be handled through an external provider, and order fulfillment should run asynchronously after the order is created.[3][1][5][2][7]

## Mental Model to Remember

When designing an e-commerce system, think in this order:

1. **How are products read and searched?**
2. **How is cart state stored and updated?**
3. **How is inventory checked safely?**
4. **How is payment authorized?**
5. **How is the final order stored immutably?**
6. **What parts are read-heavy versus consistency-heavy?**

If these six points are clear, the design usually sounds structured and interview-ready.[1][5][2]