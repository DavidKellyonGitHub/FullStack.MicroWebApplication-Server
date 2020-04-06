package com.dmvs.blog.projBlog.Services;

import com.dmvs.blog.projBlog.Models.BlogPost;
import com.dmvs.blog.projBlog.Repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BlogPostService {
    private BlogPostRepository blogRepo;

    @Autowired
    public BlogPostService(BlogPostRepository repository) {
        this.blogRepo = repository;
    }

    public BlogPost findById(Long id) {
        return blogRepo.findById(id).get();
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

    public BlogPost savePost(String tag) {
        return blogRepo.save(new BlogPost());
    }

    public BlogPost update(Long id) {
        return blogRepo.save(new BlogPost());
    }

    public Boolean delete(Long id) {
        blogRepo.delete(blogRepo.findById(id).get());
        return true;
    }

    public Boolean deleteAll(Long id) {
        blogRepo.deleteAll();
        return true;
    }
}