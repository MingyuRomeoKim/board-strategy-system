package com.mingyu.homework.api.v1.repository;


import com.mingyu.homework.api.v1.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
}
