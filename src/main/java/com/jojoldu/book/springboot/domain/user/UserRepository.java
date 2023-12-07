package com.jojoldu.book.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmai(String email); // 소셜 로그인으로 반환되는 값 중 email을 통해 사용자 가입여부 판단
}
