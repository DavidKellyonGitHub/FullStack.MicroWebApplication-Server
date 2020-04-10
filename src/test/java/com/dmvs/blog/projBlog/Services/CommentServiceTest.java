package com.dmvs.blog.projBlog.Services;

import com.dmvs.blog.projBlog.Models.Comment;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    private Comment testComment;

    @Before
    public void init(){
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