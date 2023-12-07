package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.LoginUser;
import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import com.jojoldu.book.springboot.service.posts.PostsService;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService; // @Autowired 붙이는 경우는 언제고 안 붙이는 경우는 언제여,,? Test클래스만 붙이네
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        /** @LoginUser 어노테이션 사용 전
        //OAuth2UserService에서 로그인 성공 시, session에 user정보 저장함
        //index.mustache에서 userName 사용위해 session에 저장된 user 정보 가져옴
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null) {
            model.addAttribute("clientName", user.getName());
        }
         **/
        // 어노테이션 사용으로 세션 정보 가지고 오도록 코드 개선
        if(user != null) {
            model.addAttribute("clientName", user.getName());
        }
        
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

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);

        return "posts-update";
    }
}
