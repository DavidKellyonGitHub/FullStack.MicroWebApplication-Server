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
    @Column(name = "TEXT")
    private String text;
    @Column(name = "LIKES")
    private Integer likes;
    @Column(name = "BLOG_ID")
    private Long blogId;
    @Column(name = "USER_ID")
    private Long userId;

    public Comment() {
    }

    public Comment(LocalDate dateCreated, String username, String text, Integer likes, Long blogId, Long userId) {
        this.dateCreated = dateCreated;
        this.username = username;
        this.text = text;
        this.likes = likes;
        this.blogId = blogId;
        this.userId = userId;
    }

    public Comment(Long commentId, LocalDate dateCreated, String username, String text, Integer likes, Long blogId, Long userId) {
        this(dateCreated, username, text, likes, blogId, userId);
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

    public void setUsername(String username) {
        this.username = username;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(commentId, comment.commentId) &&
                Objects.equals(dateCreated, comment.dateCreated) &&
                Objects.equals(username, comment.username) &&
                Objects.equals(text, comment.text) &&
                Objects.equals(likes, comment.likes) &&
                Objects.equals(blogId, comment.blogId) &&
                Objects.equals(userId, comment.userId);
    }
}