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
 INSERT INTO tbl_order (user_id, date_placed, order_status_id) VALUES (4, NOW(), 1);
 INSERT INTO tbl_order (user_id, date_placed, order_status_id) VALUES (4, NOW(), 4);

 -- Link Products to Orders
 INSERT INTO tbl_order_details (order_id, product_id) VALUES (1, 1);
 INSERT INTO tbl_order_details (order_id, product_id) VALUES (2, 2);
--
-- -- Insert Sales Records
-- INSERT INTO tbl_sales (order_id, sales_date, quantity, total_amount) VALUES (1, NOW(), 2, 59.98);
-- INSERT INTO tbl_sales (order_id, sales_date, quantity, total_amount) VALUES (2, NOW(), 1, 59.99);

