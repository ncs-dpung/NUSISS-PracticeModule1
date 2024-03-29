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

--------------- User Management ---------------
-- Insert Roles
INSERT INTO tbl_role (role_name, created_at, updated_at, created_by, updated_by) VALUES ('Admin', NOW(), NOW(), 'system', 'system');

-- Insert Actions
INSERT INTO tbl_action (authority, created_at, updated_at, created_by, updated_by) VALUES ('C,R,U,D', NOW(), NOW(), 'system', 'system');

-- Insert Role-Action Junctions
INSERT INTO role_action_junction (role_id, action_id) VALUES (1, 1);

-- Insert Staff
INSERT INTO tbl_staff (first_name, last_name, position, department, email, phone_number, address, created_at, updated_at, created_by, updated_by)
VALUES ('John', 'Doe', 'Manager', 'IT', 'john.doe@example.com', '123-456-7890', '1234 Street Name, City', NOW(), NOW(), 'system', 'system');

-- Insert Users
INSERT INTO tbl_user (user_name, email, password, staff_id, created_at, updated_at, created_by, updated_by)
VALUES ('johndoe', 'john.doe@example.com', 'encrypted_password_here', 1, NOW(), NOW(), 'system', 'system');

--------------- User Management ---------------

-- Insert Categories
INSERT INTO tbl_category (category_name) VALUES ('Yarn');
INSERT INTO tbl_category (category_name) VALUES ('Crochet Hooks');


-- Insert Suppliers
INSERT INTO tbl_supplier (supplier_name, contact_info, address) VALUES ('Lion', 'contact@lionbrand.com', '123 Lion Street');
INSERT INTO tbl_supplier (supplier_name, contact_info, address) VALUES ('Sirdar', 'info@sirdar.com', '456 Sirdar Road');
INSERT INTO tbl_supplier (supplier_name, contact_info, address) VALUES ('Cori Cori Yarn', 'support@coricoriyarn.com', '789 Cori Cori Ave');

-- Insert Products
-- Yarns
INSERT INTO tbl_product (name, category_id, price, batch_no, supplier_id, quantity_available, reorder_level) VALUES ('24/7 Ecru', 1, 5.00, '098', 1, 8, 10); -- Low stock
INSERT INTO tbl_product (name, category_id, price, batch_no, supplier_id, quantity_available, reorder_level) VALUES ('24/7 White', 1, 5.00, '100', 1, 150, 15); -- Sufficient stock
INSERT INTO tbl_product (name, category_id, price, batch_no, supplier_id, quantity_available, reorder_level) VALUES ('24/7 Pink', 1, 5.00, '101', 1, 6, 10); -- Low stock
INSERT INTO tbl_product (name, category_id, price, batch_no, supplier_id, quantity_available, reorder_level) VALUES ('Super Soft Wool', 1, 7.50, 'SSW001', 1, 20, 30); -- Low stock
INSERT INTO tbl_product (name, category_id, price, batch_no, supplier_id, quantity_available, reorder_level) VALUES ('Chunky Cotton', 1, 8.00, 'CC002', 2, 150, 100); -- Sufficient stock
INSERT INTO tbl_product (name, category_id, price, batch_no, supplier_id, quantity_available, reorder_level) VALUES ('Cotton DK Midnight', 1, 4.50, '500', 2, 90, 20); -- Sufficient stock
INSERT INTO tbl_product (name, category_id, price, batch_no, supplier_id, quantity_available, reorder_level) VALUES ('Cotton DK Island White', 1, 4.50, '501', 2, 20, 20); -- Low stock
INSERT INTO tbl_product (name, category_id, price, batch_no, supplier_id, quantity_available, reorder_level) VALUES ('Cotton DK Vanilla', 1, 4.50, '502', 2, 15, 15); -- Low stock
-- Crochet Hooks
INSERT INTO tbl_product (name, category_id, price, batch_no, supplier_id, quantity_available, reorder_level) VALUES ('Tulip', 2, 12.00, 'TLP003', 3, 60, 50); -- Sufficient stock
INSERT INTO tbl_product (name, category_id, price, batch_no, supplier_id, quantity_available, reorder_level) VALUES ('Clover', 2, 8.00, 'CLV004', 3, 30, 50); -- Low stock
INSERT INTO tbl_product (name, category_id, price, batch_no, supplier_id, quantity_available, reorder_level) VALUES ('Boye', 2, 5.00, 'BY005', 3, 10, 50); -- Low stock

 -- Insert Customers
 INSERT INTO tbl_customer (name, contact_info) VALUES ('John Doe', 'john.doe@example.com');
 INSERT INTO tbl_customer (name, contact_info) VALUES ('Jane Smith', 'jane.smith@example.com');

 -- Insert OrderStatus
 INSERT INTO tbl_order_status (status_name) VALUES ('Pending');
 INSERT INTO tbl_order_status (status_name) VALUES ('Processed');
 INSERT INTO tbl_order_status (status_name) VALUES ('Delivered');

 -- Insert Orders
INSERT INTO tbl_order (user_id, customer_id, date_placed, date_shipped, order_status_id) VALUES (1, 1, NOW(), NOW(), 3);
INSERT INTO tbl_order (user_id, customer_id, date_placed, date_shipped, order_status_id) VALUES (1, 1, NOW(), NULL, 1);
INSERT INTO tbl_order (user_id, customer_id, date_placed, date_shipped, order_status_id) VALUES (1, 2, NOW(), NULL, 2);

 -- Link Products to Orders
INSERT INTO tbl_order_items (order_id, product_id, quantity, price)
VALUES (1, 3, 2, 10.00), -- 2 units of product 3, each priced at $10.00 for order 1
       (1, 2, 4, 20.00);  -- 4 units of product 2, each priced at $20.00 for order 1
INSERT INTO tbl_order_items (order_id, product_id, quantity, price)
VALUES (2, 1, 1, 5.00); -- 1 unit of product 1, priced at $5.00 for order 2
INSERT INTO tbl_order_items (order_id, product_id, quantity, price)
VALUES (3, 4, 3, 22.50); -- 3 units of product 4, priced at $22.50 for order 3
--
-- -- Insert Sales Records
-- INSERT INTO tbl_sales (order_id, sales_date, quantity, total_amount) VALUES (1, NOW(), 2, 59.98);
-- INSERT INTO tbl_sales (order_id, sales_date, quantity, total_amount) VALUES (2, NOW(), 1, 59.99);

