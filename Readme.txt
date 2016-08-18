DB
================
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

