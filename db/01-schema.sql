DROP DATABASE IF EXISTS inventory_db;
CREATE DATABASE inventory_db;
USE inventory_db;

--    -- Create Role Table
--    CREATE TABLE tbl_role (
--        role_id BIGINT AUTO_INCREMENT PRIMARY KEY,
--        role_name VARCHAR(255) NOT NULL,
--        created_at TIMESTAMP NOT NULL,
--        updated_at TIMESTAMP NOT NULL,
--        created_by VARCHAR(255) NOT NULL,
--        updated_by VARCHAR(255) NOT NULL
--    );
--    CREATE TABLE tbl_action (
--        action_id BIGINT AUTO_INCREMENT PRIMARY KEY,
--        authority VARCHAR(255) NOT NULL,
--        created_at TIMESTAMP NOT NULL,
--        updated_at TIMESTAMP NOT NULL,
--        created_by VARCHAR(255) NOT NULL,
--        updated_by VARCHAR(255) NOT NULL
--    );
--
--    -- Create Role-Action Junction Table
--    CREATE TABLE role_action_junction (
--        role_id BIGINT NOT NULL,
--        action_id BIGINT NOT NULL,
--        PRIMARY KEY (role_id, action_id),
--        FOREIGN KEY (role_id) REFERENCES tbl_role(role_id),
--        FOREIGN KEY (action_id) REFERENCES tbl_action(action_id)
--    );
--
--    -- Create Staff Table
--    CREATE TABLE tbl_staff (
--        staff_id BIGINT AUTO_INCREMENT PRIMARY KEY,
--        first_name VARCHAR(255) NOT NULL,
--        last_name VARCHAR(255) NOT NULL,
--        position VARCHAR(255),
--        department VARCHAR(255),
--        email VARCHAR(255) UNIQUE NOT NULL,
--        phone_number VARCHAR(255),
--        address VARCHAR(255),
--        created_at TIMESTAMP NOT NULL,
--        updated_at TIMESTAMP NOT NULL,
--        created_by VARCHAR(255) NOT NULL,
--        updated_by VARCHAR(255) NOT NULL
--    );
--
--    -- Create User Table
--    CREATE TABLE tbl_user (
--        user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
--        user_name VARCHAR(255) UNIQUE NOT NULL,
--        email VARCHAR(255) NOT NULL,
--        password VARCHAR(255) NOT NULL,
--        staff_id BIGINT,
--        created_at TIMESTAMP NOT NULL,
--        updated_at TIMESTAMP NOT NULL,
--        created_by VARCHAR(255) NOT NULL,
--        updated_by VARCHAR(255) NOT NULL,
--        FOREIGN KEY (staff_id) REFERENCES tbl_staff(staff_id)
--    );

 -- Create Category Table
 CREATE TABLE IF NOT EXISTS tbl_category (
     category_id BIGINT PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
     category_name VARCHAR(255) NOT NULL
 );

 -- Create Supplier Table
 CREATE TABLE IF NOT EXISTS tbl_supplier (
     supplier_id BIGINT PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
     supplier_name VARCHAR(255) NOT NULL,
     contact_info VARCHAR(255),
     address VARCHAR(255)
 );

 -- Create Product Table (Mixed Product and Inventory Table)
 -- 1 supplier many products; 1 product 1 supplier
 -- 1 category many products; 1 product 1 category
CREATE TABLE IF NOT EXISTS tbl_product (
    product_id BIGINT PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    category_id BIGINT,
    price DECIMAL(10, 2) NOT NULL,
    batch_no VARCHAR(255),
    supplier_id BIGINT,
    quantity_available INT NOT NULL,
    reorder_level INT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES tbl_category(category_id),
    FOREIGN KEY (supplier_id) REFERENCES tbl_supplier(supplier_id)
);

 -- Create Customer Table
 CREATE TABLE IF NOT EXISTS tbl_customer (
     customer_id BIGINT PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
     customer_name VARCHAR(255) NOT NULL,
     contact_info VARCHAR(255)
 );

 -- Create Order Status Table
 CREATE TABLE IF NOT EXISTS tbl_order_status (
     order_status_id BIGINT PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
     status_name VARCHAR(255) NOT NULL
 );

 -- Create Order Table
 CREATE TABLE IF NOT EXISTS tbl_order (
     order_id BIGINT PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
--     user_id BIGINT,
     customer_id BIGINT,
     date_placed DATETIME NOT NULL,
     date_shipped DATETIME,
     order_status_id BIGINT,
--     FOREIGN KEY (user_id) REFERENCES tbl_user(user_id),
     FOREIGN KEY (customer_id) REFERENCES tbl_customer(customer_id),
     FOREIGN KEY (order_status_id) REFERENCES tbl_order_status(order_status_id)
 );

 -- Junction Table for Many-to-Many relationship between Orders and Products
 CREATE TABLE IF NOT EXISTS tbl_order_items (
     order_id BIGINT,
     product_id BIGINT,
     quantity INT NOT NULL,
     price DECIMAL(10, 2) NOT NULL,
     PRIMARY KEY (order_id, product_id),
     FOREIGN KEY (order_id) REFERENCES tbl_order(order_id),
     FOREIGN KEY (product_id) REFERENCES tbl_product(product_id)
 );
--
-- -- Create Sales Table (Sales Report?)
-- CREATE TABLE IF NOT EXISTS tbl_sales (
--     sales_id BIGINT PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
--     order_id BIGINT,
--     sales_date DATETIME NOT NULL,
--     quantity INT NOT NULL,
--     total_amount DECIMAL(10, 2) NOT NULL,
--     FOREIGN KEY (order_id) REFERENCES tbl_order(order_id)
-- );
--
