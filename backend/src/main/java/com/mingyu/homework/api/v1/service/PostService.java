package com.mingyu.homework.api.v1.service;

import com.mingyu.homework.api.v1.dto.response.PostDetailResponseDto;
import com.mingyu.homework.api.v1.dto.response.PostListResponseDto;
import com.mingyu.homework.api.v1.entity.Post;
import com.mingyu.homework.api.v1.repository.PostRepository;
import com.mingyu.homework.api.v1.service.strategy.LoadStrategy;
import com.mingyu.homework.error.exception.BadRequestException;
import com.mingyu.homework.error.exception.ResourceNotFoundException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

        if (!(strategyType.equals("paging") || strategyType.equals("infinite"))) {
            throw BadRequestException.invalidStrategy(strategyType);
        }

        LoadStrategy strategy = strategyMap.get(strategyType);

        if (strategy == null) {
            if (strategyMap.containsKey("paging")) {
                strategy = strategyMap.get("paging");
            } else {
                throw BadRequestException.invalidStrategy(strategyType);
            }
        }

        return strategy.loadPosts(cursor, pageable);
    }

    @Cacheable(value = "postDetailCache", key = "#postId")
    public PostDetailResponseDto getPostDetail(UUID postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> ResourceNotFoundException.postNotFound(postId.toString()));

        return PostDetailResponseDto.fromEntity(post);
    }
}
