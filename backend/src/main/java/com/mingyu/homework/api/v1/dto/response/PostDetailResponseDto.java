package com.mingyu.homework.api.v1.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.mingyu.homework.api.v1.entity.Post;
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
@Schema(description = "게시글 조회 상세보기 응답 DTO")
public class PostDetailResponseDto {

    @Schema(description = "게시글 ID")
    private UUID postId;

    @Schema(description = "게시글 제목")
    private String title;

    @Schema(description = "게시글 작성자")
    private String author;

    @Schema(description = "게시글 작성일")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss")
    private LocalDateTime createdAt;

    @Schema(description = "게시글 내용")
    private String content;

    public static PostDetailResponseDto fromEntity(Post entity) {
        return PostDetailResponseDto.builder()
                .postId(entity.getPostId())
                .title(entity.getTitle())
                .author(entity.getAuthor())
                .content(entity.getContent())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}