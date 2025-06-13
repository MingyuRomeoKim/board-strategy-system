package com.mingyu.homework.api.v1.service.strategy;

import com.mingyu.homework.api.v1.dto.response.PostResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LoadStrategy {
    List<PostResponseDto> loadPosts(Pageable pageable);
    String getType(); // "infinite", "paging" 등 식별용
}
