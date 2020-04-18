package com.dmvs.blog.projBlog.Services;

import com.dmvs.blog.projBlog.Models.BlogPost;
import com.dmvs.blog.projBlog.Models.Comment;
import com.dmvs.blog.projBlog.Repositories.BlogPostRepository;
import com.dmvs.blog.projBlog.Repositories.CommentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles()
class BlogPostServiceTest {

    @Autowired
    BlogPostService blogPostService;

    @MockBean
    BlogPostRepository blogPostRepository;

    @Test
    void findById() {
    }

    @Test
    void findByDate() {
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



    @Test
    @DisplayName("Test findById - Found")
    public void testFindByIdFound() {
        Long givenBlogPostId = 1L;
        BlogPost mockBlogPost = new BlogPost(givenBlogPostId, LocalDate.now(), "Liskov", "blog title", "blog body", "blog tag", "posted");
        given(blogPostRepository.findById(givenBlogPostId)).willReturn(Optional.of(mockBlogPost));
        Optional<BlogPost> returnBlogPost = blogPostService.findById(givenBlogPostId);
        assertTrue(returnBlogPost.isPresent());
        assertSame(returnBlogPost.get(), mockBlogPost);
   }

    @Test
    @DisplayName("Test findById - Not Found")
    public void testFindByIdNotFound(){
        Long givenBlogPostId = 1L;
        given(blogPostRepository.findById(givenBlogPostId)).willReturn(Optional.empty());
        Optional<BlogPost> returnComment = blogPostService.findById(givenBlogPostId);
        assertFalse(returnComment.isPresent());
    }

    @Test
    @DisplayName("Test findAll - Found")
    public void testFindAllFound() {
        BlogPost blogPost1 = new BlogPost(LocalDate.now(), "Liskov", "blog title", "blog body", "blog tag", "posted");
        BlogPost blogPost2 = new BlogPost(LocalDate.now(), "Liskov", "blog title 2", "blog body 2", "Blog tag" ,"posted");
        List<BlogPost> mockBlogPostList = new ArrayList<>(Arrays.asList(blogPost1, blogPost2));
        given(blogPostRepository.findAll()).willReturn(mockBlogPostList);
        List<BlogPost> returnBlogPostList = blogPostService.findAll();
        assertEquals(2, returnBlogPostList.size());
        assertTrue(returnBlogPostList.contains(blogPost1));
        assertTrue(returnBlogPostList.contains(blogPost2));
    }

    @Test
    @DisplayName("Test savePost - Success")
    public void testSavePost() {
        BlogPost mockBlogPost = new BlogPost(1L, LocalDate.now(), "Liskov", "blog title", "Blog body", "Blog tag", "posted");
        given(blogPostRepository.save(mockBlogPost)).willReturn(mockBlogPost);
        BlogPost returnBlogPost = blogPostService.savePost(mockBlogPost);
        assertEquals(mockBlogPost, returnBlogPost);
    }

    @Test
    @DisplayName("Test update - Success")
    public void testUpdateSuccess(){
        Long givenBlogPostId = 1L;
        BlogPost expected = new BlogPost(LocalDate.of(2020,04,18), "Liskov", "updated title", "updated body", "updated tag", "posted");
        BlogPost originalPost = new BlogPost(LocalDate.of(2020,04,18), "Liskov", "blog title", "blog body", "blog tag", "posted");
        given(blogPostRepository.findById(givenBlogPostId)).willReturn(Optional.of(originalPost));
        given(blogPostRepository.save(originalPost)).willReturn(originalPost);
        Optional<BlogPost> returnBlogPost = blogPostService.updatePost(givenBlogPostId, expected);
        assertEquals(expected, returnBlogPost.get());
    }

    @Test
    @DisplayName("Test update - Not Found")
    public void testUpdateNotFound(){
        Long givenId = 1L;
        BlogPost expected = new BlogPost(LocalDate.now(),"Liskov", "Blog Title", "blog body", "blog tag", "posted");
        given(blogPostRepository.findById(givenId)).willReturn(Optional.empty());
        Optional<BlogPost> returnBlogPost = blogPostService.updatePost(givenId, expected);
        assertFalse(returnBlogPost.isPresent());
    }

//
//    @Test
//    @DisplayName("Test update - Found and deleted")
//    public void testDeleteCommentFound() {
//        Long givenCommentId = 1L;
//        Comment mockComment = new Comment(givenCommentId, LocalDate.of(2015, 4, 9),
//                "Rosalind", "I admire your writing sir.", 1, 1L, 1L);
//        given(commentRepository.findById(givenCommentId)).willReturn(Optional.of(mockComment));
//
//        Boolean returnBoolean = commentService.deleteCommentById(givenCommentId);
//
//        verify(commentRepository, times(1)).deleteById(givenCommentId);
//        assertTrue(returnBoolean);
//    }
//
//    @Test
//    @DisplayName("Test update - Not Found")
//    public void testDeleteCommentNotFound(){
//        Long givenCommentId = 1L;
//        given(commentRepository.findById(givenCommentId)).willReturn(Optional.empty());
//
//        Boolean returnBoolean = commentService.deleteCommentById(givenCommentId);
//
//        verify(commentRepository, times(0)).deleteById(givenCommentId);
//        assertFalse(returnBoolean);
//    }
}