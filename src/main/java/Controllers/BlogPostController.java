package Controllers;

import Models.BlogPost;
import Services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogPostController {

    @Autowired
    BlogPostService blogPostService;

    @ResponseBody
    public ResponseEntity<BlogPost> findById(int Id){
        return null;
    }

    @ResponseBody
    public ResponseEntity<BlogPost> findbyTag(String tag){
        return null;
    }

    @ResponseBody
    public ResponseEntity<BlogPost> findAll(){
        return null;
    }

    @ResponseBody
    public ResponseEntity<String> savePost(String tag){
        return null;
    }

    @ResponseBody
    public ResponseEntity<String> update(Integer postId){
        return null;
    }

    @ResponseBody
    public ResponseEntity<String> delete(Integer postId){
        return null;
    }

    @ResponseBody
    public ResponseEntity<String> deleteAll(Integer postId){
        return null;
    }



}
