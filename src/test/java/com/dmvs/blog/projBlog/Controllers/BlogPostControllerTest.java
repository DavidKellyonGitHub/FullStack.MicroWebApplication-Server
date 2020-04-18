package com.dmvs.blog.projBlog.Controllers;

import com.dmvs.blog.projBlog.Models.BlogPost;
import com.dmvs.blog.projBlog.Models.Comment;
import com.dmvs.blog.projBlog.Services.BlogPostService;
import org.hibernate.dialect.Database;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("h2")
class BlogPostControllerTest {


    @MockBean
    BlogPostService blogPostService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /blogPost/1 - Found")
    public void testFindByIdFound() throws Exception {
        Long givenId = 1L;
        BlogPost getBlogPost = new BlogPost(1L, LocalDate.of(2015, 4, 17),
                "This is my first post", "Read all about it", "#Coding", "active");
        given(blogPostService.findById(givenId)).willReturn(Optional.of(getBlogPost));

        mockMvc.perform(get("/zcwApp/blogPost/{blogId}", givenId))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.blogId", is(1)))
                .andExpect(jsonPath("$.dateCreated", is("2015-04-17")))
                .andExpect(jsonPath("$.title", is("This is my first post")))
                .andExpect(jsonPath("$.body", is("Read all about it")))
                .andExpect(jsonPath("$.tag", is("#Coding")))
                .andExpect(jsonPath("$.status", is("active")));
    }
}