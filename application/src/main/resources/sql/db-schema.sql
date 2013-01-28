--
-- Stores the available jobs
--
create table jobs (
  jobId int primary key auto_increment,
  jobTitle varchar(30)
);

--
-- insert some default jobs
--
insert into jobs (jobTitle) values ('HR');
insert into jobs (jobTitle) values ('Operations');
insert into jobs (jobTitle) values('Developer');
insert into jobs (jobTitle) values('Tester');

--
-- The Employee table
-- index FK_JOBTITLEID (jobId),
create table employees (
  id int primary key auto_increment,
  firstName varchar(30),
  secondName varchar(30),
  jobId int,
  deskId varchar(30),

  constraint FK_JOBTITLEID foreign key (jobId) references jobs (jobId)
);