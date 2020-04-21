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
@RequestMapping("/zcwApp/blogPost")
@CrossOrigin
public class BlogPostController {

    private BlogPostService blogPostService;

    @Autowired
    public BlogPostController(BlogPostService blogPostService){
        this.blogPostService = blogPostService;
    }

    @GetMapping("/{blogId}")
    public ResponseEntity<?> findById(@PathVariable Long blogId) {
        return blogPostService.findById(blogId)
                .map(blogPost -> ResponseEntity
                        .ok()
                        .body(blogPost))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/allByUser/{username}")
    public ResponseEntity<List<BlogPost>> findAllByUser(@PathVariable String username){
        return new ResponseEntity<>(blogPostService.findAllByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/allByTag/")
    public ResponseEntity<List<BlogPost>> findByTag(@RequestParam String tag){
        return new ResponseEntity<>(blogPostService.findByTag(tag), HttpStatus.OK);
    }

    @GetMapping("/allByDate/")
    public ResponseEntity<List<BlogPost>> findByDate(@RequestParam String year,@RequestParam String month,@RequestParam String day){
        LocalDate localDate = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
        return new ResponseEntity<>(blogPostService.findAllByDateCreateAfter(localDate), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BlogPost>> findAll(){
        return new ResponseEntity<>(blogPostService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> savePost(@RequestBody BlogPost blogPost){
        BlogPost newBlogPost = blogPostService.savePost(blogPost);

        try {
            return ResponseEntity
                    .created(new URI("/zcwApp/blogPost/" + newBlogPost.getBlogId()))
                    .body(newBlogPost);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update/{blogId}")
    public ResponseEntity<?> updatePost(@PathVariable Long blogId, @RequestBody BlogPost newBlogPost){
        Optional<BlogPost> updatedBlogPost = blogPostService.updatePost(blogId, newBlogPost);

        return updatedBlogPost
                .map(blogPost -> {
                    try{
                        return ResponseEntity
                                .ok()
                                .location(new URI("/zcwApp/blogPost/" + blogPost.getBlogId()))
                                .body(blogPost);
                    }catch(URISyntaxException e){
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{blogId}")
    public ResponseEntity<Boolean> delete(@PathVariable Long blogId){
        if(blogPostService.deletePost(blogId))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Boolean> deleteAll(){
        return new ResponseEntity<>(blogPostService.deleteAll(), HttpStatus.OK);
    }
}