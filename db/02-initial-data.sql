-- Insert Roles
INSERT INTO tbl_role (RoleName) VALUES ('Administrator');
INSERT INTO tbl_role (RoleName) VALUES ('Warehouse Manager');
INSERT INTO tbl_role (RoleName) VALUES ('Procurement Officer');
INSERT INTO tbl_role (RoleName) VALUES ('Salesperson');

-- Insert Users (passwords are just placeholders, ensure to use hashed passwords in production)
INSERT INTO tbl_user (Username, Email, Password, RoleID) VALUES ('admin', 'admin@example.com', 'adminpassword', 1);
INSERT INTO tbl_user (Username, Email, Password, RoleID) VALUES ('warehouse', 'warehouse@example.com', 'warehousepassword', 2);
INSERT INTO tbl_user (Username, Email, Password, RoleID) VALUES ('procurement', 'procurement@example.com', 'procurementpassword', 3);
INSERT INTO tbl_user (Username, Email, Password, RoleID) VALUES ('sales', 'sales@example.com', 'salespassword', 4);

-- Insert Categories
INSERT INTO tbl_category (CategoryName) VALUES ('Normal Dolls');
INSERT INTO tbl_category (CategoryName) VALUES ('Series Dolls');
INSERT INTO tbl_category (CategoryName) VALUES ('Customize Dolls');

-- Insert Suppliers
INSERT INTO tbl_supplier (SupplierName, ContactInfo, Address, AdditionalNotes) VALUES ('Supplier A', 'contact@suppliera.com', 'Address A');
INSERT INTO tbl_supplier (SupplierName, ContactInfo, Address, AdditionalNotes) VALUES ('Supplier B', 'contact@supplierb.com', 'Address B');

-- Insert Products
INSERT INTO tbl_product (Name, CategoryID, Price, batchNo) VALUES ('Rebbe Doll', 1, 29.99, 'DOLL12345');
INSERT INTO tbl_product (Name, CategoryID, Price, batchNo) VALUES ('Collector Esther Doll', 3, 59.99, 'DOLL67890');

-- Insert Inventory Records
INSERT INTO tbl_inventory (ProductID, QuantityAvailable, ReorderLevel) VALUES (1, 100, 20);
INSERT INTO tbl_inventory (ProductID, QuantityAvailable, ReorderLevel) VALUES (2, 20, 50);

-- Insert Customers
INSERT INTO tbl_customer (Name, ContactInfo) VALUES ('John Doe', 'john.doe@example.com');
INSERT INTO tbl_customer (Name, ContactInfo) VALUES ('Jane Smith', 'jane.smith@example.com');

-- Insert OrderStatus
INSERT INTO tbl_order_status (StatusName) VALUES ('Pending');
INSERT INTO tbl_order_status (StatusName) VALUES ('Processed');
INSERT INTO tbl_order_status (StatusName) VALUES ('Shipped');
INSERT INTO tbl_order_status (StatusName) VALUES ('Delivered');

-- Insert Orders
INSERT INTO tbl_order (UserID, DatePlaced, OrderStatusID) VALUES (4, NOW(), 1);
INSERT INTO tbl_order (UserID, DatePlaced, OrderStatusID) VALUES (4, NOW(), 4);

-- Insert Sales Records
INSERT INTO tbl_sales (OrderID, SalesDate, Quantity, TotalAmount) VALUES (1, NOW(), 2, 59.98);
INSERT INTO tbl_sales (OrderID, SalesDate, Quantity, TotalAmount) VALUES (2, NOW(), 1, 59.99);

-- Insert into Junction Tables for Many-to-Many Relationships
-- Link Products to Suppliers
INSERT INTO tbl_supplier_products (SupplierID, ProductID) VALUES (1, 1);
INSERT INTO tbl_supplier_products (SupplierID, ProductID) VALUES (2, 2);

-- Link Products to Orders
INSERT INTO tbl_order_details (OrderID, ProductID) VALUES (1, 1);
INSERT INTO tbl_order_details (OrderID, ProductID) VALUES (2, 2);