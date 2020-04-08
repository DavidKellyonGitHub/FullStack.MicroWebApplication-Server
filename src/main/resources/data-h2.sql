Insert into blog_post (blog_id, date_created, title, body, tag, status)
Values (103 ,date '2015-04-09' + time '13:45:03', 'SCENE III. A room in the palace.',
        'ROSALIND and more text', 'Scene 3', 'posted');

Insert into comment (comment_id, date_created, username, user_email, text, likes, blog_id)
Values (1001 , date '2015-04-09' + time '13:45:03', 'Rosalind', 'rosalid@gmail.com',
        'I admire your writing sir.', 1, 103);
Insert into comment (comment_id, date_created, username, user_email, text, likes, blog_id)
Values (1002 , date '2015-04-09' + time '13:45:03', 'Rosalind', 'rosalid@gmail.com',
        'I admire your writing sir.', 100, 103);
Insert into comment (comment_id, date_created, username, user_email, text, likes, blog_id)
Values (1003 , date '2015-04-09' + time '13:45:03', 'Rosalind', 'rosalid@gmail.com',
        'I admire your writing sir.', 10, 103);