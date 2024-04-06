# Frontend

### Description
  1. Order Management Page : CRUD operations for suppliers (supplier info), track delivery to customer

### Features on Order Management Page
`Note: 1-4 is a table; 5 is another table`
    1. Add Order -> Select Customer, Product from available list (dropdown); other fields can just insert; order quantity cannot exceed available quantity
    2. Edit Order -> Order status must be 'Pending' to edit; If order status is 'Processed', only delivery details can be updated; If order status is 'Delivered', no changes can be made
    3. Delete Order -> Order status must be 'Pending' to delete
    4. View Order Details
    5. Track Delivery to Customer -> Display orders that are 'Pending' and 'Processed'; update delivery status
 