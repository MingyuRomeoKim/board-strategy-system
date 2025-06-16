package com.mingyu.homework.api.v1.repository;


import com.mingyu.homework.api.v1.entity.Post;
import com.mingyu.homework.api.v1.repository.mapper.post.PostListMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

    @Query("""
        SELECT p.postId AS postId, p.title AS title, p.author AS author, p.createdAt AS createdAt
        FROM Post p
        ORDER BY p.postId DESC 
    """)
    Page<PostListMapping> findAllList(Pageable pageable);

    @Query("""
        SELECT p.postId AS postId, p.title AS title, p.author AS author, p.createdAt AS createdAt
        FROM Post p
        WHERE (:cursor IS NULL OR p.postId < :cursor)
        ORDER BY p.postId DESC
    """)
    List<PostListMapping> findPostsWithCursorPagination(@Param("cursor") UUID cursor, Pageable pageable);
}
