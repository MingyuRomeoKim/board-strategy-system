package com.mingyu.homework.unit.service;

import com.mingyu.homework.api.v1.dto.response.PostDetailResponseDto;
import com.mingyu.homework.api.v1.dto.response.PostListResponseDto;
import com.mingyu.homework.api.v1.entity.Post;
import com.mingyu.homework.api.v1.repository.PostRepository;
import com.mingyu.homework.api.v1.service.PostService;
import com.mingyu.homework.config.TestCacheConfig;
import com.mingyu.homework.support.PostDummyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Import(TestCacheConfig.class)
public class PostServiceTest {

    @Autowired
    PostRepository postRepository;
    @Autowired
    PostService postService;

    private Post savedPost;

    @BeforeEach
    void setup() {
        List<Post> posts = PostDummyFactory.createBulk(1000);
        postRepository.deleteAll();
        savedPost = postRepository.saveAll(posts).get(0);
    }

    @Test
    void 전략_기반_페이징_조회_성공() {
        Pageable pageable = PageRequest.of(0, 10);
        List<PostListResponseDto> result = postService.getPosts("paging", null, pageable);
        assertEquals(10, result.size());
    }

    @Test
    void 전략_기반_무한스크롤_조회_성공() {
        Pageable pageable = PageRequest.of(0, 20);
        List<PostListResponseDto> result = postService.getPosts("infinite", null, pageable);
        assertEquals(20, result.size());
    }

    @Test
    void 게시글_상세보기_조회_성공() {
        PostDetailResponseDto result = postService.getPostDetail(savedPost.getPostId());
        assertNotNull(result);
        assertEquals(savedPost.getPostId(), result.getPostId());
    }

    @Test
    void 게시글_상세보기_조회_실패() {
        UUID invalidPostId = UUID.randomUUID();
        assertThrows(ResponseStatusException.class, () -> postService.getPostDetail(invalidPostId));
    }
}
