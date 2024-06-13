create database Ecommerce
-- Tạo bảng Products
CREATE TABLE Products (
    product_id INT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(100) NOT NULL,
    description NVARCHAR(MAX),
    price DECIMAL(18, 2) NOT NULL,
    stock INT NOT NULL
);
-- Thay đổi collation của cột name và description trong bảng Products để sử dụng UTF-8
ALTER TABLE Products
ALTER COLUMN name NVARCHAR(100) COLLATE Latin1_General_100_CI_AS_SC_UTF8 NOT NULL;

ALTER TABLE Products
ALTER COLUMN description NVARCHAR(MAX) COLLATE Latin1_General_100_CI_AS_SC_UTF8;
DROP TABLE Products;

INSERT INTO Products (name, description, price, stock) VALUES
(N'Sản phẩm D', N'Mô tả cho Sản phẩm D', 15.00, 120),
(N'Sản phẩm E', N'Mô tả cho Sản phẩm E', 25.50, 180);


-- Tạo bảng Users
CREATE TABLE Users (
    user_id INT PRIMARY KEY IDENTITY(1,1),
    username NVARCHAR(50) NOT NULL UNIQUE,
    password NVARCHAR(255) NOT NULL,
    role NVARCHAR(20) NOT NULL
);

-- Tạo bảng Orders
CREATE TABLE Orders (
    order_id INT PRIMARY KEY IDENTITY(1,1),
    user_id INT NOT NULL,
    total_amount DECIMAL(18, 2) NOT NULL,
    order_date DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

-- Tạo bảng OrderDetails
CREATE TABLE OrderDetails (
    order_detail_id INT PRIMARY KEY IDENTITY(1,1),
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(18, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES Orders(order_id),
    FOREIGN KEY (product_id) REFERENCES Products(product_id)
);
-- Chèn dữ liệu vào bảng Products
INSERT INTO Products (name, description, price, stock) VALUES
(N'Sản phẩm A', N'Mô tả cho Sản phẩm A', 10.50, 100),
(N'Sản phẩm B', N'Mô tả cho Sản phẩm B', 20.00, 150),
(N'Sản phẩm C', N'Mô tả cho Sản phẩm C', 30.75, 200),
(N'Sản phẩm D', N'Mô tả cho Sản phẩm A', 10.50, 100),
(N'Sản phẩm E', N'Mô tả cho Sản phẩm B', 20.00, 150),
(N'Sản phẩm C', N'Mô tả cho Sản phẩm C', 30.75, 200),
(N'Sản phẩm A', N'Mô tả cho Sản phẩm A', 10.50, 100),
(N'Sản phẩm B', N'Mô tả cho Sản phẩm B', 20.00, 150),
(N'Sản phẩm C', N'Mô tả cho Sản phẩm C', 30.75, 200),
(N'Sản phẩm D', N'Mô tả cho Sản phẩm A', 10.50, 100),
(N'Sản phẩm E', N'Mô tả cho Sản phẩm B', 20.00, 150),
(N'Sản phẩm C', N'Mô tả cho Sản phẩm C', 30.75, 200),
(N'Sản phẩm A', N'Mô tả cho Sản phẩm A', 10.50, 100),
(N'Sản phẩm B', N'Mô tả cho Sản phẩm B', 20.00, 150),
(N'Sản phẩm C', N'Mô tả cho Sản phẩm C', 30.75, 200),
(N'Sản phẩm D', N'Mô tả cho Sản phẩm A', 10.50, 100),
(N'Sản phẩm E', N'Mô tả cho Sản phẩm B', 20.00, 150),
(N'Sản phẩm C', N'Mô tả cho Sản phẩm C', 30.75, 200),
(N'Sản phẩm A', N'Mô tả cho Sản phẩm A', 10.50, 100),
(N'Sản phẩm B', N'Mô tả cho Sản phẩm B', 20.00, 150),
(N'Sản phẩm C', N'Mô tả cho Sản phẩm C', 30.75, 200),
(N'Sản phẩm D', N'Mô tả cho Sản phẩm A', 10.50, 100),
(N'Sản phẩm E', N'Mô tả cho Sản phẩm B', 20.00, 150),
(N'Sản phẩm C', N'Mô tả cho Sản phẩm C', 30.75, 200),
(N'Sản phẩm A', N'Mô tả cho Sản phẩm A', 10.50, 100),
(N'Sản phẩm B', N'Mô tả cho Sản phẩm B', 20.00, 150),
(N'Sản phẩm C', N'Mô tả cho Sản phẩm C', 30.75, 200),
(N'Sản phẩm D', N'Mô tả cho Sản phẩm A', 10.50, 100),
(N'Sản phẩm E', N'Mô tả cho Sản phẩm B', 20.00, 150),
(N'Sản phẩm C', N'Mô tả cho Sản phẩm C', 30.75, 200),
(N'Sản phẩm A', N'Mô tả cho Sản phẩm A', 10.50, 100),
(N'Sản phẩm B', N'Mô tả cho Sản phẩm B', 20.00, 150),
(N'Sản phẩm C', N'Mô tả cho Sản phẩm C', 30.75, 200),
(N'Sản phẩm D', N'Mô tả cho Sản phẩm A', 10.50, 100),
(N'Sản phẩm E', N'Mô tả cho Sản phẩm B', 20.00, 150),
(N'Sản phẩm C', N'Mô tả cho Sản phẩm C', 30.75, 200);
-- Chèn dữ liệu vào bảng Users
INSERT INTO Users (username, password, role) VALUES
('admin', 'admin_password_hash', 'admin'),
('khachhang1', 'khachhang1_password_hash', 'customer'),
('khachhang2', 'khachhang2_password_hash', 'customer');

-- Chèn dữ liệu vào bảng Orders
INSERT INTO Orders (user_id, total_amount, order_date) VALUES
(2, 31.50, '2024-06-05 10:00:00'),  -- Đơn hàng của khách hàng 1, tổng 31.50 (bao gồm 2 sản phẩm A)
(3, 75.50, '2024-06-05 11:00:00');  -- Đơn hàng của khách hàng 2, tổng 75.50

-- Chèn dữ liệu vào bảng OrderDetails
INSERT INTO OrderDetails (order_id, product_id, quantity, price) VALUES
(1, 1, 2, 10.50),  -- Đơn hàng 1 gồm 2 sản phẩm A
(1, 2, 1, 10.50),  -- Đơn hàng 1 gồm 1 sản phẩm B
(2, 3, 2, 30.75);  -- Đơn hàng 2 gồm 2 sản phẩm C

-- Tạo bảng MoneyTransfer
CREATE TABLE MoneyTransfer (
    id INT PRIMARY KEY IDENTITY(1,1),
    Username VARCHAR(255) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    AccountNo VARCHAR(255) NOT NULL,
    AccountType VARCHAR(255) NOT NULL,
    Gender VARCHAR(50) NOT NULL,
    Address VARCHAR(255) NOT NULL,
    Amount DECIMAL(10, 2) NOT NULL
);
-- Chèn dữ liệu vào bảng MoneyTransfer
INSERT INTO MoneyTransfer (Username, Password, AccountNo, AccountType, Gender, Address, Amount) VALUES
('user1', 'password1', '123456789', 'Saving', 'Male', 'Address 1', 1000.00),
('user2', 'password2', '987654321', 'Checking', 'Female', 'Address 2', 1500.00),
('user3', 'password3', '192837465', 'Saving', 'Male', 'Address 3', 2000.00),
('user4', 'password4', '564738291', 'Checking', 'Female', 'Address 4', 2500.00),
('user5', 'password5', '374829165', 'Saving', 'Male', 'Address 5', 3000.00);

DROP TABLE IF EXISTS MoneyTransfer;

CREATE TABLE MoneyTransfer (
    id INT PRIMARY KEY IDENTITY(1,1),
    user_id INT,
    Username NVARCHAR(255) NOT NULL,
    Password NVARCHAR(255) NOT NULL,
    AccountNo NVARCHAR(255) NOT NULL,
    AccountType NVARCHAR(255) NOT NULL,
    Gender NVARCHAR(50) NOT NULL,
    Address NVARCHAR(255) NOT NULL,
    Amount DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

-- Chèn dữ liệu vào bảng MoneyTransfer
INSERT INTO MoneyTransfer (user_id, Username, Password, AccountNo, AccountType, Gender, Address, Amount) VALUES
(1, 'admin', 'admin_password_hash', '111111111', 'Checking', 'Male', 'Admin Address', 5000.00),
(2, 'khachhang1', 'khachhang1_password_hash', '222222222', 'Saving', 'Female', 'Customer Address 1', 1000.00),
(3, 'khachhang2', 'khachhang2_password_hash', '333333333', 'Checking', 'Male', 'Customer Address 2', 1500.00);
