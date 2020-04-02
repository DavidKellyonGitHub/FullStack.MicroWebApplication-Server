package Services;

import Models.BlogPost;
import Repositories.BlogPostRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;

@Service
public class BlogPostService {
    BlogPostRepository blogRepo;


    public BlogPost findById(int Id){
        return null;
    }

    public BlogPost findByDate(LocalDate date) {
        return null;
    }

        public BlogPost findbyTag(String tag){
        return null;
    }


    public BlogPost findAll(){
        return null;
    }


    public Boolean savePost(String tag){
        return null;
    }


    public Boolean update(Integer postId){
        return null;
    }

    public Boolean delete(Integer postId){
        return null;
    }

    public Boolean deleteAll(Integer postId){
        return null;
    }
}
