-- Insert Roles
INSERT INTO Role (RoleName) VALUES ('Administrator');
INSERT INTO Role (RoleName) VALUES ('Warehouse Manager');
INSERT INTO Role (RoleName) VALUES ('Procurement Officer');
INSERT INTO Role (RoleName) VALUES ('Salesperson');

-- Insert Users (passwords are just placeholders, ensure to use hashed passwords in production)
INSERT INTO User (Username, Email, Password, RoleID) VALUES ('admin', 'admin@example.com', 'adminpassword', 1);
INSERT INTO User (Username, Email, Password, RoleID) VALUES ('warehouse', 'warehouse@example.com', 'warehousepassword', 2);
INSERT INTO User (Username, Email, Password, RoleID) VALUES ('procurement', 'procurement@example.com', 'procurementpassword', 3);
INSERT INTO User (Username, Email, Password, RoleID) VALUES ('sales', 'sales@example.com', 'salespassword', 4);

-- Insert Categories
INSERT INTO Category (CategoryName) VALUES ('Normal Dolls');
INSERT INTO Category (CategoryName) VALUES ('Series Dolls');
INSERT INTO Category (CategoryName) VALUES ('Customize Dolls');

-- Insert Suppliers
INSERT INTO Supplier (SupplierName, ContactInfo, Address, AdditionalNotes) VALUES ('Supplier A', 'contact@suppliera.com', 'Address A');
INSERT INTO Supplier (SupplierName, ContactInfo, Address, AdditionalNotes) VALUES ('Supplier B', 'contact@supplierb.com', 'Address B');

-- Insert Products
INSERT INTO Product (Name, CategoryID, Price, batchNo) VALUES ('Rebbe Doll', 1, 29.99, 'DOLL12345');
INSERT INTO Product (Name, CategoryID, Price, batchNo) VALUES ('Collector Esther Doll', 3, 59.99, 'DOLL67890');

-- Insert Inventory Records
INSERT INTO Inventory (ProductID, QuantityAvailable, ReorderLevel) VALUES (1, 100, 20);
INSERT INTO Inventory (ProductID, QuantityAvailable, ReorderLevel) VALUES (2, 20, 50);

-- Insert Customers
INSERT INTO Customer (Name, ContactInfo) VALUES ('John Doe', 'john.doe@example.com');
INSERT INTO Customer (Name, ContactInfo) VALUES ('Jane Smith', 'jane.smith@example.com');

-- Insert OrderStatus
INSERT INTO OrderStatus (StatusName) VALUES ('Pending');
INSERT INTO OrderStatus (StatusName) VALUES ('Processed');
INSERT INTO OrderStatus (StatusName) VALUES ('Shipped');
INSERT INTO OrderStatus (StatusName) VALUES ('Delivered');

-- Insert Orders
INSERT INTO Order (UserID, DatePlaced, OrderStatusID) VALUES (4, NOW(), 1);
INSERT INTO Order (UserID, DatePlaced, OrderStatusID) VALUES (4, NOW(), 4);

-- Insert Sales Records
INSERT INTO Sales (OrderID, SalesDate, Quantity, TotalAmount) VALUES (1, NOW(), 2, 59.98);
INSERT INTO Sales (OrderID, SalesDate, Quantity, TotalAmount) VALUES (2, NOW(), 1, 59.99);

-- Insert into Junction Tables for Many-to-Many Relationships
-- Link Products to Suppliers
INSERT INTO Supplier_Products (SupplierID, ProductID) VALUES (1, 1);
INSERT INTO Supplier_Products (SupplierID, ProductID) VALUES (2, 2);

-- Link Products to Orders
INSERT INTO Order_Details (OrderID, ProductID) VALUES (1, 1);
INSERT INTO Order_Details (OrderID, ProductID) VALUES (2, 2);
