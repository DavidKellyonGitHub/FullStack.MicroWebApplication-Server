package Controllers;

import Models.Comment;
import Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

//    @GetMapping("/Comment/{id}")
//    public ResponseEntity<?> findById(@PathVariable Long id) {
//        return this.commentService.findById(id)
//                .map(comment -> ResponseEntity.ok().body(comment))
//                .orElse(ResponseEntity.notFound().build());
//    }

    @ResponseBody
    public ResponseEntity<Iterable<Comment>> findAll() {
        return null;
    }

    @ResponseBody
    public ResponseEntity<Boolean> saveComment(Comment comment) {
        return null;
    }

    @ResponseBody
    public ResponseEntity<Boolean> updateComment(Comment comment) {
        return null;
    }

    @ResponseBody
    public ResponseEntity<Boolean> deleteComment(Integer commentId) {
        return null;
    }
}
