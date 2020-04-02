package Services;

import Models.Comment;
import Repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    CommentRepository commentRepo;

    @Autowired
    public CommentService(CommentRepository commentRepo) {
        this.commentRepo = commentRepo;
    }

    public Comment findById(Long commentId) {
        return null;
    }

    public List<Comment> findAll(Long blogId) {
        return null;
    }

    public Boolean saveComment(Comment comment) {
        return null;
    }

    public Boolean updateComment(Comment comment) {
        return null;
    }

    public Boolean deleteComment(Comment comment) {
        return null;
    }
}
