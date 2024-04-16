DROP DATABASE IF EXISTS inventory_db;
CREATE DATABASE inventory_db;
USE inventory_db;

CREATE TABLE IF NOT EXISTS tbl_action (
	action_id bigint PRIMARY KEY NOT NULL,
	created_at datetime(6) NOT NULL,
	created_by varchar(255) NOT NULL,
	updated_at datetime(6) NOT NULL,
	updated_by varchar(255) NOT NULL,
	authority varchar(255) DEFAULT NULL
);
CREATE TABLE IF NOT EXISTS tbl_action_seq (
  next_val bigint DEFAULT NULL
);


CREATE TABLE IF NOT EXISTS tbl_role (
  role_id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
  created_at datetime(6) NOT NULL,
  created_by varchar(255) NOT NULL,
  updated_at datetime(6) NOT NULL,
  updated_by varchar(255) NOT NULL,
  role_name varchar(255) DEFAULT NULL
);


CREATE TABLE IF NOT EXISTS tbl_user (
  user_id bigint PRIMARY KEY NOT NULL,
  created_at datetime(6) NOT NULL,
  created_by varchar(255) NOT NULL,
  updated_at datetime(6) NOT NULL,
  updated_by varchar(255) NOT NULL,
  email varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  user_name varchar(255) DEFAULT NULL,
  staff_id bigint DEFAULT NULL,
  UNIQUE KEY UK_6jr81l5qqpxjp72fgi23ubqc9 (user_name),
  UNIQUE KEY UK_q5evh1040f2kot3qkedknhcnw (staff_id)
);
CREATE TABLE IF NOT EXISTS tbl_user_seq (
  next_val bigint DEFAULT NULL
);


CREATE TABLE IF NOT EXISTS tbl_staff (
  staff_id bigint PRIMARY KEY NOT NULL,
  created_at datetime(6) NOT NULL,
  created_by varchar(255) NOT NULL,
  updated_at datetime(6) NOT NULL,
  updated_by varchar(255) NOT NULL,
  first_name varchar(255) DEFAULT NULL,
  last_name varchar(255) DEFAULT NULL,
  user_id bigint DEFAULT NULL,
  UNIQUE KEY UK_q2grayd65whio4f09wrksgppi (user_id),
  CONSTRAINT FKcext7q2qufelwm0px5g5ocs2 FOREIGN KEY (user_id) REFERENCES tbl_user (user_id)
);
ALTER TABLE tbl_user 
ADD CONSTRAINT FK3095tuitq1uai23erfm9bh056 FOREIGN KEY (staff_id) REFERENCES tbl_staff (staff_id);

CREATE TABLE IF NOT EXISTS tbl_staff_seq (
  next_val bigint DEFAULT NULL
);


CREATE TABLE IF NOT EXISTS role_action_junction (
  role_id bigint NOT NULL,
  action_id bigint NOT NULL,
  PRIMARY KEY (role_id,action_id),
  KEY FKnglu36kin16ib0dtu4smdot93 (action_id),
  CONSTRAINT FKnglu36kin16ib0dtu4smdot93 FOREIGN KEY (action_id) REFERENCES tbl_action (action_id),
  CONSTRAINT FKtjrp0a5mjoc54xhxk7jx66i1p FOREIGN KEY (role_id) REFERENCES tbl_role (role_id)
);
CREATE TABLE IF NOT EXISTS user_role_junction (
  user_id bigint NOT NULL,
  role_id bigint NOT NULL,
  PRIMARY KEY (user_id,role_id),
  KEY FKtha8dn4i95nh9hl8f9wmf9ywj (role_id),
  CONSTRAINT FK8h61ju8ce7ja9binnpl8yk4u4 FOREIGN KEY (user_id) REFERENCES tbl_user (user_id),
  CONSTRAINT FKtha8dn4i95nh9hl8f9wmf9ywj FOREIGN KEY (role_id) REFERENCES tbl_role (role_id)
);

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
    staff_id BIGINT,
    customer_id BIGINT,
    date_placed DATE NOT NULL,
    date_shipped DATE,
    order_status_id BIGINT,
    FOREIGN KEY (staff_id) REFERENCES tbl_staff(staff_id),
    FOREIGN KEY (customer_id) REFERENCES tbl_customer(customer_id),
    FOREIGN KEY (order_status_id) REFERENCES tbl_order_status(order_status_id)
);

-- Junction Table for Many-to-Many relationship between Orders and Products
CREATE TABLE IF NOT EXISTS tbl_order_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT,
    product_id BIGINT,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES tbl_order(order_id),
    FOREIGN KEY (product_id) REFERENCES tbl_product(product_id)
);
