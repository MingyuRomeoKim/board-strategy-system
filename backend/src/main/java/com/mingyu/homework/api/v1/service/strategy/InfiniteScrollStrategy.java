package com.mingyu.homework.api.v1.service.strategy;

import com.mingyu.homework.api.v1.dto.response.PostResponseDto;
import com.mingyu.homework.api.v1.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
@Qualifier("infinite")
public class InfiniteScrollStrategy implements LoadStrategy {

    private final PostRepository postRepository;

    @Override
    public List<PostResponseDto> loadPosts(Pageable pageable) {
        return postRepository.findAll(pageable)
                .stream()
                .map(PostResponseDto::fromEntity)
                .toList();
    }

    @Override
    public String getType() {
        return "infinite";
    }
}
