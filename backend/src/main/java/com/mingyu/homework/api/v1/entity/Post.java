package com.mingyu.homework.api.v1.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Builder
@Table(name = "posts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Post {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID postId;

    private String title;

    private String content;

    private String author;

    private LocalDateTime createdAt;
}
