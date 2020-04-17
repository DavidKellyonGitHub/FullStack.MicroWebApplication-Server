Insert into blog_post (blog_id, date_created, username, title, body, tag, status)
Values (1 ,date '2020-04-15' + time '13:00:00', 'Von', 'Trump says US has ‘passed the peak’ of coronavirus outbreak!',
'“While we must remain vigilant, it is clear that our aggressive strategy is working,” Trump said at a White House news briefing with coronavirus task force on Wednesday. “The battle continues, but the data suggests that nationwide we have passed the peak on new cases.”',
'Trump', 'posted');
Insert into blog_post (blog_id, date_created, username, title, body, tag, status)
Values (2 ,date '2020-04-16' + time '13:00:00', 'Von', 'Some stimulus checks are being sent to the wrong accounts: ''The bank account number is not even close.''',
'Around 80 million people were to receive the deposit this week, the Treasury Department said on Monday. But Wednesday, many people expressed concern and worry when the government website said the cash, up to $1,200 per person, was sent to a bank account that didn''t seem to belong to them.',
'Money', 'posted');

Insert into comment (comment_id, date_created, username, text, likes, blog_id)
Values (1 , date '2015-04-09' + time '13:45:03', 'Rosalind',
        'I admire your writing sir.', 1, 1);
Insert into comment (comment_id, date_created, username, text, likes, blog_id)
Values (2 , date '2015-04-09' + time '13:50:00', 'Horatio',
        'This has been great effort.', 100, 1);
Insert into comment (comment_id, date_created, username, text, likes, blog_id)
Values (3 , date '2015-04-09' + time '13:55:00', 'Moe',
        'One of the best blogs..', 10, 1);
Insert into comment (comment_id, date_created, username, text, likes, blog_id)
Values (4 , date '2015-04-09' + time '13:55:00', 'Moe',
        'One of the best blogs..', 11, 2);
