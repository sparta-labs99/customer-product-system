-- Customer Data
INSERT INTO customers (name, email, password, phone_number, status, created_at, updated_at) VALUES
('김민수', 'user01@example.com', '$2a$04$JdjUbxUUiB1osaLbFi4Y..SmyTxJkWe0ZtwXR//7nz.1YhmPXmOA2', '010-1001-1001', 'ACTIVE', NOW(), NOW()),
('이지은', 'user02@example.com', '$2a$04$JdjUbxUUiB1osaLbFi4Y..SmyTxJkWe0ZtwXR//7nz.1YhmPXmOA2', '010-1002-1002', 'ACTIVE', NOW(), NOW()),
('박지호', 'user03@example.com', '$2a$04$JdjUbxUUiB1osaLbFi4Y..SmyTxJkWe0ZtwXR//7nz.1YhmPXmOA2', '010-1003-1003', 'ACTIVE', NOW(), NOW()),
('최유리', 'user04@example.com', '$2a$04$JdjUbxUUiB1osaLbFi4Y..SmyTxJkWe0ZtwXR//7nz.1YhmPXmOA2', '010-1004-1004', 'INACTIVE', NOW(), NOW()),
('정현우', 'user05@example.com', '$2a$04$JdjUbxUUiB1osaLbFi4Y..SmyTxJkWe0ZtwXR//7nz.1YhmPXmOA2', '010-1005-1005', 'ACTIVE', NOW(), NOW()),
('강민지', 'user06@example.com', '$2a$04$JdjUbxUUiB1osaLbFi4Y..SmyTxJkWe0ZtwXR//7nz.1YhmPXmOA2', '010-1006-1006', 'ACTIVE', NOW(), NOW()),
('조승현', 'user07@example.com', '$2a$04$JdjUbxUUiB1osaLbFi4Y..SmyTxJkWe0ZtwXR//7nz.1YhmPXmOA2', '010-1007-1007', 'SUSPENDED', NOW(), NOW()),
('윤서연', 'user08@example.com', '$2a$04$JdjUbxUUiB1osaLbFi4Y..SmyTxJkWe0ZtwXR//7nz.1YhmPXmOA2', '010-1008-1008', 'ACTIVE', NOW(), NOW()),
('장민철', 'user09@example.com', '$2a$04$JdjUbxUUiB1osaLbFi4Y..SmyTxJkWe0ZtwXR//7nz.1YhmPXmOA2', '010-1009-1009', 'ACTIVE', NOW(), NOW()),
('임수정', 'user10@example.com', '$2a$04$JdjUbxUUiB1osaLbFi4Y..SmyTxJkWe0ZtwXR//7nz.1YhmPXmOA2', '010-1010-1010', 'ACTIVE', NOW(), NOW()),
('한도윤', 'user11@example.com', '$2a$04$JdjUbxUUiB1osaLbFi4Y..SmyTxJkWe0ZtwXR//7nz.1YhmPXmOA2', '010-1011-1011', 'ACTIVE', NOW(), NOW()),
('송지아', 'user12@example.com', '$2a$04$JdjUbxUUiB1osaLbFi4Y..SmyTxJkWe0ZtwXR//7nz.1YhmPXmOA2', '010-1012-1012', 'INACTIVE', NOW(), NOW()),
('오은우', 'user13@example.com', '$2a$04$JdjUbxUUiB1osaLbFi4Y..SmyTxJkWe0ZtwXR//7nz.1YhmPXmOA2', '010-1013-1013', 'ACTIVE', NOW(), NOW()),
('신가영', 'user14@example.com', '$2a$04$JdjUbxUUiB1osaLbFi4Y..SmyTxJkWe0ZtwXR//7nz.1YhmPXmOA2', '010-1014-1014', 'ACTIVE', NOW(), NOW()),
('황우진', 'user15@example.com', '$2a$04$JdjUbxUUiB1osaLbFi4Y..SmyTxJkWe0ZtwXR//7nz.1YhmPXmOA2', '010-1015-1015', 'ACTIVE', NOW(), NOW());

-- Admin Data
INSERT INTO admins (name, email, password, phone_number, role, status, created_at) VALUES
('최고관리자', 'superadmin@example.com', '$2a$04$JdjUbxUUiB1osaLbFi4Y..SmyTxJkWe0ZtwXR//7nz.1YhmPXmOA2', '010-9999-9999', 'SUPER_ADMIN', 'ACTIVE', NOW()),
('운영관리자', 'opadmin@example.com', '$2a$04$JdjUbxUUiB1osaLbFi4Y..SmyTxJkWe0ZtwXR//7nz.1YhmPXmOA2', '010-8888-8888', 'OPERATION_ADMIN', 'ACTIVE', NOW());

-- Product Data
INSERT INTO products (name, category, price, stock, status, admin_id, created_at, updated_at) VALUES
('삼성 갤럭시 S24', 'ELECTRONICS', 1200000, 50, 'FOR_SALE', 1, NOW(), NOW()),
('나이키 에어포스 1', 'FASHION', 139000, 100, 'FOR_SALE', 1, NOW(), NOW()),
('신라면 40봉', 'FOOD', 30000, 200, 'FOR_SALE', 2, NOW(), NOW()),
('LG 그램 16인치', 'ELECTRONICS', 1800000, 0, 'OUT_OF_STOCK', 1, NOW(), NOW()),
('무지 티셔츠', 'FASHION', 15000, 300, 'FOR_SALE', 2, NOW(), NOW());

-- Order Data
INSERT INTO orders (order_number, customer_id, product_id, admin_id, quantity, total_price, status, created_at, updated_at) VALUES
('ORD-20260713-0001', 1, 1, 1, 1, 1200000, 'COMPLETED', NOW(), NOW()),
('ORD-20260713-0002', 2, 2, NULL, 2, 278000, 'SHIPPING', NOW(), NOW()),
('ORD-20260713-0003', 3, 3, NULL, 1, 30000, 'PENDING', NOW(), NOW()),
('ORD-20260713-0004', 4, 1, 1, 1, 1200000, 'CANCELED', NOW(), NOW()),
('ORD-20260713-0005', 5, 5, NULL, 3, 45000, 'PENDING', NOW(), NOW());

commit;