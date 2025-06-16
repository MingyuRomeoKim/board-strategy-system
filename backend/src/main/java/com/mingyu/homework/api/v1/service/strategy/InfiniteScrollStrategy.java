package com.mingyu.homework.api.v1.service.strategy;

import com.mingyu.homework.api.v1.dto.response.PostListResponseDto;
import com.mingyu.homework.api.v1.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;


@Component
@RequiredArgsConstructor
@Qualifier("infinite")
public class InfiniteScrollStrategy implements LoadStrategy {

    private final PostRepository postRepository;

    @Override
    public List<PostListResponseDto> loadPosts(UUID cursor, Pageable pageable) {
        return postRepository.findPostsWithCursorPagination(cursor, pageable)
                .stream()
                .map(PostListResponseDto::fromProjection)
                .toList();
    }

    @Override
    public String getType() {
        return "infinite";
    }
}
