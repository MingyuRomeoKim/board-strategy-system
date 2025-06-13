package com.mingyu.homework.api.v1.dto.response;

import com.mingyu.homework.api.v1.entity.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "게시글 조회시 응답 DTO")
public class PostResponseDto {
    private UUID postId;

    public static PostResponseDto fromEntity(Post entity) {
        return PostResponseDto.builder()
                .postId(entity.getPostId())
                .build();
    }
}
