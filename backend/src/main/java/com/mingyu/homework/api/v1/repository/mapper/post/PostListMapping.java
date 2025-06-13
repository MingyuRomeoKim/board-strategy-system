package com.mingyu.homework.api.v1.repository.mapper.post;

import java.time.LocalDateTime;
import java.util.UUID;

public interface PostListMapping {

    UUID getPostId();
    String getTitle();
    String getAuthor();
    LocalDateTime getCreatedAt();
}
