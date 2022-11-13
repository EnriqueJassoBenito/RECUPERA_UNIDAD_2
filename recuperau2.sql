drop database if exists recuperau2;
create database recuperau2;
use recuperau2;

create table teachers(
 id bigint primary key auto_increment,
 name varchar(40) not null,
 surname varchar(40) not null,
 lastname varchar(40) not null,
 birthday date not null,
 curp varchar(100) not null,
 employee_number int not null
);

create table students(
id bigint primary key auto_increment,
 name varchar(40) not null,
 surname varchar(40) not null,
 lastname varchar(40) not null,
 birthday date not null,
 curp varchar(100) not null,
 enrollment varchar(50) not null
);

create table qualifications(
id bigint primary key auto_increment,
subjects varchar(50) not null,
qualification double not null,
id_student bigint not null,
foreign key (id_student) references students(id)
);


select * from teachers;
select * from students;
select * from qualifications;

DELETE FROM teachers WHERE id=2;
DELETE FROM students WHERE id=5;

SELECT s.name, q.qualification , q.subjects FROM qualifications q INNER JOIN students s ON q.id_student = q.id;

SELECT AVG(qualification) as Promedio_General FROM qualifications;