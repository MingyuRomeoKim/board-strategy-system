package com.mingyu.homework.api.v1.repository;


import com.mingyu.homework.api.v1.entity.Post;
import com.mingyu.homework.api.v1.repository.mapper.post.PostListMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

    @Query("""
        SELECT p.postId AS postId, p.title AS title, p.author AS author, p.createdAt AS createdAt
        FROM Post p
    """)
    Page<PostListMapping> findAllList(Pageable pageable);

}
