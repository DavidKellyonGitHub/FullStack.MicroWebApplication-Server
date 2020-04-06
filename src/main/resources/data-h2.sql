Insert into blogs (blog_id, blog_date, blog_title, blog_content, blog_tag, blog_status, blog_comment_count)
Values (103 ,date '2015-04-09' + time '13:45:03', 'SCENE III. A room in the palace.',
        'ROSALIND and more text', 'Scene 3', 'posted',2);

Insert into comments (comment_ID, comment_blog_ID, comment_author, comment_author_email, comment_date, comment_content, comment_like_count)
Values (1001 ,102,'Rosalind', 'rosalid@gmail.com',
        date '2015-04-09' + time '13:45:03', 'I admire your writing sir.', 2);

Insert into likes (like_type, like_comment_ID)
Values ('LIKE', 1001);