package com.dmvs.blog.projBlog.Services;

import com.dmvs.blog.projBlog.Models.Comment;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@SpringBootTest
@RunWith(SpringRunner.class)
class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    private Comment testComment;

    @Before
    public void init(){
        testComment = new Comment();
        testComment.setText("test");
        testComment.setDateCreated(LocalDate.now());
        testComment.setLikes(10);
        testComment.setUser("von");
        testComment.setUserEmail("von@gmail.com");
        testComment.setBlogId(1L);
    }
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
    void deleteComment() {
    }
}