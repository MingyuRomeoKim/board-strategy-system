package com.mingyu.homework.api.v1.controller;

import com.mingyu.homework.api.v1.service.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "01. 게시글", description = "게시글 관련 API")
@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {

    public final PostService postService;


}
