CREATE TABLE IF NOT EXISTS blogs (

                                     blog_ID SERIAL primary key NOT NULL,
                                     blog_date timestamp NOT NULL,
                                     blog_title varchar(50) NOT NULL,
                                     blog_content varchar(2000) NOT NULL,
                                     blog_tag varchar(20) NOT NULL,
                                     blog_status varchar(20) NOT NULL DEFAULT 'draft',
                                     blog_comment_count numeric(200) NOT NULL DEFAULT '0'
);

CREATE TABLE IF NOT EXISTS comments (

                                        comment_ID SERIAL primary key NOT NULL,
                                        comment_blog_ID numeric NOT NULL,
                                        comment_author varchar(20) NOT NULL,
                                        comment_author_email varchar(30) NOT NULL,
                                        comment_date timestamp NOT NULL,
                                        comment_content varchar(200) NOT NULL,
                                        comment_like_count numeric(20) NOT NULL

);

CREATE TABLE IF NOT EXISTS likes (
                                     like_type varchar(10) NOT NULL,
                                     like_comment_ID numeric(20) NOT NULL
);