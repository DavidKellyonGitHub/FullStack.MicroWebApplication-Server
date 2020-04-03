package Services;

import Models.Comment;
import Repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    CommentRepository commentRepo;

    @Autowired
    public CommentService(CommentRepository commentRepo) {
        this.commentRepo = commentRepo;
    }

    public Optional<Comment> findById(Long commentId) {
        return commentRepo.findById(commentId);
    }

    public List<Comment> findAllByBlogId(Long blogId) {
        return commentRepo.findAllByBlogId(blogId);
    }

    public Comment saveComment(Comment comment) {
        return commentRepo.save(comment);
    }

    public Boolean deleteComment(Long commentId) {
        commentRepo.deleteById(commentId);
        return findById(commentId).isPresent();
    }
}
