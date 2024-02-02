DROP DATABASE IF EXISTS inventory-db;
CREATE DATABASE inventory-db;
USE inventory-db;

-- Create Role Table
CREATE TABLE IF NOT EXISTS tbl_role (
    roleid BIGINT PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
    role_name VARCHAR(255) NOT NULL
);

-- Create User Table
CREATE TABLE IF NOT EXISTS tbl_user (
    userid BIGINT PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
    user_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    roleid BIGINT,
    created_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (roleid) REFERENCES tbl_role(roleid)
);

-- Create Category Table
CREATE TABLE IF NOT EXISTS tbl_category (
    categoryid BIGINT PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
    category_name VARCHAR(255) NOT NULL
);

-- Create Supplier Table
CREATE TABLE IF NOT EXISTS tbl_supplier (
    supplierid BIGINT PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
    supplier_name VARCHAR(255) NOT NULL,
    contact_info VARCHAR(255),
    address VARCHAR(255)
);

-- Create Product Table
CREATE TABLE IF NOT EXISTS tbl_product (
    productid BIGINT PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    categoryid BIGINT,
    price DECIMAL(10, 2) NOT NULL,
    quantity INT NOT NULL,
    batch_no VARCHAR(255),
    FOREIGN KEY (categoryid) REFERENCES tbl_category(categoryid)
);

-- Create Inventory Table
CREATE TABLE IF NOT EXISTS tbl_inventory (
    inventoryid BIGINT PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
    productid BIGINT,
    quantity_available INT NOT NULL,
    reorder_level INT NOT NULL,
    FOREIGN KEY (productid) REFERENCES tbl_product(productid)
);

-- Create Customer Table
CREATE TABLE IF NOT EXISTS tbl_customer (
    customerid BIGINT PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    contact_info VARCHAR(255)
);

-- Create Order Status Table
CREATE TABLE IF NOT EXISTS tbl_order_status (
    order_statusid BIGINT PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
    status_name VARCHAR(255) NOT NULL
);

-- Create Order Table
CREATE TABLE IF NOT EXISTS tbl_order (
    orderid BIGINT PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
    userid BIGINT,
    date_placed DATETIME NOT NULL,
    order_statusid BIGINT,
    FOREIGN KEY (userid) REFERENCES tbl_user(userid),
    FOREIGN KEY (order_statusid) REFERENCES tbl_order_status(order_statusid)
);

-- Create Sales Table
CREATE TABLE IF NOT EXISTS tbl_sales (
    salesid BIGINT PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
    orderid BIGINT,
    sales_date DATETIME NOT NULL,
    quantity INT NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (orderid) REFERENCES tbl_order(orderid)
);

-- Junction Table for Many-to-Many relationship between Orders and Products
CREATE TABLE IF NOT EXISTS tbl_order_details (
    orderid BIGINT,
    productid BIGINT,
    PRIMARY KEY (orderid, productid),
    FOREIGN KEY (orderid) REFERENCES tbl_order(orderid),
    FOREIGN KEY (productid) REFERENCES tbl_product(productid)
);

-- Junction Table for Many-to-Many relationship between Suppliers and Products
CREATE TABLE IF NOT EXISTS tbl_supplier_products (
    supplierid BIGINT,
    productid BIGINT,
    PRIMARY KEY (supplierid, productid),
    FOREIGN KEY (supplierid) REFERENCES tbl_supplier(supplierid),
    FOREIGN KEY (productid) REFERENCES tbl_product(productid)
);
