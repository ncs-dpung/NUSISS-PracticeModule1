 # Supplier Order (Future Feature)
 
 1. Make supplier and product many-to-many relationship
 2. Incorporate with redis to implement SSE for restocking notification
 
 
 
 
 --  -- Create Product Table
 -- CREATE TABLE IF NOT EXISTS tbl_product (
 --     product_id BIGINT PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
 --     name VARCHAR(255) NOT NULL,
 --     category_id BIGINT,
 --     price DECIMAL(10, 2) NOT NULL,
 --     quantity INT NOT NULL,
 --     batch_no VARCHAR(255),
 --     FOREIGN KEY (category_id) REFERENCES tbl_category(category_id)
 -- );
 --
 -- -- Create Inventory Table
 -- CREATE TABLE IF NOT EXISTS tbl_inventory (
 --     inventory_id BIGINT PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
 --     product_id BIGINT,
 --     quantity_available INT NOT NULL,
 --     reorder_level INT NOT NULL,
 --     FOREIGN KEY (product_id) REFERENCES tbl_product(product_id)
 -- );
-- -- Junction Table for Many-to-Many relationship between Suppliers and Products
-- CREATE TABLE IF NOT EXISTS tbl_supplier_products (
--     supplier_id BIGINT,
--     product_id BIGINT,
--     PRIMARY KEY (supplier_id, product_id),
--     FOREIGN KEY (supplier_id) REFERENCES tbl_supplier(supplier_id),
--     FOREIGN KEY (product_id) REFERENCES tbl_product(product_id)
-- );


-- -- Insert Products
-- INSERT INTO tbl_product (name, category_id, price, quantity, batch_no) VALUES ('Rebbe Doll', 1, 29.99, 106, 'DOLL12345');
-- INSERT INTO tbl_product (name, category_id, price, quantity, batch_no) VALUES ('Collector Esther Doll', 3, 59.99, 32, 'DOLL67890');
--
-- -- Insert Inventory Records
-- INSERT INTO tbl_inventory (product_id, quantity_available, reorder_level) VALUES (1, 100, 20);
-- INSERT INTO tbl_inventory (product_id, quantity_available, reorder_level) VALUES (2, 20, 50);
--
-- -- Insert into Junction Tables for Many-to-Many Relationships
-- -- Link Products to Suppliers
-- INSERT INTO tbl_supplier_products (supplier_id, product_id) VALUES (1, 1);
-- INSERT INTO tbl_supplier_products (supplier_id, product_id) VALUES (2, 2);