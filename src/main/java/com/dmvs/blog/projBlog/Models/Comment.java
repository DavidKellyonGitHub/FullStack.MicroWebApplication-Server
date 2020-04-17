package com.dmvs.blog.projBlog.Models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private Long commentId;
    @Column(name = "DATE_CREATED")
    private LocalDate dateCreated;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "USER_EMAIL")
    private String userEmail;
    @Column(name = "TEXT")
    private String text;
    @Column(name = "LIKES")
    private Integer likes;
    @Column(name = "BLOG_ID")
    private Long blogId;

    public Comment() {
    }

    public Comment(LocalDate dateCreated, String username, String userEmail, String text, Integer likes, Long blogId) {
        this.dateCreated = dateCreated;
        this.username = username;
        this.userEmail = userEmail;
        this.text = text;
        this.likes = likes;
        this.blogId = blogId;
    }

    public Comment(Long commentId, LocalDate dateCreated, String username, String userEmail, String text, Integer likes, Long blogId) {
        this(dateCreated, username, userEmail, text, likes, blogId);
        this.commentId = commentId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String user) {
        this.username = username;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(commentId, comment.commentId) &&
                Objects.equals(dateCreated, comment.dateCreated) &&
                Objects.equals(username, comment.username) &&
                Objects.equals(userEmail, comment.userEmail) &&
                Objects.equals(text, comment.text) &&
                Objects.equals(likes, comment.likes) &&
                Objects.equals(blogId, comment.blogId);
    }
}
