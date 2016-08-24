#DB
#================
drop table if exists departments;
create table departments(id int not null auto_increment primary key,
dept_name varchar(25));
drop table if exists employees;
create table  employees(id int not null auto_increment primary key,
last_name varchar(25),
email varchar(50),
dept_id int);

insert into departments values(null,'财务部');
insert into departments values(null,'开发部');
insert into departments values(null,'人事部');
insert into departments values(null,'公关部');

insert into employees values(null,'Tom','tom@163.com',1);
insert into employees values(null,'Jerry','Jerry@163.com',2);
insert into employees values(null,'Nike','Nike@163.com',3);
insert into employees values(null,'Rose','Rose@163.com',3);
insert into employees values(null,'ATGUIGU','ATGUIGU@163.com',2);

#============tx test================
drop table if exists book;
create table book(
isbn varchar(50) primary key,
book_name varchar(100),
price int);

drop table if exists book_stock;
create table book_stock(
isbn varchar(50) primary key,
stock int,
check(stock>0));

drop table if exists account;
create table account(
username varchar(50) primary key,
balance int,
check(balance>0));

insert into book values('1001','Java',100);
insert into book values('1002','Oracle',70);
insert into book_stock values('1001',0);
insert into book_stock values('1002',8);
insert into account values('AA',100);

insert into account values('Tom',60);
