package Services;

import Models.BlogPost;
import Repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BlogPostService {
    BlogPostRepository blogRepo;

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