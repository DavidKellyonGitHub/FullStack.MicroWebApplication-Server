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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        BlogPost mockBlogPost = new BlogPost(givenBlogPostId, LocalDate.of(2015, 4, 9),
                "Rosalind", "blog title", "blog body", "blog tag", "posted");
        given(blogPostRepository.findById(givenBlogPostId)).willReturn(Optional.of(mockBlogPost));

        Optional<BlogPost> returnBlogPost = blogPostService.findById(givenBlogPostId);

        assertTrue(returnBlogPost.isPresent());
        assertSame(returnBlogPost.get(), mockBlogPost);
   }
//
//    @Test
//    @DisplayName("Test findById - Not Found")
//    public void testFindByIdNotFound(){
//        Long givenCommentId = 1L;
//        given(commentRepository.findById(givenCommentId)).willReturn(Optional.empty());
//
//        Optional<Comment> returnComment = commentService.findById(givenCommentId);
//
//        assertFalse(returnComment.isPresent());
//    }
//
//    @Test
//    @DisplayName("Test findAllByUserId - Found")
//    public void testFindAllByUserIdFound() {
//        Long givenUserId = 2L;
//        Comment comment1 = new Comment(1L, LocalDate.of(2015, 4, 9),
//                "Rosalind", "I admire your writing sir.", 1, 2L, givenUserId);
//        Comment comment2 = new Comment(2L, LocalDate.of(2020, 5, 10),
//                "changed", "I admire your change sir.", 10, 1L, givenUserId);
//        List<Comment> mockCommentList = new ArrayList<>(Arrays.asList(comment1, comment2));
//        given(commentRepository.findAllByUserId(givenUserId)).willReturn(mockCommentList);
//
//        List<Comment> returnCommentList = commentService.findAllByUserId(givenUserId);
//
//        assertEquals(2, returnCommentList.size());
//        assertTrue(returnCommentList.contains(comment1));
//        assertTrue(returnCommentList.contains(comment2));
//    }
//
//    @Test
//    @DisplayName("Test findAllByBlogId - Found")
//    public void testFindAllByBlogIdFound() {
//        Long givenBlogId = 1L;
//        Comment comment1 = new Comment(1L, LocalDate.of(2015, 4, 9),
//                "Rosalind", "I admire your writing sir.", 1, givenBlogId, 1L);
//        Comment comment2 = new Comment(2L, LocalDate.of(2020, 5, 10),
//                "changed", "I admire your change sir.", 10, givenBlogId, 4L);
//        List<Comment> mockCommentList = new ArrayList<>(Arrays.asList(comment1, comment2));
//        given(commentRepository.findAllByBlogId(givenBlogId)).willReturn(mockCommentList);
//
//        List<Comment> returnCommentList = commentService.findAllByBlogId(givenBlogId);
//
//        assertEquals(2, returnCommentList.size());
//        assertTrue(returnCommentList.contains(comment1));
//        assertTrue(returnCommentList.contains(comment2));
//    }
//
//    @Test
//    @DisplayName("Test findAll - Found")
//    public void testFindAll() {
//        Comment comment1 = new Comment(1L, LocalDate.of(2015, 4, 9),
//                "Rosalind", "I admire your writing sir.", 1, 1L, 2L);
//        Comment comment2 = new Comment(2L, LocalDate.of(2020, 5, 10),
//                "changed", "I admire your change sir.", 10, 3L, 4L);
//        List<Comment> mockCommentList = new ArrayList<>(Arrays.asList(comment1, comment2));
//        given(commentRepository.findAll()).willReturn(mockCommentList);
//
//        List<Comment> returnCommentList = commentService.findAllComments();
//
//        assertEquals(2, returnCommentList.size());
//        assertTrue(returnCommentList.contains(comment1));
//        assertTrue(returnCommentList.contains(comment2));
//    }
//
//    @Test
//    @DisplayName("Test save - Success")
//    public void testSave() {
//        Comment mockComment = new Comment(1L, LocalDate.of(2015, 4, 9),
//                "Rosalind", "I admire your writing sir.", 1, 1L, 1L);
//        given(commentRepository.save(mockComment)).willReturn(mockComment);
//
//        Comment returnComment = commentService.saveComment(mockComment);
//
//        assertEquals(mockComment, returnComment);
//    }
//
//    @Test
//    @DisplayName("Test update - Success")
//    public void testUpdateSuccess(){
//        Long givenCommentId = 1L;
//        String expectedText = "I admire your change sir.";
//        Comment beforeComment = new Comment(givenCommentId, LocalDate.of(2015, 4, 9),
//                "Rosalind", "I admire your writing sir.", 1, 1L, 1L);
//        given(commentRepository.findById(givenCommentId)).willReturn(Optional.of(beforeComment));
//        given(commentRepository.save(beforeComment)).willReturn(beforeComment);
//
//        Optional<Comment> returnComment = commentService.updateComment(givenCommentId, expectedText);
//        String actualText = returnComment.get().getText();
//
//        assertEquals(expectedText, actualText);
//    }
//
//    @Test
//    @DisplayName("Test update - Not Found")
//    public void testUpdateNotFound(){
//        Long givenCommentId = 1L;
//        String expectedText = "I admire your change sir.";
//        given(commentRepository.findById(givenCommentId)).willReturn(Optional.empty());
//
//        Optional<Comment> returnComment = commentService.updateComment(givenCommentId, expectedText);
//
//        assertFalse(returnComment.isPresent());
//    }
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