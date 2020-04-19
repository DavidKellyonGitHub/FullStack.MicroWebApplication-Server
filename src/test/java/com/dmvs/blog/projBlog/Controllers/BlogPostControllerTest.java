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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
                "Matt","This is my first post", "Read all about it", "coding", "active");
        given(blogPostService.findById(givenId)).willReturn(Optional.of(getBlogPost));

        mockMvc.perform(get("/zcwApp/blogPost/{blogId}", givenId))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.blogId", is(1)))
                .andExpect(jsonPath("$.dateCreated", is("2015-04-17")))
                .andExpect(jsonPath("$.username", is("Matt")))
                .andExpect(jsonPath("$.title", is("This is my first post")))
                .andExpect(jsonPath("$.body", is("Read all about it")))
                .andExpect(jsonPath("$.tag", is("coding")))
                .andExpect(jsonPath("$.status", is("active")));
    }

    @Test
    @DisplayName("GET /blogPost/1 - Not Found")
    public void testFindByIdNotFound() throws Exception {
        given(blogPostService.findById(1L)).willReturn(Optional.empty());

        mockMvc.perform(get("/zcwApp/blogPost/{blogId}", 1L))

                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /blogPost/allByTag/coding - Found")
    public void testFindAllByTag() throws Exception {
        Long givenBlogId = 1L;
        BlogPost blogPost1 = new BlogPost(1L, LocalDate.of(2015, 4, 17),
                "Matt","This is my first post", "Read all about it", "coding", "active");
        BlogPost blogPost2 = new BlogPost(2L, LocalDate.of(2015, 5, 11),
                "Matt","This is my second post", "Maybe read some of it?", "coding", "active");
        List<BlogPost> blogPostList = new ArrayList<>(Arrays.asList(blogPost1, blogPost2));
        given(blogPostService.findByTag("coding")).willReturn(blogPostList);

        mockMvc.perform(get("/zcwApp/blogPost/allByTag/", givenBlogId)
                .param("tag", "coding"))


                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                .andExpect(jsonPath("$.*").isArray())
                .andExpect(jsonPath("$[0].blogId", is(1)))
                .andExpect(jsonPath("$[0].dateCreated", is("2015-04-17")))
                .andExpect(jsonPath("$[0].username", is("Matt")))
                .andExpect(jsonPath("$[0].title", is("This is my first post")))
                .andExpect(jsonPath("$[0].body", is("Read all about it")))
                .andExpect(jsonPath("$[0].tag", is("coding")))
                .andExpect(jsonPath("$[0].status", is("active")))
                .andExpect(jsonPath("$[1].blogId", is(2)))
                .andExpect(jsonPath("$[1].dateCreated", is("2015-05-11")))
                .andExpect(jsonPath("$[1].username", is("Matt")))
                .andExpect(jsonPath("$[1].title", is("This is my second post")))
                .andExpect(jsonPath("$[1].body", is("Maybe read some of it?")))
                .andExpect(jsonPath("$[1].tag", is("coding")))
                .andExpect(jsonPath("$[1].status", is("active")));
    }

    @Test
    @DisplayName("POST /blogPost - Success")
    public void testSaveBlogPostSuccess() throws Exception {
        BlogPost postBlogPost = new BlogPost(1L, LocalDate.of(2015, 4, 17),
                "Matt","This is my first post", "Read all about it", "coding", "active");
        BlogPost mockBlogPost = new BlogPost(1L, LocalDate.of(2015, 4, 17),
                "Matt","This is my first post", "Read all about it", "coding", "active");
        given(blogPostService.savePost(postBlogPost)).willReturn(mockBlogPost);

        //String test = asJsonString(postBlogPost);
        mockMvc.perform(post("/zcwApp/blogPost/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postBlogPost)))

                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                .andExpect(header().string(HttpHeaders.LOCATION, "/zcwApp/blogPost/1"))

                .andExpect(jsonPath("$.blogId", is(1)))
                .andExpect(jsonPath("$.dateCreated", is("2015-04-17")))
                .andExpect(jsonPath("$.username", is("Matt")))
                .andExpect(jsonPath("$.title", is("This is my first post")))
                .andExpect(jsonPath("$.body", is("Read all about it")))
                .andExpect(jsonPath("$.tag", is("coding")))
                .andExpect(jsonPath("$.status", is("active")));
    }

    @Test
    @DisplayName("GET /blogPost/allByDate/ - Found")
    public void testFindAllByDate() throws Exception {
        Long givenBlogId = 1L;
        BlogPost blogPost1 = new BlogPost(1L, LocalDate.of(2015, 4, 17),
                "Matt","This is my first post", "Read all about it", "coding", "active");
        BlogPost blogPost2 = new BlogPost(2L, LocalDate.of(2015, 5, 11),
                "Matt","This is my second post", "Maybe read some of it?", "coding", "active");
        List<BlogPost> blogPostList = new ArrayList<>(Arrays.asList(blogPost1));
        given(blogPostService.findAllByDateCreateAfter(LocalDate.of(2015, 4, 17))).willReturn(blogPostList);

        mockMvc.perform(get("/zcwApp/blogPost/allByDate/", givenBlogId)
                .param("year", "2015")
                .param("month", "4")
                .param("day", "17"))


                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                .andExpect(jsonPath("$.*").isArray())
                .andExpect(jsonPath("$[0].blogId", is(1)))
                .andExpect(jsonPath("$[0].dateCreated", is("2015-04-17")))
                .andExpect(jsonPath("$[0].username", is("Matt")))
                .andExpect(jsonPath("$[0].title", is("This is my first post")))
                .andExpect(jsonPath("$[0].body", is("Read all about it")))
                .andExpect(jsonPath("$[0].tag", is("coding")))
                .andExpect(jsonPath("$[0].status", is("active")));
    }

    @Test
    @DisplayName("GET /blogPost/all - Found")
    public void testFindAll() throws Exception {
        Long givenBlogId = 1L;
        BlogPost blogPost1 = new BlogPost(1L, LocalDate.of(2015, 4, 17),
                "Matt","This is my first post", "Read all about it", "coding", "active");
        BlogPost blogPost2 = new BlogPost(2L, LocalDate.of(2015, 5, 11),
                "Matt","This is my second post", "Maybe read some of it?", "coding", "active");
        List<BlogPost> blogPostList = new ArrayList<>(Arrays.asList(blogPost1, blogPost2));
        given(blogPostService.findAll()).willReturn(blogPostList);

        mockMvc.perform(get("/zcwApp/blogPost/all/", givenBlogId))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                .andExpect(jsonPath("$.*").isArray())
                .andExpect(jsonPath("$[0].blogId", is(1)))
                .andExpect(jsonPath("$[0].dateCreated", is("2015-04-17")))
                .andExpect(jsonPath("$[0].username", is("Matt")))
                .andExpect(jsonPath("$[0].title", is("This is my first post")))
                .andExpect(jsonPath("$[0].body", is("Read all about it")))
                .andExpect(jsonPath("$[0].tag", is("coding")))
                .andExpect(jsonPath("$[0].status", is("active")))
                .andExpect(jsonPath("$[1].blogId", is(2)))
                .andExpect(jsonPath("$[1].dateCreated", is("2015-05-11")))
                .andExpect(jsonPath("$[1].username", is("Matt")))
                .andExpect(jsonPath("$[1].title", is("This is my second post")))
                .andExpect(jsonPath("$[1].body", is("Maybe read some of it?")))
                .andExpect(jsonPath("$[1].tag", is("coding")))
                .andExpect(jsonPath("$[1].status", is("active")));
    }

    @Test
    @DisplayName("PUT /blogPost/1 - Success")
    public void testUpdateBlogPostSuccess() throws Exception {
        Long givenId = 1L;
        BlogPost mockBlogPost1 = new BlogPost(LocalDate.of(2015, 4, 17),
                "Matt", "This is my first post", "Read all about it", "coding", "active");
        BlogPost mockBlogPost2 = new BlogPost(1L, LocalDate.of(2015, 4, 17),
                "Matt", "This is my post", "Check it out", "coding", "active");
        given(blogPostService.updatePost(givenId, mockBlogPost1)).willReturn(Optional.of(mockBlogPost2));

        mockMvc.perform(put("/zcwApp/blogPost/update/{blogId}", givenId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(mockBlogPost1)))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                .andExpect(jsonPath("$.blogId", is(1)))
                .andExpect(jsonPath("$.dateCreated", is("2015-04-17")))
                .andExpect(jsonPath("$.username", is("Matt")))
                .andExpect(jsonPath("$.title", is("This is my post")))
                .andExpect(jsonPath("$.body", is("Check it out")))
                .andExpect(jsonPath("$.tag", is("coding")))
                .andExpect(jsonPath("$.status", is("active")));
    }

    @Test
    @DisplayName("PUT /blogPost/1 - Not Found")
    public void testUpdateBlogPostNotFound() throws Exception {
        Long givenId = 1L;
        BlogPost mockBlogPost1 = new BlogPost(LocalDate.of(2015, 4, 17),
                "Matt", "This is my first post", "Read all about it", "coding", "active");
        given(blogPostService.updatePost(givenId, mockBlogPost1)).willReturn(Optional.empty());

        mockMvc.perform(put("/zcwApp/blogPost/update/{blogId}", givenId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(mockBlogPost1)))

                .andExpect(status().isNotFound());

    }

    @Test
    @DisplayName("DELETE /blogPost/delete/1 - Success")
    public void testDeleteBlogPostSuccess() throws Exception {
        Long givenId = 1L;
        given(blogPostService.deletePost(givenId)).willReturn(true);

        mockMvc.perform(delete("/zcwApp/blogPost/delete/{blogId}", givenId))

                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /comment/delete/1 - Not Found")
    public void testDeleteBlogPostNotFound() throws Exception {
        Long givenId = 1L;
        given(blogPostService.deletePost(givenId)).willReturn(false);

        mockMvc.perform(delete("/zcwApp/blogPost/delete/{blogId}", givenId))

                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /comment/deleteAll/ - Success")
    public void testDeleteAllBlogPost() throws Exception {
        given(blogPostService.deleteAll()).willReturn(true);

        mockMvc.perform(delete("/zcwApp/blogPost/deleteAll/"))

                .andExpect(status().isOk());
    }


    public static String asJsonString(final BlogPost obj) {
        try {
            StringBuilder jsonString = new StringBuilder("{");
            jsonString.append("\"blogId\":"+obj.getBlogId()+",")
                    .append("\"dateCreated\":\""+obj.getDateCreated()+"\",")
                    .append("\"username\":\""+obj.getUsername()+"\",")
                    .append("\"title\":\""+obj.getTitle()+"\",")
                    .append("\"body\":\""+obj.getBody()+"\",")
                    .append("\"tag\":\""+obj.getTag()+"\",")
                    .append("\"status\":\""+obj.getStatus()+"\"}");
            return jsonString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}