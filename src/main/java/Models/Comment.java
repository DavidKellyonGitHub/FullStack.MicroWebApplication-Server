package Models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Comment {

    private String comment;
    private Integer commentId;
    private LocalDate dateCreated;
    private Integer thumbsUp;
    private Integer thumbsDown;
    @Id
    private Long blogId;

    public Comment(String comment, Integer commentId, LocalDate dateCreated, Integer thumbsUp, Integer thumbsDown, Long blogId){
        this.comment = comment;
        this.commentId = commentId;
        this.dateCreated = dateCreated;
        this.thumbsUp = thumbsUp;
        this.thumbsDown = thumbsDown;
        this.blogId = blogId;
    }

    public Comment(){}

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getThumbsUp() {
        return thumbsUp;
    }

    public void setThumbsUp(Integer thumbsUp) {
        this.thumbsUp = thumbsUp;
    }

    public Integer getThumbsDown() {
        return thumbsDown;
    }

    public void setThumbsDown(Integer thumbsDown) {
        this.thumbsDown = thumbsDown;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }
}
