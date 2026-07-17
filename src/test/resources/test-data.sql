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
                                                                                                   ('운영관리자', 'opadmin@examplsuperadmin@example.come.com', '$2a$04$tEbz419zqq3C7vrvYzxeXeL5vjLpphS/0cuup8cjWKODDalHrBDxy', '010-8888-8888', 'OPERATION_ADMIN', 'ACTIVE', NOW(), NOW());

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
                                                                                                  ('예란지 닭가슴살 3kg', 'FOOD', 35000, 120, 'FOR_SALE', 2, NOW(), NOW()),
                                                                                                  ('삼성 갤럭시 버즈3 프로', 'ELECTRONICS', 259000, 80, 'FOR_SALE', 1, NOW(), NOW()),
                                                                                                  ('아디다스 삼바 OG', 'FASHION', 139000, 60, 'FOR_SALE', 2, NOW(), NOW()),
                                                                                                  ('오뚜기 진라면 30봉', 'FOOD', 21000, 400, 'FOR_SALE', 2, NOW(), NOW()),
                                                                                                  ('다이슨 에어랩 컴플리트', 'ELECTRONICS', 650000, 0, 'OUT_OF_STOCK', 1, NOW(), NOW()),
                                                                                                  ('유니클로 히트텍 크루넥', 'FASHION', 19900, 500, 'FOR_SALE', 2, NOW(), NOW()),
                                                                                                  ('애플워치 시리즈10', 'ELECTRONICS', 599000, 35, 'FOR_SALE', 1, NOW(), NOW()),
                                                                                                  ('노스페이스 눕시 패딩', 'FASHION', 289000, 45, 'FOR_SALE', 2, NOW(), NOW()),
                                                                                                  ('곰곰 국내산 삼겹살 1kg', 'FOOD', 18900, 200, 'FOR_SALE', 2, NOW(), NOW()),
                                                                                                  ('레노버 요가 슬림 노트북', 'ELECTRONICS', 1450000, 0, 'DISCONTINUED', 1, NOW(), NOW()),
                                                                                                  ('필라 디스럽터2 스니커즈', 'FASHION', 79000, 90, 'FOR_SALE', 2, NOW(), NOW());

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
                                                                                                                                ('ORD-20260713-0008', 8, 10, NULL, 2, 70000, 'COMPLETED', NOW(), NOW()), -- 윤서연 닭가슴살 구매
                                                                                                                                ('ORD-20260714-0001', 3,  11, NULL, 1, 259000,  'COMPLETED', NOW(), NOW()),
                                                                                                                                ('ORD-20260714-0002', 5,  12, NULL, 1, 139000,  'SHIPPING',  NOW(), NOW()),
                                                                                                                                ('ORD-20260714-0003', 9,  13, NULL, 2, 42000,   'PENDING',   NOW(), NOW()),
                                                                                                                                ('ORD-20260714-0004', 11, 16, 1,    1, 599000,  'COMPLETED', NOW(), NOW()),
                                                                                                                                ('ORD-20260714-0005', 6,  17, NULL, 1, 289000,  'PENDING',   NOW(), NOW()),
                                                                                                                                ('ORD-20260714-0006', 14, 18, NULL, 3, 56700,   'COMPLETED', NOW(), NOW()),
                                                                                                                                ('ORD-20260714-0007', 7,  20, NULL, 1, 79000,   'CANCELED',  NOW(), NOW()),
                                                                                                                                ('ORD-20260715-0001', 1,  9,  1,    1, 899000,  'COMPLETED', NOW(), NOW()),
                                                                                                                                ('ORD-20260715-0002', 4,  2,  NULL, 1, 139000,  'SHIPPING',  NOW(), NOW()),
                                                                                                                                ('ORD-20260715-0003', 10, 3,  NULL, 4, 120000,  'COMPLETED', NOW(), NOW()),
                                                                                                                                ('ORD-20260715-0004', 13, 7,  NULL, 2, 59800,   'PENDING',   NOW(), NOW()),
                                                                                                                                ('ORD-20260715-0005', 15, 15, NULL, 2, 39800,   'COMPLETED', NOW(), NOW()),
                                                                                                                                ('ORD-20260715-0006', 2,  11, NULL, 1, 259000,  'COMPLETED', NOW(), NOW()),
                                                                                                                                ('ORD-20260715-0007', 12, 1,  1,    1, 1200000, 'CANCELED',  NOW(), NOW()),
                                                                                                                                ('ORD-20260716-0001', 8,  13, NULL, 1, 21000,   'COMPLETED', NOW(), NOW()),
                                                                                                                                ('ORD-20260716-0002', 3,  20, NULL, 1, 79000,   'COMPLETED', NOW(), NOW()),
                                                                                                                                ('ORD-20260716-0003', 9,  6,  NULL, 1, 399000,  'SHIPPING',  NOW(), NOW()),
                                                                                                                                ('ORD-20260716-0004', 5,  17, 2,    1, 289000,  'COMPLETED', NOW(), NOW()),
                                                                                                                                ('ORD-20260716-0005', 1,  18, NULL, 2, 37800,   'PENDING',   NOW(), NOW()),
                                                                                                                                ('ORD-20260716-0006', 11, 12, NULL, 1, 139000,  'COMPLETED', NOW(), NOW()),
                                                                                                                                ('ORD-20260717-0001', 6,  9,  NULL, 1, 899000,  'PENDING',   NOW(), NOW()),
                                                                                                                                ('ORD-20260717-0002', 14, 16, NULL, 1, 599000,  'COMPLETED', NOW(), NOW()),
                                                                                                                                ('ORD-20260717-0003', 7,  3,  NULL, 2, 60000,   'COMPLETED', NOW(), NOW()),
                                                                                                                                ('ORD-20260717-0004', 10, 15, NULL, 1, 19900,   'COMPLETED', NOW(), NOW()),
                                                                                                                                ('ORD-20260717-0005', 4,  8,  NULL, 2, 48000,   'COMPLETED', NOW(), NOW());

