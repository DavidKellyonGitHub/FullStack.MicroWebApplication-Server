package Services;

import Models.BlogPost;
import Repositories.BlogPostRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BlogPostService implements BlogPostRepository{
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

    //nothing of interest beyond this point






































    @Override
    public <S extends BlogPost> S save(S s) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends BlogPost> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<BlogPost> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public BlogPost getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends BlogPost> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends BlogPost> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends BlogPost> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends BlogPost> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends BlogPost> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends BlogPost> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public Optional<BlogPost> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<BlogPost> findAll() {
        return null;
    }

    @Override
    public List<BlogPost> findAll(Sort sort) {
        return null;
    }

    @Override
    public List<BlogPost> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public Page<BlogPost> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends BlogPost> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(BlogPost blogPost) {

    }

    @Override
    public void deleteAll(Iterable<? extends BlogPost> iterable) {

    }

    @Override
    public void deleteAll() {

    }


}
