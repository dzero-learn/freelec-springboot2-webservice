package com.jojoldu.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// 스프링부트의 자동설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정 (뭔솔)
// 이 위치부터 설정을 읽어감 -> 항상 프로젝트의 최상담에 위치해야함
@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // run() -> 내장 was 실행 : 톰캣 설치 필요x, 스프링부트로 만들어진 jar파일 실행
        SpringApplication.run(Application.class, args);
    }
}
