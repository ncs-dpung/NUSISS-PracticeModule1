-- Create Role Table
CREATE TABLE tbl_role (
    RoleID INT PRIMARY KEY AUTO_INCREMENT,
    RoleName VARCHAR(255) NOT NULL
);

-- Create User Table
CREATE TABLE tbl_user (
    UserID INT PRIMARY KEY AUTO_INCREMENT,
    Username VARCHAR(255) NOT NULL,
    Email VARCHAR(255) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    RoleID INT,
    FOREIGN KEY (RoleID) REFERENCES Role(RoleID)
);

-- Create Category Table
CREATE TABLE tbl_category (
    CategoryID INT PRIMARY KEY AUTO_INCREMENT,
    CategoryName VARCHAR(255) NOT NULL
);

-- Create Supplier Table
CREATE TABLE tbl_supplier (
    SupplierID INT PRIMARY KEY AUTO_INCREMENT,
    SupplierName VARCHAR(255) NOT NULL,
    ContactInfo VARCHAR(255),
    Address VARCHAR(255)
);

-- Create Product Table
CREATE TABLE tbl_product (
    ProductID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(255) NOT NULL,
    CategoryID INT,
    Price DECIMAL(10, 2) NOT NULL,
    Quantity INT NOT NULL,
    batchNo VARCHAR(255),
    FOREIGN KEY (CategoryID) REFERENCES Category(CategoryID)
);

-- Create Inventory Table
CREATE TABLE tbl_inventory (
    InventoryID INT PRIMARY KEY AUTO_INCREMENT,
    ProductID INT,
    QuantityAvailable INT NOT NULL,
    ReorderLevel INT NOT NULL,
    FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
);

-- Create Customer Table
CREATE TABLE tbl_customer (
    CustomerID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(255) NOT NULL,
    ContactInfo VARCHAR(255)
);

-- Create Order Status Table
CREATE TABLE tbl_order_status (
    OrderStatusID INT PRIMARY KEY AUTO_INCREMENT,
    StatusName VARCHAR(255) NOT NULL,
);

-- Create Order Table
CREATE TABLE tbl_order (
    OrderID INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT,
    DatePlaced DATETIME NOT NULL,
    OrderStatusID INT,
    FOREIGN KEY (UserID) REFERENCES User(UserID),
    FOREIGN KEY (OrderStatusID) REFERENCES OrderStatus(OrderStatusID)
);

-- Create Sales Table
CREATE TABLE tbl_sales (
    SalesID INT PRIMARY KEY AUTO_INCREMENT,
    OrderID INT,
    SalesDate DATETIME NOT NULL,
    Quantity INT NOT NULL,
    TotalAmount DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (OrderID) REFERENCES Order(OrderID)
);

-- Junction Table for Many-to-Many relationship between Orders and Products
CREATE TABLE tbl_order_details (
    OrderID INT,
    ProductID INT,
    PRIMARY KEY (OrderID, ProductID),
    FOREIGN KEY (OrderID) REFERENCES Order(OrderID),
    FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
);

-- Junction Table for Many-to-Many relationship between Suppliers and Products
CREATE TABLE tbl_supplier_products (
    SupplierID INT,
    ProductID INT,
    PRIMARY KEY (SupplierID, ProductID),
    FOREIGN KEY (SupplierID) REFERENCES Supplier(SupplierID),
    FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
);
