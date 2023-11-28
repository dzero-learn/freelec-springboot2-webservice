package com.jojoldu.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 주요 어노테이션을 클래스에 가깝게 둔다.
// why? 코틀린 등의 새 언어 전환으로 롬복이 더이상 필요 없을 경우 쉽게 삭제 할 수 있음.
@Getter // 롬복 어노테이션 : Getter 메소드 자동 생성
@NoArgsConstructor // 롬복 어노테이션 : 기본 생성자 자동 추가
@Entity // JPA 어노테이션
public class Posts { // 실제 DB의 테이블과 매칭될 클래스 = Entity 클래스
    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성규칙, GenerationType.IDENTITY=auto_increment
    private Long id;
    @Column(length = 500, nullable = false) // 테이블의 칼럼, 기본값 외에 추가로 변경이 필요한 옵션이 있을 경우 명시
    private String title;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    private String author; // 별도 옵션 없으면 @Column 명시 안 해줘도 됨

    @Builder // 해당 클래스의 빌더 패턴 클래스 생성(? 뭔소리람)
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
