package com.mingyu.homework.api.v1.dto.response;

import com.mingyu.homework.api.v1.entity.Post;
import com.mingyu.homework.api.v1.repository.mapper.post.PostListMapping;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "게시글 조회 리스트 응답 DTO")
public class PostListResponseDto {

    @Schema(description = "게시글 ID")
    private UUID postId;

    @Schema(description = "게시글 제목")
    private String title;

    @Schema(description = "게시글 작성자")
    private String author;

    @Schema(description = "게시글 작성일")
    private LocalDateTime createdAt;

    public static PostListResponseDto fromEntity(Post entity) {
        return PostListResponseDto.builder()
                .postId(entity.getPostId())
                .title(entity.getTitle())
                .author(entity.getAuthor())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public static PostListResponseDto fromProjection(PostListMapping projection) {
        return PostListResponseDto.builder()
                .postId(projection.getPostId())
                .title(projection.getTitle())
                .author(projection.getAuthor())
                .createdAt(projection.getCreatedAt())
                .build();
    }
}
