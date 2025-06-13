package com.mingyu.homework.integration;

import com.mingyu.homework.api.v1.repository.PostRepository;
import com.mingyu.homework.support.PostDummyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PostControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PostRepository postRepository;

    @BeforeEach
    void init() {
        postRepository.saveAll(PostDummyFactory.createBulk(1000));
    }

    @Test
    void 전체_페이징_전략_API_호출_성공() throws Exception {
        mockMvc.perform(get("/api/v1/posts")
                        .param("strategy", "paging")
                        .param("size", "15"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(15));
    }
}
