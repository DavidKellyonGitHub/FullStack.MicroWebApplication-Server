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
        return commentRepo.getOne(commentId);
    }

    public List<Comment> findAllByBlogId(Long blogId) {
        return commentRepo.findAllByBlogId(blogId);
    }

    public Boolean saveComment(Comment comment) {
        if(!commentRepo.existsById(comment.getCommentId())){
            commentRepo.save(comment);
            return true;
        }
        return false;
    }

    public Boolean updateComment(Comment comment) {
        if(commentRepo.existsById(comment.getCommentId())){
            commentRepo.save(comment);
            return true;
        }
        return false;
    }

    public Boolean deleteComment(Comment comment) {
        if(commentRepo.existsById(comment.getCommentId())){
            commentRepo.delete(comment);
            return true;
        }
        return false;
    }
}
