package com.dmvs.blog.projBlog.Repositories;

import com.dmvs.blog.projBlog.Models.BlogPost;
import com.dmvs.blog.projBlog.Models.Comment;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@ConditionalOnProperty(name = "h2.db.recreate", havingValue = "true")
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


        // Save a default Blog Post
        BlogPost defaultNotebook = new BlogPost(LocalDate.of(2020,4, 15),
                "Trump says US has ‘passed the peak’ of coronavirus outbreak!",
                "“While we must remain vigilant, it is clear that our aggressive strategy is working,” " +
                        "Trump said at a White House news briefing with coronavirus task force on Wednesday. " +
                        "“The battle continues, but the data suggests that nationwide we have passed the peak on new cases.”",
                        "Trump", "posted");
        this.blogPostRepository.save(defaultNotebook);

        BlogPost quotesNotebook = new BlogPost(LocalDate.of(2020,4, 15),
                "Some stimulus checks are being sent to the wrong accounts: ''The bank account number is not even close.''",
                "Around 80 million people were to receive the deposit this week, the Treasury Department said on Monday." +
                        " But Wednesday, many people expressed concern and worry when the government website said the cash, " +
                        "up to $1,200 per person, was sent to a bank account that didn''t seem to belong to them.",
                        "Money", "posted");
        this.blogPostRepository.save(quotesNotebook);

        // Save Comment to Blog Post #1
        Comment comment1 = new Comment(LocalDate.of(2020,4, 15), "Rosalind", "rosalind@gmail.com",
                "I admire your writting sir.", 1, 2L);
        this.commentRepository.save(comment1);

        // Save a quote note
        Comment comment2 = new Comment(LocalDate.of(2020,4, 15), "Horatio", "horatio@gmail.com",
                "This has been great effort.", 10, 2L);
        this.commentRepository.save(comment2);

    }
}
