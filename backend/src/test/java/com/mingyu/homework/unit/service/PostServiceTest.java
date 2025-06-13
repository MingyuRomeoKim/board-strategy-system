package com.mingyu.homework.unit.service;

import com.mingyu.homework.api.v1.dto.response.PostResponseDto;
import com.mingyu.homework.api.v1.repository.PostRepository;
import com.mingyu.homework.api.v1.service.PostService;
import com.mingyu.homework.support.PostDummyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class PostServiceTest {

    @Autowired PostRepository postRepository;
    @Autowired PostService postService;

    @BeforeEach
    void setup() {
        postRepository.saveAll(PostDummyFactory.createBulk(1000));
    }

    @Test
    void 전략_기반_페이징_조회_성공() {
        Pageable pageable = PageRequest.of(0, 10);
        List<PostResponseDto> result = postService.getPosts("paging", pageable);
        assertEquals(10, result.size());
    }

    @Test
    void 전략_기반_무한스크롤_조회_성공() {
        Pageable pageable = PageRequest.of(1, 20);
        List<PostResponseDto> result = postService.getPosts("infinite", pageable);
        assertEquals(20, result.size());
    }
}