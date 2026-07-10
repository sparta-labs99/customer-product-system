package com.example.customerproductsystem;

import lombok.extern.slf4j.Slf4j; // 롬복 로깅 어노테이션 추가
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.sql.DataSource;
import java.sql.Connection;

@Slf4j
@SpringBootApplication
@EnableJpaAuditing
public class CustomerProductSystemApplication {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    public static void main(String[] args) {
        SpringApplication.run(CustomerProductSystemApplication.class, args);

        log.info("========================================");
        log.info("CustomerProductSystem 서버가 성공적으로 떴습니다!");
        log.info("========================================");
    }

    @Bean
    public CommandLineRunner testDatabaseConnection(DataSource dataSource) {
        return args -> {
            log.info("▶ 설정 파일 읽기 테스트");
            log.info("DB URL = {}", url);
            log.info("DB USER = {}", username);
            log.info("DB PASSWORD = {}", password);

            log.info("▶ 실제 DB 커넥션 테스트");
            try (Connection connection = dataSource.getConnection()) {
                log.info("🎉🎉 물리적 DB 연결 성공! 🎉🎉");
                log.info("연결된 데이터베이스 카탈로그: {}", connection.getCatalog());

            } catch (Exception e) {
                log.error("😭😭 DB 연결 실패... 에러를 확인해주세요.");
                log.error("에러 원인: ", e);
            }
        };
    }
}