-- ====================================================================
-- 5. Review Data
-- ====================================================================
INSERT INTO reviews (rating, contents, status, customer_id, product_id, order_id, created_at, updated_at) VALUES
(5, '화면도 넓고 화질이 미쳤어요. 성능 최고입니다!', 'normal', 1, 1, 1, NOW(), NOW()),
(4, '노이즈 캔슬링은 정말 신세계네요. 귀가 살짝 더운 것 빼곤 만족합니다.', 'normal', 1, 6, 6, NOW(), NOW()),
(5, '자취생 필수템이죠. 저렴하게 잘 샀습니다.', 'normal', 2, 8, 7, NOW(), NOW()),
(3, '가성비는 좋은데 퍽퍽함이 다른 제품보다 좀 더 느껴져서 아쉬워요.', 'normal', 8, 10, 8, NOW(), NOW()),
(1, '사이즈가 잘못 와서 반품신청 하려다 귀찮아서 그냥 둡니다. 평점 깎아요.', 'deleted', 2, 2, 2, NOW(), NOW()),
(1, '이어폰 노이즈캔슬링 켜면 웅웅거리는 소리가 계속 나요. 불량인가 싶습니다.', 'normal', 3, 11, 9, NOW(), NOW()),
(2, '워치 배터리가 광고만큼 안 가요. 하루도 못 버티는 날이 많네요.', 'normal', 11, 16, 12, NOW(), NOW()),
(3, '고기 상태는 나쁘지 않은데 지방이 좀 많은 편이었어요.', 'normal', 14, 18, 14, NOW(), NOW()),
(4, '패드 화면 정말 예쁘고 그림 그리기 좋아요. 무게가 살짝 아쉬울 뿐.', 'normal', 1, 9, 16, NOW(), NOW()),
(5, '4개 사서 쟁여놨는데 역시 신라면이 진리네요. 재구매 의사 100%입니다.', 'normal', 10, 3, 18, NOW(), NOW()),
(1, '기모가 다 뜯겨서 왔어요. 세탁도 안 했는데 이 상태면 좀...', 'deleted', 15, 15, 20, NOW(), NOW()),
(2, '재구매인데 이번 제품은 착용감이 전보다 별로였습니다.', 'normal', 2, 11, 21, NOW(), NOW()),
(3, '맛은 무난한데 스프가 좀 짠 편이에요. 물 조절 필요합니다.', 'normal', 8, 13, 23, NOW(), NOW()),
(4, '가볍고 통기성 좋아서 여름 운동화로 딱입니다. 사이즈는 정사이즈 추천.', 'normal', 3, 20, 24, NOW(), NOW()),
(5, '겨울 내내 이거 하나로 버텼어요. 보온성 최고, 디자인도 무난해서 만족합니다.', 'normal', 5, 17, 26, NOW(), NOW()),
(1, '색상이 사진이랑 완전 달라요. 실물이 훨씬 칙칙합니다.', 'normal', 11, 12, 28, NOW(), NOW()),
(2, '스트랩이 자꾸 헐거워져서 불편해요. 다른 스트랩 사야 할 듯.', 'normal', 14, 16, 30, NOW(), NOW()),
(3, '평범한 라면 맛이에요. 특별히 나쁘진 않지만 감동도 없네요.', 'normal', 7, 3, 31, NOW(), NOW()),
(4, '얇은데도 따뜻해서 데일리로 입기 좋아요. 목 늘어남도 없고 만족합니다.', 'normal', 10, 15, 32, NOW(), NOW()),
(5, '전자레인지 3분이면 끝이라 자취생한테 이만한 게 없어요. 항상 쟁여둡니다.', 'normal', 4, 8, 33, NOW(), NOW()),
(1, '배송은 빨랐는데 제품 박스가 심하게 찌그러져서 왔어요. 포장 신경 좀 써주세요.', 'normal', 1, 1, 1, NOW(), NOW()),
(1, '신어보니 발볼이 너무 좁아서 아파요. 사이즈 문의했는데 답도 늦고요.', 'normal', 4, 2, 17, NOW(), NOW()),
(1, '유통기한이 얼마 안 남은 걸 보내주셨어요. 확인 좀 하고 발송해주시길.', 'normal', 3, 3, 3, NOW(), NOW()),
(1, '헤드폰 한쪽에서 소리가 안 나요. 새 제품 맞나 의심스럽습니다.', 'deleted', 1, 6, 6, NOW(), NOW()),
(1, '패딩 지퍼가 처음부터 뻑뻑하더니 며칠 만에 완전히 고장났어요.', 'normal', 6, 17, 13, NOW(), NOW()),
(1, '노트북 전원이 안 켜져서 반품했습니다. 검수를 제대로 하시는 건지.', 'normal', 12, 1, 22, NOW(), NOW()),
(1, '삼겹살 색이 좀 이상해서 못 먹고 버렸어요. 신선도 문제 있는 듯합니다.', 'normal', 8, 13, 8, NOW(), NOW()),
(1, '워치 화면에 스크래치가 나 있는 채로 도착했어요. 중고 보내신 거 아니죠?', 'normal', 11, 16, 12, NOW(), NOW()),
(1, '스니커즈 접착 부분이 뜯어져서 며칠 만에 밑창이 분리됐어요.', 'normal', 3, 20, 24, NOW(), NOW()),
(1, '이 가격에 이 퀄리티는 아니라고 봅니다. 마감이 너무 허술해요.', 'normal', 9, 6, 25, NOW(), NOW()),
(2, '기대했던 것보다 화질이 별로예요. 가격 대비 아쉬운 느낌입니다.', 'normal', 2, 2, 2, NOW(), NOW()),
(1, '면 재질이 얇아서 비쳐요. 두꺼운 걸 원하셨다면 비추천입니다.', 'normal', 4, 5, 5, NOW(), NOW()),
(2, '배송 포장은 괜찮았는데 제품 자체 마감이 조잡한 편이에요.', 'normal', 5, 5, 5, NOW(), NOW()),
(2, '충전 케이블이 너무 짧아서 콘센트 옆에서만 써야 해요. 불편합니다.', 'normal', 9, 13, 11, NOW(), NOW()),
(2, '아이패드 액정에 미세한 얼룩이 있어요. 반품하기도 귀찮아서 그냥 씁니다.', 'normal', 6, 9, 29, NOW(), NOW()),
(2, '옥스포드 셔츠 원단이 생각보다 뻣뻣하고 핏도 애매해요.', 'normal', 13, 7, 19, NOW(), NOW()),
(2, '샴푸 냄새처럼 화학약품 냄새가 강하게 나서 거슬려요.', 'normal', 15, 15, 20, NOW(), NOW()),
(2, '재구매했는데 예전 품질이 아니에요. 실망스럽습니다.', 'normal', 2, 11, 21, NOW(), NOW()),
(2, '히트텍 목 부분이 하루 만에 늘어났어요. 세탁 후엔 더 심해질 듯.', 'normal', 10, 15, 32, NOW(), NOW()),
(2, '음질은 무난한데 착용감이 오래 쓰면 귀가 아파요.', 'normal', 9, 6, 25, NOW(), NOW()),
(2, '가격 대비 무난한 성능이에요. 특별히 좋지도 나쁘지도 않습니다.', 'normal', 5, 5, 5, NOW(), NOW()),
(2, '무게감이 있어서 휴대성은 별로지만 성능은 준수합니다.', 'normal', 12, 1, 22, NOW(), NOW()),
(2, '고기 양은 넉넉한데 손질 상태가 좀 아쉬웠어요.', 'normal', 8, 13, 23, NOW(), NOW()),
(2, '핏은 괜찮은데 색상이 화면과 살짝 달라서 별 세 개 드려요.', 'normal', 7, 20, 15, NOW(), NOW()),
(3, '보급형치고는 쓸만한데 딱히 감동은 없는 무난한 제품입니다.', 'normal', 10, 3, 18, NOW(), NOW()),
(3, '배송은 정시에 왔고 제품도 설명대로예요. 딱 기대한 만큼입니다.', 'normal', 13, 7, 19, NOW(), NOW()),
(3, '패딩 볼륨감은 좋은데 안감 마감이 좀 거칠어요.', 'normal', 6, 17, 26, NOW(), NOW()),
(3, '가성비로 사기엔 나쁘지 않은데 재구매 의사는 반반입니다.', 'normal', 14, 16, 30, NOW(), NOW()),
(3, '무난하게 신고 다니기 좋아요. 다만 쿠션감은 기대 이하였습니다.', 'normal', 7, 3, 31, NOW(), NOW()),
(3, '스프 양이 조금 적은 느낌이지만 면발은 쫄깃해서 만족합니다.', 'normal', 4, 8, 33, NOW(), NOW()),
(4, '디자인도 예쁘고 배송도 빨라서 만족스러운 구매였습니다.', 'normal', 1, 9, 16, NOW(), NOW()),
(4, '무선 이어폰치고 음질 훌륭하고 연결도 끊김 없어요. 추천합니다.', 'normal', 3, 11, 9, NOW(), NOW()),
(4, '고급스러운 색감에 착화감도 편해서 자주 신게 되네요.', 'normal', 5, 12, 10, NOW(), NOW()),
(4, '아이 반찬용으로 사봤는데 간이 세지 않아서 좋았어요.', 'normal', 8, 10, 8, NOW(), NOW()),
(5, '노이즈캔슬링 성능이 확실히 다르네요. 비행기에서 유용했습니다.', 'normal', 9, 6, 25, NOW(), NOW()),
(5, '기모 안감이 부드럽고 따뜻해서 겨울 내내 잘 입을 것 같아요.', 'normal', 10, 15, 32, NOW(), NOW()),
(5, '디스플레이 화질이 선명하고 필기감도 좋아서 만족합니다.', 'normal', 6, 9, 29, NOW(), NOW()),
(4, '패션 아이템으로 코디하기 좋고 재질도 부드러워요.', 'normal', 13, 7, 19, NOW(), NOW()),
(4, '헤어 스타일링 결과물이 확실히 좋아서 값어치 하는 듯합니다.', 'normal', 6, 14, 13, NOW(), NOW()),
(4, '휴대성 좋고 배터리도 하루 종일 잘 버텨서 만족스러워요.', 'normal', 11, 16, 30, NOW(), NOW()),
(5, '완전 만족스러운 구매예요! 다음에도 여기서 또 살 것 같습니다.', 'normal', 4, 1, 4, NOW(), NOW()),
(5, '이 가격에 이 성능이면 최고죠. 주변에도 추천했어요.', 'normal', 12, 1, 22, NOW(), NOW()),
(5, '포장부터 배송까지 완벽했고 제품 퀄리티도 기대 이상입니다.', 'normal', 14, 18, 14, NOW(), NOW()),
(5, '색상 예쁘고 착화감 최고예요. 인생 신발 만난 느낌입니다.', 'normal', 3, 20, 24, NOW(), NOW()),
(5, '가족 모두 만족한 상품이에요. 재구매 의사 백프로입니다.', 'normal', 4, 8, 33, NOW(), NOW()),
(5, '화면 색감이 정말 예쁘고 배터리도 오래가서 매우 만족합니다.', 'normal', 1, 9, 16, NOW(), NOW()),
(5, '두께감도 적당하고 보온성도 훌륭해서 겨울 필수템이 됐어요.', 'normal', 5, 17, 26, NOW(), NOW()),
(5, '디자인, 성능, 가격 삼박자 다 갖춘 제품이네요. 강력 추천합니다.', 'normal', 11, 16, 30, NOW(), NOW()),
(5, '역대급 만족도예요. 이 브랜드 제품은 믿고 삽니다.', 'normal', 9, 13, 11, NOW(), NOW()),
(5, '선물용으로 샀는데 받는 사람도 정말 좋아했어요. 최고입니다.', 'normal', 15, 15, 20, NOW(), NOW());




COMMIT;