package com.dmvs.blog.projBlog.Services;

import com.dmvs.blog.projBlog.Models.Comment;
import com.dmvs.blog.projBlog.Repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private CommentRepository commentRepo;

    @Autowired
    public CommentService(CommentRepository commentRepo) {
        this.commentRepo = commentRepo;
    }

    public Optional<Comment> findById(Long commentId) {
        return commentRepo.findById(commentId);
    }

    public List<Comment> findAllByUserId(Long userId){
        return commentRepo.findAllByUserId(userId);
    }

    public List<Comment> findAllByBlogId(Long blogId) {
        return commentRepo.findAllByBlogId(blogId);
    }

    public List<Comment> findAllComments(){
        return commentRepo.findAll();
    }

    public Comment saveComment(Comment comment) {
        return commentRepo.save(comment);
    }

    public Optional<Comment> updateComment(Long commentId, String newText){
        Optional<Comment> currentComment = findById(commentId);
        if(currentComment.isPresent()){
            currentComment.get().setText(newText);
            commentRepo.save(currentComment.get());
        }
        return currentComment;
    }

    public Boolean deleteCommentById(Long commentId) {
        Optional<Comment> currentComment = findById(commentId);
        if(currentComment.isPresent()) {
            commentRepo.deleteById(commentId);
            return true;
        }
        return false;
    }
}
