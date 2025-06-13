package com.mingyu.homework.api.v1.service.strategy;

import com.mingyu.homework.api.v1.dto.response.PostResponseDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Qualifier("paging")
public class PagingStrategy implements LoadStrategy{
    @Override
    public List<PostResponseDto> loadPosts(Pageable pageable) {
        return List.of();
    }

    @Override
    public String getType() {
        return "";
    }
}
