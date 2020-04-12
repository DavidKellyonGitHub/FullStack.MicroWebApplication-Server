Insert into blog_post (blog_id, date_created, title, body, tag, status)
Values (1 ,date '2015-04-09' + time '13:00:00', 'SCENE III. A room in the palace.',
        'ROSALIND and more text', 'Scene 3', 'posted');

Insert into comment (comment_id, date_created, author, author_email, text, likes, blog_id)
Values (1 , date '2015-04-09' + time '13:45:03', 'Rosalind', 'rosalid@gmail.com',
        'I admire your writing sir.', 1, 1);
Insert into comment (comment_id, date_created, author, author_email, text, likes, blog_id)
Values (2 , date '2015-04-09' + time '13:50:00', 'Horatio', 'horatio@gmail.com',
        'This has been great effort.', 100, 1);
Insert into comment (comment_id, date_created, author, author_email, text, likes, blog_id)
Values (3 , date '2015-04-09' + time '13:55:00', 'Von', 'von@gmail.com',
        'One of the best blogs..', 10, 1);