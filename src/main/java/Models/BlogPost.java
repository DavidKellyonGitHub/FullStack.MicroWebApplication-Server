package Models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.HashSet;

@Entity
public class BlogPost {
    @Id
    private Long blogId;
    private LocalDate dateCreated;
    private String body;
    private HashSet<String> blogTag;

    public BlogPost(Long blogId, LocalDate dateCreated, String body, HashSet<String> blogTag) {
        this.blogId = blogId;
        this.dateCreated = dateCreated;
        this.body = body;
        this.blogTag = blogTag;
    }

    public BlogPost(){};

    public long getBlogId() {
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public HashSet<String> getBlogTag() {
        return blogTag;
    }

    public void setBlogTag(HashSet<String> blogTag) {
        this.blogTag = blogTag;
    }

    ;
}
