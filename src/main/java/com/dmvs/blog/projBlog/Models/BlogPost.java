package com.dmvs.blog.projBlog.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BLOG_ID")
    private Long blogId;
    @Column(name = "DATE_CREATED")
    private LocalDate dateCreated;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "BODY", length = 6500)
    private String body;
    @Column(name = "TAG")
    private String tag;
    @Column(name = "STATUS")
    private String status;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "BLOG_ID")
    @JsonIgnore
    private List<Comment> commentList;

    public BlogPost() {
    }

    public BlogPost(Long blogId, LocalDate dateCreated, String title, String body, String tag, String status) {
        this.blogId = blogId;
        this.dateCreated = dateCreated;
        this.title = title;
        this.body = body;
        this.tag = tag;
        this.status = status;
    }

    public BlogPost(LocalDate dateCreated, String title, String body, String tag, String status) {
        this.dateCreated = dateCreated;
        this.title = title;
        this.body = body;
        this.tag = tag;
        this.status = status;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
