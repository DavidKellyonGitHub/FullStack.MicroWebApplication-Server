package com.dmvs.blog.projBlog.Models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class BlogPostTest {

    @Test
    public void testGetSetCommentList(){
        BlogPost testPost = new BlogPost(1L, LocalDate.of(2015, 4, 9),
                "Rosalind", "SCENE III.", "I admire your writing sir.", "hamlet", "pending");
        Comment comment1 = new Comment(1L, LocalDate.of(2015, 4, 9),
                "Rosalind", "I admire your writing sir.", 1, 1L, 1L);
        Comment comment2 = new Comment(2L, LocalDate.of(2020, 5, 10),
                "changed", "I admire your change sir.", 10, 2L, 2L);
        List<Comment> commentList = new ArrayList<>(Arrays.asList(comment1, comment2));
        testPost.setCommentList(commentList);

        List<Comment> returnCommentList = testPost.getCommentList();

        for(Comment c: returnCommentList){
            assertTrue(testPost.getCommentList().contains(c));
        }
    }

    @Test
    public void testGetSetUserAccount(){
        BlogPost post1 = new BlogPost(1L, LocalDate.of(2015, 4, 9),
                "Rosalind", "SCENE III.", "I admire your writing sir.", "hamlet", "pending");
        BlogPost post2 = new BlogPost(2L, LocalDate.of(2020, 5, 10),
                "Rosalind", "changed", "I admire your change sir.", "changed","posted");
        UserAccount testUser = new UserAccount(1L, LocalDate.of(2015, 4, 9),
                "Rosalind", "rosaPass", "rosa@gmail.com");
        post1.setUserAccount(testUser);
        post2.setUserAccount(testUser);

        UserAccount retUserAccount = post1.getUserAccount();
        assertEquals(testUser, retUserAccount);
        retUserAccount = post2.getUserAccount();
        assertEquals(testUser, retUserAccount);;
    }
}