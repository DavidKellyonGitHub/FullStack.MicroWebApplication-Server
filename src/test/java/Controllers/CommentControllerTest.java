package Controllers;

import Models.Comment;
import Services.CommentService;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CommentControllerTest {

    @MockBean
    private CommentService service;

    @Autowired
    private MockMvc mockMvc;

//    @Test
//    @DisplayName("GET /commentId/1 - Found")
//    void testFindById() throws Exception{
//        // Setup our mocked service
//        Comment mockComment = new Comment("Sample text", 1L, LocalDate.now(), 2, 3, 4L);
//        doReturn(Optional.of(mockComment)).when(service).findById(1L);
//
//        // Execute the GET request
//        mockMvc.perform((get("/commentId/{commentId}", 1L)))
//
//                //Validate the response code and content type
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//
//                // Validate the returned fields
//                .andExpect(jsonPath("$.text", is("Sample text")))
//                .andExpect(jsonPath("$.commentId", is(1L)))
//                .andExpect(jsonPath("$.dateCreated", is(LocalDate.now())))
//                .andExpect(jsonPath("$.thumbsUp", is(2)))
//                .andExpect(jsonPath("$.thumbsDown", is(3)))
//                .andExpect(jsonPath("$.blogId", is(4L)));
//
//    }

    @Test
    void findById() {
    }

    @Test
    void findAllByBlogId() {
    }

    @Test
    void saveComment() {
    }

    @Test
    void updateComment() {
    }

    @Test
    void deleteComment() {
    }


}