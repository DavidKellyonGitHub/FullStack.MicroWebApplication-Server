package com.dmvs.blog.projBlog.Controllers;

import com.dmvs.blog.projBlog.Models.Comment;
import com.dmvs.blog.projBlog.Services.CommentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @Test
    @DisplayName("GET /comment/1 - Found")
    void testFindByIdFound() throws Exception {
        Long givenId = 1L;
        Comment mockComment = new Comment(1L, LocalDate.of(2015, 4, 9),
                "Rosalind", "rosalid@gmail.com",
                "I admire your writing sir.", 1, 1L);
        given(commentService.findById(givenId)).willReturn(java.util.Optional.of(mockComment));

        mockMvc.perform(get("/comment/{commentId}", givenId))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.commentId", is(1)))
                .andExpect(jsonPath("$.dateCreated", is("2015-04-09")))
                .andExpect(jsonPath("$.user", is("Rosalind")))
                .andExpect(jsonPath("$.userEmail", is("rosalid@gmail.com")))
                .andExpect(jsonPath("$.text", is("I admire your writing sir.")))
                .andExpect(jsonPath("$.likes", is(1)))
                .andExpect(jsonPath("$.blogId", is(1)));
    }

    @Test
    @DisplayName("GET /comment/1 - Not Found")
    void testFindByIdNotFound() throws Exception {
        given(commentService.findById(1L)).willReturn(Optional.empty());

        mockMvc.perform(get("/comment/{commentId}", 1L))

                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /comment - Sucess")
    void testSaveComment() throws Exception {
        Comment postComment = new Comment(LocalDate.of(2015, 4, 9),
                "Rosalind", "rosalid@gmail.com",
                "I admire your writing sir.", 1, 1L);
        Comment mockComment = new Comment(1L, LocalDate.of(2015, 4, 9),
                "Rosalind", "rosalid@gmail.com",
                "I admire your writing sir.", 1, 1L);
        given(commentService.saveComment(postComment)).willReturn(mockComment);
        mockMvc.perform(post("/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(postComment)))

                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                .andExpect(header().string(HttpHeaders.LOCATION, "/comment/1"))

                .andExpect(jsonPath("$.commentId", is(1)))
                .andExpect(jsonPath("$.dateCreated", is("2015-04-09")))
                .andExpect(jsonPath("$.user", is("Rosalind")))
                .andExpect(jsonPath("$.userEmail", is("rosalid@gmail.com")))
                .andExpect(jsonPath("$.text", is("I admire your writing sir.")))
                .andExpect(jsonPath("$.likes", is(1)))
                .andExpect(jsonPath("$.blogId", is(1)));

    }

    @Test
    @DisplayName("PUT /comment/1 - Success")
    void testUpdateComment() throws Exception {
        Comment putComment = new Comment(1L, LocalDate.of(2015, 4, 9),
                "changed", "change@gmail.com",
                "I admire your change sir.", 10, 1L);
        Comment mockComment = new Comment(1L, LocalDate.of(2015, 4, 9),
                "Rosalind", "rosalid@gmail.com",
                "I admire your writing sir.", 1, 1L);
        given(commentService.findById(1L)).willReturn(Optional.of(mockComment));
        given(commentService.saveComment(any())).willReturn(putComment);

        mockMvc.perform(put("/comment/{commentId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.IF_MATCH, 1)
                .content(asJsonString(putComment)))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                .andExpect(header().string(HttpHeaders.LOCATION, "/comment/1"))

                .andExpect(jsonPath("$.commentId", is(1)))
                .andExpect(jsonPath("$.dateCreated", is("2015-04-09")))
                .andExpect(jsonPath("$.user", is("changed")))
                .andExpect(jsonPath("$.userEmail", is("change@gmail.com")))
                .andExpect(jsonPath("$.text", is("I admire your change sir.")))
                .andExpect(jsonPath("$.likes", is(10)))
                .andExpect(jsonPath("$.blogId", is(1)));
    }

    static String asJsonString(final Comment obj) {
        try {
            StringBuilder jsonString = new StringBuilder("{");
            jsonString.append("\"commentId\":"+obj.getCommentId()+",")
                    .append("\"dateCreated\":\""+obj.getDateCreated()+"\",")
                    .append("\"user\":\""+obj.getUser()+"\",")
                    .append("\"userEmail\":\""+obj.getUserEmail()+"\",")
                    .append("\"text\":\""+obj.getText()+"\",")
                    .append("\"likes\":"+obj.getLikes()+",")
                    .append("\"blogId\":"+obj.getBlogId()+"}");
            return jsonString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}