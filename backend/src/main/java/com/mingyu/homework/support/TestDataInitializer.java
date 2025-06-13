package com.mingyu.homework.support;

import com.mingyu.homework.api.v1.repository.PostRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
@RequiredArgsConstructor
public class TestDataInitializer {

    private final PostRepository postRepository;

    @PostConstruct
    public void init() {
        if (postRepository.count() == 0) {
            postRepository.saveAll(PostDummyFactory.createBulk(1000));
        }
    }
}
