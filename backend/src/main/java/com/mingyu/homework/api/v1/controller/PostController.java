package com.mingyu.homework.api.v1.controller;

import com.mingyu.homework.api.v1.dto.response.PostDetailResponseDto;
import com.mingyu.homework.api.v1.dto.response.PostListResponseDto;
import com.mingyu.homework.api.v1.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "01. 게시글", description = "게시글 관련 API")
@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    public final PostService postService;

    @GetMapping
    @Operation(summary = "게시글 리스트 조회", description = "게시글의 목록을 조회합니다. strategy가 'infinite'인 경우 cursor 파라미터를 사용하여 cursor-based pagination을 지원합니다.")
    public ResponseEntity<List<PostListResponseDto>> getPosts(
            @RequestParam(defaultValue = "paging") String strategy,
            @RequestParam(required = false) UUID cursor,
            Pageable pageable
    ) {
        List<PostListResponseDto> posts = postService.getPosts(strategy, cursor, pageable);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{postId}")
    @Operation(summary = "게시글 상세 조회", description = "ID에 맞는 게시글을 상세조회 합니다.")
    public ResponseEntity<PostDetailResponseDto> getPostDetail(@PathVariable @NotNull(message = "게시글 조회시 아이디는 필수입니다.") UUID postId) {
        return ResponseEntity.ok(postService.getPostDetail(postId));
    }

}
