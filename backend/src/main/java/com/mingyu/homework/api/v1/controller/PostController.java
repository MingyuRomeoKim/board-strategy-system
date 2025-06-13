package com.mingyu.homework.api.v1.controller;

import com.mingyu.homework.api.v1.dto.response.PostResponseDto;
import com.mingyu.homework.api.v1.service.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "01. 게시글", description = "게시글 관련 API")
@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    public final PostService postService;

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getPosts(
            @RequestParam(defaultValue = "paging") String strategy,
            Pageable pageable
    ) {
        List<PostResponseDto> posts = postService.getPosts(strategy, pageable);
        return ResponseEntity.ok(posts);
    }

}
