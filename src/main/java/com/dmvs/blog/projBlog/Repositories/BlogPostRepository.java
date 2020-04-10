package com.dmvs.blog.projBlog.Repositories;

import com.dmvs.blog.projBlog.Models.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

    @Query(value = "SELECT * FROM BlogPost WHERE createdDate = ?1", nativeQuery = true)
    List<BlogPost> findByDate(LocalDate date);

    @Query(value = "SELECT * FROM BlogPost WHERE tag = ?1", nativeQuery = true)
    List<BlogPost> findByTag(String tag);
}
