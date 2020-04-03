package Controllers;

import Models.Comment;
import Services.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class CommentControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CommentService service;

    @Test
    public void findByIdTest() throws Exception {
        Long givenId = 1L;
        String testComment = "I like this post!";
        BDDMockito
                .given(service.findById(givenId))
                .willReturn(new Comment(givenId, testComment, LocalDate.now(), 0, 0, ));

        String expectedContent = "{\"id\":null,\"firstName\":\"Leon\",\"lastName\":\"Hunter\"}";
        this.mvc.perform(MockMvcRequestBuilders
                .get("/people/" + givenId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }
}