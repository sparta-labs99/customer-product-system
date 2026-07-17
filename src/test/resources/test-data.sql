-- ====================================================================
-- 1. Customer Data (기존 유지)
-- ====================================================================
INSERT INTO customers (name, email, password, phone_number, status, created_at, updated_at) VALUES
                                                                                                ('김민수', 'user01@example.com', '$2a$04$tEbz419zqq3C7vrvYzxeXeL5vjLpphS/0cuup8cjWKODDalHrBDxy', '010-1001-1001', 'ACTIVE', NOW(), NOW()),
                                                                                                ('이지은', 'user02@example.com', '$2a$04$tEbz419zqq3C7vrvYzxeXeL5vjLpphS/0cuup8cjWKODDalHrBDxy', '010-1002-1002', 'ACTIVE', NOW(), NOW()),
                                                                                                ('박지호', 'user03@example.com', '$2a$04$tEbz419zqq3C7vrvYzxeXeL5vjLpphS/0cuup8cjWKODDalHrBDxy', '010-1003-1003', 'ACTIVE', NOW(), NOW()),
                                                                                                ('최유리', 'user04@example.com', '$2a$04$tEbz419zqq3C7vrvYzxeXeL5vjLpphS/0cuup8cjWKODDalHrBDxy', '010-1004-1004', 'INACTIVE', NOW(), NOW()),
                                                                                                ('정현우', 'user05@example.com', '$2a$04$tEbz419zqq3C7vrvYzxeXeL5vjLpphS/0cuup8cjWKODDalHrBDxy', '010-1005-1005', 'ACTIVE', NOW(), NOW()),
                                                                                                ('강민지', 'user06@example.com', '$2a$04$tEbz419zqq3C7vrvYzxeXeL5vjLpphS/0cuup8cjWKODDalHrBDxy', '010-1006-1006', 'ACTIVE', NOW(), NOW()),
                                                                                                ('조승현', 'user07@example.com', '$2a$04$tEbz419zqq3C7vrvYzxeXeL5vjLpphS/0cuup8cjWKODDalHrBDxy', '010-1007-1007', 'SUSPENDED', NOW(), NOW()),
                                                                                                ('윤서연', 'user08@example.com', '$2a$04$tEbz419zqq3C7vrvYzxeXeL5vjLpphS/0cuup8cjWKODDalHrBDxy', '010-1008-1008', 'ACTIVE', NOW(), NOW()),
                                                                                                ('장민철', 'user09@example.com', '$2a$04$tEbz419zqq3C7vrvYzxeXeL5vjLpphS/0cuup8cjWKODDalHrBDxy', '010-1009-1009', 'ACTIVE', NOW(), NOW()),
                                                                                                ('임수정', 'user10@example.com', '$2a$04$tEbz419zqq3C7vrvYzxeXeL5vjLpphS/0cuup8cjWKODDalHrBDxy', '010-1010-1010', 'ACTIVE', NOW(), NOW()),
                                                                                                ('한도윤', 'user11@example.com', '$2a$04$tEbz419zqq3C7vrvYzxeXeL5vjLpphS/0cuup8cjWKODDalHrBDxy', '010-1011-1011', 'ACTIVE', NOW(), NOW()),
                                                                                                ('송지아', 'user12@example.com', '$2a$04$tEbz419zqq3C7vrvYzxeXeL5vjLpphS/0cuup8cjWKODDalHrBDxy', '010-1012-1012', 'INACTIVE', NOW(), NOW()),
                                                                                                ('오은우', 'user13@example.com', '$2a$04$tEbz419zqq3C7vrvYzxeXeL5vjLpphS/0cuup8cjWKODDalHrBDxy', '010-1013-1013', 'ACTIVE', NOW(), NOW()),
                                                                                                ('신가영', 'user14@example.com', '$2a$04$tEbz419zqq3C7vrvYzxeXeL5vjLpphS/0cuup8cjWKODDalHrBDxy', '010-1014-1014', 'ACTIVE', NOW(), NOW()),
                                                                                                ('황우진', 'user15@example.com', '$2a$04$tEbz419zqq3C7vrvYzxeXeL5vjLpphS/0cuup8cjWKODDalHrBDxy', '010-1015-1015', 'ACTIVE', NOW(), NOW());

-- ====================================================================
-- 2. Admin Data (기존 유지)
-- ====================================================================
INSERT INTO admins (name, email, password, phone_number, role, status, created_at, updated_at) VALUES
                                                                                                   ('최고관리자', 'superadmin@example.com', '$2a$04$tEbz419zqq3C7vrvYzxeXeL5vjLpphS/0cuup8cjWKODDalHrBDxy', '010-9999-9999', 'SUPER_ADMIN', 'ACTIVE', NOW(), NOW()),
                                                                                                   ('운영관리자', 'opadmin@example.com', '$2a$04$tEbz419zqq3C7vrvYzxeXeL5vjLpphS/0cuup8cjWKODDalHrBDxy', '010-8888-8888', 'OPERATION_ADMIN', 'ACTIVE', NOW(), NOW());

