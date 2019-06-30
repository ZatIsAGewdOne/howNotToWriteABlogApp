insert into blog.users(username, password, role, account_non_expired, account_non_locked, credentials_non_expired, enabled) values ('user', '$2a$12$/zcvtohDL.h9pJj8bQfgqefyd/YfsGfRXXd0IpP6Bh7U1eSAVA6iO', 'USER', true, true, true, true);
insert into blog.users(username, password, role, account_non_expired, account_non_locked, credentials_non_expired, enabled) values ('admin', '$2a$12$JCLrU6R.bTJTZxAF.1QdGO8utAf2BqQSfP3b.x1SA6gFRjV0bJNVi', 'ADMIN', true, true, true, true);
insert into blogs(user_id, blog_date, blog_text) values (1, date ('2018-01-01'), 'Some text');
insert into blogs(user_id, blog_date, blog_text) values (2, date ('2018-05-01'), 'More text here');
