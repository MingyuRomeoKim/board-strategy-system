package com.mingyu.homework.integration;

import com.mingyu.homework.api.v1.entity.Post;
import com.mingyu.homework.api.v1.repository.PostRepository;
import com.mingyu.homework.config.TestCacheConfig;
import com.mingyu.homework.support.PostDummyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Import(TestCacheConfig.class)
public class PostControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PostRepository postRepository;

    private Post savedPost;

    @BeforeEach
    void init() {
        List<Post> posts = PostDummyFactory.createBulk(1000);
        postRepository.deleteAll();
        savedPost = postRepository.saveAll(posts).get(0);
    }

    @Test
    void 전체_페이징_전략_API_호출_성공() throws Exception {
        mockMvc.perform(get("/api/v1/posts")
                        .param("strategy", "paging")
                        .param("size", "15"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(15));
    }

    @Test
    void 존재하는_게시글_상세보기_호출_성공() throws Exception {
        mockMvc.perform(get("/api/v1/posts/" + savedPost.getPostId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.postId").value(savedPost.getPostId().toString()))
                .andExpect(jsonPath("$.title").value(savedPost.getTitle()));
    }

    @Test
    void 존재하지않는_게시글_상세보기_404_반환() throws Exception {
        mockMvc.perform(get("/api/v1/posts/" + java.util.UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