-- ====================================================================
-- 3. Product Data (기존 1~5번 유지 + 6~10번 추가)
-- ====================================================================
INSERT INTO products (name, category, price, stock, status, admin_id, created_at, updated_at) VALUES
                                                                                                  ('삼성 갤럭시 S24', 'ELECTRONICS', 1200000, 50, 'FOR_SALE', 1, NOW(), NOW()),
                                                                                                  ('나이키 에어포스 1', 'FASHION', 139000, 100, 'FOR_SALE', 1, NOW(), NOW()),
                                                                                                  ('신라면 40봉', 'FOOD', 30000, 200, 'FOR_SALE', 2, NOW(), NOW()),
                                                                                                  ('LG 그램 16인치', 'ELECTRONICS', 1800000, 0, 'OUT_OF_STOCK', 1, NOW(), NOW()),
                                                                                                  ('무지 티셔츠', 'FASHION', 15000, 300, 'FOR_SALE', 2, NOW(), NOW()),
                                                                                                  ('소니 헤드폰 WH-1000XM5', 'ELECTRONICS', 399000, 40, 'FOR_SALE', 1, NOW(), NOW()),
                                                                                                  ('스파오 옥스포드 셔츠', 'FASHION', 29900, 150, 'FOR_SALE', 2, NOW(), NOW()),
                                                                                                  ('햇반 24개입', 'FOOD', 24000, 500, 'FOR_SALE', 2, NOW(), NOW()),
                                                                                                  ('아이패드 에어 6세대', 'ELECTRONICS', 899000, 15, 'FOR_SALE', 1, NOW(), NOW()),
                                                                                                  ('예란지 닭가슴살 3kg', 'FOOD', 35000, 120, 'FOR_SALE', 2, NOW(), NOW());

-- ====================================================================
-- 4. Order Data
-- ====================================================================
INSERT INTO orders (order_number, customer_id, product_id, admin_id, quantity, total_price, status, created_at, updated_at) VALUES
                                                                                                                                ('ORD-20260713-0001', 1, 1, 1, 1, 1200000, 'COMPLETED', NOW(), NOW()),
                                                                                                                                ('ORD-20260713-0002', 2, 2, NULL, 2, 278000, 'SHIPPING', NOW(), NOW()),
                                                                                                                                ('ORD-20260713-0003', 3, 3, NULL, 1, 30000, 'PENDING', NOW(), NOW()),
                                                                                                                                ('ORD-20260713-0004', 4, 1, 1, 1, 1200000, 'CANCELED', NOW(), NOW()),
                                                                                                                                ('ORD-20260713-0005', 5, 5, NULL, 3, 45000, 'PENDING', NOW(), NOW()),
                                                                                                                                ('ORD-20260713-0006', 1, 6, NULL, 1, 399000, 'COMPLETED', NOW(), NOW()), -- 김민수 소니 헤드폰 구매
                                                                                                                                ('ORD-20260713-0007', 2, 8, NULL, 1, 24000, 'COMPLETED', NOW(), NOW()),  -- 이지은 햇반 구매
                                                                                                                                ('ORD-20260713-0008', 8, 10, NULL, 2, 70000, 'COMPLETED', NOW(), NOW()); -- 윤서연 닭가슴살 구매

-- ====================================================================
-- 5. Review Data
-- ====================================================================
INSERT INTO reviews (rating, contents, status, customer_id, product_id, order_id, created_at, updated_at) VALUES
(5, '화면도 넓고 화질이 미쳤어요. 성능 최고입니다!', 'normal', 1, 1, 1, NOW(), NOW()),
(4, '노이즈 캔슬링은 정말 신세계네요. 귀가 살짝 더운 것 빼곤 만족합니다.', 'normal', 1, 6, 6, NOW(), NOW()),
(5, '자취생 필수템이죠. 저렴하게 잘 샀습니다.', 'normal', 2, 8, 7, NOW(), NOW()),
(3, '가성비는 좋은데 퍽퍽함이 다른 제품보다 좀 더 느껴져서 아쉬워요.', 'normal', 8, 10, 8, NOW(), NOW()),
(1, '사이즈가 잘못 와서 반품신청 하려다 귀찮아서 그냥 둡니다. 평점 깎아요.', 'deleted', 2, 2, 2, NOW(), NOW());

COMMIT;