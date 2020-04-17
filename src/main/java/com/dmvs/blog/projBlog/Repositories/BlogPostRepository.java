package com.dmvs.blog.projBlog.Repositories;

import com.dmvs.blog.projBlog.Models.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

    List<BlogPost> findAllByDateCreatedAfter(LocalDate date);

    List<BlogPost> findAllByTag(String tag);
}
