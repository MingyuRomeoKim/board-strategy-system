package com.mingyu.homework.support;

import com.mingyu.homework.api.v1.entity.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

public class PostDummyFactory {
    public static Post create(int index) {
        return Post.builder()
                .title("제목 " + index)
                .content("내용 " + index + "번 게시글입니다.")
                .author("user" + (index % 10))
                .createdAt(LocalDateTime.now().minusDays(index % 30))
                .build();
    }

    public static List<Post> createBulk(int count) {
        return IntStream.range(0, count)
                .mapToObj(PostDummyFactory::create)
                .toList();
    }
}