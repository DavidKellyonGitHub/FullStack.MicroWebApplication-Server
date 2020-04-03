package Controllers;

import Services.BlogPostService;
import org.hibernate.dialect.Database;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class BlogPostControllerTest {


    @MockBean
    BlogPostService blogPostService;

    @Autowired
    private MockMvc mockMvc;


    @Mock
    Database  mockBlogPostDb;

    @Test
    void findById() {
    }

    @Test
    void findbyTag() {
    }

    @Test
    void findAll() {
    }

    @Test
    void savePost() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteAll() {
    }
}