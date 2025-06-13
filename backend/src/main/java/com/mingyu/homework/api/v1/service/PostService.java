package com.mingyu.homework.api.v1.service;

import com.mingyu.homework.api.v1.dto.response.PostResponseDto;
import com.mingyu.homework.api.v1.service.strategy.LoadStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostService {

    private final Map<String, LoadStrategy> strategyMap;

    public List<PostResponseDto> getPosts(String strategyType, Pageable pageable) {
        LoadStrategy strategy = strategyMap.getOrDefault(strategyType, strategyMap.get("paging"));
        return strategy.loadPosts(pageable);
    }
}
