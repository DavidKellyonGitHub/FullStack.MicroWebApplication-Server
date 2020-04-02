package Controllers;

import Models.Comment;
import Services.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommentController {
    CommentService commentService;

    @ResponseBody
    public ResponseEntity<Comment> findById(Integer id) {
        return null;
    }

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
