package com.dmvs.blog.projBlog.Controllers;

import com.dmvs.blog.projBlog.Models.BlogPost;
import com.dmvs.blog.projBlog.Services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/blogPost")
public class BlogPostController {

    private BlogPostService blogPostService;

    @Autowired
    public BlogPostController(BlogPostService blogPostService){
        this.blogPostService = blogPostService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long blogId) {
        return this.blogPostService.findById(blogId)
                .map(blogPost -> ResponseEntity.ok().body(blogPost))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{tag}")
    public ResponseEntity<List<BlogPost>> findByTag(@PathVariable String tag){
        return new ResponseEntity<>(blogPostService.findByTag(tag), HttpStatus.OK);
    }

    @GetMapping("/{date}")
    public ResponseEntity<List<BlogPost>> findByDate(@PathVariable LocalDate localDate){
        return new ResponseEntity<>(blogPostService.findByDate(localDate), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BlogPost>> findAll(){
        return new ResponseEntity<>(blogPostService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> savePost(@RequestBody BlogPost blogPost){
        BlogPost newBlogPost = blogPostService.savePost(blogPost);

        try {
            return ResponseEntity
                    .created(new URI("/blogPost/" + newBlogPost.getBlogId()))
                    .body(newBlogPost);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long blogId, @RequestBody BlogPost blogPost){
        Optional<BlogPost> existingBlogPost = blogPostService.findById(blogId);

        return existingBlogPost
                .map(c -> {
                    c.setBody(blogPost.getBody());
                    c.setTag(blogPost.getTag());
                    c.setTitle(blogPost.getTitle());
                    try{
                        return ResponseEntity
                                .ok()
                                .location(new URI("/comment/" + c.getBlogId()))
                                .body(c);
                    }catch(URISyntaxException e){
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long blogId){
        Optional<BlogPost> existingBlogPost = blogPostService.findById(blogId);

        return existingBlogPost
                .map(c -> {
                    blogPostService.deletePost(c.getBlogId());
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Boolean> deleteAll(){
        return new ResponseEntity<>(blogPostService.deleteAll(), HttpStatus.OK);
    }
}