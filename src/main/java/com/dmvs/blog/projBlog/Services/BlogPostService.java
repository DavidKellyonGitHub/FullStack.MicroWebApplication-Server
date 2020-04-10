package com.dmvs.blog.projBlog.Services;

import com.dmvs.blog.projBlog.Models.BlogPost;
import com.dmvs.blog.projBlog.Repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BlogPostService {

    private BlogPostRepository blogRepo;

    @Autowired
    public BlogPostService(BlogPostRepository repository) {
        this.blogRepo = repository;
    }

    public Optional<BlogPost> findById(Long blogId) {
        return blogRepo.findById(blogId);
    }

    public List<BlogPost> findAll(){
        return blogRepo.findAll();
    }

    public List<BlogPost> findByDate(LocalDate date) {
        return blogRepo.findByDate(date);
    }

    public List<BlogPost> findByTag(String tag) {
        return blogRepo.findByTag(tag);
    }

    public BlogPost savePost(BlogPost blogPost) {
        return blogRepo.save(blogPost);
    }

    public Boolean deletePost(Long blogId) {
        blogRepo.deleteById(blogId);
        return true;
    }

    public Boolean deleteAll() {
        blogRepo.deleteAll();
        return true;
    }
}