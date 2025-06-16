package com.mingyu.homework.api.v1.service.strategy;

import com.mingyu.homework.api.v1.dto.response.PostListResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface LoadStrategy {
    List<PostListResponseDto> loadPosts(UUID cursor, Pageable pageable);
    String getType(); // "infinite", "paging" 등 식별용
}
