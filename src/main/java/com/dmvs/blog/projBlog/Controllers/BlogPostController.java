package com.dmvs.blog.projBlog.Controllers;

import com.dmvs.blog.projBlog.Services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BlogPostController {
    BlogPostService blogPostService;

    @Autowired
    public BlogPostController(BlogPostService blogPostService){
        this.blogPostService = blogPostService;
    }

    @GetMapping("/BlogPost/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return new ResponseEntity<>(blogPostService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/BlogPost/{tag}")
    public ResponseEntity<?> findbyTag(@PathVariable String tag){
        return new ResponseEntity<>(blogPostService.findByTag(tag),HttpStatus.OK);
    }

    @GetMapping("/BlogPost/all")
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(blogPostService.findAll(),HttpStatus.OK);
    }

    @PostMapping("/BlogPost/add/{tag}")
    public ResponseEntity<?> savePost(@PathVariable String tag){
        return new ResponseEntity<>(blogPostService.savePost(tag), HttpStatus.OK);
    }

    @PutMapping("/BlogPost/add/{id}")
    public ResponseEntity<?> update(@PathVariable Long id){
        return new ResponseEntity<>(blogPostService.update(id), HttpStatus.OK);
    }

    @DeleteMapping("/BlogPost/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return new ResponseEntity<>(blogPostService.delete(id),HttpStatus.OK);
    }

    @DeleteMapping("/BlogPost/deleteAll")
    public ResponseEntity<?> deleteAll(Long id){
        return new ResponseEntity<>(blogPostService.delete(id),HttpStatus.OK);
    }



}