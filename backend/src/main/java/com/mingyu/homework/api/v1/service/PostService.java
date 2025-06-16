package com.mingyu.homework.api.v1.service;

import com.mingyu.homework.api.v1.dto.response.PostDetailResponseDto;
import com.mingyu.homework.api.v1.dto.response.PostListResponseDto;
import com.mingyu.homework.api.v1.entity.Post;
import com.mingyu.homework.api.v1.repository.PostRepository;
import com.mingyu.homework.api.v1.service.strategy.LoadStrategy;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final Map<String, LoadStrategy> strategyMap;
    private final PostRepository postRepository;

    public PostService(List<LoadStrategy> strategies, PostRepository postRepository) {
        this.strategyMap = strategies.stream()
                .collect(Collectors.toMap(LoadStrategy::getType, Function.identity()));
        this.postRepository = postRepository;
    }

    @Cacheable(value = "postListCache", key = "T(java.lang.String).format('%s:%s:%d:%d', #strategyType, #cursor, #pageable.pageNumber, #pageable.pageSize)")
    public List<PostListResponseDto> getPosts(String strategyType, UUID cursor, Pageable pageable) {
        LoadStrategy strategy = strategyMap.getOrDefault(strategyType, strategyMap.get("paging"));
        return strategy.loadPosts(cursor, pageable);
    }

    @Cacheable(value = "postDetailCache", key = "#postId")
    public PostDetailResponseDto getPostDetail(UUID postId) {
        Post post =  postRepository.findById(postId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글 존재하지 않음."));

        return PostDetailResponseDto.fromEntity(post);
    }
}
