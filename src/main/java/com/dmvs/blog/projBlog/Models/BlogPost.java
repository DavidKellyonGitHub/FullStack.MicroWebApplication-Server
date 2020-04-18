package com.dmvs.blog.projBlog.Models;

import  javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BLOG_ID")
    private Long blogId;
    @Column(name = "DATE_CREATED")
    private LocalDate dateCreated;
    @Column(name = "USERNAME")
    private String username;
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
    private List<Comment> commentList;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserAccount userAccount;

    public BlogPost() {
    }

    public BlogPost(LocalDate dateCreated, String username, String title, String body, String tag, String status) {
        this.dateCreated = dateCreated;
        this.username = username;
        this.title = title;
        this.body = body;
        this.tag = tag;
        this.status = status;
    }

    public BlogPost(Long blogId, LocalDate dateCreated, String username, String title, String body, String tag, String status) {
        this(dateCreated, username, title, body, tag, status);
        this.blogId = blogId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogPost blogPost = (BlogPost) o;
        return Objects.equals(blogId, blogPost.blogId) &&
                Objects.equals(dateCreated, blogPost.dateCreated) &&
                Objects.equals(username, blogPost.username) &&
                Objects.equals(title, blogPost.title) &&
                Objects.equals(body, blogPost.body) &&
                Objects.equals(tag, blogPost.tag) &&
                Objects.equals(status, blogPost.status) &&
                Objects.equals(userAccount, blogPost.userAccount);
    }
}
