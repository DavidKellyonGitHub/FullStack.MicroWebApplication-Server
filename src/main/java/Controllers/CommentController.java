package Controllers;

import Models.Comment;
import Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/comment")
public class CommentController {

    CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return this.commentService.findById(id)
                .map(comment -> ResponseEntity.ok().body(comment))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all/{blogId}")
    public ResponseEntity<Iterable<Comment>> findAllByBlogId(@PathVariable Long blogId) {
        return new ResponseEntity<>(commentService.findAllByBlogId(blogId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment) {
        Comment newComment = commentService.saveComment(comment);
        try {
            return ResponseEntity
                    .created(new URI("/comment/" + newComment.getCommentId()))
                    .body(newComment);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.MULTI_STATUS.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Long commentId, @RequestBody Comment newComment) {
        Optional<Comment> existingComment = commentService.findById(commentId);

        return existingComment
                .map(c -> {
                    c.setText(newComment.getText());
                    try{
                        return ResponseEntity
                                .ok()
                                .location(new URI("/comment/" + c.getCommentId()))
                                .body(c);
                    }catch(URISyntaxException e){
                        return ResponseEntity.status(HttpStatus.MULTI_STATUS.INTERNAL_SERVER_ERROR).build();
                    }
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId) {
        Optional<Comment> existingCar = commentService.findById(commentId);

        return existingCar
                .map(c -> {
                    commentService.deleteComment(c.getCommentId());
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
