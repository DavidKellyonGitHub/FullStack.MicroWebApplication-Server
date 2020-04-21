package com.dmvs.blog.projBlog.Controllers;

import com.dmvs.blog.projBlog.Models.Comment;
import com.dmvs.blog.projBlog.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/zcwApp/comment")
@CrossOrigin
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<?> findById(@PathVariable Long commentId) {
        return commentService.findById(commentId)
                .map(comment -> ResponseEntity
                        .ok()
                        .body(comment))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/allByUserId/{userId}")
    public ResponseEntity<Iterable<Comment>> findAllByUserId(@PathVariable Long userId){
        return new ResponseEntity<>(commentService.findAllByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/allByBlogId/{blogId}")
    public ResponseEntity<Iterable<Comment>> findAllByBlogId(@PathVariable Long blogId) {
        return new ResponseEntity<>(commentService.findAllByBlogId(blogId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Comment>> findAllComments(){
        return new ResponseEntity<>(commentService.findAllComments(), HttpStatus.OK);
    }

    @GetMapping("/+likes/{commentId}")
    public ResponseEntity<?> findAndIncreaseLike(@PathVariable Long commentId){
        return commentService.findAndIncreaseLike(commentId)
                .map(comment -> ResponseEntity
                        .ok()
                        .body(comment))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment) {
        Comment newComment = commentService.saveComment(comment);

        try {
            return ResponseEntity
                    .created(new URI("/zcwApp/comment/" + newComment.getCommentId()))
                    .body(newComment);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Long commentId, @RequestParam String newText) {
        Optional<Comment> updatedComment = commentService.updateComment(commentId, newText);

        return updatedComment
                .map(comment -> {
                    try{
                        return ResponseEntity
                                .ok()
                                .location(new URI("/zcwApp/comment/" + comment.getCommentId()))
                                .body(comment);
                    }catch(URISyntaxException e){
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<Boolean> deleteComment(@PathVariable Long commentId) {
        if(commentService.deleteCommentById(commentId))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }
}
