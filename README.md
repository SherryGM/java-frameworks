# java-frameworks
This project exists to compare service-layer implementations between Java frameworks. The platform for this comparison is a basic Bourbon shopping cart application written in AngularJS.


## Service Definition

### List Items
* `GET /svc/items`
* 200: Success
* Response Body:
```json
[
  {
    id: 1,
    name: "Booker's Single Barrel Bourbon",
    description: "",
    price: 41.99,
    shortname: "bookers"
  }, {
    ...
  }
]
```

### View Item
* `GET /svc/item/{shortname}`
* 200: Success
* 404: No such item
* Response Body:
```json
{
  id: 1,
  name: "Booker's Single Barrel Bourbon",
  description: "",
  price: 41.99,
  shortname: "bookers"
}
```

### Add Item to Cart
* `PUT /svc/cart/`
* 200: Success
* Request Body: `{ itemId: 1, qty: 1 }`

### List Items in Cart
* `GET /svc/cart`
* 200: Success
* Response Body:
```json
[
  {
    id: 1,
    qty: 1,
    name: "Booker's Single Barrel Bourbon",
    price: 41.99,
    shortname: "bookers"
  }, {
    ...
  }
]
```

### Update Qty in Cart
* `POST /svc/cart/{itemId}`
* 200: Success
* 404: No such item in cart
* Request Body: `{ qty: 2 }`

### Remove Item From Cart
* `DELETE /svc/cart/{itemId}`
* 200: Success
* 404: No such item in cart

### Save Shipping Info
* `PUT /svc/order/shipping`
* 200: Success
* Request Body:
```json
{
  name: "Michael Dowden",
  address: "123 Nowhere Ln",
  city: "Springfield",
  state: "TN",
  zip: "12345",
  phone: "123-456-7890",
  email: "xyz@null.org"
}
```

### Fetch Shipping Info
* `GET /svc/order/shipping`
* 200: Success
* Response Body:
```json
{
  name: "Michael Dowden",
  address: "123 Nowhere Ln",
  city: "Springfield",
  state: "TN",
  zip: "12345",
  phone: "123-456-7890",
  email: "xyz@null.org"
}
```

### Place Order
* `POST /svc/order/checkout`
* 200: Success
* Response Header: `Location: http://localhost:8080/svc/order/12345`

### Fetch Order Info
* `GET /svc/order/{orderNumber}`
* 200: Success
* 404: No such order
* Response Body:
```json
{
  total: 41.99,
  address: {
    name: "Michael Dowden",
    address: "123 Nowhere Ln",
    city: "Springfield",
    state: "TN",
    zip: "12345",
    phone: "123-456-7890",
    email: "xyz@null.org"
  },
  items: [
    {
      id: 1,
      name: "Booker's Single Barrel Bourbon",
      description: "",
      price: 41.99,
      shortname: "bookers"
    }, {
      ...
    }
  ]
}
```
