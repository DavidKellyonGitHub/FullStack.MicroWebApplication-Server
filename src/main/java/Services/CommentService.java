package Services;

import Models.Comment;
import Repositories.CommentRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService implements CommentRepository {
    CommentRepository commentRepo;


    public Comment findById(Integer commentId) {
        return null;
    }

    public List<Comment> findAll(Integer blogId) {
        return null;
    }

    public Boolean saveComment(Comment comment) {
        return null;
    }

    public Boolean updateComment(Comment comment) {
        return null;
    }

    public Boolean deleteComment(Comment comment) {
        return null;
    }

    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public List<Comment> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Comment> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Comment> findAllById(Iterable<Long> iterable) {
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
    public void delete(Comment comment) {

    }

    @Override
    public void deleteAll(Iterable<? extends Comment> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Comment> S save(S s) {
        return null;
    }

    @Override
    public <S extends Comment> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Comment> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Comment> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Comment> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Comment getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends Comment> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Comment> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Comment> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Comment> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Comment> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Comment> boolean exists(Example<S> example) {
        return false;
    }
}
