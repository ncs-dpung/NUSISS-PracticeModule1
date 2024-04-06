# Frontend

### Description
 1. Supplier Management Page : CRUD operations for suppliers (supplier info)
 2. Product Management Page : CRUD operations for products (product info) & restocking notification

### Features on Supplier Management Page
 1. Add Supplier
 2. Edit Supplier
 3. Delete Supplier -> unable to delete if there are products associated with the supplier
 4. View Supplier Details

### Features on Product/Inventory Management Page
`Note: 1-4 is a table; 5 is another table`
1. Add Product -> Select Supplier, Category from available list (dropdown); other fields can just insert
2. Edit Product -> Select Supplier, Category from available list (dropdown); other fields can just insert
3. Delete Product -> unable to delete if available quantity is not zero
4. View Product Details
5. Inventory Restock Notification (Update quantity) -> a table displaying products that are below reorder level; insert quantity to restock

### Change Log
- 2024-03-29 : Initial commit
-- Remove delivery tracking from Supplier Management Page; no supplier order history