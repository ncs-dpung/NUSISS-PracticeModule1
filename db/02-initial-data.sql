-- Insert Roles
-- INSERT INTO tbl_role (role_name) VALUES ('Administrator');
-- INSERT INTO tbl_role (role_name) VALUES ('Warehouse Manager');
-- INSERT INTO tbl_role (role_name) VALUES ('Procurement Officer');
-- INSERT INTO tbl_role (role_name) VALUES ('Salesperson');
--
-- -- Insert Users (passwords are just placeholders, ensure to use hashed passwords in production)
-- INSERT INTO tbl_user (user_name, email, password, roleid) VALUES ('admin', 'admin@example.com', 'adminpassword', 1);
-- INSERT INTO tbl_user (user_name, email, password, roleid) VALUES ('warehouse', 'warehouse@example.com', 'warehousepassword', 2);
-- INSERT INTO tbl_user (user_name, email, password, roleid) VALUES ('procurement', 'procurement@example.com', 'procurementpassword', 3);
-- INSERT INTO tbl_user (user_name, email, password, roleid) VALUES ('sales', 'sales@example.com', 'salespassword', 4);

-- Insert Categories
INSERT INTO tbl_category (category_name) VALUES ('Normal Dolls');
INSERT INTO tbl_category (category_name) VALUES ('Series Dolls');
INSERT INTO tbl_category (category_name) VALUES ('Customize Dolls');

-- Insert Suppliers
INSERT INTO tbl_supplier (supplier_name, contact_info, address) VALUES ('Supplier A', 'contact@suppliera.com', 'Address A');
INSERT INTO tbl_supplier (supplier_name, contact_info, address) VALUES ('Supplier B', 'contact@supplierb.com', 'Address B');

-- Insert Products
INSERT INTO tbl_product (name, categoryid, price, quantity, batch_no) VALUES ('Rebbe Doll', 1, 29.99, 106, 'DOLL12345');
INSERT INTO tbl_product (name, categoryid, price, quantity, batch_no) VALUES ('Collector Esther Doll', 3, 59.99, 32, 'DOLL67890');

-- Insert Inventory Records
INSERT INTO tbl_inventory (productid, quantity_available, reorder_level) VALUES (1, 100, 20);
INSERT INTO tbl_inventory (productid, quantity_available, reorder_level) VALUES (2, 20, 50);

-- Insert Customers
INSERT INTO tbl_customer (name, contact_info) VALUES ('John Doe', 'john.doe@example.com');
INSERT INTO tbl_customer (name, contact_info) VALUES ('Jane Smith', 'jane.smith@example.com');

-- Insert OrderStatus
INSERT INTO tbl_order_status (status_name) VALUES ('Pending');
INSERT INTO tbl_order_status (status_name) VALUES ('Processed');
INSERT INTO tbl_order_status (status_name) VALUES ('Shipped');
INSERT INTO tbl_order_status (status_name) VALUES ('Delivered');

-- Insert Orders
INSERT INTO tbl_order (userid, date_placed, order_statusid) VALUES (4, NOW(), 1);
INSERT INTO tbl_order (userid, date_placed, order_statusid) VALUES (4, NOW(), 4);

-- Insert Sales Records
INSERT INTO tbl_sales (orderid, sales_date, quantity, total_amount) VALUES (1, NOW(), 2, 59.98);
INSERT INTO tbl_sales (orderid, sales_date, quantity, total_amount) VALUES (2, NOW(), 1, 59.99);

-- Insert into Junction Tables for Many-to-Many Relationships
-- Link Products to Suppliers
INSERT INTO tbl_supplier_products (supplierid, productid) VALUES (1, 1);
INSERT INTO tbl_supplier_products (supplierid, productid) VALUES (2, 2);

-- Link Products to Orders
INSERT INTO tbl_order_details (orderid, productid) VALUES (1, 1);
INSERT INTO tbl_order_details (orderid, productid) VALUES (2, 2);
