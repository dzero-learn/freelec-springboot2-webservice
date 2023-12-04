package com.jojoldu.book.springboot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
        // 머스테치 스타터 의존성을 주입하였기 때문에, 앞에 경로와 확장자는 자동으로 들어간다.
        // 앞 경로 : src/main/resources/templates , 확장자: .mustache
        return "index";
        // src/main/resources/templates/index.mustache 로 변환되어
        // view Resolver(=URL요청의 결과를 전달할 타입과 값을 지정하는 관리자)가 처리
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        // src/main/resources/templates/posts-save.mustache 호출
        return "posts-save";
    }
}
