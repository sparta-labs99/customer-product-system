## 🛒 고객-상품 주문 시스템 (Customer-Product System)

Spring Boot와 MySQL, Spring Data JPA를 기반으로 구축된 관리자용 이커머스 및 주문 관리 백엔드 시스템입니다.

### 📁 디렉토리 구조
```text
src
└── main
    └── java
        └── com.example.customerproductsystem
            ├── admin           # 관리자 도메인 (회원/상품/주문 통합 제어)
            ├── common          # 공통 기능 (설정, BaseEntity, 글로벌 예외 처리 등)
            ├── customer        # 고객 도메인 (인증, 프로필 관리 등)
            │   ├── controller  # API 요청 처리 및 응답 반환
            │   ├── dto         # 계층 간 데이터 교환 객체 (Request/Response 분리)
            │   ├── entity      # DB 테이블 매핑 도메인 객체
            │   ├── repository  # Spring Data JPA 기반 DB 접근 계층
            │   └── service     # 핵심 비즈니스 로직 및 트랜잭션 처리
            ├── order           # 주문 도메인 (주문 생성, 취소, 재고 차감/복구)
            └── product         # 상품 도메인 (조회, 정렬, 검색)
```
---

### ⚙️ 비즈니스 로직 및 핵심 기능 설명

#### 👤 1. 고객 (Customer)

#### 📦 2. 상품 (Product)

#### 🧾 3. 주문 (Order) 관리

#### 🛡️ 4. 관리자 (Admin) 제어


---
### 🛠 주요 기술 스택

| 구분 | 기술                 |
| :--- |:-------------------|
| **Language** | Java 17            |
| **Framework** | Spring Boot 4.1.0  |
| **ORM** | Spring Data JPA    |
| **Database** | MySQL              |
| **Frontend** | Thymeleaf, Bootstrap |
| **Library** | Lombok, Spring Validation, BCrypt |

---

### 📝API 명세서

🔗 [API 명세서 (Postman) 바로가기](https://www.notion.so/teamsparta/9752dc3ef5148341872201ccbfe97bb1?v=c5c2dc3ef51483b4a3a4885c8e9c5d41)

---

### 📊 ERD (Entity Relationship Diagram)
<img src="./docs/images/ERD.png" width="400" alt="ERD Diagram"/>

---
### 개발 규칙
📝 [깃 허브](https://www.notion.so/teamsparta/Github-Rules-b292dc3ef51483b791b1016f91311061)