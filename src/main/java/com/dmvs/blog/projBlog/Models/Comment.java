package com.dmvs.blog.projBlog.Models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Comment {

    @Id
    @GeneratedValue
    @Column(name="COMMENT_ID")
    private Long commentId;
    @Column(name="TEXT")
    private String text;
    @Column(name="DATE_CREATED")
    private LocalDate dateCreated;
    @Column(name="THUMBS_UP")
    private Integer thumbsUp;
    @Column(name="THUMBS_DOWN")
    private Integer thumbsDown;
    @ManyToOne
    @JoinColumn(name="BLOG_ID")
    private BlogPost blogPost;

    public Comment(String text, Long commentId, LocalDate dateCreated,
                   Integer thumbsUp, Integer thumbsDown, BlogPost blogPost){
        this.text = text;
        this.commentId = commentId;
        this.dateCreated = dateCreated;
        this.thumbsUp = thumbsUp;
        this.thumbsDown = thumbsDown;
        this.blogPost = blogPost;
    }

    public Comment(){}

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
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

    public BlogPost getBlogPost() {
        return blogPost;
    }

    public void setBlogId(BlogPost blogPost) {
        this.blogPost = blogPost;
    }
}
