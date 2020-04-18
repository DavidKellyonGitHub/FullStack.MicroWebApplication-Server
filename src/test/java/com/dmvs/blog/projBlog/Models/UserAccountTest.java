package com.dmvs.blog.projBlog.Models;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class UserAccountTest {


    @Test
    public void testGetSetBlogPostList(){
        UserAccount testUser = new UserAccount(1L, LocalDate.of(2015, 4, 9),
                "Rosalind", "rosaPass", "rosa@gmail.com");
        BlogPost post1 = new BlogPost(1L, LocalDate.of(2015, 4, 9),
                "Rosalind", "SCENE III.", "I admire your writing sir.", "hamlet", "pending");
        BlogPost post2 = new BlogPost(2L, LocalDate.of(2020, 5, 10),
                "Von", "changed", "I admire your change sir.", "changed","posted");
        List<BlogPost> blogPostsList = new ArrayList<>(Arrays.asList(post1, post2));
        testUser.setBlogPostList(blogPostsList);

        List<BlogPost> returnBlogPostList = testUser.getBlogPostList();

        for(BlogPost b: returnBlogPostList){
            assertTrue(testUser.getBlogPostList().contains(b));
        }
    }

    @Test
    public void testGetSetCommentList(){
        UserAccount testUser = new UserAccount(1L, LocalDate.of(2015, 4, 9),
                "Rosalind", "rosaPass", "rosa@gmail.com");
        Comment comment1 = new Comment(1L, LocalDate.of(2015, 4, 9),
                "Rosalind", "I admire your writing sir.", 1, 1L, 1L);
        Comment comment2 = new Comment(2L, LocalDate.of(2020, 5, 10),
                "changed", "I admire your change sir.", 10, 2L, 2L);
        List<Comment> commentList = new ArrayList<>(Arrays.asList(comment1, comment2));
        testUser.setCommentList(commentList);

        List<Comment> returnCommentList = testUser.getCommentList();

        for(Comment c: returnCommentList){
            assertTrue(testUser.getCommentList().contains(c));
        }
    }
}