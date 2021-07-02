insert into user (name,create_time) values
('test1',now()),
('test2',now()),
('test3',now());
update user set name='test001' where name='test1';
insert into book (name,create_time) values ('book1',now());