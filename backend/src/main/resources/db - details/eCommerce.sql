CREATE DATABASE ecommerce;

use ecommerce;




SELECT id, available, brand, category, description, name, price, quantity
FROM ecommerce.product;




INSERT INTO ecommerce.product
(name, price, description, category, brand, available, quantity)
VALUES

-- MOBILES
('iPhone 16 Pro', 129999, 'Apple flagship smartphone', 'Mobiles', 'Apple', true, 25),
('Samsung Galaxy S25 Ultra', 119999, 'Premium Android smartphone', 'Mobiles', 'Samsung', true, 18),
('OnePlus 14', 69999, 'Flagship killer smartphone', 'Mobiles', 'OnePlus', true, 30),

-- LAPTOPS
('MacBook Air M4', 114999, 'Apple M4 lightweight laptop', 'Laptops', 'Apple', true, 12),
('Dell XPS 15', 139999, 'Professional ultrabook', 'Laptops', 'Dell', true, 8),
('Lenovo Legion 5', 99999, 'Gaming laptop with RTX graphics', 'Laptops', 'Lenovo', true, 15),

-- AUDIO
('Sony WH-1000XM6', 29999, 'Noise cancelling headphones', 'Audio', 'Sony', true, 30),
('Boat Rockerz 550', 1999, 'Wireless headphones', 'Audio', 'Boat', true, 75),
('JBL Flip 7', 8999, 'Portable Bluetooth speaker', 'Audio', 'JBL', true, 40),

-- WEARABLES
('Apple Watch Series 11', 45999, 'Advanced health tracking smartwatch', 'Wearables', 'Apple', true, 20),
('Samsung Galaxy Watch 8', 34999, 'Premium Android smartwatch', 'Wearables', 'Samsung', true, 15),
('Noise ColorFit Pro 6', 4999, 'Budget fitness smartwatch', 'Wearables', 'Noise', true, 50),

-- ACCESSORIES
('Logitech MX Master 3S', 9999, 'Wireless productivity mouse', 'Accessories', 'Logitech', true, 40),
('Redragon K552', 3999, 'Mechanical gaming keyboard', 'Accessories', 'Redragon', true, 35),
('Anker 20000mAh Power Bank', 3499, 'Fast charging power bank', 'Accessories', 'Anker', true, 60),

-- CAMERAS
('Canon EOS R10', 89999, 'Mirrorless camera', 'Cameras', 'Canon', true, 10),
('Sony Alpha A6700', 119999, 'Professional mirrorless camera', 'Cameras', 'Sony', true, 8),
('Nikon Z50', 79999, 'Compact mirrorless camera', 'Cameras', 'Nikon', true, 12),

-- FOOTWEAR
('Nike Air Max', 8999, 'Comfortable running shoes', 'Footwear', 'Nike', true, 50),
('Adidas Ultraboost', 11999, 'Premium sports shoes', 'Footwear', 'Adidas', true, 30),
('Puma Velocity Nitro', 6999, 'Lightweight running shoes', 'Footwear', 'Puma', true, 45),

-- CLOTHING
('Levis Slim Fit Jeans', 2499, 'Classic blue denim jeans', 'Clothing', 'Levis', true, 60),
('Puma Sports T-Shirt', 1499, 'Breathable workout t-shirt', 'Clothing', 'Puma', true, 90),
('Allen Solly Casual Shirt', 1999, 'Cotton casual shirt', 'Clothing', 'Allen Solly', true, 55),

-- GAMING
('PlayStation 5 Pro', 69999, 'Next generation gaming console', 'Gaming', 'Sony', true, 20),
('Xbox Series X', 54999, 'Microsoft gaming console', 'Gaming', 'Microsoft', true, 18),
('Nintendo Switch OLED', 32999, 'Portable gaming console', 'Gaming', 'Nintendo', true, 25);