package com.dmvs.blog.projBlog.Services;

import com.dmvs.blog.projBlog.Models.Comment;
import com.dmvs.blog.projBlog.Repositories.CommentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CommentServiceTest {

    @ MockBean
    CommentRepository commentRepository;

    @Autowired
    CommentService commentService;

    @Test
    @DisplayName("Test findById - Found")
    public void testFindByIdFound() {
        Long givenCommentId = 1L;
        Comment mockComment = new Comment(givenCommentId, LocalDate.of(2015, 4, 9),
                "Rosalind", "rosalid@gmail.com",
                "I admire your writing sir.", 1, 1L);
        given(commentRepository.findById(givenCommentId)).willReturn(Optional.of(mockComment));

        Optional<Comment> returnComment = commentService.findById(givenCommentId);

        assertTrue(returnComment.isPresent());
        assertSame(returnComment.get(), mockComment);
    }

    @Test
    @DisplayName("Test findById - Not Found")
    public void testFindByIdNotFound(){
        Long givenCommentId = 1L;
        given(commentRepository.findById(givenCommentId)).willReturn(Optional.empty());

        Optional<Comment> returnComment = commentService.findById(givenCommentId);

        assertFalse(returnComment.isPresent());
    }

    @Test
    @DisplayName("Test findAllByBlogId - Found")
    public void testFindAllByBlogIdFound() {
        Long givenBlogId = 1L;
        Comment comment1 = new Comment(1L, LocalDate.of(2015, 4, 9),
                "Rosalind", "rosalid@gmail.com",
                "I admire your writing sir.", 1, givenBlogId);
        Comment comment2 = new Comment(2L, LocalDate.of(2020, 5, 10),
                "changed", "changed@gmail.com",
                "I admire your change sir.", 10, givenBlogId);
        List<Comment> mockCommentList = new ArrayList<>(Arrays.asList(comment1, comment2));
        given(commentRepository.findAllByBlogId(givenBlogId)).willReturn(mockCommentList);

        List<Comment> returnCommentList = commentService.findAllByBlogId(givenBlogId);

        assertEquals(2, returnCommentList.size());
        assertTrue(returnCommentList.contains(comment1));
        assertTrue(returnCommentList.contains(comment2));
    }

    @Test
    @DisplayName("Test save - Success")
    public void testSave() {
        Comment mockComment = new Comment(1L, LocalDate.of(2015, 4, 9),
                "Rosalind", "rosalid@gmail.com",
                "I admire your writing sir.", 1, 1L);
        given(commentRepository.save(mockComment)).willReturn(mockComment);

        Comment returnComment = commentService.saveComment(mockComment);

        assertEquals(mockComment, returnComment);
    }

    @Test
    @DisplayName("Test update - Success")
    public void testUpdateSuccess(){
        Long givenCommentId = 1L;
        String expectedText = "I admire your change sir.";
        Comment beforeComment = new Comment(givenCommentId, LocalDate.of(2015, 4, 9),
                "Rosalind", "rosalid@gmail.com",
                "I admire your writing sir.", 1, 1L);
        given(commentRepository.findById(givenCommentId)).willReturn(Optional.of(beforeComment));
        given(commentRepository.save(beforeComment)).willReturn(beforeComment);

        Optional<Comment> returnComment = commentService.updateComment(givenCommentId, expectedText);
        String actualText = returnComment.get().getText();

        assertEquals(expectedText, actualText);
    }

    @Test
    @DisplayName("Test update - Not Found")
    public void testUpdateNotFound(){
        Long givenCommentId = 1L;
        String expectedText = "I admire your change sir.";
        given(commentRepository.findById(givenCommentId)).willReturn(Optional.empty());

        Optional<Comment> returnComment = commentService.updateComment(givenCommentId, expectedText);

        assertFalse(returnComment.isPresent());
    }

    @Test
    @DisplayName("Test update - Found and deleted")
    public void testDeleteCommentFound() {
        Long givenCommentId = 1L;
        Comment beforeComment = new Comment(givenCommentId, LocalDate.of(2015, 4, 9),
                "Rosalind", "rosalid@gmail.com",
                "I admire your writing sir.", 1, 1L);
        given(commentRepository.findById(givenCommentId)).willReturn(Optional.of(beforeComment));

        Boolean returnBoolean = commentService.deleteCommentById(givenCommentId);

        verify(commentRepository, times(1)).deleteById(givenCommentId);
        assertTrue(returnBoolean);
    }

    @Test
    @DisplayName("Test update - Not Found")
    public void testDeleteCommentNotFound(){
        Long givenCommentId = 1L;
        given(commentRepository.findById(givenCommentId)).willReturn(Optional.empty());

        Boolean returnBoolean = commentService.deleteCommentById(givenCommentId);

        verify(commentRepository, times(0)).deleteById(givenCommentId);
        assertFalse(returnBoolean);
    }
}