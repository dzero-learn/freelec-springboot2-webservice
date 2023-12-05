package com.jojoldu.book.springboot.service.posts;

import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import com.jojoldu.book.springboot.web.dto.PostsListResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.springboot.web.dto.PostsUpdateRequestDto;
import javafx.geometry.Pos;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.TransactionScoped;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();

    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream() // 이해가 안가는게,, repository에서 List<Posts>를 반환하는데 왜 때문에 Posts로 반환되는겨,,? stream에서 뭐가 처리가 되는건가,,?
                .map(PostsListResponseDto::new) //posts -> new PostsListResponseDto(posts)
                .collect(Collectors.toList());
    }

    @Transactional // 트랜잭션 붙이는 기준이 뭐예요,, 도대체,,
    public void delete (Long id) {
        // postsRepository.deleteById(id); 라고 썼는데,
        // 해당 id의 포스트가 존재하는지 먼저 확인하는 것이 안전하므로 엔티티 조회 후 delete()를 쓰자!
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당하는 게시글이 없습니다. id="+id));

        postsRepository.delete(posts);

    }
}
