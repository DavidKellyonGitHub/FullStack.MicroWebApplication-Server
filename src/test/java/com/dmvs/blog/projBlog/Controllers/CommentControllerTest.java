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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

;

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
    public void testFindByIdFound() throws Exception {
        Long givenId = 1L;
        Comment getComment = new Comment(1L, LocalDate.of(2015, 4, 9),
                "Rosalind", "I admire your writing sir.", 1, 1L);
        given(commentService.findById(givenId)).willReturn(Optional.of(getComment));

        mockMvc.perform(get("/zcwApp/comment/{commentId}", givenId))

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
    public void testFindByIdNotFound() throws Exception {
        given(commentService.findById(1L)).willReturn(Optional.empty());

        mockMvc.perform(get("/zcwApp/comment/{commentId}", 1L))

                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /comment/allByBlogId/1 - Found")
    public void testFindAllByBlogId() throws Exception {
        Long givenBlogId = 1L;
        Comment comment1 = new Comment(1L, LocalDate.of(2015, 4, 9),
                "Rosalind", "I admire your writing sir.", 1, givenBlogId);
        Comment comment2 = new Comment(2L, LocalDate.of(2020, 5, 10),
                "changed", "I admire your change sir.", 10, givenBlogId);
        List<Comment> commentList = new ArrayList<>(Arrays.asList(comment1, comment2));
        given(commentService.findAllByBlogId(1L)).willReturn(commentList);

        mockMvc.perform(get("/zcwApp/comment/allByBlogId/{blogId}", givenBlogId))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                .andExpect(jsonPath("$.*").isArray())
                .andExpect(jsonPath("$[0].commentId", is(1)))
                .andExpect(jsonPath("$[0].dateCreated", is("2015-04-09")))
                .andExpect(jsonPath("$[0].user", is("Rosalind")))
                .andExpect(jsonPath("$[0].userEmail", is("rosalid@gmail.com")))
                .andExpect(jsonPath("$[0].text", is("I admire your writing sir.")))
                .andExpect(jsonPath("$[0].likes", is(1)))
                .andExpect(jsonPath("$[0].blogId", is(1)))
                .andExpect(jsonPath("$[1].commentId", is(2)))
                .andExpect(jsonPath("$[1].dateCreated", is("2020-05-10")))
                .andExpect(jsonPath("$[1].user", is("changed")))
                .andExpect(jsonPath("$[1].userEmail", is("changed@gmail.com")))
                .andExpect(jsonPath("$[1].text", is("I admire your change sir.")))
                .andExpect(jsonPath("$[1].likes", is(10)))
                .andExpect(jsonPath("$[1].blogId", is(1)));

    }

    @Test
    @DisplayName("POST /comment - Success")
    public void testSaveCommentSuccess() throws Exception {
        Comment postComment = new Comment(LocalDate.of(2015, 4, 9),
                "Rosalind", "I admire your writing sir.", 1, 1L);
        Comment mockComment = new Comment(1L, LocalDate.of(2015, 4, 9),
                "Rosalind", "I admire your writing sir.", 1, 1L);
        given(commentService.saveComment(postComment)).willReturn(mockComment);

        mockMvc.perform(post("/zcwApp/comment/save")
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

//    @Test
//    @DisplayName("POST /comment - URISyntaxException")
//    public void testSaveCommentError() throws Exception {
//        Comment postComment = new Comment( LocalDate.of(2015, 4, 9),
//                "Rosalind", "rosalid@gmail.com",
//                "I admire your writing sir.", 1, 1L);
//        given(commentService.saveComment(postComment)).willReturn(postComment);
//
//        mockMvc.perform(post("/comment/save")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(postComment)))
//
//                .andExpect(status().isInternalServerError());
//    }

    @Test
    @DisplayName("PUT /comment/1 - Success")
    public void testUpdateCommentSuccess() throws Exception {
        Long givenId = 1L;
        Comment mockComment = new Comment(givenId, LocalDate.of(2015, 4, 9),
                "changed", "I admire your change sir.", 10, 1L);
        String changedText = "I admire your change sir.";
        given(commentService.updateComment(givenId, changedText)).willReturn(Optional.of(mockComment));

        mockMvc.perform(put("/zcwApp/comment/update/{commentId}", givenId)
                .header(HttpHeaders.IF_MATCH, 1)
                .param("newText", changedText))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                .andExpect(jsonPath("$.commentId", is(1)))
                .andExpect(jsonPath("$.dateCreated", is("2015-04-09")))
                .andExpect(jsonPath("$.user", is("changed")))
                .andExpect(jsonPath("$.userEmail", is("change@gmail.com")))
                .andExpect(jsonPath("$.text", is("I admire your change sir.")))
                .andExpect(jsonPath("$.likes", is(10)))
                .andExpect(jsonPath("$.blogId", is(1)));
    }

    @Test
    @DisplayName("PUT /comment/1 - Not Found")
    public void testUpdateCommentNotFound() throws Exception {
        Long givenId = 1L;
        String changedText = "I admire your change sir.";
        given(commentService.updateComment(givenId, changedText)).willReturn(Optional.empty());

        mockMvc.perform(put("/zcwApp/comment/update/{commentId}", givenId)
                .header(HttpHeaders.IF_MATCH, 1)
                .param("newText", changedText))

                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /comment/delete/1 - Success")
    public void testDeleteCommentSuccess() throws Exception {
        Long givenId = 1L;
        given(commentService.deleteCommentById(givenId)).willReturn(true);

        mockMvc.perform(delete("/zcwApp/comment/delete/{commentId}", givenId))

                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /comment/delete/1 - Not Found")
    public void testDeleteCommentNotFound() throws Exception {
        Long givenId = 1L;
        given(commentService.deleteCommentById(givenId)).willReturn(false);

        mockMvc.perform(delete("/zcwApp/comment/delete/{commentId}", givenId))

                .andExpect(status().isNotFound());
    }

    public static String asJsonString(final Comment obj) {
        try {
            StringBuilder jsonString = new StringBuilder("{");
            jsonString.append("\"commentId\":"+obj.getCommentId()+",")
                    .append("\"dateCreated\":\""+obj.getDateCreated()+"\",")
                    .append("\"username\":\""+obj.getUsername()+"\",")
                    .append("\"text\":\""+obj.getText()+"\",")
                    .append("\"likes\":"+obj.getLikes()+",")
                    .append("\"blogId\":"+obj.getBlogId()+"}");
            return jsonString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}