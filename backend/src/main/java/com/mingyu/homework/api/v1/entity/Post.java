package com.mingyu.homework.api.v1.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Table(name = "posts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
