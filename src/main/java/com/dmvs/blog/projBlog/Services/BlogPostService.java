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

    public List<BlogPost> findByTag(String tag) {
        return blogRepo.findAllByTag(tag);
    }

    public List<BlogPost> findAllByDateCreateAfter(LocalDate date) {
        return blogRepo.findAllByDateCreatedAfter(date);
    }

    public List<BlogPost> findAll(){
        return blogRepo.findAll();
    }

    public BlogPost savePost(BlogPost blogPost) {
        return blogRepo.save(blogPost);
    }

    public Optional<BlogPost> updatePost(Long blogId, BlogPost newBlogPost){
        Optional<BlogPost> currentBlogPost = findById(blogId);
        if(currentBlogPost.isPresent()){
            currentBlogPost.get().setTitle(newBlogPost.getTitle());
            currentBlogPost.get().setBody(newBlogPost.getBody());
            currentBlogPost.get().setTag(newBlogPost.getTag());
            currentBlogPost.get().setStatus(newBlogPost.getStatus());
            blogRepo.save(currentBlogPost.get());
        }
        return currentBlogPost;
    }

    public Boolean deletePost(Long blogId) {
        if(findById(blogId).isPresent()) {
            blogRepo.deleteById(blogId);
            return true;
        }
        return false;
    }

    public Boolean deleteAll() {
        blogRepo.deleteAll();
        return true;
    }
}