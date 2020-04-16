package com.dmvs.blog.projBlog.Repositories;

import com.dmvs.blog.projBlog.Models.BlogPost;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "noteit.db.recreate", havingValue = "true")
public class DbSeeder implements CommandLineRunner {
    private BlogPostRepository blogPostRepository;
    private CommentRepository commentRepository;

    public DbSeeder(BlogPostRepository blogPostRepository,
                    CommentRepository commentRepository) {
        this.blogPostRepository = blogPostRepository;
        this.commentRepository = commentRepository;
    }


    @Override
    public void run(String... args) {
        // Remove all existing entities
        this.blogPostRepository.deleteAll();
        this.commentRepository.deleteAll();


        // Save a default notebook
        BlogPost defaultNotebook = new Notebook("Default");
        this.blogPostRepository.save(defaultNotebook);

        var quotesNotebook = new Notebook("Quotes");
        this.blogPostRepository.save(quotesNotebook);

        // Save the welcome note
        var note = new Note("Hello", "Welcome to Note It", defaultNotebook);
        this.commentRepository.save(note);

        // Save a quote note
        var quoteNote = new Note("Latin Quote", "Carpe Diem", quotesNotebook);
        this.commentRepository.save(quoteNote);

        System.out.println("Initialized database");
    }
}
