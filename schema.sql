create database library;

create table user(id int auto_increment not null,name varchar(30),contact varchar(15),type varchar(30),email varchar(30),primary key(id));

create table book(id int auto_increment not null,title varchar(30),author varchar(30),category varchar(30),added date,primary key(id));

create  table damage(id int auto_increment primary key,book_id int,damagedate timestamp,user_id int,foreign key(user_id) references user(id),foreign key(book_id) references book(id));



create table bookissue
(
id int auto_increment primary key,
user_id int,
book_id int,
book_title varchar(30),
dateissue timestamp,
duration int,
foreign key(user_id) references user(id),
foreign key(book_id) references book(id)
);

create table fine_table (id int auto_increment primary key,user_id int,book_id int,extraday int,finedue int,finepaid int,foreign key(user_id) references user(id),foreign key(book_id) references book(id));



INSERT INTO user
VALUES
  (100,'Dheeraj Sharma','9643233421','Student','dheeraj@gmail.com');
  

INSERT INTO user(name,contact,type,email)
VALUES
  ('Pankaj Verma','9643233420','Student','pankaj@gmail.com'),
  ('Saurabh Verma','9643233422','Student','saurabh@gmail.com'),
  ('Shashank Chandrakar','9643233423','Student','shashank@gmail.com'), 
  ('Shubham Gupta','9643233424','Student','shubbham@gmail.com'),
  ('Nitin Janardhan','9643233425','Teacher','nitin@gmail.com'),
  ('Kush Verma','9643233426','Teacher','kush@gmail.com'),
  ('Harshit Singh','9643233427','Teacher','harshit@gmail.com'),
  ('Shubham Singh','9643233428','Teacher','shubhamsingh@gmail.com'),
  ('Deepak Singh','9643233429','Teacher','deepak@gmail.com');



INSERT INTO book
VALUES
 (400,'c++ program','jerry','Technical','2018-06-13');


ALTER TABLE book
ADD COLUMN status VARCHAR(15);


UPDATE book 
SET 
    satus = 'present';





INSERT INTO book(title,author,category,added,status)
VALUES
('java program','virat','Technical','2018-06-13','present'),
('c program','sehwag','Technical','2018-06-13','present'),
('c# program','rohit','Technical','2018-06-13','present'),
('python program','shikhar','Technical','2018-06-13','present'),
('english grammer','dhoni','basic','2018-06-13','present'),
('hindi grammer','zaheer','basic','2018-06-13','present'),
('english short story','vinayak','basic','2018-06-13','present'),
('hindi shortstory','utkarsh','basic','2018-06-13','present'),
('english poem','rajat','basic','2018-06-13','present'),
('hindi poem','arpit','basic','2018-06-13','present'),
('java program','virat','Technical','2018-06-13','present'),
('c program','sehwag','Technical','2018-06-13','present'),
('c# program','rohit','Technical','2018-06-13','present'),
('python program','shikhar','Technical','2018-06-13','present'),
('computer','prashat','intermediate','2018-06-13','present'),
('datastructure','Asif','intermediate','2018-06-13','present'),
('physical','Akash','basic','2018-06-13','present'),
('HTML','Randy','Technical','2018-06-13','present'),
('CSS','roman','Technical','2018-06-13','present');



ALTER TABLE damage AUTO_INCREMENT=1;
ALTER TABLE bookissue AUTO_INCREMENT=1;
ALTER TABLE fine_table AUTO_INCREMENT=1;























